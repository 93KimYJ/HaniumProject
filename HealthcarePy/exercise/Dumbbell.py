import cv2
import mediapipe as mp
import numpy as np
import requests
import json
import ApiMapping as ApiMapping

mp_drawing = mp.solutions.drawing_utils
mp_pose = mp.solutions.pose

def calculate_angle(a,b,c):
    a = np.array(a) # First
    b = np.array(b) # Mid
    c = np.array(c) # End
    
    radians = np.arctan2(c[1]-b[1], c[0]-b[0]) - np.arctan2(a[1]-b[1], a[0]-b[0])
    angle = np.abs(radians*180.0/np.pi)
    
    if angle >180.0:
        angle = 360-angle
        
    return angle 


# Curl counter variables

## Setup mediapipe instance
def start(uid):
    cap = cv2.VideoCapture(0)
    counter = 0 
    stage = None
    with mp_pose.Pose(min_detection_confidence=0.5, min_tracking_confidence=0.5) as pose:
        while cap.isOpened():
            ret, frame = cap.read()
            
            # 좌우반전
            frame = cv2.flip(frame, 1)

            # Recolor image to RGB
            image = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            image.flags.writeable = False
        
            # Make detection
            results = pose.process(image)
        
            # Recolor back to BGR
            image.flags.writeable = True
            image = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)
            
            # Extract landmarks
            try:
                landmarks = results.pose_landmarks.landmark
                
                # Get coordinates
                shoulder = [landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].x,landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].y]
                elbow = [landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].x,landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].y]
                wrist = [landmarks[mp_pose.PoseLandmark.LEFT_WRIST.value].x,landmarks[mp_pose.PoseLandmark.LEFT_WRIST.value].y]
                
                # Calculate angle (화면에 보이는 각도를 사용한다. 실제 각도를 찾으려면 어려운 수학적 계산이 필요할것)
                # 각도 계산보단 랜드마크간의 상대적 거리를 계산하는게 더 나을듯함
                # 얼굴 크기나 어깨 너비와 같은 랜드마크를 통해 상대적 위치값을 산출하고 그를 통해 조건을 만족하기 위해 필요한 손의 위치값을 설정하는게 어떨까?
                # 옆으로 향했을땐 각도만으로 잘 작동한다. 그러니 정면의 경우만 고려해서 계산식을 산출하면 된다.
                #
                # 1. 어깨 너비를 통해 기준값을 설정. 해당 값을 사용해 팔 위치값 판정
                #   1.1. 어깨 너비와 팔의 길이를 비교한 후 적당한 값으로 기준값 설정
                # 2. 손의 높이가 팔꿈치보다 '기준값' 이상으로 높아지면 카운터 작동(또는 어깨 포인트와 손 포인트 거리로 계산)
                # 
                #  30도 이하임과 동시에 팔 위치 상대값 계산하면 될듯함
                #
                # 감지 판단도 더 개선해야함

                #각도 계산하는 함수. 화면에 비치는 손 - 팔꿈치 - 어깨 각도를 계산함
                angle = calculate_angle(shoulder, elbow, wrist)
                
                # Visualize angle
                cv2.putText(image, str(angle), 
                            tuple(np.multiply(elbow, [640, 480]).astype(int)), 
                            cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 255), 2, cv2.LINE_AA
                                    )
                
                # 포인트 감지, 0.29 - 0.3 이상이면 감지로 판단
                rightHandVisibility = landmarks[mp_pose.PoseLandmark.RIGHT_INDEX].visibility
                rightElbowVisibility = landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW].visibility


                # 계산시작
                # 1. 기준값 생성: 양 어깨 너비 계산
                rightShoulder = landmarks[mp_pose.PoseLandmark.RIGHT_SHOULDER.value].x
                leftShoulder = landmarks[mp_pose.PoseLandmark.LEFT_SHOULDER.value].x

                distanceVal = leftShoulder - rightShoulder
                #print(distanceVal)
                
                # 2. 더해질 값 생성. 팔꿈치 + 해당 값보다 높이 올라가야 스코어 올라감. 위쪽일수록 위치값이 낮다... 반대로 계산해야 함
                addHight = distanceVal  * 0.8

                # 조건 만족 계산
                isReachedTargetHeight = False
                isReachedTargetDown = False

                rightHand = landmarks[mp_pose.PoseLandmark.LEFT_INDEX.value].y
                rightElbow = landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW.value].y

                # up 판정조건
                if rightHand < rightElbow - addHight:
                    isReachedTargetHeight = True

                # down 판정조건
                if rightHand > rightElbow + distanceVal: # up보다 약간 길어야 적당한듯함... 원근법을 고려해 파라미터 조정 필요함
                    isReachedTargetDown = True

                print(isReachedTargetHeight, " : ", f"Hand:{rightHand:.3} : Elbow:{rightElbow:.3} + {addHight:.3}")

                ##

                # 팔의 구성요소(?) 카메라에 비칠때만 계산하기(좀 애매함)
                isHandAndElbowVisible = rightHandVisibility > 0.3 and rightElbowVisibility > 0.3
                #print(isHandAndElbowVisible, " : ", f"Hand:{rightHandVisibility:.5} : Elbow:{rightElbowVisibility:.5}")

                # Curl counter logic
                if not isHandAndElbowVisible:
                    stage = "notVisible"
                elif angle > 160 and isReachedTargetDown:
                    stage = "down"
                elif angle < 30 and stage =='down' and isReachedTargetHeight:
                    stage="up"
                    counter +=1
                    print(counter)
                        
            except:
                pass

            #print(f"L {leftHand:.2f} : {rightHand:.2f} R")

            


            
            # Render curl counter
            # Setup status box
            cv2.rectangle(image, (0,0), (225,73), (245,117,16), -1)
            
            # Rep data
            cv2.putText(image, 'REPS', (15,12), 
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0,0,0), 1, cv2.LINE_AA)
            cv2.putText(image, str(counter), 
                        (10,60), 
                        cv2.FONT_HERSHEY_SIMPLEX, 1, (255,255,255), 2, cv2.LINE_AA)
            
            # Stage data
            cv2.putText(image, 'STAGE', (65,12), 
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (0,0,0), 1, cv2.LINE_AA)
            cv2.putText(image, stage, 
                        (60,60), 
                        cv2.FONT_HERSHEY_SIMPLEX, 1, (255,255,255), 2, cv2.LINE_AA)
            
            
            # Render detections
            mp_drawing.draw_landmarks(image, results.pose_landmarks, mp_pose.POSE_CONNECTIONS,
                                    mp_drawing.DrawingSpec(color=(245,117,66), thickness=1, circle_radius=2), 
                                    mp_drawing.DrawingSpec(color=(245,66,230), thickness=1, circle_radius=2) 
                                    )               
            
            # cv2.flip(image, 1)
            cv2.imshow('Mediapipe Feed', image)

            leftHand = landmarks[mp_pose.PoseLandmark.LEFT_INDEX.value].x
            leftHandHight = landmarks[mp_pose.PoseLandmark.LEFT_INDEX.value].y
            rightHand = landmarks[mp_pose.PoseLandmark.RIGHT_INDEX.value].x
            rightHandHight = landmarks[mp_pose.PoseLandmark.RIGHT_INDEX.value].y

            leftElbow = landmarks[mp_pose.PoseLandmark.LEFT_ELBOW.value].y
            rightElbow = landmarks[mp_pose.PoseLandmark.RIGHT_ELBOW.value].y

            isMakeX = leftHand < rightHand and leftHandHight < rightElbow and rightHandHight < leftElbow 

            # 동작으로 종료
            if cv2.waitKey(10) & 0xFF == ord('q') or isMakeX:
                user_data = {
                    'userId': uid,
                    'type' : 'dumbbel',
                    'cnt': counter,
                }

                url = ApiMapping.mappingData.get("dumbbellEnd")
                headers = {'Content-Type': 'application/json'}
                response = requests.post(url, data=json.dumps(user_data), headers=headers)

                if response.status_code == 200:
                    # 요청 성공
                    print('데이터 전송 성공')
                else:
                    # 요청 실패
                    print('데이터 전송 실패')
                break


        cap.release()
        cv2.destroyAllWindows()

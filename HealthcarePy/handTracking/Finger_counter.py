import cv2
import mediapipe as mp
import time
import requests
import json
import ApiMapping as ApiMapping

# For webcam input:
def startFingerCount():
    mp_drawing = mp.solutions.drawing_utils
    mp_drawing_styles = mp.solutions.drawing_styles
    mp_hands = mp.solutions.hands

    pCnt = 0

    reValue = 0
    timeCountSwitch = 0
    killSwitch = 0

    beforeCnt = 0
    startTime = time.time()

    cap = cv2.VideoCapture(0)
    with mp_hands.Hands(
            model_complexity=0,
            min_detection_confidence=0.5,
            min_tracking_confidence=0.5) as hands:
        while cap.isOpened():
            success, image = cap.read()
            if not success:
                print("Ignoring empty camera frame.")
                # If loading a video, use 'break' instead of 'continue'.
                continue

            # To improve performance, optionally mark the image as not writeable to
            # pass by reference.
            image.flags.writeable = False
            image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
            results = hands.process(image)

            # Draw the hand annotations on the image.
            image.flags.writeable = True
            image = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)

            # Initially set finger count to 0 for each cap
            fingerCount = 0

            if results.multi_hand_landmarks:

                for hand_landmarks in results.multi_hand_landmarks:
                    # Get hand index to check label (left or right)
                    handIndex = results.multi_hand_landmarks.index(hand_landmarks)
                    handLabel = results.multi_handedness[handIndex].classification[0].label

                    # Set variable to keep landmarks positions (x and y)
                    handLandmarks = []

                    # Fill list with x and y positions of each landmark
                    for landmarks in hand_landmarks.landmark:
                        handLandmarks.append([landmarks.x, landmarks.y])

                    # Test conditions for each finger: Count is increased if finger is
                    #   considered raised.
                    # Thumb: TIP x position must be greater or lower than IP x position,
                    #   deppeding on hand label.
                    # 각 손가락 포인트값의 상대 위치로 펼친 손가락 개수 반환
                    #
                    # 배열의 앞자리는 손관절 번호, 뒷자리는 0일경우 가로, 1일경우 세로

                    # 엄지손가락 감지 : 부정확도 큼, 초기값 [4][0], [3][0]

                    # if handLabel == "Left" and handLandmarks[4][0] > handLandmarks[3][0]:
                    #   #print(handLandmarks[4][0],":",handLandmarks[3][0])
                    #   fingerCount = fingerCount+1
                    # elif handLabel == "Right" and handLandmarks[4][0] < handLandmarks[3][0]:
                    #   fingerCount = fingerCount+1

                    # Other fingers: TIP y position must be lower than PIP y position,
                    #   as image origin is in the upper left corner.
                    if handLandmarks[8][1] < handLandmarks[6][1]:  # Index finger
                        fingerCount = fingerCount+1
                    if handLandmarks[12][1] < handLandmarks[10][1]:  # Middle finger
                        fingerCount = fingerCount+1
                    if handLandmarks[16][1] < handLandmarks[14][1]:  # Ring finger
                        fingerCount = fingerCount+1
                    if handLandmarks[20][1] < handLandmarks[18][1]:  # Pinky
                        fingerCount = fingerCount+1

                    # Draw hand landmarks
                    mp_drawing.draw_landmarks(
                        image,
                        hand_landmarks,
                        mp_hands.HAND_CONNECTIONS,
                        mp_drawing_styles.get_default_hand_landmarks_style(),
                        mp_drawing_styles.get_default_hand_connections_style())

            # n초 이상이면 작동하게 하는 로직
            #
            # 
            if (timeCountSwitch == 0 and fingerCount != beforeCnt):
                print(fingerCount)
                beforeCnt = fingerCount
                timeCountSwitch = 1
                startTime = time.time()

            if (fingerCount != beforeCnt):
                timeCountSwitch = 0
                startTime = time.time()

            endTime = time.time()

            timeCnt = endTime - startTime

            #print(f"{timeCnt:.5f} sec, ",beforeCnt,":",fingerCount,"   sw:",timeCountSwitch)
            
            
            # 중단
            if (timeCnt > 1.5 and fingerCount in [1, 2]):

                

                fingerData = {
                    'cnt': fingerCount,
                }

                url = ApiMapping.mappingData.get("fingerCnt")
                headers = {'Content-Type': 'application/json'}
                response = requests.post(
                    url, data=json.dumps(fingerData), headers=headers)

                if response.status_code == 200:
                    # 요청 성공
                    print('데이터 전송 성공')
                    #choice = response.json() # 컨트롤러 완성시 사용

                    if fingerCount == 1:
                        reValue = 1
                    elif fingerCount == 2:
                        reValue = 2

                else:
                    # 요청 실패
                    print('데이터 전송 실패')
                killSwitch = 1

            # Display finger count
            #(left+, top+)
            cv2.putText(image, str(fingerCount), (50, 450),
                        cv2.FONT_HERSHEY_SIMPLEX, 3, (255, 255, 0), 3)
            
            cv2.putText(image, "spread your fingers to select", (50, 320),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 0), 1)
            
            cv2.putText(image, "1: dumbbell, 2: menu", (50, 340),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255, 255, 0), 1)

            # Display image
            cv2.imshow('MediaPipe Hands', image)
            if cv2.waitKey(5) & 0xFF == 27:
                break

            if (killSwitch == 1):

                break

    cap.release()
    cv2.destroyAllWindows()
    return reValue
    

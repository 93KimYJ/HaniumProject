import requests
import json

response = requests.get('http://localhost:8080/pyAppMapping?')
if response.status_code == 200:
    data = response.json()
    print(data)
    # JSON 데이터 사용
else:
    print('서버 상태 불량')

'''
user_data = {
    'name': 'John',
    'age': 30,
}

url = 'http://localhost:8080/pyApiTest?'
headers = {'Content-Type': 'application/json'}
response = requests.post(url, data=json.dumps(user_data), headers=headers)

if response.status_code == 200:
    # 요청 성공
    print('데이터 전송 성공')
else:
    # 요청 실패
    print('데이터 전송 실패')


'''
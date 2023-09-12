import requests
import json

response = requests.get('http://localhost:8088/pyAppMapping?')

if response.status_code == 200:
    mappingData = response.json()
    print(mappingData)
    # JSON 데이터 사용
else:
    print('서버 상태 불량')
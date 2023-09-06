import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from PyIndex import WindowClass2
import requests
import json
import ApiMapping

# UI파일 연결
form_class = uic.loadUiType("login.ui")[0]

# 화면을 띄우는데 사용되는 Class 선언
class WindowClass(QDialog, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

        # 시그널
        self.loginBtn.clicked.connect(self.executeLogin)

    # 행동
    def executeLogin(self):

        id = self.idInput.text()
        password = self.passwordInput.text()

        loginData = {
            'userId' : id,
            'password' : password
        }

        url = ApiMapping.mappingData.get("login")
        headers = {'Content-Type': 'application/json'}
        response = requests.post(url, data=json.dumps(loginData), headers=headers)

        if response.status_code == 200:
            # 요청 성공
            print('데이터 전송 성공')


            result = response.json()
            print(result)
            if(result.get('userId') != None):
                print("로그인 성공")
                self.hide()
                self.mainPage = WindowClass2(id)
                self.mainPage.exec()
                self.show()
            else:
                print("로그인 실패")
        else:
            # 요청 실패
            print('데이터 전송 실패')


        

    

if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass()
    myWindow.show()
    app.exec_()
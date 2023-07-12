import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from exercise import Dumbbell
from exercise import PushUp




# UI파일 연결
form_class = uic.loadUiType("untitled.ui")[0]

# 화면을 띄우는데 사용되는 Class 선언
class WindowClass2(QDialog, form_class) :
    def __init__(self) :
        super().__init__()
        self.setupUi(self)

		# 위젯 시그널 입력
        ''' ex)
        self.Qt_Designer에서_정한_objectName값.clicked.connect(self.작동시킬함수)
        self.Qt_Designer에서_정한_objectName값2.clicked.connect(self.작동시킬함수2)
        .
        .
        .
        '''
        self.startDumbbelBtn.clicked.connect(self.startDumbbelFuc)
        self.pushUpBtn.clicked.connect(self.startPushUpFuc)

	# 작동시킬 함수들 작성
    ''' ex)
    def 작동시킬함수(self):
    	print("함수작동")
        self.출력할위젯objectName값.setText(str(1))
     
    def 작동시킬함수2(self):
    	print("2함수작동")
        self.출력할위젯objectName값.setText(str(2))
    .
    .
    .
    '''
    def startDumbbelFuc(self):
        Dumbbell.start()

    def startPushUpFuc(self):
        PushUp.start()
    
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    myWindow = WindowClass2() 
    myWindow.show()
    app.exec_()
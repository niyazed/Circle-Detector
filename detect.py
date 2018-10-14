import cv2
import numpy as np
import sys


try:
    filename = sys.argv[1]
except IndexError:
    print("Nnoo")

#load image

img = cv2.imread(filename,0)

#threshold setup
cimg = cv2.cvtColor(img,cv2.COLOR_GRAY2BGR)
ret,th1 = cv2.threshold(cimg,55,255,cv2.THRESH_BINARY_INV)

#circle detection
circles = cv2.HoughCircles(img,cv2.HOUGH_GRADIENT,1,100,param1=100,param2=45,minRadius=20,maxRadius=70)
circles = np.uint16(np.around(circles))
for i in circles[0,:]:
    cv2.circle(th1,(i[0],i[1]),i[2],(0,255,115),2)  # draw the circle
    
#image print
#cv2.imshow('detected circles',th1)

cv2.imwrite("output.png", th1)  #image write
cv2.waitKey(5000)          
cv2.destroyAllWindows()     
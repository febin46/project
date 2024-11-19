import cv2
# Load the face recognizer and the face cascade
recognizer =cv2.face.LBPHFaceRecognizer_create()
recognizer.read('trainer/trainer.yml')
face_cascade=cv2.CascadeClassifier('D:\\CODES\\facerec\\trainer\\haarcascade_frontalface_default.xml')

# Initialize and start the video frame capture
cam = cv2.VideoCapture(0)
font = cv2.FONT_HERSHEY_SIMPLEX

while True:
    ret, frame = cam.read()
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    faces = face_cascade.detectMultiScale(gray, 1.2, 5)

    for (x, y, w, h) in faces:
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
        id, confidence = recognizer.predict(gray[y:y+h, x:x+w])

        # Check if confidence is less than 100 ==> "0" is perfect match
        if confidence < 100:
            id = "Person"
            confidence = "  {0}%".format(round(100 - confidence))
        else:
            id = "unknown"
            confidence = "  {0}%".format(round(100 - confidence))

        cv2.putText(frame, str(id), (x+5, y-5), font, 1, (255, 255, 255), 2)
        cv2.putText(frame, str(confidence), (x+5, y+h-5), font, 1, (255, 255, 0), 1)

    cv2.imshow('camera', frame)

    # Press 'x' to exit the video window
    if cv2.waitKey(10) & 0xFF == ord('x'):
        break

# Cleanup
cam.release()
cv2.destroyAllWindows()


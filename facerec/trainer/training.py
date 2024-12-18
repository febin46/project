import cv2
import numpy as np
from PIL import Image
import os

# Path for face image database
path = r'D:\\CODES\\facerec\\trainer\dataset'

recognizer = cv2.face.LBPHFaceRecognizer_create()
detector = cv2.CascadeClassifier(r'D:\\CODES\\facerec\\trainer\\haarcascade_frontalface_default.xml')

# Function to get the images and label data
def getImagesAndLabels(path):
    imagePaths = [os.path.join(path, f) for f in os.listdir(path) if f.endswith('.jpg') or f.endswith('.png')  or f.endswith('.jpeg')]
    faceSamples = []
    ids = []
    for imagePath in imagePaths:
        PIL_img = Image.open(imagePath).convert('L')  # convert it to grayscale
        img_numpy = np.array(PIL_img, 'uint8')
        id = int(os.path.split(imagePath)[-1].split(".")[1])
        faces = detector.detectMultiScale(img_numpy)
        for (x, y, w, h) in faces:
            faceSamples.append(img_numpy[y:y+h, x:x+w])
            ids.append(id)
    return faceSamples, ids

print("\n [INFO] Training faces. It will take a few seconds. Wait ...")
faces, ids = getImagesAndLabels(path)
recognizer.train(faces, np.array(ids))

# Save the model into trainer/trainer.yml
if not os.path.exists('trainer'):
    os.makedirs('trainer')
recognizer.write('trainer/trainer.yml')

# Print the number of faces trained and end program
print(f"\n [INFO] {len(np.unique(ids))} faces trained. Exiting Program")

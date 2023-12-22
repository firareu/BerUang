# BerUang
> Manage Your Money With Us!

<p align="center">
  <img src="https://github.com/firareu/BerUang/blob/MDFiraTL/BerUang.png" width="400"/>
</p>
  
"BerUang" is a financial management application developed by our team to tackle the prevalent issue of online lending and poor financial decision-making. This app addresses the challenges by offering a monthly expense tracking feature that analyzes individual budgets, providing spending recommendations. Users receive notifications if they exceed predefined financial category budgets. Moreover, the app includes an article section that leverages machine learning to offer personalized financial guidance based on user behavior, enhancing financial planning capabilities. With these features, BerUang aims to empower users with better financial management practices and insightful guidance for improved monetary decision-making.

### BerUang TEAM | CH2-PS111

| Name | Role | Email
|---------|---------|---------|
| Ni Luh Ade Meina Rossalina | Machine Learning | m014bsx1813@bangkit.academy | 
| Muhammad Akhsan Nurramdhan | Machine Learning | m267bsy1861@bangkit.academy |
| Mutiq Anisa Tanjung | Machine Learning | m296bsx0487@bangkit.academy |
| Lintang Arsa Naura | Cloud Computing | c227bsx3811@bangkit.academy |
| Ferrari Romano | Cloud Computing | c227bsy4033@bangkit.academy |
| Putri Dian Shafira | Mobile Development | a296bsx2745@bangkit.academy |
| Abdurrahman Al Aufa | Mobile Development | a283bsy2381@bangkit.academy |

### BerUang TEAM | Branch

| Role | Branch
|---------|---------|
| Machine Learning | ML | 
| Cloud Computing | api-artikel , backend-API |
| Mobile Development | BerUang-App |

## Machine Learning
### Overview
We create a machine learning that detect a lot of kinds of endangered species in Indonesia, and also categorized non-endanged species in one category.

The goal of this model is to recognize and give information about what kinds of endangered species are there in Indonesia, and which one is categorized as non-endangered as well.

<details>
  <summary>Output Example of Our Application</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Scan_after.png" width="500"/>
</details>

### Dataset and Data Preparation
The dataset we use for this model is a combined dataset of our own collective dataset, [Indonesian Endangered Species 1](https://www.kaggle.com/datasets/nadyanurfadhila/indonesian-endangered-animal), and [Indonesian Endangered Species 2](https://www.kaggle.com/datasets/nadyanurfadhila/indonesia-endangered-animal2). You can download the dataset [here](https://drive.google.com/uc?export=download&id=1C2ML8iLXsRkCa-wz-grqHjMMsJzLJAf3). This dataset contains 5107 images of 58 kinds of endangered species in Indonesia, and one category for non-endangered species in Indonesia.

We split the dataset using [split-folders](https://pypi.org/project/split-folders/) library in Python into three directories Training, Validation, Testing with Ratio (0.8, 0.1, 0.1). Then, we use image augmentation to rescale the dataset by 1/255 and resize it to 224x224, we think this number is not too big and not too small to affect loss and the performance of our CNN.

<details>
  <summary>Example of Dataset with Each Labels</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Dataset%20Overview.png" width="500"/>
</details>

### Transfer Learning
Our model in this application is using transfer learning from [Xception](https://keras.io/api/applications/xception/). We did some modification and adjustable to make the best model possible.

<details>
  <summary>Model Summary</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Model%20Summary.png" width="500"/>
</details>

<details>
  <summary>Model Flowchart</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Model%20Flowchart.png" width="500"/>
</details>

### Model
<details>
  <summary>Model Accuracy</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Model%20Accuracy.png" width="500"/>
</details>

<details>
  <summary>Model Loss</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Model%20Loss.png" width="500"/>
</details>

<details>
  <summary>Testing Example</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Machine%20Learning/Media/Testing%20Example.png" width="500"/>
</details>

## Cloud Computing
<h1 align="center">This is backend-API for Beruang</h1>

### Requirements
- Text Editor (Visual Studio Code)
- Git
- Google Cloud Account
- Google Cloud SDK

#### 1. Setup Google Cloud Platfrom
Enable the following API : 
- App Engine API
- Cloud Firestore API

#### 2. Install Google Cloud SDK
#### 3. Setup Firebase
- Login to Firebase, go to console and connect it to Google Cloud Project.
- Enable Firebase Authentication and Firestore Database.
- Make a service account and download the file. (Make sure to integrate the serviceAccount.json with the code later)

#### 4. Clone Project and set Google Cloud Account
- Use 'git clone https://github.com/firareu/BerUang.git -b test-api' to copy the project.
- In terminal use "git init" and connect your google cloud account

#### 5. Deploy the project on App Engine
- Deploy the project to App Engine using 'gcloud app deploy'

#### 6. API Documentation
You can check API Documentation in the following link. 
[Postman API Beruang](https://dark-space-932472.postman.co/workspace/New-Team-Workspace~e1a57e86-16c4-4e9b-9f2e-8f205b6f8d22/collection/30258106-5688daea-5cdf-4924-aa11-5effcc6db047?action=share&creator=30258106)

<h1 align="center">This is Flask API Article for Beruang</h1>
This work is part of using article scraping techniques on news websites

This API has endpoints to make it work for the mobile development, it's integrated with Cloud Storage, The API is deployed on the Google Cloud Run.

## Need
* Python
* Docker
* Google Cloud Platform Account
* Google Cloud Platform - Cloud Build API


# Run locally
```
$ git clone -b api-article https://github.com/firareu/BerUang.git
$ pip install -r requirements. txt
$ python main.py
```

## How to deploy to cloud-run using cloud SDK
```
$ gcloud init
$ gcloud services enable run.googleapis.com
$ gcloud builds submit --tag gcr.io/[project-id-kalian]/beruang-api-article
$ gcloud run deploy --image gcr.io/[project-id-kalian]/bear-fire-article --platform managed --region asia-southeast2 --allow-unauthenticated bear-fire-article
```

## How to deploy to cloud-run using Google Cloud Platform

1. Make sure you have an active Google Cloud Platform (GCP) account. If not, register and create a new project at https://console.cloud.google.com.

2. Make sure you have Google Cloud SDK installed (https://cloud.google.com/sdk) and initialize it by running the following command in the terminal or command prompt:
    ``` gcloud init ```
 
3. Create a repository on a code management service such as GitHub or GitLab, and make sure it contains all the files required for your Flask application, including the Dockerfile, requirements.txt, and your Flask application code.

4. Open a terminal or command prompt, then navigate to the directory where you want to clone the Flask repository.

5. Clone the Flask repository by running the following command:
    ` git clone -b api-article https://github.com/firareu/BerUang.git`
 
  6. Once the cloning process is complete, direct the terminal or command prompt to the Flask directory you just cloned.
 
  7. Build a local Docker container by running the following command:
    ` docker build -t gcr.io/[PROJECT_ID]/bear-api-articles . `
     Replace [PROJECT_ID] with the Google Cloud Platform project ID that you specified previously.
    
  8. After the build process is complete, verify that the local Docker container is running by running the following command:
     ` docker run -p 8080:8080 gcr.io/[PROJECT_ID]/bear-api-article `
     Make sure there are no errors and the Flask application is running fine on localhost.

9. If the previous step was successful, stop and delete the running Docker container by pressing Ctrl+C in the terminal or command prompt.

10. To publish the Docker container to Google Cloud Container Registry, run the following command:
     ` docker push gcr.io/[PROJECT_ID]/bear-fire-articles `
     The container will be uploaded to the Container Registry in the appropriate Google Cloud Platform project.
    
11. Next, create a Cloud Run service by running the following command on asia-southeast2(jakarta):
     ` gcloud run deploy --image gcr.io/[PROJECT_ID]/fire-bear-articles --platform managed --region asia-southeast2 --allow-unauthenticated fire-bears-articles`
 
12. GCP will ask you to select a region to deploy the Cloud Run service. Select the region that suits your needs.

13. After the deployment process is complete, GCP will provide a URL that can be used to access the deployed Flask application. Copy the URL from the output and try accessing it in a web browser or by using API testing software such as Postman.


## How to deploy to cloud-run using Google Cloud Platform
You can check API Documentation in the following link.
[Postman API Bear Article](https://documenter.getpostman.com/view/14512676/2s9YkraKg1)

## Mobile Development

### Overview

We build android applications for end users so that everyone can use our applications. The following is the application flow based on the Mockup.
<details>
  <summary>Application Flowchart</summary>
  <img src="https://github.com/firareu/BerUang/blob/MDFiraTL/md-mockup/BerUangFlow.jpg"/>
</details>

And this is the libraries we used to build the android application:
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://bumptech.github.io/glide/)
* [RecyclerView](https://www.geeksforgeeks.org/android-recyclerview/)
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)

Our application is not yet perfect where the application can only do the following things:
* Display articles
* Provide article recommendations
* Filter articles by category
* Displays income data
* Displays outcome data
* Displays historical outcome data
* And all the MVP UI of the application has been created

Here is our application's Screenshot
<p float="left">
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Splash%20Screen.png" width="24%" />
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Login.png" width="24%" /> 
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Register.png" width="24%" />
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Home.png" width="24%" />
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/List%20Tumbuhan.png" width="24%" />
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/List%20Hewan.png" width="24%" />
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Scan%20before.png" width="24%" />
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Scan%20after.png" width="24%" />
</p>


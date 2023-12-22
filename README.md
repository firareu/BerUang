<h1 align="center">This is Flask API Article for Beruang</h1>


This work is part of **Beruang Capstone Project**

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
    ` docker build -t gcr.io/[PROJECT_ID]/beruang-api-article . `
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






<p align="left">
</p>

<h3 align="left">Languages and Tools:</h3>
<p align="left"> <a href="https://flask.palletsprojects.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/pocoo_flask/pocoo_flask-icon.svg" alt="flask" width="40" height="40"/> </a> </a> <a href="https://postman.com" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" alt="postman" width="40" height="40"/> </a> <a href="https://www.python.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/python/python-original.svg" alt="python" width="40" height="40"/> </a> </p>

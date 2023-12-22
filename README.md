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

### Overview

We create our private API for login/register, list animals & plants, and then we deploy it in VM on Google Cloud Platform.

### PRIVATE API (LOGIN/REGISTER, LIST ANIMALS AND PLANTS)
we create the api use node js with express js. 
for the source code, check this [link](https://github.com/risavarrahman/futon-capstone).

### SERVER
for the server, we use virtual machine (VM) in Google Cloud Platform to deploy our server. We use only VM because it easy to deploy and for us, it not hard to  for maintenance.

<details>
<summary>Virtual Machine (VM)</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Cloud%20Computing/media/Screenshot%202022-06-12%20225402.png" width="500"/>
</details>

## Mobile Development

### Overview

We build an android application for end user so everyone can use our application. This is our [flowchart](https://drive.google.com/file/d/1IoWHeYlkkhACOg2P4lvqJZR6DIS1DcWK/view?usp=sharing) to explain how our app is work
<details>
  <summary>Application Flowchart</summary>
  <img src="https://github.com/nadikarim/FUTON/blob/main/Mobile%20Development/Media/Flowchart%20FUTON.jpg"/>
</details>

And this is the libraries we used to build the android application:
* [Retrofit](https://square.github.io/retrofit/)
* [Tensorflow Lite](https://www.tensorflow.org/lite)
* [Dagger-Hilt](https://dagger.dev/hilt/)
* [Tasty Toasty](https://github.com/usman18/TastyToasty)
* [Glide](https://bumptech.github.io/glide/)


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


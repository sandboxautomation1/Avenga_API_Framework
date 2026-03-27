![This is an image](https://images.teamtailor-cdn.com/images/s3/teamtailor-production/gallery_picture-v6/image_uploads/27de8d15-0ed0-43a9-bb65-5c316ed8775f/original.png)

---

## Table Of Contents

* [Tech Stack](#tech-stack)
* [Architecture](#architecture)
  * [Project Structure](#project-structure)
  * [Project Folders](#-project-folders)
* [Getting Started](#getting-started)
* [Reporting](#reporting)

---

## Tech Stack

| Technology     | Purpose                         |
|----------------|---------------------------------|
| Java           | Core programming language       |
| Selenium       | UI automation                   |
| Rest Assured   | API testing                     |
| TestNG         | Test orchestration              |
| Maven          | Build and dependency management |
| Extent Reports | Rich HTML reporting             |
| Docker         | Manage containers               |

---

## Architecture

### Project Structure

```
📁 src/
├── 📁 main/
│   └── 📁 java/
│       └── 📁 org.avenga/
│           ├── 📁 client/
│           │   └── BaseClient.java
│           ├── 📁 config/
│           │   └── ConfigManager.java
│           ├── 📁 data/
│           │   ├── Endpoints.java
│           │   └── FrameworkConstants.java
│           ├── 📁 models/
│           │   ├── 📁 request/
│           │   │   ├── AuthorRequest.java
│           │   │   └── BookRequest.java
│           │   └── 📁 response/
│           │       ├── AuthorResponse.java
│           │       └── BookResponse.java
│           ├── 📁 reporting/
│           │   ├── ExtentListener.java
│           │   ├── ExtentManager.java
│           │   ├── ExtentTestManager.java
│           │   └── WriterOutputStream.java
│           ├── 📁 services/
│           │   ├── Authors.java
│           │   ├── Books.java
│           │   └── Users.java
│           └── 📁 utils/
│               ├── DataUtils.java
│               ├── FileUtils.java
│               └── JsonUtils.java
└──📁 tests/
    └── 📁 java/
        ├── 📁 base/
        │    └── JsonUtils.java
        ├── 📁 tests/
        │    ├── TS_001_Books.java
        │    └── TS_002_Authors.java
        └── 📁 resources/
             └── testng.xml
```

### 📂 Project Folders

1. <code>src/main/java/org.avenga/</code> - Core Framework Layer
<br>

This layer contains the main logic of the framework.
<br>
   - <code>client/</code> Contains class responsible for request and response specifications
   - <code>config/</code> Contains class that handles configuration management.
   - <code>data/</code> Classes which contains API endpoints and framework constants are here. 
   - <code>models/</code> Represents request and response data structures.
   - <code>reporting</code> Manages test reporting via Extent Reports.
   - <code>services/</code> Acts as the API interaction layer.
   - <code>utils/</code> Contains helper and utility classes.

<br><br>
   
2. <code>src/test/java/org.avenga/</code> - Test Layer
   - <code>base/</code> Base test class used for setup and teardown.
   - <code>tests</code> Contains TestNG test classes.
<br>

---

## Getting Started

**Step 1:** Clone the repository:

```bash
git clone https://github.com/sandboxautomation1/Avenga_API_Framework.git
```

**Step 2:** Run all tests:

```bash
   mvn clean test
```

---

## Reporting
<br>

HTML file with test results are automatically generated under <code>/test-output</code>.
After opening the file, the dashboard will be displayed. On the left side you will see buttons for Dashboard, Categories and Tests List.
#### Dashboard
![](https://private-user-images.githubusercontent.com/270893907/570441202-338adfb7-d8e6-46a8-ac6d-20a43c62684a.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NzQ2MjAwOTQsIm5iZiI6MTc3NDYxOTc5NCwicGF0aCI6Ii8yNzA4OTM5MDcvNTcwNDQxMjAyLTMzOGFkZmI3LWQ4ZTYtNDZhOC1hYzZkLTIwYTQzYzYyNjg0YS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjYwMzI3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI2MDMyN1QxMzU2MzRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zNWNiZDJkOTYyMDgzMTk0ZTA3Yjk5YmVlMWY5ZTQzYmUxMTk5YzllOWM3ZGMwNjc5YmY1NmNlNGNmNTU4YzY1JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.2-OKN5MnqDBaPLx6i0oVnj9kTnhC3dq2BXMHxx6XO7g)
<br>

#### Categories
![](https://private-user-images.githubusercontent.com/270893907/570441201-2188aa4c-3512-446c-9395-4914f979e66d.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NzQ2MjAwOTQsIm5iZiI6MTc3NDYxOTc5NCwicGF0aCI6Ii8yNzA4OTM5MDcvNTcwNDQxMjAxLTIxODhhYTRjLTM1MTItNDQ2Yy05Mzk1LTQ5MTRmOTc5ZTY2ZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjYwMzI3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI2MDMyN1QxMzU2MzRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT03ODY1MDU4NWJjNzgwMzAxMWQzZDY2NDg2MDY3OWJmNmI4YTk4ZTcyY2M3MDhmYmIyYzE4OTk0OTVhMmVkY2I5JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.QECSt6IMt_LoPAHxVLH6X09aA50LO_j7xc6zgMoBL1I)

#### Tests List

![](https://private-user-images.githubusercontent.com/270893907/570441199-3f092f93-eaca-433f-8d66-2a51aad9dd17.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NzQ2MjAzNzIsIm5iZiI6MTc3NDYyMDA3MiwicGF0aCI6Ii8yNzA4OTM5MDcvNTcwNDQxMTk5LTNmMDkyZjkzLWVhY2EtNDMzZi04ZDY2LTJhNTFhYWQ5ZGQxNy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjYwMzI3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI2MDMyN1QxNDAxMTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1kZDU2NDM4OTRmZWU1NTdmZWZlZDYxOTBhNmEzMTVjZDAyNmI2Mjg2MDVkOTY4ZWRmY2QxNDQ3NmFkMzNkMTNkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.TpWhkaw5wpEw_Mehgvcyv5TPLvOD1QFuqa5mYqN3Eaw)

<br> 

<b>Request Details</b>
![](https://private-user-images.githubusercontent.com/270893907/570441203-17fec21f-c5b1-4c66-b7c0-6aa29762e9d2.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NzQ2MjAzNzIsIm5iZiI6MTc3NDYyMDA3MiwicGF0aCI6Ii8yNzA4OTM5MDcvNTcwNDQxMjAzLTE3ZmVjMjFmLWM1YjEtNGM2Ni1iN2MwLTZhYTI5NzYyZTlkMi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjYwMzI3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI2MDMyN1QxNDAxMTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05NjJkZjk2MGJlZDlkZDIyYzFjNTc5ZjQyNGY3MDhiZDk5Y2ZiNGNjYTkwMDlmNzMxOWFhZDY2YmVmOTE3MTMzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.5MaGiToEbcb0lrakN054_h-xiRcpOR3QjFAssUgri9c)

<b>Response Details</b>
![](https://private-user-images.githubusercontent.com/270893907/570441200-fce92afd-ebc3-46af-b061-329fc6794e5f.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NzQ2MjAzNzIsIm5iZiI6MTc3NDYyMDA3MiwicGF0aCI6Ii8yNzA4OTM5MDcvNTcwNDQxMjAwLWZjZTkyYWZkLWViYzMtNDZhZi1iMDYxLTMyOWZjNjc5NGU1Zi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjYwMzI3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI2MDMyN1QxNDAxMTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT02ODZiNTg0MjhkNWQxNGJlMzYyNGFlNGE0YmY5MDFjN2ZlN2EyMWFmMmIxMDg1MzdhNzEwODk0OTVlY2E0MGVjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.nSH9lXJlWNcP_jUtMjf8t9YtBC97xKAAQKWUR5SG8-4)



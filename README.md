# 공지사항(게시글) 관리 REST API 구현

<!--배지-->
<!-- ![MIT License][license-shield] ![Repository Size][repository-size-shield] ![Issue Closed][issue-closed-shield] -->

<!--프로젝트 대문 이미지-->
<!-- ![Project Title](img/project-title.png) -->

<!--프로젝트 버튼-->
<!-- [![Readme in English][readme-eng-shield]][readme-eng-url] [![View Demo][view-demo-shield]][view-demo-url] [![Report bug][report-bug-shield]][report-bug-url] [![Request feature][request-feature-shield]][request-feature-url] -->

<!--목차-->
<!-- 
# Table of Contents
- [[1] About the Project](#1-about-the-project)
  - [Features](#features)
  - [🛠️ Tech Stack 🛠️](#🛠️ Tech Stack 🛠️)
- [[2] Getting Started](#2-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [[3] Usage](#3-usage)
- [[4] Contact](#4-contact)
-->

# About the Project
공지사항 등록, 수정, 삭제, 조회 API 구현

## :boom: Strategy :boom:
API/NOTICE
- GET/NOTICE : 등록된 전체 게시글 조회
- POST/NOTICE : 게시글 등록
- GET/NOTICE/{ID} : 특정 게시글 조회
- PUT/NOTICE/{ID} : 특정 게시글 수정
- DELETE/NOTICE/{ID} : 특정 게시글 삭제

API/ATTACH
- GET/ATTACH/{ID} : ATTACH ID로 첨부파일 다운로드 가능(바탕화면에 TESTCHO 폴더가 생성됩니다.)

RETURN
- EXCEPTION, RESPONSE, ERRCODE 등의 클래스를 만들어 에러 발생 시에 일정한 구조로 응답을 받을 수 있도록 하였습니다.

## 🛠️ Tech Stack 🛠️
<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/><img src="https://img.shields.io/badge/3.2.8-6DB33F?style=for-the-badge&logo=3.2.8&logoColor=white"/>
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)<img src="https://img.shields.io/badge/17-%23ED8B00.svg?style=for-the-badge&logo=17&logoColor=white"/>
<img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white" /><img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge&logo=JPA&logoColor=white" /> 
<img src="https://img.shields.io/badge/DB-FFCA28?style=for-the-badge&logo=H2&logoColor=black"/><img src="https://img.shields.io/badge/H2-FFCA28?style=for-the-badge&logo=H2&logoColor=black"/>
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

# Getting Started
## Prerequisites
- H2 DATA BASE 설치(All Platforms) (ID : sa, PWD: 1234)
```bash
https://www.h2database.com/html/main.html
```

## Installation
- Repository 클론
```bash
git clone https://github.com/eunchae01/RSUPPORT.git
```
- 포스트맨 사용 시
```bash
BODY / FORMDATA
KEY : NOTICE, VALUE : {"title" : "test5",
                    "writer" : "writer5",
                    "text" : "test5",
                    "startDate" : "2024-08-04",
                    "endDate" : "2024-08-05"}
KEY : FILE, VALUE : 첨부파일 선택
```

# Results
- get/notice 실행 결과
<img width="307" alt="image" src="https://github.com/user-attachments/assets/327bd3c7-414a-4bdb-9d9b-fd389cb3cf64">

존재하지 않는 게시물인 경우

<img width="326" alt="image" src="https://github.com/user-attachments/assets/313cc3c0-50a4-42b4-bd12-fb2b2f7adb3f">


- put/notice/2 실행 결과 
<img width="336" alt="image" src="https://github.com/user-attachments/assets/ef9b4802-2ecf-4661-bc92-dcf5c84563a9">
<img width="308" alt="image" src="https://github.com/user-attachments/assets/98ab7859-bca7-4b7b-b0cc-28e5d8943a9f">  


- delete/notice/3 실행 결과
<img width="267" alt="image" src="https://github.com/user-attachments/assets/b60e77b5-136c-42c6-91a4-635627b81be1">
<img width="318" alt="image" src="https://github.com/user-attachments/assets/e7beb2ef-a58b-4b2f-a0ad-1d5138908cf7">

이미 삭제 된 게시물인 경우 

<img width="299" alt="image" src="https://github.com/user-attachments/assets/b8bc003f-2c63-4fba-a61f-11a06031cad1">



# :mailbox_with_mail: Contact
- 📧 eunchae01@naver.com



<!--Url for Badges-->
[license-shield]: https://img.shields.io/github/license/dev-ujin/readme-template?labelColor=D8D8D8&color=04B4AE
[repository-size-shield]: https://img.shields.io/github/repo-size/dev-ujin/readme-template?labelColor=D8D8D8&color=BE81F7
[issue-closed-shield]: https://img.shields.io/github/issues-closed/dev-ujin/readme-template?labelColor=D8D8D8&color=FE9A2E

<!--Url for Buttons-->
[readme-eng-shield]: https://img.shields.io/badge/-readme%20in%20english-2E2E2E?style=for-the-badge
[view-demo-shield]: https://img.shields.io/badge/-%F0%9F%98%8E%20view%20demo-F3F781?style=for-the-badge
[view-demo-url]: https://dev-ujin.github.io
[report-bug-shield]: https://img.shields.io/badge/-%F0%9F%90%9E%20report%20bug-F5A9A9?style=for-the-badge
[report-bug-url]: https://github.com/dev-ujin/readme-template/issues
[request-feature-shield]: https://img.shields.io/badge/-%E2%9C%A8%20request%20feature-A9D0F5?style=for-the-badge
[request-feature-url]: https://github.com/dev-ujin/readme-template/issues

<!--URLS-->
[license-url]: LICENSE.md
[contribution-url]: CONTRIBUTION.md
[readme-eng-url]: ../README.md



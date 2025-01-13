# SOPT-all-35-APPJAM-ANDROID-ACON
acon은 “No more research”라는 슬로건을 가진 지도앱입니다.

## 🍨 *****Contributors*****

| 공세영(Lead) <br> [@0se0](https://github.com/0se0) | 이상일 <br> [@Thirfir](https://github.com/ThirFir) | 김성민 <br> [@1971123-seongmin](https://github.com/1971123-seongmin) | 양지원 <br> [@tunaunnie](https://github.com/tunaunnie) |
|:---:|:---:|:---:|:---:|
| <img width="150" src="https://github.com/user-attachments/assets/781d97ec-3348-4ae6-8b51-f9e997e9de20"/> | <img width="150" src="https://github.com/user-attachments/assets/59cbbadc-59b0-4667-ac05-2b5836701492"/> | <img width="150" src="https://github.com/user-attachments/assets/13453d7c-fb38-4ae0-ba40-dc2fa7bbbac3"/> | <img width="150" src="https://github.com/user-attachments/assets/4a9f6470-ea8f-48ab-82df-40e2cb2773b7"/> |
|  동네인증, 업로드 | 장소탐색 | 로그인, (장소탐색 일부) | 온보딩 (장소탐색 일부) |

<br/>

## 🟨 *****SCREENSHOT*****
| 로그인 |            동네인증              |              온보딩               |              장소탐색               |              업로드               |
|:---:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------:|
| <img width="200" src=""/> | <img width="200" src=""/> | <img width="200" src=""/> | <img width="200" src=""/> | <img width="200" src=""/> |

<br/>

## 📗 *****Convention*****
[ 💻 Github Convention](https://stripe-shoemaker-907.notion.site/Github-Convention-d5c57b9b06d744bb99d503d335fc8e5d?pvs=4) <br>
[ 🚸 Naming Convention](https://stripe-shoemaker-907.notion.site/Naming-Convention-f929fcaf44684e8598df9f50e95cb192?pvs=4) <br>
[ 📛 Coding Detail Convention](https://stripe-shoemaker-907.notion.site/Coding-Detail-Convention-43f146988f114a5bb34c2e850e0af90f?pvs=4) <br>
[ 🏠 Composable Convention](https://stripe-shoemaker-907.notion.site/Composable-Convention-df0dff2511764ef8a4670bd9b865b684?pvs=4) <br>
[ 🪐 Orbit Architecture](https://stripe-shoemaker-907.notion.site/Orbit-Architecture-0c3f47033ef242be9bf9ece745f064cd?pvs=4) <br>
[ 🍎 Issue Convention & PR Convention](https://stripe-shoemaker-907.notion.site/Orbit-Architecture-0c3f47033ef242be9bf9ece745f064cd?pvs=4) <br>

## 🔧 *****TECH STACKS*****

| **Category**           | **TechStack**                               | **선정 이유**                                                                 |
|------------------------|---------------------------------------------|-------------------------------------------------------------------------------|
| **Architecture**        | SAA(Single Activity Architecture), Clean Architecture, MVI(orbit) | 각 모듈의 역할을 명확히 분리하여 코드의 가독성과 확장성을 높이고, MVI 패턴을 통해 UI 상태를 예측 가능하게 관리하고 orbit을 활용해 더 나은 상태관리  |
| **Language**            | Kotlin                                      | Android 개발에 보편적으로 사용하는 언어인 Kotlin을 사용합니다.   |
| **Dependency Injection**| Hilt                                        | Hilt는 의존성 주입을 간소화하고 코드 간 결합도를 줄여, 더 나은 테스트와 유지보수를 가능하게 합니다. |
| **Network**             | Retrofit, OkHttp                           | Retrofit은 네트워크 통신을 간단히 처리하고, OkHttp는 세부 설정 및 로깅을 제공하여, 네트워크 통신의 안정성과 성능을 개선합니다. |
| **Asynchronous**        | Coroutines, Flow                           | Coroutines와 Flow를 사용하여 비동기 작업을 관리하고, 데이터 스트림을 효율적으로 처리하여 UI 업데이트와 상태 관리를 원활하게 합니다. |
| **Image**               | Coil                                        | Coil은 이미지 로딩을 최적화하며, 빠르고 효율적인 이미지 처리를 통해 앱의 성능을 개선합니다. |
| **Strategy**            | Git Flow                                    | Git Flow 전략을 통해 명확한 브랜치 관리와 협업 프로세스를 제공하여 효율적인 협업을 가능하게 합니다. |
| **CI**                  | GitHub Action                               | GitHub Action을 활용한 CI 파이프라인을 통해 코드 품질을 유지하고 자동화된 빌드를 실행하여 오류를 미리 방지할 수 있습니다. |

<br/>

## 🩷 *****우리의 협업 룰*****
> ## 🐣 그라운드 룰
1️⃣ 불만이 생기면 서로 솔직하게 얘기하기 <br>
2️⃣ 서로의 진행 상황에 대해 관심 갖고 지켜봐주기 <br>
3️⃣ 질문 많이해도 친절하게 답하기 <br>
4️⃣ 질문 전에 본인이 질문할 내용 정확히 다시 확인하기 <br>
5️⃣ 서로에게 칭찬 많이 해주기 <br>
6️⃣ 서로에게 상처주지 않는 둥글고 예쁜 말투 사용하기 <br>

<br/>

> ## 💻 코드리뷰 룰
1. 6시간안에 PR 확인하기 (합숙 동안)
2. 친절하게 알려주기
3. 본인이 가능한 선에서 피드백 적용하되, 최대한 해보기

## 📁 *****Foldering*****

```
📦com.acon.acon
├─📂app
│  ├─📂di
│  ├─📂navigation
│  ├─📰Application.kt
│  └─📰MainActivity.kt
├─📂core
│  ├─📂designsystem
│  │  ├─📂component
│  │  ├─📂theme
│  ├─📂base
│  ├─ ...
├─📂data
│  ├─📂api
│  │  ├─📂local - SharedPreferences, DataStore, Room
│  │  └─📂remote - Retrofit
│  ├─📂datasource
│  │  ├─📂local 
│  │  └─📂remote 
│  ├─📂di - Hilt Module
│  ├─📂dto
│  │  ├─📂response - 응답 객체
│  │  └─📂request - 요청 객체
│  └─📂repository - RepositoryImpl
├─📂domain
│  ├─📂exception - 추후 논의 예정
│  ├─📂model
│  ├─📂repository
│  ├─📂usecase
├─📂feature
│  ├─📂explore
│  │  ├─📂component
│  │  ├─📂screen
│  │  │  ├─📂spotlist
│  │  │  │  ├─📂composable
│  │  │  │  │  ├─📰SpotListScreen.kt
│  │  │  │  │  └─📰SpotListScreenContent.kt
│  │  │  │  └─📰SpotListViewModel.kt
│  │  │  ├─📂spotdetail
│  │  │  │  ├─📂composable
│  │  │  │  │  ├─📰SpotDetailScreen.kt
│  │  │  │  │  └─📰SpotDetailScreenContent.kt
│  │  │  │  └─📰SpotDetailViewModel.kt
│  │  │  ├─📂...
│  │  ├─📂type
│  ├─📂signin
│  │  ├─📂component
│  │  ├─ ...

----------
## 추천 메뉴 페이지

먼저 추천 메뉴 페이지 구조는 다음과 같다.

(이미지)

1. MySQL에서 유저 정보와 유저들의 리뷰(별점)정보를 받아온다.
2. 서버에서 Python으로 구현한 머신러닝을 통하여 유저들의 평점을 예측한다.
3. 서버에서 예측된 평점을 바탕으로 추천 메뉴를 불러와 response해준다.
4. 클라이언트에서 Retrofit2를 통해 추천메뉴를 받아와 화면에 출력해준다.

### * LatentFactorModel.py

+ 사용 모듈
  - pymysql: python에서 SQL명령어를 실행하여 MySQL에 있는 데이터를 받아올 수 있다.
  - pandas: 2차원적 자료구조로 dataframe형식으로 데이터를 관리할 수 있다.
  - torch: 딥러닝 도구로서 2차원 이상의 array로 데이터를 관리할 수 있다. 특히 torch.nn.functional을 통해 기울기를 쉽게 계산할 수 있다.

+ MySQL 연결
  - 다음 명령어를 통해 MySQL에 연결한다.
  '''con = pymysql.connect(
    host='13.209.185.52',
    port=54198,
    user= 'root',
    password='1234',
    db='Kookbob',
    charset='utf8')'''

   



----------
알림
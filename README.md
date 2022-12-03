
---
## 추천 메뉴 페이지

먼저 추천 메뉴 페이지 구조는 다음과 같다.

![Alt text](../../../../../../../c:/Users/Kim/Desktop/kookbap/%EA%B5%AD%EB%B0%A5%20%EA%B5%AC%EC%A1%B0.png)

1. MySQL에서 유저 정보와 유저들의 리뷰(별점)정보를 받아온다.
2. 서버에서 Python으로 구현한 머신러닝을 통하여 유저들의 평점을 예측한다.
3. 서버에서 예측된 평점을 바탕으로 추천 메뉴를 불러와 response해준다.
4. 클라이언트에서 Retrofit2를 통해 추천메뉴를 받아와 화면에 출력해준다.

### * 머신러닝 - 잠재요인 협업필터링

+ 잠재요인 협업필터링(Latent Factor Collaborativ Filtering)
  - 어떤 사용자가 특정 메뉴를 선호할 때, 그 메뉴와 비슷한 특징을 가진 다른 메뉴를 추천하는 것이 이 방식의 기본 아이디어이다.
  - 예를 들어 다음과 같은 표가 있다. 
    |  | 떡볶이 | 라면 | 칼국수 | 국밥 | 돈까스 |
    | --- | --- | --- | --- | --- | --- |
    | 정국 | 4 |  |  | 2 |  |
    | 석진 |  | 5 |  | 3 | 1 |
    | 제니 |  |  | 3 | 4 | 4 |
    | 지수 | 5 | 2 | 1 | 2 |  |

### * LatentFactorModel.py

+ 사용 모듈
  - pymysql: python에서 SQL명령어를 실행하여 MySQL에 있는 데이터를 받아올 수 있다.
  - pandas: 2차원적 자료구조로 dataframe형식으로 데이터를 관리할 수 있다.
  - torch: 딥러닝 도구로서 2차원 이상의 array로 데이터를 관리할 수 있다. 특히 torch.nn.functional을 통해 기울기를 쉽게 계산할 수 있다.

+ MySQL 연결
  - 다음 명령어를 통해 MySQL에 연결해 SQL명령어를 실행한다.
  ```con = pymysql.connect(
    host='13.209.185.52',
    port=54198,
    user= 'root',
    password='****',
    db='Kookbob',
    charset='utf8') # mysql connection 연결

    cur = con.cursor() # connection으로부터 cursor 생성
    sql = "SELECT * FROM ...." # SQL 명령어
    cur.execute(sql) # SQL문 실행
    rows = cur.fetchall()
    data = pd.DataFrame(rows) # 실행된 SQL문을 DataFrame 형식으로 저장

+ 머신러닝
    - $H(i,x) = p_{i}·q_{x}$




   



---
알림

---
# 추천 메뉴 페이지

먼저 추천 메뉴 페이지 구조는 다음과 같다.

![Alt text](../../../../../../../c:/Users/Kim/Desktop/kookbap/%EA%B5%AD%EB%B0%A5%20%EA%B5%AC%EC%A1%B0.png)

1. MySQL에서 유저 정보와 유저들의 리뷰(별점)정보를 받아온다.
2. 서버에서 Python으로 구현한 머신러닝을 통하여 유저들의 평점을 예측한다.
3. 서버에서 예측된 평점을 바탕으로 추천 메뉴를 불러와 response해준다.
4. 클라이언트에서 Retrofit2를 통해 추천메뉴를 받아와 화면에 출력해준다.

## 머신러닝 - 잠재요인 협업필터링

+ ### 잠재요인 협업필터링(Latent Factor Collaborativ Filtering)
  - 어떤 사용자가 특정 메뉴를 선호할 때, 그 메뉴와 비슷한 특징을 가진 다른 메뉴를 추천하는 것이 이 방식의 기본 아이디어이다.
  - 예를 들어 다음과 같은 표가 있다. 
    |  | 떡볶이 | 라면 | 칼국수 | 국밥 | 돈까스 |
    | --- | --- | --- | --- | --- | --- |
    | 정국 | 4 |  |  | 2 |  |
    | 석진 |  | 5 |  | 3 | 1 |
    | 제니 |  |  | 3 | 4 | 4 |
    | 지수 | 5 | 2 | 1 | 2 |  |

    위의 유저-메뉴 표를 보면 각 유저가 각 메뉴에 부여한 별점을 확인할 수 있다. 이 때 유저-메뉴 표에 어떤 잠재 요인(Latent Facotor) 이 있다고 가정하자. 잠재요인은 메뉴의 재료, 맛, 양, 가격 등 다양한 특성들이 될 수 있다. 예를 들어 각 유저와 메뉴에 3가지의 잠재요인이 있다 가정했을 때의 표는 다음과 같다.
    |  | [a1, a2, a3] | [b1, b2, b3] | [c1, c2, c3] | [d1, d2, d3,] | [e1, e2, e3] |
    | --- | --- | --- | --- | --- | --- |
    | [w1, w2, w3] | 4 |  |  | 2 |  |
    | [x1, x2, x3] |  | 5 |  | 3 | 1 |
    | [y1, y2, y3] |  |  | 3 | 4 | 4 |
    | [z1, z2, z3] | 5 | 2 | 1 | 2 |  |

    위 행렬에서 별점의 값은 각 유저벡터와 메뉴벡터의 내적 값과 같다. ( 4 = w1a1+w2a2+w3a3)
    이를 머신러닝을 통하여 각 벡터값을 구해내면 다음과 같다.
    |  | [1.62, 1.51, -2.22] | [-0.79, 0.45, -1.85] | [1.04, -0.06, 0.43] | [1.07, 0.12, 1.18] | [1.43, -0.21, -0.50] |
    | --- | --- | --- | --- | --- | --- |
    | [0.96, 0.47, -0.76] | 3.99 | 0.90 | 1.31 | 2.00 | 1.66 |
    | [-0.03, 0.84, -2.47] | 6.70 | 4.98 | 0.98 | 3.00 | 1.00 |
    | [2.38, 0.11, -1.20] | 6.68 | 0.39 | 3.00 | 3.98 | 3.99 |
    | [0.59, 1.10, -1.06] | 4.97 | 2.01 | 1.01 | 2 | 1.14 |

    위 표를 보면 예측된 평점과 실제 평점의 차이가 얼마 나지 않음을 확인할 수 있다. 또한 각 유저가 평가하지 않은 메뉴들의 평점을 예측할 수 있다.
  - 가설, 비용, 정규화
    - 가설함수
        - $H(i,x) = p_{i}·q_{x}$ (p = Menu Latent Vector, q = User Latent Vector)
    - 비용
        - $cost(P,Q) = \sum_{(i,x)\in R}^{}$          $(r_{xi}-H(i,x))^2$
    - 업데이트
        - $P = P - \alpha\frac{\partial cost(P,Q)}{\partial P}$
        - $Q = Q - \alpha\frac{\partial cost(P,Q)}{\partial Q}$
    - 정규화
        - $cost(P,Q) = \sum_{(i,x)\in R}^{}$  $(r_{xi}-H(i,x))^2$ $+ \lambda _{1}\sum_{i}^{}$ $\left\|p_{i} \right\|_{2}^{2}$ $+ \lambda _{2}\sum_{x}^{}$ $\left\|q_{x} \right\|_{2}^{2}$
    - 정규화 반영 이후
        - $H(i,x) = \mu + bi_{i}+bu_{x}+p_{i}·q_{x}$
        - $cost(P,Q) = \sum_{(i,x)\in R}^{}$  $(r_{xi}-H(i,x))^2$ $+ \lambda _{1}\sum_{i}^{}$ $\left\|p_{i} \right\|_{2}^{2}$ $+ \lambda _{2}\sum_{x}^{}$ $\left\|q_{x} \right\|_{2}^{2}$  $+\lambda_{3}\left\|bi\right\|_{2}^{2}+\lambda_{4}\left\|bu \right\|_{2}^{2}$  


## LatentFactorModel.py

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
  ```



   



---
알림
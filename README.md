# cra_test

#### 요구사항 체크 리스트

- D1 : 함수 리팩토링
- D2 : 메서드(클래스) 리팩토링 - Unit Test
- D3 : 메서드(클래스) 리팩토링 - 확장성 고려
- D4 : 디자인 패턴 사용
- D5 : 코드 커버리지(line) 100%

| 요구사항 | 적용             |
|------|----------------|
| D1   | O              |
| D2   | O              |
| D3   | O              |
| D4   | O , Factory 패턴 |
| D5   | O              |

### 코드 커버리지 캡처 이미지

![](/images/mission2_커버리지.PNG)

코드 커버리지 제외 클래스들
- enums 패키지는 상수를 사용하는 enum 클래스를 모아둔 패키지 (커버리지에 측정된 다른 클래스에서 사용 됨)
- User.class는 main 함수 실행하는 클래스로 입출력 관련 클래스
- Factory.class는 user에서 입출력하는 Question 구현 클래스를 주입
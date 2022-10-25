# 토비의 스프링 jdbc template

## 1. Factory적용
1. ConnectionMaker Class로 분리
2. Mysql Jdbc Driver추가 하기
3. addAndGet 테스트 만들기
4. Interface적용해서 분리
5. Factory적용

## 2. add()에서 user==null인 경우 Exception처리
1. Spring-boot-starter-jdbc, spring-boot-starter-test 추가
2. Spring 도입 – 98pTest Code를 ApplicationContext사용하게 수정
3. deleteAll(), GetCount()추가 - 166p
4. deleteAll(), GetCount() 테스트 추가
5. Test Code @BeforeEach 적용
6. add()에서 user==null인 경우 Exception처리

## 3. AddStrategy
1. try / catch / final 처리 - 213
2. Statement Strategy 인터페이스 추가
3. jdbcContextWithStatementStrategy로 분리
4. deleteAllStrategy()
5. AddStrategy
   
   
   
   

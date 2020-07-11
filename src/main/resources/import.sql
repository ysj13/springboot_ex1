INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL, CREATE_DATE) VALUES (1, 'test1', '1234', '테스트1', 'test1@naver.com', CURRENT_TIMESTAMP());
INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL, CREATE_DATE) VALUES (2, 'test2', '1234', '테스트2', 'test2@naver.com', CURRENT_TIMESTAMP());
INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL, CREATE_DATE) VALUES (3, 'ysj13', '1234', 'ysj13', 'ysj13@gmail.com', CURRENT_TIMESTAMP());

INSERT INTO QUESTION (id, writer_id, title, contents, create_date, count_of_answer) VALUES (1, 1, 'React에서 컴포넌트를 어떻게 생성하나요?', 'ReactJS 는 Components 생성하는 두 가지 방법이 있습니다.',CURRENT_TIMESTAMP(), 0);
INSERT INTO QUESTION (id, writer_id, title, contents, create_date, count_of_answer) VALUES (2, 2, 'springboot 프로젝트 생성 방법이 무엇인가요?', 'starter project를 생성하세요.', CURRENT_TIMESTAMP(), 0);
INSERT INTO QUESTION (id, writer_id, title, contents, create_date, count_of_answer) VALUES (3, 3, 'spring MVC패턴이 무엇인가요?', 'model, view, controller 3가지 형태로 역할을 나누어 개발하는 방법론입니다.', CURRENT_TIMESTAMP(), 0);
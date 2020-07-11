INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (1, 'test1', '1234', '테스트1', 'test1@naver.com');
INSERT INTO USER (ID, USER_ID, PASSWORD, NAME, EMAIL) VALUES (2, 'test2', '1234', '테스트2', 'test2@naver.com');

INSERT INTO QUESTION (id, writer_id, title, contents, create_date, count_of_answer) VALUES (1, 1, 'React에서 컴포넌트를 어떻게 생성하나요?', 'ReactJS 는 Components 생성하는 두 가지 방법이 있습니다.',CURRENT_TIMESTAMP(), 0);
INSERT INTO QUESTION (id, writer_id, title, contents, create_date, count_of_answer) VALUES (2, 2, 'test2가 쓴', '테스트2가 쓴 글입니다.', CURRENT_TIMESTAMP(), 0);
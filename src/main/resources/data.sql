INSERT INTO ROLE (ROLE_ID, NAME) VALUES (1, 'ADMIN');
INSERT INTO ROLE (ROLE_ID, NAME) VALUES (2, 'STUDENT');

INSERT INTO PERMISSION (PERMISSION_ID, NAME) VALUES (1, 'admin:read');
INSERT INTO PERMISSION (PERMISSION_ID, NAME) VALUES (2, 'admin:write');
INSERT INTO PERMISSION (PERMISSION_ID, NAME) VALUES (3, 'student:read');
INSERT INTO PERMISSION (PERMISSION_ID, NAME) VALUES (4, 'student:write');

INSERT INTO ROLE_PERMISSION (ROLE_ID, PERMISSION_ID) VALUES (1, 1);
INSERT INTO ROLE_PERMISSION (ROLE_ID, PERMISSION_ID) VALUES (1, 2);
INSERT INTO ROLE_PERMISSION (ROLE_ID, PERMISSION_ID) VALUES (2, 3);
INSERT INTO ROLE_PERMISSION (ROLE_ID, PERMISSION_ID) VALUES (2, 4);


INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                      VALUES(1, 1, 'Bob', 'Smith', 'alloy123', 'password', 'koka@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES(2, 1, 'Mark', 'Jhonson', 'tetris21', 'passWo77', 'gmail@gmail.com');

INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES (3, 2, 'Maria', 'Nebora', 'studentRole87', '123PassS', 'ma_7@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES (4, 2, 'Anna', 'Yablovski', 'tentacle', 'password123', 'email@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES(5, 2, 'Garry', 'Smith', 'check456', 'truePass87', 'Tor_PassGOD@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES (6, 2, 'Vanessa', 'Iordano', 'grasp_body89', 'check56321', 'myQueryE@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES(7, 2, 'Robert', 'Jons', 'spider897_tRue', 'theBestPassword87', 'rolOverTor@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES (8, 2, 'Bridget', 'Salovski', 'Salovski78', 'passWordWord45', 'trueBody@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES(9, 2, 'Draik', 'Robertson', 'pirat1020', 'password987', 'pirat@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES (10, 2, 'Georgiy', 'Rudkovskiy', 'rusUk85', 'passGo_Re852', 'chernCom@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES(11, 2, 'Bob', 'Markovich', 'bobMarkovich12', 'killAllEnemies87', 'robBob@gmail.com');
INSERT INTO USER (USER_ID, ROLE_ID, FIRST_NAME, LAST_NAME, LOGIN, PASSWORD, EMAIL)
                    VALUES (12, 2, 'Anna', 'Smith', 'tentacle987', 'password123456', 'EMemail@gmail.com');

INSERT INTO ADDRESS (ADDRESS_ID, COUNTRY, REGION, CITY, STREET)
                    VALUES (1, 'Ukraine', 'Kharkov region', 'Kharkov', 'Shevchenko st, 12');

INSERT INTO SCHOOL (SCHOOL_ID, NAME, ADDRESS_ID)
                    VALUES (1, 'Kharkov school №5', 1);

DROP TABLE IF EXISTS user_account;  

create table user_account
(
    user_id         INT AUTO_INCREMENT  PRIMARY KEY,
    user_name    varchar(100),
    password   varchar(100),
    user_role  varchar(100),
    email	   varchar(100),
    address    varchar(100)
);


insert into user_account(user_id, user_name, password, user_role, email, address) VALUES (1, 'guru', '123', 'EMPLOYEE', 'guru@gmail.com', 'Chennai');
insert into user_account(user_id, user_name, password, user_role, email, address) VALUES (2, 'murthy', '123', 'MANAGER', 'murthy@gmail.com', 'Bangalore');
insert into user_account(user_id, user_name, password, user_role, email, address) VALUES (3, 'test', '123', 'EMPLOYEE', 'test@gmail.com', 'Chennai');


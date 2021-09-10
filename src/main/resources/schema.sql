-- you have to create database 'testdb' and create table manually before run
-- this application as 'spring.jpa.hibernate.ddl-auto = none'
-- but when run the unit test, the table will auto created  with h2 database

CREATE TABLE employees (
      id    INT(11)      NOT NULL AUTO_INCREMENT,
      name  VARCHAR(40)  NOT NULL,
      email   VARCHAR(40) ,
      birth_date  DATE ,
      PRIMARY KEY (id)
);
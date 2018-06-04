CREATE TABLE WEBPAGE(
  path VARCHAR(100), --页面路径
  title VARCHAR(100),--页面标题
  html clob,         --页面html

  PRIMARY KEY(path)
);


CREATE TABLE T_USER
(
    id integer PRIMARY KEY,
    password varchar,
    priority varchar,
    username varchar
)
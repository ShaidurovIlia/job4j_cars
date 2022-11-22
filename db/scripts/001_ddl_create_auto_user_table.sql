CREATE TABLE if not exists auto_user
(
   id SERIAL PRIMARY KEY,
   login VARCHAR UNIQUE NOT NULL,
   password VARCHAR NOT NULL
 );



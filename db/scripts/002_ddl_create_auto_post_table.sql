 CREATE TABLE if not exists post
 (id SERIAL PRIMARY KEY,
 text TEXT NOT NULL,
 created TIMESTAMP NOT NULL,
 auto_user_id INT REFERENCES auto_user(id),
 car_id int not null REFERENCES cars(id)
 );
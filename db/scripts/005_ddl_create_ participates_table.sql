CREATE TABLE if not exists participates
(
   id SERIAL PRIMARY KEY,
   post_id INT REFERENCES post(id),
   user_id INT REFERENCES auto_user(id)
);
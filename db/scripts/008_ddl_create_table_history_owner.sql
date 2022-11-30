create table if not exists history_owner
(
id serial primary key,
name varchar,
driver_id int not null reference driver(id),
car_id int not null references car(id),
startAt varchar,
endAt varchar
);
alter table auto_post ADD COLUMN car_id int NOT NULL REFERENCES car(id);
update auto_post set car_id = 1 where car_id = null;

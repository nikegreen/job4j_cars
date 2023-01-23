alter table auto_post ADD COLUMN car_id int NOT NULL REFERENCES car(id) DEFAULT 1;
update auto_post set car_id = (select id from car order by id limit 1) where car_id = null;

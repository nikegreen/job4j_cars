alter table auto_post ADD COLUMN car_id int NOT NULL REFERENCES car(id) DEFAULT 1;


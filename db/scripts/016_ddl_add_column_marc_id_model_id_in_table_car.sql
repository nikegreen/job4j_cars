
alter table car ADD COLUMN marc_id int REFERENCES car_marc(id);
update car set marc_id = q.id from (select id from car_marc where name = 'Audi') as q where car.marc_id is null or car.marc_id = 0;
comment on column car.marc_id is 'идентификатор авто марки из списка автомарок';

alter table car ADD COLUMN model_id int REFERENCES car_model(id);
update car set model_id = q.id from (select id from car_model where name = '100') as q where car.model_id is null or car.model_id = 0;
comment on column car.model_id is 'идентификатор авто модели из списка автомоделей';

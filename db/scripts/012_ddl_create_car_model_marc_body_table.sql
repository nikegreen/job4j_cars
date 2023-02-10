create table if not exists car_marc(
    id SERIAL PRIMARY KEY,
    name text UNIQUE
);
comment on table car_marc is 'список авто марок';
comment on column car_marc.id is 'идентификатор автомарки в списке';
comment on column car_marc.name is 'авто марка (брэнд)';

create table if not exists car_body(
    id SERIAL PRIMARY KEY,
    name text UNIQUE
);
comment on table car_body is 'список типов кузова автомобилей';
comment on column car_body.id is 'идентификатор типа кузова в списке';
comment on column car_body.name is 'тип кузова автомобиля';

create table if not exists car_model(
    id SERIAL PRIMARY KEY,
    name text,
    marc_id integer REFERENCES car_marc (id),
    body_id integer REFERENCES car_body (id),
    CONSTRAINT marc_model_unique UNIQUE (name, marc_id)
);
comment on table car_model is 'список моделей авто марки с marc_id';
comment on column car_model.id is 'идентификатор модели в списке';
comment on column car_model.name is 'модель марки с marc_id';
comment on column car_model.marc_id is 'идентификатор авто марки (брэнда)';
comment on column car_model.body_id is 'идентификатор типа кузова автомобиля';


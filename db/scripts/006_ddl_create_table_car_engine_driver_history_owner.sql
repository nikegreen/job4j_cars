create table if not exists engine(
    id SERIAL PRIMARY KEY,
    name text NOT NULL UNIQUE
);
comment on table engine is 'список двигателей';
comment on column engine.id is 'идентификатор двигателя';
comment on column engine.name is 'название двигателя';

create table if not exists car(
    id SERIAL PRIMARY KEY,
    name text NOT NULL UNIQUE,
    engine_id int NOT NULL REFERENCES engine(id)
);
comment on table car is 'список автомобилей';
comment on column car.id is 'идентификатор автомобиля';
comment on column car.name is 'название автомобиля';
comment on column car.engine_id is 'идентификатор двигателя из таблицы двигателей - engine';

create table if not exists driver(
    id SERIAL PRIMARY KEY,
    name text NOT NULL UNIQUE,
    user_id int NOT NULL REFERENCES auto_user(id)
);
comment on table driver is 'список водителей';
comment on column driver.id is 'идентификатор водителя';
comment on column driver.name is 'имя водителя';
comment on column driver.user_id is 'идентификатор пользователя из таблицы пользователей - auto_user';

create table if not exists history_owner(
    id SERIAL PRIMARY KEY,
    car_id int NOT NULL REFERENCES car(id),
    driver_id int NOT NULL REFERENCES driver(id),
    startAt timestamp without time zone NOT NULL,
    endAt timestamp without time zone
);
comment on table history_owner is 'История владельцев машин. Список всех машин водителя, список всех владельцев по машине';
comment on column history_owner.id is 'идентификатор записи в истории владельцев машин';
comment on column history_owner.car_id is 'идентификатор автомобиля из таблицы автомобилей - car';
comment on column history_owner.driver_id is 'идентификатор водителя из таблицы водителей - driver';
comment on column history_owner.startAt is 'дата и время начала периода вождения водителем автомобиля';
comment on column history_owner.endAt is 'дата и время конца периода вождения водителем автомобиля, null - ещё продолжает водить';

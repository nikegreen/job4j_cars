create table if not exists photo(
    id SERIAL PRIMARY KEY,
    name text NOT NULL UNIQUE,
    file_name text NOT NULL UNIQUE,
    post_id int NOT NULL REFERENCES post(id)
);
comment on table engine is 'список фотографий для всех постов';
comment on column engine.id is 'идентификатор фотографии';
comment on column engine.name is 'название фотографии';
comment on column engine.file_name is 'путь и имя к файлу с фотографией';
comment on column engine.post_id is 'идентификатор объявления к которому привязана фотография';


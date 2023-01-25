create table if not exists photo(
    id SERIAL PRIMARY KEY,
    name text NOT NULL UNIQUE,
    file_name text NOT NULL UNIQUE,
    post_id int NOT NULL REFERENCES auto_post(id)
);
comment on table photo is 'список фотографий для всех постов';
comment on column photo.id is 'идентификатор фотографии';
comment on column photo.name is 'название фотографии';
comment on column photo.file_name is 'путь и имя к файлу с фотографией';
comment on column photo.post_id is 'идентификатор объявления к которому привязана фотография';


DROP TABLE IF EXISTS auto_user CASCADE;
create table auto_user(
    id SERIAL PRIMARY KEY,
    login text NOT NULL UNIQUE,
    password text NOT NULL
);
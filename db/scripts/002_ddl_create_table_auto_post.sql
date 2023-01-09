DROP TABLE IF EXISTS auto_post CASCADE;
create table auto_post(
    id SERIAL PRIMARY KEY,
    _text text NOT NULL,
    created  timestamp without time zone NOT NULL,
    auto_user_id int NOT NULL REFERENCES auto_user(id),
    CONSTRAINT auto_post_unique UNIQUE (_text, created, auto_user_id)
);
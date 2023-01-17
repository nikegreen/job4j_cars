create table if not exists participates (
    id SERIAL PRIMARY KEY,
    user_id int NOT NULL REFERENCES auto_user (id),
    post_id int NOT NULL REFERENCES auto_post (id));
comment on table participates is 'подписка пользователя на объявление';
comment on column participates.id is 'идентификатор подписки';
comment on column participates.user_id is 'ссылка на пользователя из таблицы пользователей';
comment on column participates.post_id is 'ссылка на объявление из таблицы объявлений';

comment on table price_history is 'история изменения цен в объявлениях';
comment on column price_history.id is 'идентификатор записи изменения цены в объявлении';
comment on column price_history.before is 'цена в объявлении до изменения';
comment on column price_history.after is 'цена в объявлении после изменения';
comment on column price_history.created is 'время и дата изменения цены';
comment on column price_history.auto_post_id is 'ссылка на объявление из таблицы объявлений';

comment on table auto_post is 'список объявлений';
comment on column auto_post.id is 'идентификатор объявления';
comment on column auto_post._text is 'текст объявления';
comment on column auto_post.created is 'время и дата создания объявления';
comment on column auto_post.auto_user_id is 'ссылка на пользователя который создал объявление из таблицы пользователей';

comment on table auto_user is 'список пользователей';
comment on column auto_user.id is 'идентификатор пользователя';
comment on column auto_user.login is 'login для входа на сайт для пользователя';
comment on column auto_user.password is 'пароль пользователя для входа на сайт';

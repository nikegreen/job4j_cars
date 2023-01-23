insert into engine (name) values ('4g64');
insert into engine (name) values ('4g63');
insert into engine (name) values ('4g63t');
insert into car (name, engine_id) values('mistsubishi airtrack', (select id from engine where name = '4g64'));
insert into car (name, engine_id) values('mistsubishi galant', (select id from engine where name = '4g63t'));
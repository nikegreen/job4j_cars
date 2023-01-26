insert into engine (name) values ('4g64');
insert into engine (name) values ('4g63');
insert into engine (name) values ('4g63t');
insert into car (name, engine_id) values('mitsubishi airtrack', (select id from engine where name = '4g64'));
insert into car (name, engine_id) values('mitsubishi galant', (select id from engine where name = '4g63t'));
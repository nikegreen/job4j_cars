INSERT INTO auto_user (login, password) VALUES ('user', '1');
INSERT INTO auto_user (login, password) VALUES ('admin', '1');

insert into driver (name, user_id) values('driver 1 user', (select id from auto_user where login = 'user'));
insert into driver (name, user_id) values('driver 2 admin', (select id from auto_user where login = 'admin'));

insert into car (name,  marc_id, model_id, engine_id) values('Audi-A6', (select id from car_marc where name = 'Audi'), (select id from car_model where name = 'A6') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Audi-A6', '2007-02-26T21:23:14.250', (select id from auto_user where login = 'admin'), (select id from car where name = 'Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 1 Audi A6', 'f1.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 2 Audi A6', 'f2.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 3 Audi A6', 'f3.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 4 Audi A6', 'f4.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 5 Audi A6', 'f5.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 6 Audi A6', 'f6.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 7 Audi A6', 'f7.jpg', (select id from auto_post where _text = 'sell Audi-A6'));
insert into photo (name, file_name, post_id) values ('photo 8 Audi A6', 'f8.jpg', (select id from auto_post where _text = 'sell Audi-A6'));


insert into car (name,  marc_id, model_id, engine_id) values('BMW-X5', (select id from car_marc where name = 'BMW'), (select id from car_model where name = 'X5') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell BMW-X5', '2007-02-26T21:23:14.250', (select id from auto_user where login = 'admin'), (select id from car where name = 'BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 1 BMW X5', 'f9.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 2 BMW X5', 'f10.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 3 BMW X5', 'f11.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 4 BMW X5', 'f12.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 5 BMW X5', 'f13.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 6 BMW X5', 'f14.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 7 BMW X5', 'f15.jpg', (select id from auto_post where _text = 'sell BMW-X5'));
insert into photo (name, file_name, post_id) values ('photo 8 BMW X5', 'f16.jpg', (select id from auto_post where _text = 'sell BMW-X5'));

insert into car (name,  marc_id, model_id, engine_id) values('BMW-X6', (select id from car_marc where name = 'BMW'), (select id from car_model where name = 'X6') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell BMW-X6', '2007-02-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 1 BMW X6', 'f17.jpg', (select id from auto_post where _text = 'sell BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 2 BMW X6', 'f18.jpg', (select id from auto_post where _text = 'sell BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 3 BMW X6', 'f19.jpg', (select id from auto_post where _text = 'sell BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 4 BMW X6', 'f20.jpg', (select id from auto_post where _text = 'sell BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 5 BMW X6', 'f21.jpg', (select id from auto_post where _text = 'sell BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 6 BMW X6', 'f22.jpg', (select id from auto_post where _text = 'sell BMW-X6'));
insert into photo (name, file_name, post_id) values ('photo 7 BMW X6', 'f23.jpg', (select id from auto_post where _text = 'sell BMW-X6'));

insert into car (name,  marc_id, model_id, engine_id) values('Ford-Focus', (select id from car_marc where name = 'Ford'), (select id from car_model where name = 'Focus') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Ford-Focus', '2007-02-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 1 Ford Focus', 'f24.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 2 Ford Focus', 'f25.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 3 Ford Focus', 'f26.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 4 Ford Focus', 'f27.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 5 Ford Focus', 'f28.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 6 Ford Focus', 'f29.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 7 Ford Focus', 'f30.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));
insert into photo (name, file_name, post_id) values ('photo 8 Ford Focus', 'f31.jpg', (select id from auto_post where _text = 'sell Ford-Focus'));

insert into car (name,  marc_id, model_id, engine_id) values('Hyundai-Accent', (select id from car_marc where name = 'Hyundai'), (select id from car_model where name = 'Accent') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Hyundai-Accent', '2007-02-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Hyundai-Accent'));
insert into photo (name, file_name, post_id) values ('photo 1 Hyundai Accent', 'f32.jpg', (select id from auto_post where _text = 'sell Hyundai-Accent'));
insert into photo (name, file_name, post_id) values ('photo 2 Hyundai Accent', 'f33.jpg', (select id from auto_post where _text = 'sell Hyundai-Accent'));
insert into photo (name, file_name, post_id) values ('photo 3 Hyundai Accent', 'f34.jpg', (select id from auto_post where _text = 'sell Hyundai-Accent'));
insert into photo (name, file_name, post_id) values ('photo 4 Hyundai Accent', 'f35.jpg', (select id from auto_post where _text = 'sell Hyundai-Accent'));

insert into car (name,  marc_id, model_id, engine_id) values('Hyundai-Santa Fe', (select id from car_marc where name = 'Hyundai'), (select id from car_model where name = 'Santa Fe') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Hyundai-Santa Fe', '2007-02-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Hyundai-Santa Fe'));
insert into photo (name, file_name, post_id) values ('photo 1 Hyundai Santa Fe', 'f36.jpg', (select id from auto_post where _text = 'sell Hyundai-Santa Fe'));
insert into photo (name, file_name, post_id) values ('photo 2 Hyundai Santa Fe', 'f37.jpg', (select id from auto_post where _text = 'sell Hyundai-Santa Fe'));
insert into photo (name, file_name, post_id) values ('photo 3 Hyundai Santa Fe', 'f38.jpg', (select id from auto_post where _text = 'sell Hyundai-Santa Fe'));
insert into photo (name, file_name, post_id) values ('photo 4 Hyundai Santa Fe', 'f39.jpg', (select id from auto_post where _text = 'sell Hyundai-Santa Fe'));

insert into car (name,  marc_id, model_id, engine_id) values('Kia-Rio', (select id from car_marc where name = 'Kia'), (select id from car_model where name = 'Rio') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Kia-Rio', '2007-04-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Kia-Rio'));
insert into photo (name, file_name, post_id) values ('photo 1 Kia Rio', 'f40.jpg', (select id from auto_post where _text = 'sell Kia-Rio'));
insert into photo (name, file_name, post_id) values ('photo 2 Kia Rio', 'f41.jpg', (select id from auto_post where _text = 'sell Kia-Rio'));
insert into photo (name, file_name, post_id) values ('photo 3 Kia Rio', 'f42.jpg', (select id from auto_post where _text = 'sell Kia-Rio'));
insert into photo (name, file_name, post_id) values ('photo 4 Kia Rio', 'f43.jpg', (select id from auto_post where _text = 'sell Kia-Rio'));
insert into photo (name, file_name, post_id) values ('photo 5 Kia Rio', 'f44.jpg', (select id from auto_post where _text = 'sell Kia-Rio'));

insert into car (name,  marc_id, model_id, engine_id) values('Mercedes-Benz-E-Класс', (select id from car_marc where name = 'Mercedes-Benz'), (select id from car_model where name = 'E-Класс') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Mercedes-Benz-E-Класс', '2008-10-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 1 Mercedes-Benz E-Класс', 'f45.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 2 Mercedes-Benz E-Класс', 'f46.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 3 Mercedes-Benz E-Класс', 'f47.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 4 Mercedes-Benz E-Класс', 'f48.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 5 Mercedes-Benz E-Класс', 'f49.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 6 Mercedes-Benz E-Класс', 'f50.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));
insert into photo (name, file_name, post_id) values ('photo 7 Mercedes-Benz E-Класс', 'f51.jpg', (select id from auto_post where _text = 'sell Mercedes-Benz-E-Класс'));

insert into car (name,  marc_id, model_id, engine_id) values('Mitsubishi-Pajero', (select id from car_marc where name = 'Mitsubishi'), (select id from car_model where name = 'Pajero') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Mitsubishi-Pajero', '2008-10-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Mitsubishi-Pajero'));
insert into photo (name, file_name, post_id) values ('photo 1 Mitsubishi Pajero', 'f52.jpg', (select id from auto_post where _text = 'sell Mitsubishi-Pajero'));
insert into photo (name, file_name, post_id) values ('photo 2 Mitsubishi Pajero', 'f53.jpg', (select id from auto_post where _text = 'sell Mitsubishi-Pajero'));
insert into photo (name, file_name, post_id) values ('photo 3 Mitsubishi Pajero', 'f54.jpg', (select id from auto_post where _text = 'sell Mitsubishi-Pajero'));

insert into car (name,  marc_id, model_id, engine_id) values('Nissan-Pathfinder', (select id from car_marc where name = 'Nissan'), (select id from car_model where name = 'Pathfinder') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Nissan-Pathfinder', '2008-10-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Nissan-Pathfinder'));
insert into photo (name, file_name, post_id) values ('photo 1 Nissan Pathfinder', 'f55.jpg', (select id from auto_post where _text = 'sell Nissan-Pathfinder'));
insert into photo (name, file_name, post_id) values ('photo 2 Nissan Pathfinder', 'f56.jpg', (select id from auto_post where _text = 'sell Nissan-Pathfinder'));
insert into photo (name, file_name, post_id) values ('photo 3 Nissan Pathfinder', 'f57.jpg', (select id from auto_post where _text = 'sell Nissan-Pathfinder'));

insert into car (name,  marc_id, model_id, engine_id) values('Opel-Astra', (select id from car_marc where name = 'Opel'), (select id from car_model where name = 'Astra') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Opel-Astra', '2008-10-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Opel-Astra'));
insert into photo (name, file_name, post_id) values ('photo 1 Opel Astra', 'f58.jpg', (select id from auto_post where _text = 'sell Opel-Astra'));

insert into car (name,  marc_id, model_id, engine_id) values('Renault-Duster', (select id from car_marc where name = 'Renault'), (select id from car_model where name = 'Duster') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Renault-Duster', '2007-04-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Renault-Duster'));
insert into photo (name, file_name, post_id) values ('photo 1 Renault Duster', 'f59.jpg', (select id from auto_post where _text = 'sell Renault-Duster'));
insert into photo (name, file_name, post_id) values ('photo 2 Renault Duster', 'f60.jpg', (select id from auto_post where _text = 'sell Renault-Duster'));
insert into photo (name, file_name, post_id) values ('photo 3 Renault Duster', 'f61.jpg', (select id from auto_post where _text = 'sell Renault-Duster'));
insert into photo (name, file_name, post_id) values ('photo 4 Renault Duster', 'f62.jpg', (select id from auto_post where _text = 'sell Renault-Duster'));

insert into car (name,  marc_id, model_id, engine_id) values('Subaru-Outback', (select id from car_marc where name = 'Subaru'), (select id from car_model where name = 'Outback') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Subaru-Outback', '2007-04-26T21:23:14.250', (select id from auto_user where login = 'user'), (select id from car where name = 'Subaru-Outback'));
insert into photo (name, file_name, post_id) values ('photo 1 Subaru Outback', 'f63.jpg', (select id from auto_post where _text = 'sell Subaru-Outback'));
insert into photo (name, file_name, post_id) values ('photo 2 Subaru Outback', 'f64.jpg', (select id from auto_post where _text = 'sell Subaru-Outback'));
insert into photo (name, file_name, post_id) values ('photo 3 Subaru Outback', 'f65.jpg', (select id from auto_post where _text = 'sell Subaru-Outback'));

insert into car (name,  marc_id, model_id, engine_id) values('Toyota-Land Cruiser', (select id from car_marc where name = 'Toyota'), (select id from car_model where name = 'Land Cruiser') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Toyota-Land Cruiser', '2009-10-26T21:21:21.250', (select id from auto_user where login = 'admin'), (select id from car where name = 'Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 1 Toyota Land Cruiser', 'f66.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 2 Toyota Land Cruiser', 'f67.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 3 Toyota Land Cruiser', 'f68.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 4 Toyota Land Cruiser', 'f69.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 5 Toyota Land Cruiser', 'f70.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 6 Toyota Land Cruiser', 'f71.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 7 Toyota Land Cruiser', 'f72.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));
insert into photo (name, file_name, post_id) values ('photo 8 Toyota Land Cruiser', 'f73.jpg', (select id from auto_post where _text = 'sell Toyota-Land Cruiser'));

insert into car (name,  marc_id, model_id, engine_id) values('Toyota-RAV4', (select id from car_marc where name = 'Toyota'), (select id from car_model where name = 'RAV4') ,(select id from engine where name = 'бензин'));
insert into auto_post (_text, created, auto_user_id, car_id) values('sell Toyota-RAV4', '2009-10-26T21:21:21.250', (select id from auto_user where login = 'admin'), (select id from car where name = 'Toyota-RAV4'));
insert into photo (name, file_name, post_id) values ('photo 1 Toyota RAV4', 'f74.jpg', (select id from auto_post where _text = 'sell Toyota-RAV4'));
insert into photo (name, file_name, post_id) values ('photo 2 Toyota RAV4', 'f75.jpg', (select id from auto_post where _text = 'sell Toyota-RAV4'));
insert into photo (name, file_name, post_id) values ('photo 3 Toyota RAV4', 'f76.jpg', (select id from auto_post where _text = 'sell Toyota-RAV4'));
insert into photo (name, file_name, post_id) values ('photo 4 Toyota RAV4', 'f77.jpg', (select id from auto_post where _text = 'sell Toyota-RAV4'));
insert into photo (name, file_name, post_id) values ('photo 5 Toyota RAV4', 'f78.jpg', (select id from auto_post where _text = 'sell Toyota-RAV4'));
insert into photo (name, file_name, post_id) values ('photo 6 Toyota RAV4', 'f79.jpg', (select id from auto_post where _text = 'sell Toyota-RAV4'));


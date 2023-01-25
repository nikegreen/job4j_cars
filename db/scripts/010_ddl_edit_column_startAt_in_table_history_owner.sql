alter table history_owner drop COLUMN startAt;
alter table history_owner ADD COLUMN startAt timestamp without time zone;
comment on column history_owner.startAt is 'дата и время начала периода вождения водителем автомобиля';
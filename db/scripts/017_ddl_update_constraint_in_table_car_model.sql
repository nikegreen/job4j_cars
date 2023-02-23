ALTER TABLE IF EXISTS car_model DROP CONSTRAINT IF EXISTS marc_model_unique;
ALTER TABLE IF EXISTS car_model ADD CONSTRAINT marc_model_body_unique UNIQUE (name, marc_id, body_id);
--comment on CONSTRAINT marc_model_body_unique on car_model is 'ограничение на повторение комбинаций имени модели, идентификаторов марки автомобиля и типа кузова автомобиля';

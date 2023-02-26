package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.CarBody;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об кузовах автомобилей
 */
public interface CarBodyAbstractRepository {
    /**
     * Список  отсортированных по id.
     * @return список типов корпусов автомобилей.
     */
    List<CarBody> findAll();

    /**
     * Найти тип корпуса автомобиля по ID
     * @param id идентификотор типа корпуса
     * @return тип корпуса автомобиля.
     */
    Optional<CarBody> findById(int id);

    /**
     * Найти тип корпуса автомобиля по имени
     * @param bodyName имени типа корпуса
     * @return тип корпуса автомобиля.
     */
    Optional<CarBody>  findByName(String bodyName);
}

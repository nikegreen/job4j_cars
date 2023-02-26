package ru.job4j.cars.service;

import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Optional;

/**
 * Сервис кузовов авто
 */
public interface CarBodyAbstractService {
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
}

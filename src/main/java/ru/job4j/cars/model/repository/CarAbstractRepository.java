package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.Car;

import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об автомобилях в ОЗУ
 */
public interface CarAbstractRepository {
    /**
     * Сохранить в базе.
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    Car create(Car car);

    /**
     * Обновить в базе автомобиль.
     * @param car автомобиль.
     */
    void update(Car car);

    /**
     * Удалить автомобиль по id.
     * @param id ID
     */
    void delete(int id);

    /**
     * Список автомобилей отсортированных по id.
     * @return список автомобилей.
     */
    List<Car> findAllOrderById();

    /**
     * Найти автомобиль по ID
     * @return автомобиль.
     */
    Optional<Car> findById(int id);

    /**
     * Список автомобилей по имени LIKE %key%
     * @param key ключевая строка
     * @return список автомобилей.
     */
    List<Car> findByLikeName(String key);
}

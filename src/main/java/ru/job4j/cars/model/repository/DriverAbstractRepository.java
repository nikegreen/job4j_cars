package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverAbstractRepository {
    /**
     * Сохранить в базе водителей.
     * @param driver водитель.
     * @return водитель с id.
     */
    Driver create(Driver driver);

    /**
     * Обновить в базе водителя.
     * @param driver водитель.
     */
    void update(Driver driver);

    /**
     * Удалить водителя по id.
     * @param id ID
     */
    void delete(int id);

    /**
     * Список водителей отсортированных по id.
     * @return список водителей.
     */
    List<Driver> findAllOrderById();

    /**
     * Найти водителя по ID
     * @return водитель.
     */
    Optional<Driver> findById(int id);

    /**
     * Список водителей по имени LIKE %key%
     * @param key ключевая строка
     * @return список водителей.
     */
    List<Driver> findByLikeName(String key);
}

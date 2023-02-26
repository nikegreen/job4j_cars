package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.Engine;

import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об двигателях
 */
public interface EngineAbstractRepository {
    /**
     * Сохранить в базе двигателей.
     * @param engine двигатель.
     * @return двигатель с id.
     */
    Engine create(Engine engine);

    /**
     * Обновить в базе двигателей.
     * @param engine двигатель.
     */
    void update(Engine engine);

    /**
     * Удалить двигатель по id.
     * @param id ID
     */
    void delete(int id);

    /**
     * Список двигателей отсортированных по id.
     * @return список двигателей.
     */
    List<Engine> findAllOrderById();

    /**
     * Найти двигатель по ID
     * @return двигатель.
     */
    Optional<Engine> findById(int id);

    /**
     * Список двигателей по имени LIKE %key%
     * @param key ключевая строка
     * @return список двигателей.
     */
    List<Engine> findByLikeName(String key);
}

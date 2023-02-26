package ru.job4j.cars.service;

import ru.job4j.cars.model.CarMarc;

import java.util.List;
import java.util.Optional;

/**
 * Сервис марок авто
 */
public interface CarMarcAbstractService {
    /**
     * Список авто марок отсортированных по id.
     * @return список авто марок.
     */
    List<CarMarc> findAll();

    /**
     * Найти авто марку по ID
     * @param id идентификотор авто марки
     * @return авто марка.
     */
    Optional<CarMarc> findById(int id);
}

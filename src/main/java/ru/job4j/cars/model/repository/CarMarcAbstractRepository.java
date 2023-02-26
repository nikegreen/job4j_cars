package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.CarMarc;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об марках автомобилей
 */
public interface CarMarcAbstractRepository {
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

    /**
     * Найти авто марку по имени
     * @param name имя авто марки
     * @return ID авто марки, 0 если нет такого имени.
     */
    int findIdByName(String name);
}

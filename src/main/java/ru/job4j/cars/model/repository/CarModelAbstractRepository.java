package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.CarModel;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об моделях авто
 */
public interface CarModelAbstractRepository {

    /**
     * Список моделей отсортированных по id.
     * @return список моделей авто марки.
     */
    List<CarModel> findAll();

    /**
     * Найти модель авто марки по ID
     * @param id идентификотор модели авто марки
     * @return модель авто марки.
     */
    Optional<CarModel> findById(int id);

    /**
     * Список моделей отсортированных по id
     * для выбранной по идентфикатору авто марки.
     * @param id идентификотор авто марки
     * @return список моделей авто марки.
     */
    List<CarModel> findAllByMarcId(int id);

    /**
     * Список моделей отсортированных по id
     * для выбранной по типу кузова авто.
     * @param id идентификотор типа кузова авто
     * @return список всех моделей всех авто марок
     * с выбранным типом кузова.
     */
    List<CarModel> findAllByBodyId(int id);

    /**
     * Список моделей отсортированных по id
     * для выбранной по марке и типу кузова авто.
     * @param marcId идентификотор авто марки
     * @param bodyId идентификотор типа кузова авто
     * @return список всех моделей всех авто марок
     * с выбранной маркой и типом кузова.
     */
    List<CarModel> findAllByMarcIdAndBodyId(int marcId, int bodyId);

    /**
     * Найти модель авто марки по имени
     * @param name имя модели авто марки
     * @return модель авто марки.
     */
    Optional<CarModel> findByName(String name);
}

package ru.job4j.cars.service;

import ru.job4j.cars.model.PriceHistory;

import java.util.List;
import java.util.Optional;

public interface PriceHistoryAbstractService {
    /**
     * Сохранить в базе изменение цены.
     * @param priceHistory запись с измением цены.
     * @return запись с измением цены с id.
     */
    PriceHistory create(PriceHistory priceHistory);

    /**
     * Обновить в базе запись с измением цены.
     * @param priceHistory запись с измением цены.
     */
    void update(PriceHistory priceHistory);

    /**
     * Удалить запись с измением цены по id.
     * @param id ID запись с измением цены.
     */
    void delete(int id);

    /**
     * Список записей с измением цены отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.PriceHistory>} список записей с измением цены.
     */
    List<PriceHistory> findAllOrderById();

    /**
     * Список запись с измением цены отфильтрованных по id поста.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список записей с измением цены.
     */
    List<PriceHistory> findAllWherePost(int postId);

    /**
     * Найти запись с измением цены по ID
     * @return запись с измением цены.
     */
    Optional<PriceHistory> findById(int id);
}

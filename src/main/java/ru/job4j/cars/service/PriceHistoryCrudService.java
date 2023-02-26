package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PriceHistory;
import ru.job4j.cars.model.repository.PriceHistoryCrudRepository;
import ru.job4j.cars.model.repository.PriceHistoryMemRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис истории цены в БД
 */
@Service
@RequiredArgsConstructor
public class PriceHistoryCrudService implements PriceHistoryAbstractService {
    private final PriceHistoryCrudRepository histories;

    /**
     * Сохранить в базе изменение цены.
     * @param priceHistory запись с измением цены.
     * @return запись с измением цены с id.
     */
    @Override
    public PriceHistory create(PriceHistory priceHistory) {
        return histories.create(priceHistory);
    }

    /**
     * Обновить в базе запись с измением цены.
     * @param priceHistory запись с измением цены.
     */
    @Override
    public void update(PriceHistory priceHistory) {
        histories.update(priceHistory);
    }

    /**
     * Удалить запись с измением цены по id.
     * @param id ID запись с измением цены.
     */
    @Override
    public void delete(int id) {
        histories.delete(id);
    }

    /**
     * Список записей с измением цены отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.PriceHistory>}
     * список записей с измением цены.
     */
    @Override
    public List<PriceHistory> findAllOrderById() {
        return histories.findAllOrderById();
    }

    /**
     * Список запись с измением цены отфильтрованных по id поста.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список записей с измением цены.
     */
    @Override
    public List<PriceHistory> findAllWherePost(int postId) {
        return histories.findAllWherePost(postId);
    }

    /**
     * Найти запись с измением цены по ID
     * @return запись с измением цены.
     */
    @Override
    public Optional<PriceHistory> findById(int id) {
        return histories.findById(id);
    }
}

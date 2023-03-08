package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище истории цены в БД
 */
@Repository
@RequiredArgsConstructor
public class PriceHistoryCrudRepository implements PriceHistoryAbstractRepository {
    /**
     * HQL
     */
    public static final String FIND_ALL_BY_POST_ID =
            "from PriceHistory p where p.autoPostId = :fId";

    /**
     * Hibernate CRUD хранилище
     */
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе изменение цены.
     * @param priceHistory запись с измением цены.
     * @return запись с измением цены с id.
     */
    @Override
    public PriceHistory create(PriceHistory priceHistory) {
        return  crudRepository.create(priceHistory);
    }

    /**
     * Обновить в базе запись с измением цены.
     * @param priceHistory запись с измением цены.
     */
    @Override
    public void update(PriceHistory priceHistory) {
        crudRepository.update(priceHistory);
    }

    /**
     * Удалить запись с измением цены по id.
     * @param id ID запись с измением цены.
     */
    @Override
    public void delete(int id) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setId(id);
        crudRepository.delete(priceHistory);
    }

    /**
     * Список записей с измением цены отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.PriceHistory>}
     * список записей с измением цены.
     */
    @Override
    public List<PriceHistory> findAllOrderById() {
        return crudRepository.findAll(PriceHistory.class);
    }

    /**
     * Список запись с измением цены отфильтрованных по id поста.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список записей с измением цены.
     */
    @Override
    public List<PriceHistory> findAllWherePost(int postId) {
        return crudRepository.tx(session  -> session.createQuery(
                        FIND_ALL_BY_POST_ID,
                        PriceHistory.class)
                .setParameter("fId", postId)
                .list()
        );
    }

    /**
     * Найти запись с измением цены по ID
     * @return запись с измением цены.
     */
    @Override
    public Optional<PriceHistory> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, PriceHistory.class));
    }
}

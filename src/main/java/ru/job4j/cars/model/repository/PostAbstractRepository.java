package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.Post;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об объявлениях
 */

public interface PostAbstractRepository {
    /**
     * Сохранить в базе объявление.
     * @param post объявление.
     * @return объявление с id.
     */
    Post create(Post post);

    /**
     * Обновить в базе объявление.
     * @param post объявление.
     */
    void update(Post post);

    /**
     * Удалить объявление по id.
     * @param id ID объявления.
     */
    void delete(int id);

    /**
     * Список объявлений отсортированных по id.
     * @return список объявлений.
     */
    List<Post> findAllOrderById();

    /**
     * Найти объявления по ID
     * @return объявление.
     */
    Optional<Post> findById(int id);

    /**
     * Список объявлений по марке LIKE %key%
     * @param key key
     * @return список объявлений.
     */
    List<Post> findByLikeCarName(String key);

    /**
     * Список объявлений за последний день
     * @return список объявлений.
     */
    List<Post> findAllToday();

    /**
     * Список объявлений c фотографиями
     * @return список объявлений.
     */
    List<Post> findAllWithPhoto();
}

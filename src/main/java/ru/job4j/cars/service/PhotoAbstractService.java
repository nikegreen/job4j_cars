package ru.job4j.cars.service;

import ru.job4j.cars.model.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoAbstractService {
    /**
     * Сохранить в базе фотографию.
     * @param photo фотография.
     * @return фотография с id.
     */
    Photo create(Photo photo);

    /**
     * Обновить в базе фотографию.
     * @param photo фотография.
     */
    void update(Photo photo);

    /**
     * Удалить фотографию по id.
     * @param id ID фотографии.
     */
    void delete(int id);

    /**
     * Список фотографий отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.Photo>} список фотографий.
     */
    List<Photo> findAllOrderById();

    /**
     * Список id объявлений с фотографий отсортированных по id фотографии.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список фотографий.
     */
    List<Photo> findAllWherePost(int postId);

    /**
     * Найти фотографию по ID
     * @return фотографию.
     */
    Optional<Photo> findById(int id);
}

package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.repository.PhotoMemRepository;

import java.util.*;

/**
 * Сервис фотографий в ОЗУ
 */
@Service
@RequiredArgsConstructor
public class PhotoMemService implements PhotoAbstractService {
    private final PhotoMemRepository photos;

    /**
     * Сохранить в базе фотографию.
     * @param photo фотография.
     * @return фотография с id.
     */
    @Override
    public Photo create(Photo photo) {
        return photos.create(photo);
    }

    /**
     * Обновить в базе фотографию.
     * @param photo фотография.
     */
    @Override
    public void update(Photo photo) {
        photos.update(photo);
    }

    /**
     * Удалить фотографию по id.
     * @param id ID фотографии.
     */
    @Override
    public void delete(int id) {
        photos.delete(id);
    }

    /**
     * Список фотографий отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.Photo>} список фотографий.
     */
    @Override
    public List<Photo> findAllOrderById() {
        return photos.findAllOrderById();
    }

    /**
     * Список id объявлений с фотографий отсортированных по id фотографии.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список фотографий.
     */
    @Override
    public List<Photo> findAllWherePost(int postId) {
        return photos.findAllWherePost(postId);
    }

    /**
     * Найти фотографию по ID
     * @return фотографию.
     */
    @Override
    public Optional<Photo> findById(int id) {
        return photos.findById(id);
    }
}

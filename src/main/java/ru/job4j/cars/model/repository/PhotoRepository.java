package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.Photo;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 * Хранилище с информацией об фотографиях
 */
@AllArgsConstructor
public class PhotoRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе фотографию.
     * @param photo фотография.
     * @return фотография с id.
     */
    public Photo create(Photo photo) {
        return  crudRepository.create(photo);
    }

    /**
     * Обновить в базе фотографию.
     * @param photo фотография.
     */
    public void update(Photo photo) {
        crudRepository.update(photo);
    }

    /**
     * Удалить фотографию по id.
     * @param id ID фотографии.
     */
    public void delete(int id) {
        Photo photo = new Photo();
        photo.setId(id);
        crudRepository.delete(photo);
    }

    /**
     * Список фотографий отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.Photo>} список фотографий.
     */
    public List<Photo> findAllOrderById() {
        return crudRepository.findAll(Photo.class);
    }

    /**
     * Список id объявлений с фотографий отсортированных по id фотографии.
     * @param postId
     * @return {@link java.util.List<java.lang.Integer>} список фотографий.
     */
    public List<Photo> findAllWherePost(int postId) {
        return crudRepository.tx(session  -> session.createQuery(
                "from Photo where post_id = :fId")
                .setParameter("fId", postId)
                .list()
        );
    }

    /**
     * Найти фотографию по ID
     * @return фотографию.
     */
    public Optional<Photo> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, Photo.class));
    }
}

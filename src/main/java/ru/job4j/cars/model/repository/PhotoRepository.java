package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 * Хранилище с информацией об фотографиях
 */
@Repository
@AllArgsConstructor
public class PhotoRepository implements PhotoAbstractRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе фотографию.
     * @param photo фотография.
     * @return фотография с id.
     */
    @Override
    public Photo create(Photo photo) {
        return  crudRepository.create(photo);
    }

    /**
     * Обновить в базе фотографию.
     * @param photo фотография.
     */
    @Override
    public void update(Photo photo) {
        crudRepository.update(photo);
    }

    /**
     * Удалить фотографию по id.
     * @param id ID фотографии.
     */
    @Override
    public void delete(int id) {
        Photo photo = new Photo();
        photo.setId(id);
        crudRepository.delete(photo);
    }

    /**
     * Список фотографий отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.Photo>} список фотографий.
     */
    @Override
    public List<Photo> findAllOrderById() {
        return crudRepository.findAll(Photo.class);
    }

    /**
     * Список id объявлений с фотографий отсортированных по id фотографии.
     * @param postId
     * @return {@link java.util.List<java.lang.Integer>} список фотографий.
     */
    @Override
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
    @Override
    public Optional<Photo> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, Photo.class));
    }
}

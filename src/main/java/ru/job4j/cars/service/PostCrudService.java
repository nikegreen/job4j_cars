package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCrudService implements PostAbstractService {
    private final PostRepository posts;

    /**
     * Сохранить в базе объявление.
     * @param post объявление.
     * @return объявление с id.
     */
    @Override
    public Post create(Post post) {
        return posts.create(post);
    }

    /**
     * Обновить в базе объявление.
     * @param post объявление.
     */
    @Override
    public void update(Post post) {
        posts.update(post);
    }

    /**
     * Удалить объявление по id.
     * @param id ID объявления.
     */
    @Override
    public void delete(int id) {
        posts.delete(id);
    }

    /**
     * Список объявлений отсортированных по id.
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllOrderById() {
        return posts.findAllOrderById();
    }

    /**
     * Найти объявления по ID
     * @return объявление.
     */
    @Override
    public Optional<Post> findById(int id) {
        return posts.findById(id);
    }

    /**
     * Список объявлений по марке LIKE %key%
     * @param key key
     * @return список объявлений.
     */
    @Override
    public List<Post> findByLikeCarName(String key) {
        return posts.findByLikeCarName(key);
    }

    /**
     * Список объявлений за последний день
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllToday() {
        return posts.findAllToday();
    }

    /**
     * Список объявлений c фотографиями
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllWithPhoto() {
        return posts.findAllWithPhoto();
    }
}

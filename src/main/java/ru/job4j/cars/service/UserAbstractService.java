package ru.job4j.cars.service;

import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

public interface UserAbstractService {
    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    User create(User user);

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    void update(User user);

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    void delete(int userId);

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    List<User> findAllOrderById();

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    Optional<User> findById(int id);

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    List<User> findByLikeLogin(String key);

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    Optional<User> findByLogin(String login);
}

package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.User;
import ru.job4j.cars.model.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис пользователей в БД
 */
@Service
@RequiredArgsConstructor
public class UserCrudService implements UserAbstractService {
    private final UserRepository users;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public User create(User user) {
        return users.create(user);
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    @Override
    public void update(User user) {
        users.update(user);
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    @Override
    public void delete(int userId) {
        users.delete(userId);
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    @Override
    public List<User> findAllOrderById() {
        return users.findAllOrderById();
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    @Override
    public Optional<User> findById(int id) {
        return users.findById(id);
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return users.findByLikeLogin(key);
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return users.findByLogin(login);
    }
}

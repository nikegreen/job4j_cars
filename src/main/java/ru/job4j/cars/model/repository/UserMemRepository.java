package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище пользователей в ОЗУ
 */
@Repository
public class UserMemRepository implements UserAbstractRepository {
    private final Map<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    public UserMemRepository() {
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("1");
        User user = new User();
        user.setLogin("user");
        user.setPassword("1");
        create(admin);
        create(user);
    }

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public User create(User user) {
        user.setId(count.incrementAndGet());
        users.putIfAbsent(user.getId(), user);
        return  user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    @Override
    public void update(User user) {
        users.replace(user.getId(), user);
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    @Override
    public void delete(int userId) {
        if (users.remove(userId) != null) {
            count.decrementAndGet();
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    @Override
    public List<User> findAllOrderById() {
        return users.values().stream().sorted(Comparator.comparingInt(User::getId)).toList();
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.getOrDefault(id, null));
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return users.values().stream().filter(user -> user.getLogin().contains(key)).toList();
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return users.values().stream().filter(user -> user.getLogin().equals(login)).findFirst();
    }
}

package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище пользователей в БД
 */
@Repository
@AllArgsConstructor
public class UserRepository implements UserAbstractRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    @Override
    public User create(User user) {
        return  crudRepository.create(user);
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    @Override
    public void update(User user) {
        crudRepository.update(user);
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    @Override
    public void delete(int userId) {
        User user = new User();
        user.setId(userId);
        crudRepository.delete(user);
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    @Override
    public List<User> findAllOrderById() {
        return crudRepository.findAll(User.class);
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, User.class));
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    @Override
    public List<User> findByLikeLogin(String key) {
        return crudRepository.findByLikeLogin(User.class, key);
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(crudRepository.tx(session -> session.createQuery(
                        "from User as i where i.login = :fLogin order by id", User.class)
                .setParameter("fLogin", login).uniqueResult()));
    }
}
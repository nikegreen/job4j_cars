package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        User result = null;
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
                result = user;
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
        return result;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.createQuery(
                        "UPDATE User SET login = :fLogin, password = :fPassword WHERE id = :fId")
                        .setParameter("fLogin", user.getLogin())
                        .setParameter("fPassword", user.getPassword())
                        .setParameter("fId", user.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                session.createQuery(
                                "DELETE User WHERE id = :fId")
                        .setParameter("fId", userId)
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        List<User> result = new ArrayList<>();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query<User> query = session.createQuery("from User order by id", User.class);
                session.getTransaction().commit();
                result = query.list();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        Optional<User> result = Optional.empty();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query<User> query = session.createQuery("from User as i where i.id = :fId", User.class)
                        .setParameter("fId", id);
                session.getTransaction().commit();
                result = query.uniqueResultOptional();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
            session.close();
        }
        return result;
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        List<User> result = new  ArrayList<>();
        try (Session session = sf.openSession()) {
            try {
                session.beginTransaction();
                Query<User> query = session
                        .createQuery("from User as i where i.login like :fKey order by id", User.class)
                        .setParameter("fKey", '%' + key + '%');
                session.getTransaction().commit();
                result = query.list();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
            session.close();
        }
        return result;
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Optional<User> result = Optional.empty();
        try (Session session = sf.openSession()) {
            Query<User> query = session.createQuery("from User as i where i.login = :fLogin", User.class)
                    .setParameter("fLogin", login);
            result = query.uniqueResultOptional();
            session.close();
        }
        return result;
    }
}
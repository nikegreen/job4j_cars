package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.Function;

/**
 * Паттерн комманд для CRUD операций с БД
 */
@Repository
@RequiredArgsConstructor
public class CrudRepository {
    private final SessionFactory sf;

    /**
     * создать сущность в БД
     * @param model создаваемая сущность
     * @return сущность с новым
     * @param <T> если ошибка - null
     */
    public <T> T create(T model) {
        return tx(session -> {
            session.persist(model); return model;
        });
    }

    /**
     * Обновление
     * @param item сущность обновляет БД
     * @param <T> если ошибка - null
     */
    public <T> void update(T item) {
        tx(session -> {
            session.update(item); return null;
        });
    }

    /**
     * удаление
     * @param item - удаляемая сущность
     * @param <T> тип.
     *           Возврат:
     *           если удалено, то null
     */
    public <T> void delete(T item) {
        tx(session -> {
            session.delete(item); return null;
        });
    }

    /**
     * Поиск всех записей
     * @param cl - класс сущности
     * @return список сущностей
     * @param <T> тип
     */
    public <T> List<T> findAll(Class<T> cl) {
        return tx(session -> (List<T>) session.createQuery("from " + cl.getName(), cl).list());
    }

    public <T> List<T> findByLikeLogin(Class<T> cl, String key) {
        return tx(session -> (List<T>) session.createQuery("from " + cl.getName()
                        +  " i where i.login like :fKey order by id", cl)
                .setParameter("fKey", '%' + key + '%').list());
    }

    /**
     * Поиск по идентификатору сущности
     * @param id - идентификатор сущности
     * @param cl - класс сущности
     * @return сущность, если не найдена, то null
     * @param <T> тип сущности
     */
    public <T> T findById(Integer id, Class<T> cl) {
        return tx(session -> (T) session.get(cl, id));
    }

    /**
     * Транзакция.
     * 1. Открывается сессия,
     * 2. начинается транзакция,
     * 3. выполняется функция,
     * 4. коммит транзакции
     * 5. если ошибка - роллбэк.
     * @param function - функция
     * @return результат типа
     * @param <T> тип
     */
    public <T> T tx(Function<Session, T> function) {
        T result = null;
        Transaction tx = null;
        Session session = sf.openSession();
        try {
            tx = session.beginTransaction();
            T res = (T) function.apply(session);
            tx.commit();
            result = res;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    /**
     * Транзакция с detach результатом.
     * 1. Открывается сессия,
     * 2. начинается транзакция,
     * 3. выполняется функция,
     * 4. коммит транзакции
     * 5. если ошибка - роллбэк.
     * @param function - функция
     * @return результат типа
     * @param <T> тип
     */
    public <T> T txStateless(Function<StatelessSession, T> function) {
        T result = null;
        Transaction tx = null;
        StatelessSession session = sf.openStatelessSession();
        try {
            tx = session.beginTransaction();
            result = function.apply(session);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }
}

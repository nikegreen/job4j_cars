package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class TemplateRepository {
    private final SessionFactory sf;
    public <T> T create(T model) {
        return tx(session -> {session.persist(model); return model; });
    }

    public <T> void update(T item) {
        tx(session -> {session.update(item); return null;});
    }

    public <T> void delete(T item) {
        tx(session -> {session.delete(item); return null;});
    }

    public <T> List<T> findAll(Class<T> cl) {
        return tx(session -> (List<T>) session.createQuery("from " + cl.getClass()).list());
    }

    public <T> T findById(Integer id, Class<T> cl) {
        return tx(session -> (T) session.get(cl.getClass(), id));
    }

    private  <T> T tx(Function<Session,T> function) {
        T result = null;
        Transaction tx = null;
        try (Session session = sf.openSession()) {
            try {
                tx = session.beginTransaction();
                result = function.apply(session);
                tx.commit();
            } catch (Exception e) {
                if (tx == null) {
                    tx = session.getTransaction();
                }
                if (tx.isActive()) {
                    tx.rollback();
                }
            }
            session.close();
        }
        return result;
    }
}

package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import ru.job4j.cars.model.Post;
import java.util.List;

@RequiredArgsConstructor
public class PostRepository {
    private final TemplateRepository repository;

    /**
     * Сохранить в базе объявлений.
     * @param post объявление.
     * @return объявление с id.
     */
    public Post create(Post post) {
        return repository.create(post);
    }

    /**
     * Обновить в базе объявлений.
     * @param post объявление.
     */
    public void update(Post post) {
        repository.update(post);
    }

    /**
     * Удалить объявление по id.
     * @param id идентификатор объявления
     */
    public void delete(int id) {
        Post post = new Post();
        post.setId(id);
        repository.delete(post);
    }

    /**
     * Список объявлений отсортированных по id.
     * @return {List<Post>} список объявлений.
     */
    public List<Post> findAllOrderById() {
        return repository.findAll(Post.class);
        /*
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
         */
    }
}

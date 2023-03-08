package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 * Хранилище с информацией об объявлениях в БД
 */
@Repository
@AllArgsConstructor
public class PostRepository {
    /**
     * HQL findAllOrderById()
     */
    public static final String FIND_ALL_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners";
    public static final String FIND_ALL_FETCH_PRICE_PARTIPATIES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts";
    public static final String FIND_ALL_FETCH_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";
    public static final String FIND_ALL_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts";

    /**
     * HQL findById()
     */
    public static final String FIND_BY_ID_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " where i.id = :fId";
    public static final String FIND_BY_ID_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts";
    public static final String FIND_BY_ID_FETCH_PARTICIPATES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts";
    public static final String FIND_BY_ID_FETCH_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";

    /**
     * HQL findByLikeCarName(String key)
     */
    public static final String FIND_ALL_BY_NAME_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " where c.name like :fKey";
    public static final String FIND_ALL_BY_NAME_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts";
    public static final String FIND_ALL_BY_NAME_FETCH_PARTICIPATES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts";
    public static final String FIND_ALL_BY_NAME_FETCH_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";

    /**
     * HQL findAllToday()
     */
    public static final String FIND_ALL_TODAY_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " where i.created >= :fDay order by i.id";
    public static final String FIND_ALL_TODAY_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts order by i.id";
    public static final String FIND_ALL_TODAY_FETCH_PARTICIPATES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts order by i.id";
    public static final String FIND_ALL_TODAY_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";

    /**
     * HQL findAllWithPhoto()
     */
    public static final String FIND_ALL_WITH_PHOTO_FETCH_PHOTO =
            "from Post i left join fetch i.photos"
            + " where i.photos.size>0";
    public static final String FIND_ALL_WITH_PHOTO_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " where i in :fPosts";
    public static final String FIND_ALL_WITH_PHOTO_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts";
    public static final String FIND_ALL_WITH_PHOTO_FETCH_PARTICIPATES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts order by i.id";

    /**
     * Hibernate CRUD хранилище
     */
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе объявление.
     * @param post объявление.
     * @return объявление с id.
     */
    public Post create(Post post) {
        return  crudRepository.create(post);
    }

    /**
     * Обновить в базе объявление.
     * @param post объявление.
     */
    public void update(Post post) {
        crudRepository.update(post);
    }

    /**
     * Удалить объявление по id.
     * @param id ID объявления.
     */
    public void delete(int id) {
        crudRepository.tx(session -> {
            Post post = session.find(Post.class, id);
            session.delete(post);
            return true;
        });
    }

    /**
     * Список объявлений отсортированных по id.
     * @return список объявлений.
     */
    public List<Post> findAllOrderById() {
        return crudRepository.tx(
            session -> {
                List<Post> posts = session.createQuery(
                        FIND_ALL_FETCH_CAR, Post.class).list();
                posts = session.createQuery(
                        FIND_ALL_FETCH_PRICE_HISTORIES, Post.class)
                        .setParameter("fPosts", posts)
                        .list();
                posts = session.createQuery(
                        FIND_ALL_FETCH_PRICE_PARTIPATIES, Post.class)
                        .setParameter("fPosts", posts)
                        .list();
                posts = session.createQuery(
                        FIND_ALL_FETCH_PHOTO, Post.class)
                        .setParameter("fPosts", posts)
                        .list();
                return posts;
            }
        );
    }

    /**
     * Найти объявления по ID
     * @return объявление.
     */
    public Optional<Post> findById(int id) {
        return crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(
                            FIND_BY_ID_FETCH_CAR, Post.class)
                            .setParameter("fId", id)
                            .list();
                    if (posts.size() == 0) {
                        return Optional.empty();
                    }
                    posts = session.createQuery(
                            FIND_BY_ID_FETCH_PRICE_HISTORIES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_BY_ID_FETCH_PARTICIPATES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_BY_ID_FETCH_PHOTO, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    return Optional.ofNullable(posts.get(0));
                }
        );
    }

    /**
     * Список объявлений по марке LIKE %key%
     * @param key key
     * @return список объявлений.
     */
    public List<Post> findByLikeCarName(String key) {
        return crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(
                            FIND_ALL_BY_NAME_FETCH_CAR, Post.class)
                            .setParameter("fKey", '%' + key + '%')
                            .list();
                    if (posts.size() == 0) {
                        return posts;
                    }
                    posts = session.createQuery(
                            FIND_ALL_BY_NAME_FETCH_PRICE_HISTORIES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_ALL_BY_NAME_FETCH_PARTICIPATES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_ALL_BY_NAME_FETCH_PHOTO, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    return posts;
                }
        );
    }

    /**
     * Список объявлений за последний день
     * @return список объявлений.
     */
    public List<Post> findAllToday() {
        return crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(
                            FIND_ALL_TODAY_FETCH_CAR, Post.class)
                        .setParameter(
                                "fDay",
                                LocalDateTime.now().toLocalDate().atTime(0, 0))
                        .list();
                    if (posts.size() == 0) {
                        return posts;
                    }
                    posts = session.createQuery(
                            FIND_ALL_TODAY_FETCH_PRICE_HISTORIES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_ALL_TODAY_FETCH_PARTICIPATES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_ALL_TODAY_PHOTO, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    return posts;
                }
        );
    }

    /**
     * Список объявлений c фотографиями
     * @return список объявлений.
     */
    public List<Post> findAllWithPhoto() {
        return crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(
                            FIND_ALL_WITH_PHOTO_FETCH_PHOTO, Post.class)
                            .list();
                    if (posts.size() == 0) {
                        return posts;
                    }
                    posts = session.createQuery(
                            FIND_ALL_WITH_PHOTO_FETCH_CAR, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_ALL_WITH_PHOTO_FETCH_PRICE_HISTORIES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    posts = session.createQuery(
                            FIND_ALL_WITH_PHOTO_FETCH_PARTICIPATES, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    return posts;
                }
        );
    }
}

package ru.job4j.cars.model.repository;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

/**
 * Хранилище с информацией об объявлениях
 */
@AllArgsConstructor
public class PostRepository {
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
        Post post = new Post();
        post.setId(id);
        crudRepository.delete(post);
    }

    /**
     * Список объявлений отсортированных по id.
     * @return список объявлений.
     */
    public List<Post> findAllOrderById() {
        return crudRepository.tx(
                session -> {
                    List<Post> res = session.createQuery(
                                "from Post i fetch all properties"
//                "from Post i left join fetch i.car"
////                        + " left join fetch h.priceHistories"
////                        + " left join fetch i.participates i"
////                        + " left join fetch i.car"
////                        + " left join fetch i.photos"
                        + " order by i.id", Post.class)
                .list();
                    List<Post> res1 = new ArrayList<>();
                    res.forEach(
                            post -> {
                                Post post1 = new Post();
                                post1.setId(post.getId());
                                post1.setText(post.getText());
                                post1.setCreated(post.getCreated());
                                post1.setCar(post.getCar());
                                post1.setUser(post.getUser());
                                post1.setPhotos(post.getPhotos());
                                post1.setParticipates(post.getParticipates());
                                post1.setPriceHistories(post.getPriceHistories());
                                res1.add(post1);
                    });
                    return res1;
                }
        );
        //return crudRepository.findAll(Post.class);
    }

    /**
     * Найти объявления по ID
     * @return объявление.
     */
    public Optional<Post> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, Post.class));
    }

    /**
     * Список объявлений по марке LIKE %key%
     * @param key key
     * @return список объявлений.
     */
    public List<Post> findByLikeCarName(String key) {
        return crudRepository.tx(session -> session.createQuery("from " + Post.class
                        +  " i where i.car.name like :fKey order by id")
                .setParameter("fKey", '%' + key + '%').list());
    }

    /**
     * Список объявлений за последний день
     * @return список объявлений.
     */
    public List<Post> findAllToday() {
        return crudRepository.tx(
                session -> session.createQuery(
                        "from " + Post.class
                        +  " i where i.created >= :fDay order by id")
                .setParameter(
                        "fDay",
                        LocalDateTime.now().toLocalDate().atTime(0,0)
                ).list()
        );
    }

    /**
     * Список объявлений c фотографиями
     * @return список объявлений.
     */
    public List<Post> findAllWithPhoto() {
        return crudRepository.tx(
                session -> session.createQuery(
                        "SELECT DISTINCT p FROM Post p INNER JOIN Photo ON p.id = post_id"
                ).list()
        );
    }
}

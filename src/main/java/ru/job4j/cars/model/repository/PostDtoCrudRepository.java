package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDtoCrudRepository implements PostDtoAbstractRepository {
    private final CrudRepository crudRepository;

    /**
     * Список всех объявлений (для отображения)
     * @return список всех объявлений (для отображения).
     */
    @Override
    public List<PostDto> findAll() {
        return crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(
                            "select distinct i from Post i left join fetch i.car c"
                                    + " left join fetch c.owners"
                                    + " order by i.id", Post.class).list();
                    posts = session.createQuery(
                                    "select distinct i from Post i left join fetch i.priceHistories"
                                            + " where i in :fPosts order by i.id", Post.class)
                            .setParameter("fPosts", posts)
                            .list();
//                    posts = session.createQuery(
//                                    "select distinct i from Post i left join fetch i.participates"
//                                            + " where i in :fPosts order by i.id", Post.class)
//                            .setParameter("fPosts", posts)
//                            .list();
                    posts = session.createQuery(
                                    "select distinct i from Post i left join fetch i.photos"
                                            + " where i in :fPosts order by i.id", Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    List<PostDto> result = new ArrayList<>();
                    posts.forEach(
                            post -> result.add(PostDto.fromPost(post))
                    );
                    return result;
                }
        );
    }

    /**
     * Найти объявления по ID
     * @param id - идентификатор объявления
     * @return объявление (для отображения).
     */
    @Override
    public Optional<PostDto> findById(int id) {
        return crudRepository.tx(
                session -> {
                            List<Post> posts = session.createQuery(
                                            "select distinct i from Post i left join fetch i.car c"
                                                    + " left join fetch c.owners"
                                                    + " where i.id = :fId", Post.class)
                                    .setParameter("fId", id)
                                    .list();
                            if (posts.size() == 0) {
                                return Optional.empty();
                            }
                            posts = session.createQuery(
                                            "select distinct i from Post i left join fetch i.priceHistories"
                                                    + " where i in :fPosts order by i.id", Post.class)
                                    .setParameter("fPosts", posts)
                                    .list();
                            posts = session.createQuery(
                                            "select distinct i from Post i left join fetch i.participates"
                                                    + " where i in :fPosts order by i.id", Post.class)
                                    .setParameter("fPosts", posts)
                                    .list();
                            posts = session.createQuery(
                                            "select distinct i from Post i left join fetch i.photos"
                                                    + " where i in :fPosts order by i.id", Post.class)
                                    .setParameter("fPosts", posts)
                                    .list();
                            return Optional.ofNullable(
                                    PostDto.fromPost(posts.size() > 0 ? posts.get(0) : null)
                            );
                });
    }
}

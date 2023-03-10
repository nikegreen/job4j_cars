package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDtoCrudRepository implements PostDtoAbstractRepository {
    /**
     * HQL findAll()
     */
    public static final String FIND_ALL_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " order by i.id";
    public static final String FIND_ALL_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts order by i.id";
    public static final String FIND_ALL_FETCH_PARTICIPATES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts order by i.id";
    public static final String FIND_ALL_FETCH_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";

    /**
     * HQL findById(int id)
     */
    public static final String FIND_BY_ID_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " where i.id = :fId";
    public static final String FIND_BY_ID_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " where i in :fPosts order by i.id";
    public static final String FIND_BY_ID_FETCH_PARTICIPATES =
            "select distinct i from Post i left join fetch i.participates"
            + " where i in :fPosts order by i.id";
    public static final String FIND_BY_ID_FETCH_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";

    /**
     * HQL findAllByFilter(PostDto filter)
     */
    public static final String FIND_ALL_FILTER_FETCH_PRICE_HISTORIES =
            "select distinct i from Post i left join fetch i.priceHistories"
            + " order by i.id";
    public static final String FIND_ALL_FILTER_FETCH_CAR =
            "select distinct i from Post i left join fetch i.car c"
            + " left join fetch c.owners"
            + " where (i in :fPosts)";
    public static final String FIND_ALL_FILTER_FETCH_PHOTO =
            "select distinct i from Post i left join fetch i.photos"
            + " where i in :fPosts order by i.id";
    private final CrudRepository crudRepository;

    /**
     * ???????????? ???????? ???????????????????? (?????? ??????????????????????)
     * @return ???????????? ???????? ???????????????????? (?????? ??????????????????????).
     */
    @Override
    public List<PostDto> findAll() {
        List<PostDto> result = crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(FIND_ALL_FETCH_CAR, Post.class)
                            .getResultList();
                    posts = session.createQuery(
                            FIND_ALL_FETCH_PRICE_HISTORIES, Post.class)
                            .setParameter("fPosts", posts)
                            .getResultList();
                    posts = session.createQuery(
                            FIND_ALL_FETCH_PARTICIPATES, Post.class)
                            .setParameter("fPosts", posts)
                            .getResultList();
                    posts = (List<Post>) session.createQuery(
                            FIND_ALL_FETCH_PHOTO, Post.class)
                            .setParameter("fPosts", posts)
                            .getResultList();
                    return posts.stream()
                            .map(post -> PostDto.fromPost(post))
                            .toList();
                }
        );
        return result;
    }

    /**
     * ?????????? ???????????????????? ???? ID
     * @param id - ?????????????????????????? ????????????????????
     * @return ???????????????????? (?????? ??????????????????????).
     */
    @Override
    public Optional<PostDto> findById(int id) {
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
                            return Optional.ofNullable(
                                    PostDto.fromPost(posts.size() > 0 ? posts.get(0) : null)
                            );
                });
    }

    /**
     * ???????????? ???????? ???????????????????? ???? ?????????????? (?????? ??????????????????????)
     * @param filter - ?????????????????? ?????????????? ??????: {@link ru.job4j.cars.model.UserDto}
     *               filter.statusId     - ???????????????????? ???? ??????????????. ???????? = 0, ???? ?????? ????????????????????.
     *               filter.car.marc.id  - ???????????????????? ???? ??????????. ???????? = 0, ???? ?????? ????????????????????.
     *               filter.car.model.id - ???????????????????? ???? ????????????. ???????? = 0, ???? ?????? ????????????????????.
     *               filter.car.bodyId   - ???????????????????? ???? ????????????. ???????? = 0, ???? ?????? ????????????????????.
     *               filter.price        - ???????????????????? ???? ???????? (???????? ???????? ?????????? ?????? ???????? ?? ??????????????).
     * @return ???????????? ???????? ?????????????????????????????? ???????????????????? (?????? ??????????????????????).
     */
    @Override
    public List<PostDto> findAllByFilter(PostDto filter) {
        return crudRepository.tx(
                session -> {
                    List<Post> posts = session.createQuery(
                            FIND_ALL_FILTER_FETCH_PRICE_HISTORIES, Post.class)
                            .list();
                    String sql = FIND_ALL_FILTER_FETCH_CAR;
                    if (filter.getCar() != null) {
                        if (filter.getCar().getMarc() != null
                                && filter.getCar().getMarc().getId() != 0) {
                            sql += " and (c.marc.id=:fMarcId)";
                        }
                        if (filter.getCar().getModel() != null
                                && filter.getCar().getModel().getId() != 0) {
                            sql += " and (c.model.id=:fModelId)";
                        }
                        if (filter.getCar().getModel().getBodyId() != 0) {
                            sql += " and (c.bodyId=:fBodyId)";
                        }
                        if (filter.getCar().getEngine() != null
                                && filter.getCar().getEngine().getId() != 0) {
                            sql += " and (c.engine.id=:fEngineId)";
                        }
                        if (filter.getStatusId() != 0) {
                            sql += " and (i.statusId=:fStatusId)";
                        }
                    }
                    sql += " order by i.id";
                    Query<Post> query = session.createQuery(sql, Post.class)
                            .setParameter("fPosts", posts);
                    if (filter.getCar() != null) {
                        if (filter.getCar().getMarc() != null
                                && filter.getCar().getMarc().getId() != 0) {
                            query = query.setParameter(
                                    "fMarcId", filter.getCar().getMarc().getId()
                            );
                        }
                        if (filter.getCar().getModel() != null
                                && filter.getCar().getModel().getId() != 0) {
                            query = query.setParameter(
                                    "fModelId", filter.getCar().getModel().getId()
                            );
                        }
                        if (filter.getCar().getModel().getBodyId() != 0) {
                            query = query.setParameter(
                                    "fBodyId",
                                    filter.getCar().getModel().getBodyId()
                            );
                        }
                        if (filter.getCar().getEngine() != null
                                && filter.getCar().getEngine().getId() != 0) {
                            query = query.setParameter(
                                    "fEngineId", filter.getCar().getEngine().getId()
                            );
                        }
                    }
                    if (filter.getStatusId() != 0) {
                        query = query.setParameter("fStatusId", filter.getStatusId());
                    }
                    posts = query.list();
                    posts = session.createQuery(
                            FIND_ALL_FILTER_FETCH_PHOTO, Post.class)
                            .setParameter("fPosts", posts)
                            .list();
                    List<PostDto> result = new ArrayList<>();
                    posts.forEach(
                            post -> {
                                PostDto postDto = PostDto.fromPost(post);
                                if (postDto.getPrice() < filter.getPrice()) {
                                    result.add(postDto);
                                }
                            }
                    );
                    return result;
                }
        );
    }
}

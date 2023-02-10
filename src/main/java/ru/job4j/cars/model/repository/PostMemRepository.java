package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMemRepository implements  PostAbstractRepository {
    private final AtomicInteger size = new AtomicInteger();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    public PostMemRepository() {
        Post post = new Post();
        post.setText("text1");
        post.setPhotos(List.of());
        post.setCreated(LocalDateTime.now());
        Car car = new Car();
        car.setId(1);
        car.setOwners(Set.of());
        Engine engine = new Engine();
        engine.setId(1);
        engine.setName("бензин");
        car.setEngine(engine);
        car.setName("name");
        CarMarc carMarc = new CarMarc();
        carMarc.setId(1);
        carMarc.setName("marc1");
        car.setMarc(carMarc);
        CarModel carModel = new CarModel(1, "model_name1", 1, 1);
        car.setModel(carModel);
        car.setBodyId(1);
        post.setCar(car);
        User user = new User();
        user.setId(1);
        user.setLogin("admin");
        user.setPassword("1");
        post.setUser(user);
        post.setParticipates(List.of());
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setId(1);
        priceHistory.setCreated(LocalDateTime.now());
        priceHistory.setAfter(1000000);
        priceHistory.setBefore(800000);
        post.setPriceHistories(List.of(priceHistory));
        create(post);

        post = new Post();
        post.setText("text2");
        post.setPhotos(List.of());
        post.setCreated(LocalDateTime.now());
        car = new Car();
        car.setId(2);
        car.setOwners(Set.of());
        engine = new Engine();
        engine.setId(2);
        engine.setName("дизель");
        car.setEngine(engine);
        car.setName("name2");
        carMarc = new CarMarc();
        carMarc.setId(2);
        carMarc.setName("marc2");
        car.setMarc(carMarc);
        carModel = new CarModel(2, "model2_name1", 2, 1);
        car.setModel(carModel);
        car.setBodyId(1);
        post.setCar(car);
        user = new User();
        user.setId(1);
        user.setLogin("admin");
        user.setPassword("1");
        post.setUser(user);
        post.setParticipates(List.of());
        priceHistory = new PriceHistory();
        priceHistory.setId(2);
        priceHistory.setCreated(LocalDateTime.now());
        priceHistory.setAfter(1000000);
        priceHistory.setBefore(800000);
        post.setPriceHistories(List.of(priceHistory));
        create(post);
    }

    /**
     * Сохранить в базе объявление.
     * @param post объявление.
     * @return объявление с id.
     */
    @Override
    public Post create(Post post) {
        post.setId(size.incrementAndGet());
        posts.putIfAbsent(post.getId(), post);
        return post;
    }

    /**
     * Обновить в базе объявление.
     * @param post объявление.
     */
    @Override
    public void update(Post post) {
        posts.replace(post.getId(), post);
    }

    /**
     * Удалить объявление по id.
     * @param id ID объявления.
     */
    @Override
    public void delete(int id) {
        posts.remove(id);
    }

    /**
     * Список объявлений отсортированных по id.
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllOrderById() {
        return posts.values().stream().toList();
    }

    /**
     * Найти объявления по ID
     * @return объявление.
     */
    @Override
    public Optional<Post> findById(int id) {
        return Optional.ofNullable(posts.getOrDefault(id, null));
    }

    /**
     * Список объявлений по марке LIKE %key%
     * @param key key
     * @return список объявлений.
     */
    @Override
    public List<Post> findByLikeCarName(String key) {
        return findAllOrderById().stream()
                .filter(post -> post.getCar().getName().contains(key))
                .toList();
    }

    /**
     * Список объявлений за последний день
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllToday() {
        LocalDateTime ldt = LocalDateTime.now()
                .toLocalDate()
                .atTime(0,0,0);
        return findAllOrderById().stream()
                .filter(post -> post.getCreated().isAfter(ldt))
                .toList();
    }

    /**
     * Список объявлений c фотографиями
     * @return список объявлений.
     */
    @Override
    public List<Post> findAllWithPhoto() {
        return findAllOrderById().stream()
                .filter(post -> post.getPhotos().size() > 0)
                .toList();
    }
}

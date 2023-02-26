package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище с информацией об объявлениях в ОЗУ
 */
@Repository
public class PostMemRepository implements  PostAbstractRepository {
    private final AtomicInteger size = new AtomicInteger();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final CarMarcMemRepository marcs;
    private final CarModelMemRepository models;

    public PostMemRepository(
            CarMarcMemRepository marcs,
            CarModelMemRepository models,
            UserMemRepository users
        ) {
        this.marcs = marcs;
        this.models = models;

        User user = users.findById(1).orElse(null);
        User user2 = users.findById(2).orElse(null);

        Driver driver = new Driver();
        driver.setId(user.getId());
        driver.setName(user.getLogin());
        driver.setUser(user);

        Driver driver2 = new Driver();
        driver2.setId(user2.getId());
        driver2.setName(user2.getLogin());
        driver2.setUser(user2);

        Engine engine = new Engine();
        engine.setId(1);
        engine.setName("бензин");

        createPost("A6", user, driver, engine);
        createPost("X5", user, driver, engine);
        createPost("X6", user2, driver2, engine);
        createPost("Focus", user2, driver2, engine);
        createPost("Accent", user2, driver2, engine);
        createPost("Santa Fe", user2, driver2, engine);
        createPost("Rio", user2, driver2, engine);
        createPost("E-Класс", user2, driver2, engine);
        createPost("Pajero", user2, driver2, engine);
        createPost("Pathfinder", user2, driver2, engine);
        createPost("Astra", user2, driver2, engine);
        createPost("Duster", user, driver, engine);
        createPost("Outback", user, driver, engine);
        createPost("Land Cruiser", user, driver, engine);
        createPost("RAV4", user2, driver2, engine);
    }

    private void createPost(String modelName, User user2, Driver driver, Engine engine) {
        Post post = new Post();
        post.setText("");
        post.setPhotos(List.of());
        post.setParticipates(List.of());
        post.setCreated(LocalDateTime.now());

        CarModel carModel = models.findByName(modelName).orElse(null);
        CarMarc carMarc =  marcs.findById(carModel.getMarcId()).orElse(null);

        Car car = new Car();
        car.setId(1);
        car.setOwners(Set.of(driver));
        car.setName("car " + carMarc.getName() + " " +  modelName);
        car.setModel(carModel);
        car.setMarc(carMarc);
        car.setEngine(engine);

        post.setCar(car);
        post.setUser(user2);
        post.setParticipates(List.of());
        create(post);
        post.setText("post " + post.getId() + " " + carMarc.getName() + " " + modelName);
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
                .atTime(0, 0, 0);
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

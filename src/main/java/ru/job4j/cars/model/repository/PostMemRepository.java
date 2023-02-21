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

    public PostMemRepository(
            CarMarcMemRepository marcs,
            CarModelMemRepository models,
//            PriceHistoryMemRepository histories,
            UserMemRepository users
        ) {
        User user = users.findById(1).orElse(null);
        User user2 = users.findById(2).orElse(null);

        //post1
        Post post = new Post();
        post.setText("text1");
        post.setPhotos(List.of());
        post.setParticipates(List.of());
        post.setCreated(LocalDateTime.now());
        Driver driver = new Driver();
        driver.setId(user.getId());
        driver.setName(user.getLogin());
        driver.setUser(user);
        Car car = new Car();
        car.setId(1);
        car.setOwners(Set.of(driver));
        car.setName("name");
        CarModel carModel = models.findById(1).orElse(null);
        car.setModel(carModel);
        CarMarc carMarc =  marcs.findById(carModel.getMarcId()).orElse(null);
        car.setMarc(carMarc);
        car.setBodyId(carModel.getBodyId());
        Engine engine = new Engine();
        engine.setId(1);
        engine.setName("бензин");
        car.setEngine(engine);
        post.setCar(car);
        post.setUser(user);
        post.setParticipates(List.of());
        create(post);

        //post2
        post = new Post();
        post.setText("text2");
        post.setPhotos(List.of());
        post.setParticipates(List.of());
        post.setCreated(LocalDateTime.now());
        car = new Car();
        car.setId(1);
        car.setOwners(Set.of(driver));
        car.setName("name2");
        carModel = models.findById(2).orElse(null);
        car.setModel(carModel);
        carMarc =  marcs.findById(carModel.getMarcId()).orElse(null);
        car.setMarc(carMarc);
        car.setBodyId(carModel.getBodyId());
        car.setEngine(engine);
        post.setCar(car);
        post.setUser(user);
        post.setParticipates(List.of());
        create(post);

        //post3
        post = new Post();
        post.setText("text3");
        post.setPhotos(List.of());
        post.setParticipates(List.of());
        post.setCreated(LocalDateTime.now());
        driver.setId(user2.getId());
        driver.setName(user2.getLogin());
        driver.setUser(user2);
        car = new Car();
        car.setId(1);
        car.setOwners(Set.of(driver));
        car.setName("name2");
        carModel = models.findById(120).orElse(null);
        car.setModel(carModel);
        carMarc =  marcs.findById(carModel.getMarcId()).orElse(null);
        car.setMarc(carMarc);
        car.setBodyId(carModel.getBodyId());
        car.setEngine(engine);
        post.setCar(car);
        post.setUser(user2);
        post.setParticipates(List.of());
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

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

    public PostMemRepository(CarMarcMemRepository marcs) {
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
        CarMarc carMarc =  marcs.findById(17).orElse(null);
        car.setMarc(carMarc);
        CarModel carModel = new CarModel(1, "model_name1", carMarc.getId(), 1);
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
        Photo photo1 = new Photo();
        photo1.setId(1);
        photo1.setName("photo1");
        photo1.setFileName("i1.png");
        Photo photo2 = new Photo();
        photo2.setId(2);
        photo2.setName("photo2");
        photo2.setFileName("i2.jpg");
        post.setPhotos(List.of(photo1, photo2));
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

        post = new Post();
        post.setText("text3");
        photo1 = new Photo();
        photo1.setId(3);
        photo1.setName("photo3");
        photo1.setFileName("i3.png");
        photo2 = new Photo();
        photo2.setId(4);
        photo2.setName("photo4");
        photo2.setFileName("i4.jpg");
        Photo photo3 = new Photo();
        photo3.setId(5);
        photo3.setName("photo5");
        photo3.setFileName("i5.jpg");
        Photo photo4 = new Photo();
        photo4.setId(6);
        photo4.setName("photo6");
        photo4.setFileName("i4.jpg");
        post.setPhotos(List.of(photo1, photo2, photo3, photo4));
        post.setCreated(LocalDateTime.now());
        car = new Car();
        car.setId(3);
        car.setOwners(Set.of());
        engine = new Engine();
        engine.setId(2);
        engine.setName("дизель");
        car.setEngine(engine);
        car.setName("name3");
        carMarc = new CarMarc();
        carMarc.setId(3);
        carMarc.setName("marc3");
        car.setMarc(carMarc);
        carModel = new CarModel(3, "model3_name1", 3, 2);
        car.setModel(carModel);
        car.setBodyId(2);
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
        priceHistory.setAfter(1300000);
        priceHistory.setBefore(1800000);
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

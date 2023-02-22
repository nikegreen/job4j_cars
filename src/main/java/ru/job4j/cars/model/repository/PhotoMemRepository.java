package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.PostMemService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PhotoMemRepository implements PhotoAbstractRepository {
    private final PostMemRepository posts;
    private final Map<Integer, Photo> photoNoOwner = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    public PhotoMemRepository(PostMemRepository posts) {
        this.posts = posts;

        Post post = posts.findByLikeCarName("Audi").get(0);
        this.create(new Photo(0, "photo 1 Audi A6", "f1.jpg", post));
        this.create(new Photo(0, "photo 2 Audi A6", "f2.jpg", post));
        this.create(new Photo(0, "photo 3 Audi A6", "f3.jpg", post));
        this.create(new Photo(0, "photo 4 Audi A6", "f4.jpg", post));
        this.create(new Photo(0, "photo 5 Audi A6", "f5.jpg", post));
        this.create(new Photo(0, "photo 6 Audi A6", "f6.jpg", post));
        this.create(new Photo(0, "photo 7 Audi A6", "f7.jpg", post));
        this.create(new Photo(0, "photo 8 Audi A6", "f8.jpg", post));

        post = posts.findByLikeCarName("BMW X5").get(0);
        this.create(new Photo(0, "photo 1 BMW X5", "f9.jpg", post));
        this.create(new Photo(0, "photo 2 BMW X5", "f10.jpg", post));
        this.create(new Photo(0, "photo 3 BMW X5", "f11.jpg", post));
        this.create(new Photo(0, "photo 4 BMW X5", "f12.jpg", post));
        this.create(new Photo(0, "photo 5 BMW X5", "f13.jpg", post));
        this.create(new Photo(0, "photo 6 BMW X5", "f14.jpg", post));
        this.create(new Photo(0, "photo 7 BMW X5", "f15.jpg", post));
        this.create(new Photo(0, "photo 8 BMW X5", "f16.jpg", post));

        post = posts.findByLikeCarName("BMW X6").get(0);
        this.create(new Photo(0, "photo 1 BMW X6", "f17.jpg", post));
        this.create(new Photo(0, "photo 2 BMW X6", "f18.jpg", post));
        this.create(new Photo(0, "photo 3 BMW X6", "f19.jpg", post));
        this.create(new Photo(0, "photo 4 BMW X6", "f20.jpg", post));
        this.create(new Photo(0, "photo 5 BMW X6", "f21.jpg", post));
        this.create(new Photo(0, "photo 6 BMW X6", "f22.jpg", post));
        this.create(new Photo(0, "photo 7 BMW X6", "f23.jpg", post));

        post = posts.findByLikeCarName("Ford Focus").get(0);
        this.create(new Photo(0, "photo 1 Ford Focus", "f24.jpg", post));
        this.create(new Photo(0, "photo 2 Ford Focus", "f25.jpg", post));
        this.create(new Photo(0, "photo 3 Ford Focus", "f26.jpg", post));
        this.create(new Photo(0, "photo 4 Ford Focus", "f27.jpg", post));
        this.create(new Photo(0, "photo 5 Ford Focus", "f28.jpg", post));
        this.create(new Photo(0, "photo 6 Ford Focus", "f29.jpg", post));
        this.create(new Photo(0, "photo 7 Ford Focus", "f30.jpg", post));
        this.create(new Photo(0, "photo 8 Ford Focus", "f31.jpg", post));

        post = posts.findByLikeCarName("Hyundai Accent").get(0);
        this.create(new Photo(0, "photo 1 Hyundai Accent", "f32.jpg", post));
        this.create(new Photo(0, "photo 2 Hyundai Accent", "f33.jpg", post));
        this.create(new Photo(0, "photo 3 Hyundai Accent", "f34.jpg", post));
        this.create(new Photo(0, "photo 4 Hyundai Accent", "f35.jpg", post));

        post = posts.findByLikeCarName("Hyundai Santa Fe").get(0);
        this.create(new Photo(0, "photo 1 Hyundai Santa Fe", "f36.jpg", post));
        this.create(new Photo(0, "photo 2 Hyundai Santa Fe", "f37.jpg", post));
        this.create(new Photo(0, "photo 3 Hyundai Santa Fe", "f38.jpg", post));
        this.create(new Photo(0, "photo 4 Hyundai Santa Fe", "f39.jpg", post));

        post = posts.findByLikeCarName("Kia Rio").get(0);
        this.create(new Photo(0, "photo 1 Kia Rio", "f40.jpg", post));
        this.create(new Photo(0, "photo 2 Kia Rio", "f41.jpg", post));
        this.create(new Photo(0, "photo 3 Kia Rio", "f42.jpg", post));
        this.create(new Photo(0, "photo 4 Kia Rio", "f43.jpg", post));
        this.create(new Photo(0, "photo 5 Kia Rio", "f44.jpg", post));

        post = posts.findByLikeCarName("Mercedes-Benz E-Класс").get(0);
        this.create(new Photo(0, "photo 1 Mercedes-Benz E-Класс", "f45.jpg", post));
        this.create(new Photo(0, "photo 2 Mercedes-Benz E-Класс", "f46.jpg", post));
        this.create(new Photo(0, "photo 3 Mercedes-Benz E-Класс", "f47.jpg", post));
        this.create(new Photo(0, "photo 4 Mercedes-Benz E-Класс", "f48.jpg", post));
        this.create(new Photo(0, "photo 5 Mercedes-Benz E-Класс", "f49.jpg", post));
        this.create(new Photo(0, "photo 6 Mercedes-Benz E-Класс", "f50.jpg", post));
        this.create(new Photo(0, "photo 7 Mercedes-Benz E-Класс", "f51.jpg", post));

        post = posts.findByLikeCarName("Mitsubishi Pajero").get(0);
        this.create(new Photo(0, "photo 1 Mitsubishi Pajero", "f52.jpg", post));
        this.create(new Photo(0, "photo 2 Mitsubishi Pajero", "f53.jpg", post));
        this.create(new Photo(0, "photo 3 Mitsubishi Pajero", "f54.jpg", post));

        post = posts.findByLikeCarName("Nissan Pathfinder").get(0);
        this.create(new Photo(0, "photo 1 Nissan Pathfinder", "f55.jpg", post));
        this.create(new Photo(0, "photo 2 Nissan Pathfinder", "f56.jpg", post));
        this.create(new Photo(0, "photo 3 Nissan Pathfinder", "f57.jpg", post));

        post = posts.findByLikeCarName("Opel Astra").get(0);
        this.create(new Photo(0, "photo 1 Opel Astra", "f58.jpg", post));

        post = posts.findByLikeCarName("Renault Duster").get(0);
        this.create(new Photo(0, "photo 1 Renault Duster", "f59.jpg", post));
        this.create(new Photo(0, "photo 2 Renault Duster", "f60.jpg", post));
        this.create(new Photo(0, "photo 3 Renault Duster", "f61.jpg", post));
        this.create(new Photo(0, "photo 4 Renault Duster", "f62.jpg", post));

        post = posts.findByLikeCarName("Subaru Outback").get(0);
        this.create(new Photo(0, "photo 1 Subaru Outback", "f63.jpg", post));
        this.create(new Photo(0, "photo 2 Subaru Outback", "f64.jpg", post));
        this.create(new Photo(0, "photo 3 Subaru Outback", "f65.jpg", post));

        post = posts.findByLikeCarName("Toyota Land Cruiser").get(0);
        this.create(new Photo(0, "photo 1 Toyota Land Cruiser", "f66.jpg", post));
        this.create(new Photo(0, "photo 2 Toyota Land Cruiser", "f67.jpg", post));
        this.create(new Photo(0, "photo 3 Toyota Land Cruiser", "f68.jpg", post));
        this.create(new Photo(0, "photo 4 Toyota Land Cruiser", "f69.jpg", post));
        this.create(new Photo(0, "photo 5 Toyota Land Cruiser", "f70.jpg", post));
        this.create(new Photo(0, "photo 6 Toyota Land Cruiser", "f71.jpg", post));
        this.create(new Photo(0, "photo 7 Toyota Land Cruiser", "f72.jpg", post));
        this.create(new Photo(0, "photo 8 Toyota Land Cruiser", "f73.jpg", post));

        post = posts.findByLikeCarName("Toyota RAV4").get(0);
        this.create(new Photo(0, "photo 1 Toyota RAV4", "f74.jpg", post));
        this.create(new Photo(0, "photo 2 Toyota RAV4", "f75.jpg", post));
        this.create(new Photo(0, "photo 3 Toyota RAV4", "f76.jpg", post));
        this.create(new Photo(0, "photo 4 Toyota RAV4", "f77.jpg", post));
        this.create(new Photo(0, "photo 5 Toyota RAV4", "f78.jpg", post));
        this.create(new Photo(0, "photo 6 Toyota RAV4", "f79.jpg", post));
    }

    /**
     * Сохранить в базе фотографию.
     * @param photo фотография.
     * @return фотография с id.
     */
    @Override
    public Photo create(Photo photo) {
        if (photo == null) {
            return null;
        }
        photo.setId(count.incrementAndGet());
        return insert(photo);
    }

    /**
     * вставить фото без проверки наличия идентификатора в хранилище
     * @param photo фотография
     * @return фотография
     */
    private Photo insert(Photo photo) {
        int postId = 0;
        if (photo.getPost() != null) {
            postId = photo.getPost().getId();
        }
        if (postId > 0)  {
            posts.findById(postId).ifPresent(post -> {
                List<Photo> ps = new ArrayList<>(post.getPhotos());
                ps.add(photo);
                post.setPhotos(ps);
            });
        } else {
            photoNoOwner.put(photo.getId(), photo);
        }
        return photo;
    }


    /**
     * Обновить в базе фотографию.
     * @param photo фотография.
     */
    @Override
    public void update(Photo photo) {
        if (photo.getId() > 0) {
            findById(photo.getId()).ifPresent(
                    photo1 -> {
                        if (photo != photo1
                                || ((photo.getName() != photo1.getName()
                                || photo.getName() != null
                                && !photo.getName().equals(photo1.getName()))
                                ||  (photo.getFileName() != photo1.getFileName()
                                || photo.getFileName() != null
                                && !photo.getFileName().equals(photo1.getFileName()))
                                ||  (photo.getPost() != photo1.getPost()
                                || photo.getPost() != null
                                && !photo.getPost().equals(photo1.getPost()))
                        )) {
                            delete(photo1.getId());
                            insert(photo);
                        }
                    }
            );
        }
    }

    /**
     * Удалить фотографию по id.
     * @param id ID фотографии.
     */
    @Override
    public void delete(int id) {
        findById(id).ifPresent(
                photo -> {
                    Post post = photo.getPost();
                    if (post != null && post.getId() > 0) {
                        post = posts.findById(post.getId()).orElse(post);
                        post.getPhotos().removeIf(photo1 -> photo1.getId() == id);
                    } else {
                        photoNoOwner.remove(id);
                    }
                }
        );
    }

    /**
     * Список фотографий отсортированных по id.
     * @return {@link java.util.List<ru.job4j.cars.model.Photo>} список фотографий.
     */
    @Override
    public List<Photo> findAllOrderById() {
        List<Photo> photoList = new ArrayList<>(photoNoOwner.values());
        posts.findAllOrderById().forEach(post -> photoList.addAll(post.getPhotos()));
        photoList.sort(Comparator.comparing(Photo::getId));
        return photoList;
    }

    /**
     * Список id объявлений с фотографий отсортированных по id фотографии.
     * @param postId - id объявления
     * @return {@link java.util.List<java.lang.Integer>} список фотографий.
     */
    @Override
    public List<Photo> findAllWherePost(int postId) {
        if (postId > 0) {
            Post post = new Post();
            post.setPhotos(List.of());
            return posts.findById(postId).orElse(post).getPhotos();
        }
        return photoNoOwner.values().stream().toList();
    }

    /**
     * Найти фотографию по ID
     * @return фотографию.
     */
    @Override
    public Optional<Photo> findById(int id) {
        return findAllOrderById().stream().filter(photo -> photo.getId() == id).findFirst();
    }
}

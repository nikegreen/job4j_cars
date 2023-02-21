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
        List<Post> postList = posts.findAllOrderById();

        Photo photo1 = new Photo();
        photo1.setName("photo1");
        photo1.setFileName("i1.png");
        photo1.setPost(postList.get(1));
        this.create(photo1);

        Photo photo2 = new Photo();
        photo2.setName("photo2");
        photo2.setFileName("i2.jpg");
        photo2.setPost(postList.get(1));
        this.create(photo2);

        //post3
        photo1 = new Photo();
        photo1.setName("photo3");
        photo1.setFileName("i3.png");
        photo1.setPost(postList.get(2));
        this.create(photo1);

        photo2 = new Photo();
        photo2.setName("photo4");
        photo2.setFileName("i4.jpg");
        photo2.setPost(postList.get(2));
        this.create(photo2);

        Photo photo3 = new Photo();
        photo3.setName("photo5");
        photo3.setFileName("i5.jpg");
        photo3.setPost(postList.get(2));
        this.create(photo3);

        Photo photo4 = new Photo();
        photo4.setName("photo6");
        photo4.setFileName("i4.jpg");
        photo4.setPost(postList.get(2));
        this.create(photo4);

        //post.setPhotos(List.of(photo1, photo2, photo3, photo4));
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

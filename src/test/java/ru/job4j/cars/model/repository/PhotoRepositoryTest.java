package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * тест хранилища фотографий
 */
class PhotoRepositoryTest {
    /**
     * Создание  фотографии, список всех  фотографий, удаление  фотографии
     */
    @Test
    void whenCreateFindAllOrderByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllOrderById();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isNotEqualTo(0);
            Post post = posts.get(0);
            assertThat(post).isNotNull();

            PhotoRepository photoRepository = new PhotoRepository(new CrudRepository(sf));
            assertThat(photoRepository).isNotNull();
            List<Photo> photos = photoRepository.findAllOrderById();
            assertThat(photos).isNotNull();
            int size = photos.size();
            Photo photo = new Photo();
            photo.setName("photo 2 super car 1");
            photo.setFileName("photo3.jpg");
            photo.setPostId(post.getId());
            Photo photo1 = photoRepository.create(photo);
            assertThat(photo1).isNotNull();
            assertThat(photo1.getId()).isNotEqualTo(0);
            assertThat(photo1.getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo1.getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo1.getPostId()).isEqualTo(post.getId());

            List<Photo> photos2 = photoRepository.findAllOrderById();
            assertThat(photos2).isNotNull().hasSize(size + 1);

            Photo photo2 = photos2.get(size);
            assertThat(photo2).isNotNull();
            assertThat(photo2.getId()).isEqualTo(photo1.getId());
            assertThat(photo2.getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo2.getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo2.getPostId()).isEqualTo(post.getId());

            photoRepository.delete(photo1.getId());
            List<Photo> photos3 = photoRepository.findAllOrderById();
            assertThat(photos3).isNotNull().hasSize(size);
        }
    }

    /**
     * Создание  фотографии, список всех  фотографий по объявлению, удаление  фотографии
     */
    @Test
    void whenCreateFindAllWherePostDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllOrderById();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isNotEqualTo(0);
            Post post = posts.get(0);
            assertThat(post).isNotNull();

            PhotoRepository photoRepository = new PhotoRepository(new CrudRepository(sf));
            assertThat(photoRepository).isNotNull();
            List<Photo> photos = photoRepository.findAllWherePost(1);
            assertThat(photos).isNotNull();
            int size = photos.size();
            Photo photo = new Photo();
            photo.setName("photo 2 super car 1");
            photo.setFileName("photo3.jpg");
            photo.setPostId(post.getId());
            Photo photo1 = photoRepository.create(photo);
            assertThat(photo1).isNotNull();
            assertThat(photo1.getId()).isNotEqualTo(0);
            assertThat(photo1.getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo1.getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo1.getPostId()).isEqualTo(post.getId());

            List<Photo> photos2 = photoRepository.findAllWherePost(1);
            assertThat(photos2).isNotNull().hasSize(size + 1);

            Photo photo2 = photos2.get(size);
            assertThat(photo2).isNotNull();
            assertThat(photo2.getId()).isEqualTo(photo1.getId());
            assertThat(photo2.getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo2.getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo2.getPostId()).isEqualTo(post.getId());

            photoRepository.delete(photo1.getId());
            List<Photo> photos3 = photoRepository.findAllWherePost(1);
            assertThat(photos3).isNotNull().hasSize(size);
        }
    }

    /**
     * Создание  фотографии, поиск фотографии по идентификатору, удаление  фотографии
     */
    @Test
    void whenCreateFindByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllOrderById();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isNotEqualTo(0);
            Post post = posts.get(0);
            assertThat(post).isNotNull();

            PhotoRepository photoRepository = new PhotoRepository(new CrudRepository(sf));
            assertThat(photoRepository).isNotNull();
            Photo photo = new Photo();
            photo.setName("photo 2 super car 1");
            photo.setFileName("photo3.jpg");
            photo.setPostId(post.getId());
            Photo photo1 = photoRepository.create(photo);
            assertThat(photo1).isNotNull();
            assertThat(photo1.getId()).isNotEqualTo(0);
            assertThat(photo1.getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo1.getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo1.getPostId()).isEqualTo(post.getId());

            Optional<Photo> photo2 = photoRepository.findById(photo1.getId());
            assertThat(photo2).isNotEqualTo(Optional.empty());
            assertThat(photo2.get().getId()).isEqualTo(photo1.getId());
            assertThat(photo2.get().getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo2.get().getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo2.get().getPostId()).isEqualTo(post.getId());

            photoRepository.delete(photo1.getId());
            Optional<Photo> photos3 = photoRepository.findById(photo1.getId());
            assertThat(photos3).isEqualTo(Optional.empty());
        }
    }

    /**
     * Создание  фотографии, поиск фотографии по идентификатору,
     * обновление фотографии, удаление  фотографии
     */    @Test
    void whenCreateFindByIdUpdateDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllOrderById();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isNotEqualTo(0);
            Post post = posts.get(0);
            assertThat(post).isNotNull();

            PhotoRepository photoRepository = new PhotoRepository(new CrudRepository(sf));
            assertThat(photoRepository).isNotNull();
            Photo photo = new Photo();
            photo.setName("photo 2 super car 1");
            photo.setFileName("photo3.jpg");
            photo.setPostId(post.getId());
            Photo photo1 = photoRepository.create(photo);
            assertThat(photo1).isNotNull();
            assertThat(photo1.getId()).isNotEqualTo(0);
            assertThat(photo1.getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo1.getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo1.getPostId()).isEqualTo(post.getId());

            Optional<Photo> photo2 = photoRepository.findById(photo1.getId());
            assertThat(photo2).isNotEqualTo(Optional.empty());
            assertThat(photo2.get().getId()).isEqualTo(photo1.getId());
            assertThat(photo2.get().getName()).isEqualTo("photo 2 super car 1");
            assertThat(photo2.get().getFileName()).isEqualTo("photo3.jpg");
            assertThat(photo2.get().getPostId()).isEqualTo(post.getId());

            photo1.setName("photo 2 super car 1-1");
            photo1.setFileName("photo3-3.jpg");
            photoRepository.update(photo1);
            assertThat(photo1).isNotNull();
            assertThat(photo1.getId()).isEqualTo(photo2.get().getId());
            assertThat(photo1.getName()).isEqualTo("photo 2 super car 1-1");
            assertThat(photo1.getFileName()).isEqualTo("photo3-3.jpg");
            assertThat(photo1.getPostId()).isEqualTo(post.getId());

            photoRepository.delete(photo1.getId());
            Optional<Photo> photos3 = photoRepository.findById(photo1.getId());
            assertThat(photos3).isEqualTo(Optional.empty());
        }
    }
}
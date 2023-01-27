package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Photo;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class PostRepositoryTest {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss.SSS");
    @Test
    void whenCreateFindAllOrderByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                        .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isNotEqualTo(0);
            User user = users.get(0);
            assertThat(user).isNotNull();

            CarRepository carRepository = new CarRepository(new CrudRepository(sf));
            assertThat(carRepository).isNotNull();
            List<Car> cars= carRepository.findAllOrderById();
            assertThat(cars).isNotNull();
            assertThat(cars.size()).isNotEqualTo(0);
            Car car = cars.get(0);
            assertThat(car).isNotNull();

            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllOrderById();
            int size = posts != null ? posts.size() : 0;

            LocalDateTime ldt = LocalDateTime.now();
            Post post = new Post();
            assertThat(post).isNotNull();
            post.setText("sell car");
            post.setUser(user);
            post.setCar(car);
            post.setCreated(ldt);
            post.setPhotos(List.of());
            post.setParticipates(List.of());
            post.setPriceHistories(List.of());
            post = postRepository.create(post);
            assertThat(post).isNotNull();
            assertThat(post.getId()).isNotEqualTo(0);
            assertThat(post.getText()).isEqualTo("sell car");
            assertThat(post.getUser()).isEqualTo(user);
            assertThat(post.getCar()).isEqualTo(car);
            assertThat(post.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post.getPhotos()).isNotNull();
            assertThat(post.getPhotos().size()).isEqualTo(0);
            assertThat(post.getParticipates()).isNotNull();
            assertThat(post.getParticipates().size()).isEqualTo(0);
            assertThat(post.getPriceHistories()).isNotNull();
            assertThat(post.getPriceHistories().size()).isEqualTo(0);

            posts = postRepository.findAllOrderById();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size + 1);
            Post post1 = posts.get(size);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post1.getPhotos()).isNotNull();
            assertThat(post1.getPhotos().size()).isEqualTo(0);
            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);
            postRepository.delete(post.getId());
            posts = postRepository.findAllOrderById();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size);
        }
    }

    @Test
    void whenCreateFindAllTodayDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isNotEqualTo(0);
            User user = users.get(0);
            assertThat(user).isNotNull();

            CarRepository carRepository = new CarRepository(new CrudRepository(sf));
            assertThat(carRepository).isNotNull();
            List<Car> cars= carRepository.findAllOrderById();
            assertThat(cars).isNotNull();
            assertThat(cars.size()).isNotEqualTo(0);
            Car car = cars.get(0);
            assertThat(car).isNotNull();

            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllToday();
            int size = posts != null ? posts.size() : 0;

            LocalDateTime ldt = LocalDateTime.now();
            Post post = new Post();
            assertThat(post).isNotNull();
            post.setText("sell car");
            post.setUser(user);
            post.setCar(car);
            post.setCreated(ldt);
            post.setPhotos(List.of());
            post.setParticipates(List.of());
            post.setPriceHistories(List.of());
            post = postRepository.create(post);
            assertThat(post).isNotNull();
            assertThat(post.getId()).isNotEqualTo(0);
            assertThat(post.getText()).isEqualTo("sell car");
            assertThat(post.getUser()).isEqualTo(user);
            assertThat(post.getCar()).isEqualTo(car);
            assertThat(post.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post.getPhotos()).isNotNull();
            assertThat(post.getPhotos().size()).isEqualTo(0);
            assertThat(post.getParticipates()).isNotNull();
            assertThat(post.getParticipates().size()).isEqualTo(0);
            assertThat(post.getPriceHistories()).isNotNull();
            assertThat(post.getPriceHistories().size()).isEqualTo(0);

            posts = postRepository.findAllToday();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size + 1);
            Post post1 = posts.get(size);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post1.getPhotos()).isNotNull();
            assertThat(post1.getPhotos().size()).isEqualTo(0);
            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);
            postRepository.delete(post.getId());
            posts = postRepository.findAllToday();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size);
        }
    }

    @Test
    void whenCreateFindByLikeCarNameDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isNotEqualTo(0);
            User user = users.get(0);
            assertThat(user).isNotNull();

            CarRepository carRepository = new CarRepository(new CrudRepository(sf));
            assertThat(carRepository).isNotNull();
            List<Car> cars= carRepository.findAllOrderById();
            assertThat(cars).isNotNull();
            assertThat(cars.size()).isNotEqualTo(0);
            Car car = cars.get(0);
            assertThat(car).isNotNull();

            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findByLikeCarName("airtrack");
            int size = posts != null ? posts.size() : 0;

            LocalDateTime ldt = LocalDateTime.now();
            Post post = new Post();
            assertThat(post).isNotNull();
            post.setText("sell car");
            post.setUser(user);
            post.setCar(car);
            post.setCreated(ldt);
            post.setPhotos(List.of());
            post.setParticipates(List.of());
            post.setPriceHistories(List.of());
            post = postRepository.create(post);
            assertThat(post).isNotNull();
            assertThat(post.getId()).isNotEqualTo(0);
            assertThat(post.getText()).isEqualTo("sell car");
            assertThat(post.getUser()).isEqualTo(user);
            assertThat(post.getCar()).isEqualTo(car);
            assertThat(post.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post.getPhotos()).isNotNull();
            assertThat(post.getPhotos().size()).isEqualTo(0);
            assertThat(post.getParticipates()).isNotNull();
            assertThat(post.getParticipates().size()).isEqualTo(0);
            assertThat(post.getPriceHistories()).isNotNull();
            assertThat(post.getPriceHistories().size()).isEqualTo(0);

            posts = postRepository.findByLikeCarName("airtrack");
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size + 1);
            Post post1 = posts.get(size);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post1.getPhotos()).isNotNull();
            assertThat(post1.getPhotos().size()).isEqualTo(0);
            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);
            postRepository.delete(post.getId());
            posts = postRepository.findByLikeCarName("airtrack");
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size);
        }
    }

    @Test
    void whenCreateFindAllWithPhotoDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isNotEqualTo(0);
            User user = users.get(0);
            assertThat(user).isNotNull();

            CarRepository carRepository = new CarRepository(new CrudRepository(sf));
            assertThat(carRepository).isNotNull();
            List<Car> cars = carRepository.findAllOrderById();
            assertThat(cars).isNotNull();
            assertThat(cars.size()).isNotEqualTo(0);
            Car car = cars.get(0);
            assertThat(car).isNotNull();

            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();
            List<Post> posts = postRepository.findAllWithPhoto();
            int size = posts != null ? posts.size() : 0;

            LocalDateTime ldt = LocalDateTime.now();
            Post post = new Post();
            assertThat(post).isNotNull();
            post.setText("sell car");
            post.setUser(user);
            post.setCar(car);
            post.setCreated(ldt);
            post.setPhotos(List.of());
            post.setParticipates(List.of());
            post.setPriceHistories(List.of());
            post = postRepository.create(post);
            assertThat(post).isNotNull();
            assertThat(post.getId()).isNotEqualTo(0);
            assertThat(post.getText()).isEqualTo("sell car");
            assertThat(post.getUser()).isEqualTo(user);
            assertThat(post.getCar()).isEqualTo(car);
            assertThat(post.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post.getPhotos()).isNotNull();
            assertThat(post.getPhotos().size()).isEqualTo(0);
            assertThat(post.getParticipates()).isNotNull();
            assertThat(post.getParticipates().size()).isEqualTo(0);
            assertThat(post.getPriceHistories()).isNotNull();
            assertThat(post.getPriceHistories().size()).isEqualTo(0);

            PhotoRepository photoRepository = new PhotoRepository(new CrudRepository(sf));
            assertThat(photoRepository).isNotNull();
            Photo photo = new Photo();
            assertThat(photo).isNotNull();
            photo.setName("new photo name");
            photo.setFileName("new_photo.jpg");
            photo.setPostId(post.getId());
            photo = photoRepository.create(photo);
            assertThat(photo).isNotNull();
            assertThat(photo.getId()).isNotEqualTo(0);
            assertThat(photo.getName()).isEqualTo("new photo name");
            assertThat(photo.getFileName()).isEqualTo("new_photo.jpg");
            assertThat(photo.getPostId()).isEqualTo(post.getId());

            posts = postRepository.findAllWithPhoto();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size + 1);
            Post post1 = posts.get(size);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post1.getPhotos()).isNotNull();

            assertThat(post1.getPhotos().size()).isEqualTo(1);
            assertThat(post1.getPhotos().get(0).getId()).isNotEqualTo(0);
            assertThat(post1.getPhotos().get(0).getName()).isEqualTo(photo.getName());
            assertThat(post1.getPhotos().get(0).getFileName()).isEqualTo(photo.getFileName());
            assertThat(post1.getPhotos().get(0).getPostId()).isEqualTo(post.getId());

            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);
            postRepository.delete(post1.getId());
            posts = postRepository.findAllWithPhoto();
            assertThat(posts).isNotNull();
            assertThat(posts.size()).isEqualTo(size);
        }
    }

    @Test
    void whenCreateFindByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isNotEqualTo(0);
            User user = users.get(0);
            assertThat(user).isNotNull();

            CarRepository carRepository = new CarRepository(new CrudRepository(sf));
            assertThat(carRepository).isNotNull();
            List<Car> cars = carRepository.findAllOrderById();
            assertThat(cars).isNotNull();
            assertThat(cars.size()).isNotEqualTo(0);
            Car car = cars.get(0);
            assertThat(car).isNotNull();

            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();

            LocalDateTime ldt = LocalDateTime.now();
            Post post = new Post();
            assertThat(post).isNotNull();
            post.setText("sell car");
            post.setUser(user);
            post.setCar(car);
            post.setCreated(ldt);
            post.setPhotos(List.of());
            post.setParticipates(List.of());
            post.setPriceHistories(List.of());
            post = postRepository.create(post);
            assertThat(post).isNotNull();
            assertThat(post.getId()).isNotEqualTo(0);
            assertThat(post.getText()).isEqualTo("sell car");
            assertThat(post.getUser()).isEqualTo(user);
            assertThat(post.getCar()).isEqualTo(car);
            assertThat(post.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post.getPhotos()).isNotNull();
            assertThat(post.getPhotos().size()).isEqualTo(0);
            assertThat(post.getParticipates()).isNotNull();
            assertThat(post.getParticipates().size()).isEqualTo(0);
            assertThat(post.getPriceHistories()).isNotNull();
            assertThat(post.getPriceHistories().size()).isEqualTo(0);


            Post post1 = postRepository.findById(post.getId()).orElse(null);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));

            assertThat(post1.getPhotos()).isNotNull();
            assertThat(post1.getPhotos().size()).isEqualTo(0);

            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);
            postRepository.delete(post1.getId());
            Post post2 = postRepository.findById(post.getId()).orElse(null);
            assertThat(post2).isNull();
        }
    }

    @Test
    void whenCreateFindByIdUpdateDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isNotEqualTo(0);
            User user = users.get(0);
            assertThat(user).isNotNull();

            CarRepository carRepository = new CarRepository(new CrudRepository(sf));
            assertThat(carRepository).isNotNull();
            List<Car> cars = carRepository.findAllOrderById();
            assertThat(cars).isNotNull();
            assertThat(cars.size()).isNotEqualTo(0);
            Car car = cars.get(0);
            assertThat(car).isNotNull();

            PostRepository postRepository = new PostRepository(new CrudRepository(sf));
            assertThat(postRepository).isNotNull();

            LocalDateTime ldt = LocalDateTime.now();
            Post post = new Post();
            assertThat(post).isNotNull();
            post.setText("sell car");
            post.setUser(user);
            post.setCar(car);
            post.setCreated(ldt);
            post.setPhotos(List.of());
            post.setParticipates(List.of());
            post.setPriceHistories(List.of());
            post = postRepository.create(post);
            assertThat(post).isNotNull();
            assertThat(post.getId()).isNotEqualTo(0);
            assertThat(post.getText()).isEqualTo("sell car");
            assertThat(post.getUser()).isEqualTo(user);
            assertThat(post.getCar()).isEqualTo(car);
            assertThat(post.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post.getPhotos()).isNotNull();
            assertThat(post.getPhotos().size()).isEqualTo(0);
            assertThat(post.getParticipates()).isNotNull();
            assertThat(post.getParticipates().size()).isEqualTo(0);
            assertThat(post.getPriceHistories()).isNotNull();
            assertThat(post.getPriceHistories().size()).isEqualTo(0);


            Post post1 = postRepository.findById(post.getId()).orElse(null);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post1.getPhotos()).isNotNull();
            assertThat(post1.getPhotos().size()).isEqualTo(0);
            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);

            post1.setText("sell new car");
            postRepository.update(post1);
            assertThat(post1).isNotNull();
            assertThat(post1.getId()).isEqualTo(post.getId());
            assertThat(post1.getText()).isEqualTo("sell new car");
            assertThat(post1.getUser()).isEqualTo(user);
            assertThat(post1.getCar()).isEqualTo(car);
            assertThat(post1.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));
            assertThat(post1.getPhotos()).isNotNull();
            assertThat(post1.getPhotos().size()).isEqualTo(0);
            assertThat(post1.getParticipates()).isNotNull();
            assertThat(post1.getParticipates().size()).isEqualTo(0);
            assertThat(post1.getPriceHistories()).isNotNull();
            assertThat(post1.getPriceHistories().size()).isEqualTo(0);

            Post post2 = postRepository.findById(post.getId()).orElse(null);
            assertThat(post2).isNotNull();
            assertThat(post2.getId()).isEqualTo(post.getId());
            assertThat(post2.getText()).isEqualTo("sell new car");
            assertThat(post2.getUser()).isEqualTo(user);
            assertThat(post2.getCar()).isEqualTo(car);
            assertThat(post2.getCreated().format(formatter)).isEqualTo(ldt.format(formatter));

            assertThat(post2.getPhotos()).isNotNull();
            assertThat(post2.getPhotos().size()).isEqualTo(0);

            assertThat(post2.getParticipates()).isNotNull();
            assertThat(post2.getParticipates().size()).isEqualTo(0);
            assertThat(post2.getPriceHistories()).isNotNull();
            assertThat(post2.getPriceHistories().size()).isEqualTo(0);

            postRepository.delete(post2.getId());
            Post post3 = postRepository.findById(post.getId()).orElse(null);
            assertThat(post3).isNull();
        }
    }
}

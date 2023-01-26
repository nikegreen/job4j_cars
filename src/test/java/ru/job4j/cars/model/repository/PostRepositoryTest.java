package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
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
            assertThat(post1.getId()).isNotEqualTo(post.getId());
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
}
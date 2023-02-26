package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * тест хранилища пользователей
 */
class UserRepositoryTest {

    /**
     * Создание пользователя, список всех пользователей, удаление пользователя
     */
    @Test
    void whenCreateFindAllOrderByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            int count = userRepository.findAllOrderById().size();
            User user = new User();
            user.setLogin("Admin");
            user.setPassword("admin");
            user = userRepository.create(user);
            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotEqualTo(0);
            assertThat(user.getLogin()).isEqualTo("Admin");
            assertThat(user.getPassword()).isEqualTo("admin");
            List<User> users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isEqualTo(count+1);
            assertThat(users.get(count).getId()).isEqualTo(user.getId());
            assertThat(users.get(count).getLogin()).isEqualTo(user.getLogin());
            assertThat(users.get(count).getPassword()).isEqualTo(user.getPassword());
            userRepository.delete(user.getId());
            users = userRepository.findAllOrderById();
            assertThat(users).isNotNull();
            assertThat(users.size()).isEqualTo(count);
        }
    }

    /**
     * Создание пользователя, поиск пользователя по логину, удаление пользователя
     */
    @Test
    void whenCreateFindByLoginDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            User user = new User();
            user.setLogin("Admin");
            user.setPassword("admin");
            user = userRepository.create(user);
            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotEqualTo(0);
            assertThat(user.getLogin()).isEqualTo("Admin");
            assertThat(user.getPassword()).isEqualTo("admin");
            Optional<User> user2 = userRepository.findByLogin(user.getLogin());
            assertThat(user2.get().getId()).isEqualTo(user.getId());
            assertThat(user2.get().getLogin()).isEqualTo(user.getLogin());
            assertThat(user2.get().getPassword()).isEqualTo(user.getPassword());
            userRepository.delete(user.getId());
            Optional<User> user3 = userRepository.findByLogin(user.getLogin());
            assertThat(user3).isEqualTo(Optional.empty());
        }
    }

    /**
     * Создание пользователя, список всех пользователей по логину, удаление пользователя
     */
    @Test
    void whenCreateFindByLikeLoginDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            List<User> users1 = userRepository.findByLikeLogin("min");
            assertThat(users1).isNotNull();
            int size = users1.size();
            User user = new User();
            user.setLogin("Admin");
            user.setPassword("admin");
            user = userRepository.create(user);
            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotEqualTo(0);
            assertThat(user.getLogin()).isEqualTo("Admin");
            assertThat(user.getPassword()).isEqualTo("admin");
            List<User> users = userRepository.findByLikeLogin("min");
            assertThat(users).isNotNull();
            assertThat(users.size()).isEqualTo(size + 1);
            assertThat(users.get(size).getId()).isEqualTo(user.getId());
            assertThat(users.get(size).getId()).isEqualTo(user.getId());
            assertThat(users.get(size).getLogin()).isEqualTo(user.getLogin());
            assertThat(users.get(size).getPassword()).isEqualTo(user.getPassword());
            userRepository.delete(user.getId());
            List<User> users2 = userRepository.findByLikeLogin("min");
            assertThat(users2).isNotEqualTo(Optional.empty());
            assertThat(users2.size()).isEqualTo(size);
        }
    }

    /**
     * Создание пользователя, поиск пользователя по идентификатору, удаление пользователя
     */
    @Test
    void whenCreateFindByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            User user = new User();
            user.setLogin("Admin");
            user.setPassword("admin");
            user = userRepository.create(user);
            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotEqualTo(0);
            assertThat(user.getLogin()).isEqualTo("Admin");
            assertThat(user.getPassword()).isEqualTo("admin");
            Optional<User> user2 = userRepository.findById(user.getId());
            assertThat(user2.get().getId()).isEqualTo(user.getId());
            assertThat(user2.get().getLogin()).isEqualTo(user.getLogin());
            assertThat(user2.get().getPassword()).isEqualTo(user.getPassword());
            userRepository.delete(user.getId());
            Optional<User> user3 = userRepository.findById(user.getId());
            assertThat(user3).isEqualTo(Optional.empty());
        }
    }

    /**
     * Создание пользователя, обновление пользователя,
     * поиск пользователя по идентификатору, удаление пользователя
     */
    @Test
    void whenCreateUpdateFindByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            User user = new User();
            user.setLogin("Admin");
            user.setPassword("admin");
            user = userRepository.create(user);
            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotEqualTo(0);
            assertThat(user.getLogin()).isEqualTo("Admin");
            assertThat(user.getPassword()).isEqualTo("admin");
            user.setLogin("Root");
            user.setPassword("root");
            userRepository.update(user);
            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotEqualTo(0);
            assertThat(user.getLogin()).isEqualTo("Root");
            assertThat(user.getPassword()).isEqualTo("root");
            Optional<User> user2 = userRepository.findById(user.getId());
            assertThat(user2.get().getId()).isEqualTo(user.getId());
            assertThat(user2.get().getLogin()).isEqualTo(user.getLogin());
            assertThat(user2.get().getPassword()).isEqualTo(user.getPassword());
            userRepository.delete(user.getId());
            Optional<User> user3 = userRepository.findById(user.getId());
            assertThat(user3).isEqualTo(Optional.empty());
        }
    }
}

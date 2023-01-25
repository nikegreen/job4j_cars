package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.User;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserRepositoryTest {
    @Test
    void test1() {
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
}
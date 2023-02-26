package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * тест хранилища водителей
 */
class DriverRepositoryTest {
    /**
     * Создание водителя, список всех водителей, удаление водителя
     */
    @Test
    void whenCreateFindAllOrderByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            User user = userRepository.findByLogin("Petrov").orElse(null);
            assertThat(user).isNotNull();
            assertThat(user.getLogin()).isEqualTo("Petrov");
            assertThat(user.getId()).isNotEqualTo(0);

            DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
            assertThat(driverRepository).isNotNull();
            List<Driver> drivers = driverRepository.findAllOrderById();
            assertThat(drivers).isNotNull();
            int size = drivers.size();

            Driver driver = new Driver();
            driver.setName("driver name");
            driver.setUser(user);
            Driver driver1 = driverRepository.create(driver);
            assertThat(driver1).isNotNull();
            assertThat(driver1.getId()).isNotEqualTo(0);
            assertThat(driver1.getName()).isEqualTo("driver name");
            assertThat(driver1.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver1.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver1.getUser().getPassword()).isEqualTo(user.getPassword());

            List<Driver> drivers1 = driverRepository.findAllOrderById();
            assertThat(drivers1).isNotNull().hasSize(size + 1);

            Driver driver2 = drivers1.get(size);
            assertThat(driver2).isNotNull();
            assertThat(driver2.getId()).isEqualTo(driver1.getId());
            assertThat(driver2.getName()).isEqualTo("driver name");
            assertThat(driver2.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver2.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver2.getUser().getPassword()).isEqualTo(user.getPassword());

            driverRepository.delete(driver1.getId());
            List<Driver> drivers2 = driverRepository.findAllOrderById();
            assertThat(drivers2).isNotNull().hasSize(size);
        }
    }

    /**
     * Создание водителя, список всех водителей по имени, удаление водителя
     */
    @Test
    void whenCreateFindByLikeNameDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            User user = userRepository.findByLogin("Petrov").orElse(null);
            assertThat(user).isNotNull();
            assertThat(user.getLogin()).isEqualTo("Petrov");
            assertThat(user.getId()).isNotEqualTo(0);

            DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
            assertThat(driverRepository).isNotNull();
            List<Driver> drivers = driverRepository.findByLikeName("name");
            assertThat(drivers).isNotNull();
            int size = drivers.size();

            Driver driver = new Driver();
            driver.setName("driver name");
            driver.setUser(user);
            Driver driver1 = driverRepository.create(driver);
            assertThat(driver1).isNotNull();
            assertThat(driver1.getId()).isNotEqualTo(0);
            assertThat(driver1.getName()).isEqualTo("driver name");
            assertThat(driver1.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver1.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver1.getUser().getPassword()).isEqualTo(user.getPassword());

            List<Driver> drivers1 = driverRepository.findByLikeName("name");
            assertThat(drivers1).isNotNull().hasSize(size + 1);

            Driver driver2 = drivers1.get(size);
            assertThat(driver2).isNotNull();
            assertThat(driver2.getId()).isEqualTo(driver1.getId());
            assertThat(driver2.getName()).isEqualTo("driver name");
            assertThat(driver2.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver2.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver2.getUser().getPassword()).isEqualTo(user.getPassword());

            driverRepository.delete(driver1.getId());
            List<Driver> photos3 = driverRepository.findByLikeName("name");
            assertThat(photos3).isNotNull().hasSize(size).containsExactlyElementsOf(drivers);
        }
    }

    /**
     * Создание водителя, поиск водителя по идентификатору, удаление водителя
     */
    @Test
    void whenCreateFindByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            User user = userRepository.findByLogin("Petrov").orElse(null);
            assertThat(user).isNotNull();
            assertThat(user.getLogin()).isEqualTo("Petrov");
            assertThat(user.getId()).isNotEqualTo(0);

            DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
            assertThat(driverRepository).isNotNull();
            Driver driver = new Driver();
            driver.setName("driver name");
            driver.setUser(user);
            Driver driver1 = driverRepository.create(driver);
            assertThat(driver1).isNotNull();
            assertThat(driver1.getId()).isNotEqualTo(0);
            assertThat(driver1.getName()).isEqualTo("driver name");
            assertThat(driver1.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver1.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver1.getUser().getPassword()).isEqualTo(user.getPassword());

            Optional<Driver> driver2 = driverRepository.findById(driver1.getId());
            assertThat(driver2).isNotEqualTo(Optional.empty());
            assertThat(driver2.get().getId()).isEqualTo(driver1.getId());
            assertThat(driver2.get().getName()).isEqualTo("driver name");
            assertThat(driver2.get().getUser().getId()).isEqualTo(user.getId());
            assertThat(driver2.get().getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver2.get().getUser().getPassword()).isEqualTo(user.getPassword());

            driverRepository.delete(driver1.getId());
            Optional<Driver> driver3 = driverRepository.findById(driver1.getId());
            assertThat(driver3).isEqualTo(Optional.empty());
        }
    }

    /**
     * Создание водителя, поиск водителя по идентификатору,
     * обновить водителя, удаление водителя
     */
    @Test
    void whenCreateFindByIdUpdateDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            UserRepository userRepository = new UserRepository(new CrudRepository(sf));
            assertThat(userRepository).isNotNull();
            User user = userRepository.findByLogin("Petrov").orElse(null);
            assertThat(user).isNotNull();
            assertThat(user.getLogin()).isEqualTo("Petrov");
            assertThat(user.getId()).isNotEqualTo(0);

            DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
            assertThat(driverRepository).isNotNull();
            Driver driver = new Driver();
            driver.setName("driver name");
            driver.setUser(user);
            Driver driver1 = driverRepository.create(driver);
            assertThat(driver1).isNotNull();
            assertThat(driver1.getId()).isNotEqualTo(0);
            assertThat(driver1.getName()).isEqualTo("driver name");
            assertThat(driver1.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver1.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver1.getUser().getPassword()).isEqualTo(user.getPassword());

            Optional<Driver> driver2 = driverRepository.findById(driver1.getId());
            assertThat(driver2).isNotEqualTo(Optional.empty());
            assertThat(driver2.get().getId()).isEqualTo(driver1.getId());
            assertThat(driver2.get().getName()).isEqualTo("driver name");
            assertThat(driver2.get().getUser().getId()).isEqualTo(user.getId());
            assertThat(driver2.get().getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver2.get().getUser().getPassword()).isEqualTo(user.getPassword());

            driver1.setName("driver name-c");
            driverRepository.update(driver1);
            assertThat(driver1).isNotNull();
            assertThat(driver1.getId()).isEqualTo(driver2.get().getId());
            assertThat(driver1.getName()).isEqualTo("driver name-c");
            assertThat(driver1.getUser().getId()).isEqualTo(user.getId());
            assertThat(driver1.getUser().getLogin()).isEqualTo(user.getLogin());
            assertThat(driver1.getUser().getPassword()).isEqualTo(user.getPassword());

            driverRepository.delete(driver1.getId());
            Optional<Driver> driver3 = driverRepository.findById(driver1.getId());
            assertThat(driver3).isEqualTo(Optional.empty());
        }
    }
}
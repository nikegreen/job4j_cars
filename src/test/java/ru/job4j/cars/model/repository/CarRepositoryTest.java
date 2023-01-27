package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.User;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class CarRepositoryTest {
    private final SessionFactory sf = new MetadataSources(
            new StandardServiceRegistryBuilder()
            .configure().build()
    ).buildMetadata().buildSessionFactory();
    @Test
    void whenCreateFindByIdDelete() {
        UserRepository userRepository = new UserRepository(new CrudRepository(sf));
        assertThat(userRepository).isNotNull();
        Optional<User> user = userRepository.findByLogin("Ivanov");
        assertThat(user).isNotEqualTo(Optional.empty());

        EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
        assertThat(engineRepository).isNotNull();
        List<Engine> engines = engineRepository.findAllOrderById();
        assertThat(engines).isNotNull();
        assertThat(engines.size()).isNotEqualTo(0);
        Engine engine = engines.get(0);
        assertThat(engine).isNotNull();

        DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
        Driver driver = new Driver();
        assertThat(driver).isNotNull();
        driver.setName("driver name1");
        driver.setUser(user.get());
        driver = driverRepository.create(driver);
        assertThat(driver).isNotNull();

        CarRepository repository = new CarRepository(new CrudRepository(sf));
        assertThat(repository).isNotNull();
        Car car = new Car();
        car.setName("car name1");
        car.setEngine(engine);
        car.setOwners(Set.of(driver));
        car = repository.create(car);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotEqualTo(0);
        assertThat(car.getName()).isNotNull().isEqualTo("car name1");
        assertThat(car.getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car.getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car.getOwners()).isNotNull();
        assertThat(car.getOwners().size()).isEqualTo(1);
        assertThat(car.getOwners()).isEqualTo(Set.of(driver));
        Optional<Car> car1 = repository.findById(car.getId());
        assertThat(car1).isNotEqualTo(Optional.empty());
        assertThat(car1.get().getId()).isEqualTo(car.getId());
        assertThat(car1.get().getName()).isNotNull().isEqualTo("car name1");
        assertThat(car1.get().getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car1.get().getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car1.get().getOwners()).isNotNull();
        assertThat(car1.get().getOwners().size()).isNotNull().isEqualTo(1);
        assertThat(car1.get().getOwners()).isEqualTo(Set.of(driver));
        repository.delete(car.getId());
        Optional<Car> car2 = repository.findById(car.getId());
        assertThat(car2).isEqualTo(Optional.empty());
        driverRepository.delete(driver.getId());
        assertThat(driverRepository.findById(driver.getId())).isEqualTo(Optional.empty());
    }

    @Test
    void whenCreateFindByIdUpdateDelete() {
        UserRepository userRepository = new UserRepository(new CrudRepository(sf));
        assertThat(userRepository).isNotNull();
        Optional<User> user = userRepository.findByLogin("Ivanov");
        assertThat(user).isNotEqualTo(Optional.empty());

        EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
        assertThat(engineRepository).isNotNull();
        List<Engine> engines = engineRepository.findAllOrderById();
        assertThat(engines).isNotNull();
        assertThat(engines.size()).isNotEqualTo(0);
        Engine engine = engines.get(0);
        assertThat(engine).isNotNull();

        DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
        Driver driver = new Driver();
        assertThat(driver).isNotNull();
        driver.setName("driver name1");
        driver.setUser(user.get());
        driver = driverRepository.create(driver);
        assertThat(driver).isNotNull();

        CarRepository repository = new CarRepository(new CrudRepository(sf));
        assertThat(repository).isNotNull();
        Car car = new Car();
        car.setName("car name1");
        car.setEngine(engine);
        car.setOwners(Set.of(driver));
        car = repository.create(car);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotEqualTo(0);
        assertThat(car.getName()).isNotNull().isEqualTo("car name1");
        assertThat(car.getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car.getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car.getOwners()).isNotNull();
        assertThat(car.getOwners().size()).isEqualTo(1);
        assertThat(car.getOwners()).isEqualTo(Set.of(driver));
        Optional<Car> car1 = repository.findById(car.getId());
        assertThat(car1).isNotEqualTo(Optional.empty());
        assertThat(car1.get().getId()).isEqualTo(car.getId());
        assertThat(car1.get().getName()).isNotNull().isEqualTo("car name1");
        assertThat(car1.get().getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car1.get().getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car1.get().getOwners()).isNotNull();
        assertThat(car1.get().getOwners().size()).isNotNull().isEqualTo(1);
        assertThat(car1.get().getOwners()).isEqualTo(Set.of(driver));
        car.setName("mitsubishi pajero sport");
        repository.update(car);
        car1 = repository.findById(car.getId());
        assertThat(car1).isNotEqualTo(Optional.empty());
        assertThat(car1.get().getId()).isEqualTo(car.getId());
        assertThat(car1.get().getName()).isNotNull().isEqualTo("mitsubishi pajero sport");
        assertThat(car1.get().getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car1.get().getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car1.get().getOwners()).isNotNull();
        assertThat(car1.get().getOwners().size()).isNotNull().isEqualTo(1);
        assertThat(car1.get().getOwners()).isEqualTo(Set.of(driver));
        repository.delete(car.getId());
        Optional<Car> car2 = repository.findById(car.getId());
        assertThat(car2).isEqualTo(Optional.empty());
        driverRepository.delete(driver.getId());
        assertThat(driverRepository.findById(driver.getId())).isEqualTo(Optional.empty());
    }

    @Test
    void whenCreateFindAllDelete() {
        UserRepository userRepository = new UserRepository(new CrudRepository(sf));
        assertThat(userRepository).isNotNull();
        Optional<User> user = userRepository.findByLogin("Ivanov");
        assertThat(user).isNotEqualTo(Optional.empty());

        EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
        assertThat(engineRepository).isNotNull();
        List<Engine> engines = engineRepository.findAllOrderById();
        assertThat(engines).isNotNull();
        assertThat(engines.size()).isNotEqualTo(0);
        Engine engine = engines.get(0);
        assertThat(engine).isNotNull();

        DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
        Driver driver = new Driver();
        assertThat(driver).isNotNull();
        driver.setName("driver name1");
        driver.setUser(user.get());
        driver = driverRepository.create(driver);
        assertThat(driver).isNotNull();

        CarRepository repository = new CarRepository(new CrudRepository(sf));
        assertThat(repository).isNotNull();
        List<Car> cars = repository.findAllOrderById();
        assertThat(cars).isNotNull();
        int size = cars.size();

        Car car = new Car();
        car.setName("car name1");
        car.setEngine(engine);
        car.setOwners(Set.of(driver));
        car = repository.create(car);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotEqualTo(0);
        assertThat(car.getName()).isNotNull().isEqualTo("car name1");
        assertThat(car.getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car.getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car.getOwners()).isNotNull();
        assertThat(car.getOwners().size()).isEqualTo(1);
        assertThat(car.getOwners()).isEqualTo(Set.of(driver));
        cars = repository.findAllOrderById();
        assertThat(cars).isNotNull();
        assertThat(cars.size()).isEqualTo(size + 1);
        Car car1 = cars.get(size);
        assertThat(car1).isNotEqualTo(Optional.empty());
        assertThat(car1.getId()).isEqualTo(car.getId());
        assertThat(car1.getName()).isNotNull().isEqualTo("car name1");
        assertThat(car1.getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car1.getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car1.getOwners()).isNotNull();
        assertThat(car1.getOwners().size()).isNotNull().isEqualTo(1);
        assertThat(car1.getOwners()).isEqualTo(Set.of(driver));
        repository.delete(car.getId());
        cars = repository.findAllOrderById();
        assertThat(cars).isNotNull();
        assertThat(cars.size()).isEqualTo(size);
        driverRepository.delete(driver.getId());
        assertThat(driverRepository.findById(driver.getId())).isEqualTo(Optional.empty());
    }

    @Test
    void whenCreateFindByLikeNameDelete() {
        UserRepository userRepository = new UserRepository(new CrudRepository(sf));
        assertThat(userRepository).isNotNull();
        Optional<User> user = userRepository.findByLogin("Ivanov");
        assertThat(user).isNotEqualTo(Optional.empty());

        EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
        assertThat(engineRepository).isNotNull();
        List<Engine> engines = engineRepository.findAllOrderById();
        assertThat(engines).isNotNull();
        assertThat(engines.size()).isNotEqualTo(0);
        Engine engine = engines.get(0);
        assertThat(engine).isNotNull();

        DriverRepository driverRepository = new DriverRepository(new CrudRepository(sf));
        Driver driver = new Driver();
        assertThat(driver).isNotNull();
        driver.setName("driver name1");
        driver.setUser(user.get());
        driver = driverRepository.create(driver);
        assertThat(driver).isNotNull();

        CarRepository repository = new CarRepository(new CrudRepository(sf));
        assertThat(repository).isNotNull();
        List<Car> cars = repository.findByLikeName("mitsubishi");
        int size = cars != null ? cars.size() : 0;

        Car car = new Car();
        car.setName("mitsubishi pajero");
        car.setEngine(engine);
        car.setOwners(Set.of(driver));
        car = repository.create(car);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isNotEqualTo(0);
        assertThat(car.getName()).isNotNull().isEqualTo("mitsubishi pajero");
        assertThat(car.getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car.getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car.getOwners()).isNotNull();
        assertThat(car.getOwners().size()).isEqualTo(1);
        assertThat(car.getOwners()).isEqualTo(Set.of(driver));
        cars = repository.findByLikeName("mitsubishi");
        assertThat(cars).isNotNull();
        assertThat(cars.size()).isEqualTo(size + 1);
        Car car1 = cars.get(size);
        assertThat(car1).isNotEqualTo(Optional.empty());
        assertThat(car1.getId()).isEqualTo(car.getId());
        assertThat(car1.getName()).isNotNull().isEqualTo("mitsubishi pajero");
        assertThat(car1.getEngine().getId()).isEqualTo(engine.getId());
        assertThat(car1.getEngine().getName()).isEqualTo(engine.getName());
        assertThat(car1.getOwners()).isNotNull();
        assertThat(car1.getOwners().size()).isNotNull().isEqualTo(1);
        assertThat(car1.getOwners()).isEqualTo(Set.of(driver));
        repository.delete(car.getId());
        cars = repository.findByLikeName("mitsubishi");
        assertThat(cars).isNotNull();
        assertThat(cars.size()).isEqualTo(size);
        driverRepository.delete(driver.getId());
        assertThat(driverRepository.findById(driver.getId())).isEqualTo(Optional.empty());
    }
}

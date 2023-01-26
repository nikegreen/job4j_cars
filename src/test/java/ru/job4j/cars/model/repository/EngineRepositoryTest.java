package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.cars.model.Engine;
//import ru.job4j.cars.model.Photo;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

class EngineRepositoryTest {
    @Test
    void whenCreateFindAllOrderByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
            assertThat(engineRepository).isNotNull();
            List<Engine> engines = engineRepository.findAllOrderById();
            assertThat(engines).isNotNull();
            int size = engines.size();
            Engine engine = new Engine();
            engine.setName("g4js");
            Engine engine1 = engineRepository.create(engine);
            assertThat(engine1).isNotNull();
            assertThat(engine1.getId()).isNotEqualTo(0);
            assertThat(engine1.getName()).isEqualTo("g4js");

            List<Engine> engines1 = engineRepository.findAllOrderById();
            assertThat(engines1).isNotNull().hasSize(size + 1);

            Engine engine2 = engines1.get(size);
            assertThat(engine2).isNotNull();
            assertThat(engine2.getId()).isEqualTo(engine1.getId());
            assertThat(engine2.getName()).isEqualTo("g4js");

            engineRepository.delete(engine1.getId());
            List<Engine> engines2 = engineRepository.findAllOrderById();
            assertThat(engines2).isNotNull().hasSize(size);
        }
    }

    @Test
    void whenCreateFindAllWherePostDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
            assertThat(engineRepository).isNotNull();
            List<Engine> engines = engineRepository.findByLikeName("g4");
            assertThat(engines).isNotNull();
            int size = engines.size();
            Engine engine = new Engine();
            engine.setName("g4js");
            Engine engine1 = engineRepository.create(engine);
            assertThat(engine1).isNotNull();
            assertThat(engine1.getId()).isNotEqualTo(0);
            assertThat(engine1.getName()).isEqualTo("g4js");

            List<Engine> engines1 = engineRepository.findByLikeName("g4");
            assertThat(engines1).isNotNull().hasSize(size + 1);

            Engine engine2 = engines1.get(size);
            assertThat(engine2).isNotNull();
            assertThat(engine2.getId()).isEqualTo(engine1.getId());
            assertThat(engine2.getName()).isEqualTo("g4js");

            engineRepository.delete(engine1.getId());
            List<Engine> photos3 = engineRepository.findByLikeName("g4");
            assertThat(photos3).isNotNull().hasSize(size).containsExactlyElementsOf(engines);
        }
    }

    @Test
    void whenCreateFindByIdDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
            assertThat(engineRepository).isNotNull();
            Engine engine = new Engine();
            engine.setName("g4js");
            Engine engine1 = engineRepository.create(engine);
            assertThat(engine1).isNotNull();
            assertThat(engine1.getId()).isNotEqualTo(0);
            assertThat(engine1.getName()).isEqualTo("g4js");

            Optional<Engine> engine2 = engineRepository.findById(engine1.getId());
            assertThat(engine2).isNotEqualTo(Optional.empty());
            assertThat(engine2.get().getId()).isEqualTo(engine1.getId());
            assertThat(engine2.get().getName()).isEqualTo("g4js");

            engineRepository.delete(engine1.getId());
            Optional<Engine> engine3 = engineRepository.findById(engine1.getId());
            assertThat(engine3).isEqualTo(Optional.empty());
        }
    }

    @Test
    void whenCreateFindByIdUpdateDelete() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            EngineRepository engineRepository = new EngineRepository(new CrudRepository(sf));
            assertThat(engineRepository).isNotNull();
            Engine engine = new Engine();
            engine.setName("g4js");
            Engine engine1 = engineRepository.create(engine);
            assertThat(engine1).isNotNull();
            assertThat(engine1.getId()).isNotEqualTo(0);
            assertThat(engine1.getName()).isEqualTo("g4js");

            Optional<Engine> engine2 = engineRepository.findById(engine1.getId());
            assertThat(engine2).isNotEqualTo(Optional.empty());
            assertThat(engine2.get().getId()).isEqualTo(engine1.getId());
            assertThat(engine2.get().getName()).isEqualTo("g4js");

            engine1.setName("g4js-c");
            engineRepository.update(engine1);
            assertThat(engine1).isNotNull();
            assertThat(engine1.getId()).isEqualTo(engine2.get().getId());
            assertThat(engine1.getName()).isEqualTo("g4js-c");
            engineRepository.delete(engine1.getId());
            Optional<Engine> engine3 = engineRepository.findById(engine1.getId());
            assertThat(engine3).isEqualTo(Optional.empty());
        }
    }
}
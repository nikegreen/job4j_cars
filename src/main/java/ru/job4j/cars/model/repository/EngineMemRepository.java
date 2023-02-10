package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class EngineMemRepository implements EngineAbstractRepository {
    private final Map<Integer, Engine> engines = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public EngineMemRepository() {
        List<String> engineNames = List.of(
                "бензин",
                "дизель",
                "гибрид",
                "газ",
                "электро",
                "газ-бензин",
                "газ-дизель"
        );
        engineNames.forEach(
                name -> {
                    Engine engine = new Engine();
                    engine.setId(counter.incrementAndGet());
                    engine.setName(name);
                    engines.putIfAbsent(
                            engine.getId(),
                            engine);
                }
        );
    }

    /**
     * Сохранить в базе двигателей.
     * @param engine двигатель.
     * @return двигатель с id.
     */
    @Override
    public Engine create(Engine engine) {
        engine.setId(counter.incrementAndGet());
        engines.putIfAbsent(engine.getId(), engine);
        return engine;
    }

    /**
     * Обновить в базе двигателей.
     * @param engine двигатель.
     */
    @Override
    public void update(Engine engine) {
        engines.replace(engine.getId(), engine);
    }

    /**
     * Удалить двигатель по id.
     * @param id ID
     */
    @Override
    public void delete(int id) {
        engines.remove(id);
    }

    /**
     * Список двигателей отсортированных по id.
     * @return список двигателей.
     */
    @Override
    public List<Engine> findAllOrderById() {
        return engines.values().stream()
                .sorted(Comparator.comparingInt(Engine::getId))
                .toList();
    }

    /**
     * Найти двигатель по ID
     * @param id ID
     * @return двигатель.
     */
    @Override
    public Optional<Engine> findById(int id) {
        return Optional.ofNullable(engines.getOrDefault(id, null));
    }

    /**
     * Список двигателей по имени LIKE %key%
     * @param key ключевая строка
     * @return список двигателей.
     */
    public List<Engine> findByLikeName(String key) {
        return engines.values().stream().filter(engine -> engine.getName().contains(key)).toList();
    }
}

package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Engine;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об двигателях
 */
@AllArgsConstructor
public class EngineRepository {
    private final CrudRepository crudRepository;
    /**
     * Сохранить в базе двигателей.
     * @param engine двигатель.
     * @return двигатель с id.
     */
    public Engine create(Engine engine) {
        return  crudRepository.create(engine);
    }

    /**
     * Обновить в базе двигателей.
     * @param engine двигатель.
     */
    public void update(Engine engine) {
        crudRepository.update(engine);
    }

    /**
     * Удалить двигатель по id.
     * @param id ID
     */
    public void delete(int id) {
        Engine engine = new Engine();
        engine.setId(id);
        crudRepository.delete(engine);
    }

    /**
     * Список двигателей отсортированных по id.
     * @return список двигателей.
     */
    public List<Engine> findAllOrderById() {
        return crudRepository.findAll(Engine.class);
    }

    /**
     * Найти двигатель по ID
     * @return двигатель.
     */
    public Optional<Engine> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, Engine.class));
    }

    /**
     * Список двигателей по имени LIKE %key%
     * @param key ключевая строка
     * @return список двигателей.
     */
    public List<Engine> findByLikeName(String key) {
        return crudRepository.tx(session -> session.createQuery(
                        "from Engine i where i.name like :fKey order by id", Engine.class)
                .setParameter("fKey", '%' + key + '%').list());
    }
}

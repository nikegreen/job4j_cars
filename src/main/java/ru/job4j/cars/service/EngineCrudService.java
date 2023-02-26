package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.repository.EngineRepository;
import java.util.List;
import java.util.Optional;

/**
 * Сервис типов двигателей в БД
 */
@Service
@RequiredArgsConstructor
public class EngineCrudService implements EngineAbstractService {
    private final EngineRepository engines;

    /**
     * Сохранить в базе двигателей.
     * @param engine двигатель.
     * @return двигатель с id.
     */
    @Override
    public Engine create(Engine engine) {
        return engines.create(engine);
    }

    /**
     * Обновить в базе двигателей.
     * @param engine двигатель.
     */
    @Override
    public void update(Engine engine) {
        engines.update(engine);
    }

    /**
     * Удалить двигатель по id.
     * @param id ID
     */
    @Override
    public void delete(int id) {
        engines.delete(id);
    }

    /**
     * Список двигателей отсортированных по id.
     * @return список двигателей.
     */
    @Override
    public List<Engine> findAllOrderById() {
        return engines.findAllOrderById();
    }

    /**
     * Найти двигатель по ID
     * @param id ID
     * @return двигатель.
     */
    @Override
    public Optional<Engine> findById(int id) {
        return engines.findById(id);
    }

    /**
     * Список двигателей по имени LIKE %key%
     * @param key ключевая строка
     * @return список двигателей.
     */
    @Override
    public List<Engine> findByLikeName(String key) {
        return engines.findByLikeName(key);
    }
}

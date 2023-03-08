package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Driver;
import ru.job4j.cars.model.repository.DriverRepository;

import java.util.List;
import java.util.Optional;

/**
 * сервис водителей.
 */
@Service
@RequiredArgsConstructor
public class DriverCrudService implements DriverAbstractService {
    private final DriverRepository drivers;

    /**
     * Сохранить в базе водителей.
     * @param driver водитель.
     * @return водитель с id.
     */
    @Override
    public Driver create(Driver driver) {
        return drivers.create(driver);
    }

    /**
     * Обновить в базе водителя.
     * @param driver водитель.
     */
    @Override
    public void update(Driver driver) {
        drivers.update(driver);
    }

    /**
     * Удалить водителя по id.
     * @param id ID
     */
    @Override
    public void delete(int id) {
        drivers.delete(id);
    }

    /**
     * Список водителей отсортированных по id.
     * @return список водителей.
     */
    @Override
    public List<Driver> findAllOrderById() {
        return drivers.findAllOrderById();
    }

    /**
     * Найти водителя по ID
     * @return водитель.
     */
    @Override
    public Optional<Driver> findById(int id) {
        return drivers.findById(id);
    }

    /**
     * Список водителей по имени LIKE %key%
     * @param key ключевая строка
     * @return список водителей.
     */
    @Override
    public List<Driver> findByLikeName(String key) {
        return drivers.findByLikeName(key);
    }
}

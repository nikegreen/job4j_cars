package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.repository.CarRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис автомобилей Hibernate CRUD
 */
@Service
@RequiredArgsConstructor
public class CarCrudService implements CarAbstractService {
    private final CarRepository cars;
    /**
     * Сохранить в базе.
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    @Override
    public Car create(Car car) {
        return cars.create(car);
    }

    /**
     * Обновить в базе автомобиль.
     * @param car автомобиль.
     */
    @Override
    public void update(Car car) {
        cars.update(car);
    }

    /**
     * Удалить автомобиль по id.
     * @param id ID
     */
    @Override
    public void delete(int id) {
        cars.delete(id);
    }

    /**
     * Список автомобилей отсортированных по id.
     * @return список автомобилей.
     */
    @Override
    public List<Car> findAllOrderById() {
        return cars.findAllOrderById();
    }

    /**
     * Найти автомобиль по ID
     * @return автомобиль.
     */
    @Override
    public Optional<Car> findById(int id) {
        return cars.findById(id);
    }

    /**
     * Список автомобилей по имени LIKE %key%
     * @param key ключевая строка
     * @return список автомобилей.
     */
    @Override
    public List<Car> findByLikeName(String key) {
        return cars.findByLikeName(key);
    }
}

package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.CarMarc;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Хранилище с информацией об автомобилях в ОЗУ
 */
@Repository
@RequiredArgsConstructor
public class CarMemRepository implements CarAbstractRepository {
    private final Map<Integer, Car> marcs = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    /**
     * Сохранить в базе.
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    public Car create(Car car) {
        if (car == null) {
            return null;
        }
        car.setId(counter.incrementAndGet());
        marcs.putIfAbsent(car.getId(), car);
        return car;
    }

    /**
     * Обновить в базе автомобиль.
     * @param car автомобиль.
     */
    public void update(Car car) {
        marcs.replace(car.getId(), car);
    }

    /**
     * Удалить автомобиль по id.
     * @param id ID
     */
    public void delete(int id) {
        marcs.remove(id);
    }

    /**
     * Список автомобилей отсортированных по id.
     * @return список автомобилей.
     */
    public List<Car> findAllOrderById() {
        return marcs.values().stream().sorted(Comparator.comparing(Car::getId)).toList();
    }

    /**
     * Найти автомобиль по ID
     * @return автомобиль.
     */
    public Optional<Car> findById(int id) {
        return Optional.ofNullable(marcs.getOrDefault(id, null));
    }

    /**
     * Список автомобилей по имени LIKE %key%
     * @param key ключевая строка
     * @return список автомобилей.
     */
    public List<Car> findByLikeName(String key) {
        return marcs.values()
                .stream()
                .filter(marc -> marc.getName().contains(key))
                .toList();
    }
}

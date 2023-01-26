package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import ru.job4j.cars.model.Car;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об автомобилях
 */
@AllArgsConstructor
public class CarRepository {
    private final CrudRepository crudRepository;
    /**
     * Сохранить в базе.
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    public Car create(Car car) {
        return  crudRepository.create(car);
    }

    /**
     * Обновить в базе автомобиль.
     * @param car автомобиль.
     */
    public void update(Car car) {
        crudRepository.update(car);
    }

    /**
     * Удалить автомобиль по id.
     * @param id ID
     */
    public void delete(int id) {
        Car car = new Car();
        car.setId(id);
        crudRepository.delete(car);
    }

    /**
     * Список автомобилей отсортированных по id.
     * @return список автомобилей.
     */
    public List<Car> findAllOrderById() {
        return crudRepository.tx(session -> session.createQuery(
                "from Car i left join fetch i.owners order by i.id", Car.class)
                .list());
    }

    /**
     * Найти автомобиль по ID
     * @return автомобиль.
     */
    public Optional<Car> findById(int id) {
        return Optional.ofNullable(
                crudRepository.tx(session ->
                   session.createQuery(
                           "from Car i join fetch i.owners where i.id = :fId",
                           Car.class)
                           .setParameter("fId", id)
                           .uniqueResult()
                )
        );
    }

    /**
     * Список автомобилей по имени LIKE %key%
     * @param key ключевая строка
     * @return список автомобилей.
     */
    public List<Car> findByLikeName(String key) {
        return crudRepository.tx(session -> session.createQuery(
                        "from Car i join fetch i.owners where i.name like :fKey order by i.id", Car.class)
                .setParameter("fKey", '%' + key + '%').list());
    }
}

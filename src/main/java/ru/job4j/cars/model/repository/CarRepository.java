package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об автомобилях
 */
@Repository
@AllArgsConstructor
public class CarRepository implements  CarAbstractRepository {
    /**
     * HQL
     */
    public static final String FIND_ALL =
            "from Car i left join fetch i.owners order by i.id";
    public static final String FIND_BY_ID =
            "from Car i join fetch i.owners where i.id = :fId";
    public static final String FIND_ALL_BY_NAME =
            "from Car i join fetch i.owners where i.name like :fKey order by i.id";

    /**
     * Hibernate CRUD repository
     */
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе.
     * @param car автомобиль.
     * @return автомобиль с id.
     */
    @Override
    public Car create(Car car) {
        return  crudRepository.create(car);
    }

    /**
     * Обновить в базе автомобиль.
     * @param car автомобиль.
     */
    @Override
    public void update(Car car) {
        crudRepository.update(car);
    }

    /**
     * Удалить автомобиль по id.
     * @param id ID
     */
    @Override
    public void delete(int id) {
        Car car = new Car();
        car.setId(id);
        crudRepository.delete(car);
    }

    /**
     * Список автомобилей отсортированных по id.
     * @return список автомобилей.
     */
    @Override
    public List<Car> findAllOrderById() {
        return crudRepository.tx(
                session -> session.createQuery(FIND_ALL, Car.class).list()
        );
    }

    /**
     * Найти автомобиль по ID
     * @return автомобиль.
     */
    @Override
    public Optional<Car> findById(int id) {
        return Optional.ofNullable(
                crudRepository.tx(session ->
                   session.createQuery(
                           FIND_BY_ID,
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
    @Override
    public List<Car> findByLikeName(String key) {
        return crudRepository.tx(session -> session.createQuery(
                        FIND_ALL_BY_NAME,
                        Car.class)
                .setParameter("fKey", '%' + key + '%').list());
    }
}

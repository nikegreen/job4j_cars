package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об кузовах автомобилей в БД
 */
@Repository
@RequiredArgsConstructor
public class CarBodyCrudRepository implements CarBodyAbstractRepository {
    public static final String FIND_BY_NAME = "from CarBody i where i.name :fName";
    private final CrudRepository crudRepository;

    /**
     * Список  отсортированных по id.
     * @return список типов корпусов автомобилей.
     */
    public List<CarBody> findAll() {
        return crudRepository.findAll(CarBody.class);
    }

    /**
     * Найти тип корпуса автомобиля по ID
     * @param id идентификотор типа корпуса
     * @return тип корпуса автомобиля.
     */
    public Optional<CarBody> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, CarBody.class));
    }

    /**
     * Найти тип корпуса автомобиля по имени
     * @param bodyName имени типа корпуса
     * @return тип корпуса автомобиля.
     */
    @Override
    public Optional<CarBody>  findByName(String bodyName) {
        return crudRepository.tx(session -> session.createQuery(FIND_BY_NAME, CarBody.class)
                .setParameter("fName", bodyName).uniqueResultOptional());
    }
}

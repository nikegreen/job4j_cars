package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CarBodyCrudRepository implements CarBodyAbstractRepository {
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

}

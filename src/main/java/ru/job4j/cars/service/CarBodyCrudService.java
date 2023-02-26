package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarBody;
import ru.job4j.cars.model.repository.CarBodyCrudRepository;
import java.util.List;
import java.util.Optional;

/**
 * Сервис кузовов авто в БД
 */
@Service
@RequiredArgsConstructor
public class CarBodyCrudService implements  CarBodyAbstractService {
    private final CarBodyCrudRepository bodies;

    /**
     * Список  отсортированных по id.
     * @return список типов корпусов автомобилей.
     */
    @Override
    public List<CarBody> findAll() {
        return bodies.findAll();
    }

    /**
     * Найти тип корпуса автомобиля по ID
     * @param id идентификотор типа корпуса
     * @return тип корпуса автомобиля.
     */
    @Override
    public Optional<CarBody> findById(int id) {
        return bodies.findById(id);
    }
}

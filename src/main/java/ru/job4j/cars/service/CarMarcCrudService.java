package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarMarc;
import ru.job4j.cars.model.repository.CarMarcCrudRepository;
import java.util.List;
import java.util.Optional;

/**
 * Сервис марок авто в БД
 */
@Service
@RequiredArgsConstructor
public class CarMarcCrudService implements CarMarcAbstractService {
    private final CarMarcCrudRepository marcs;

    /**
     * Список авто марок отсортированных по id.
     * @return список авто марок.
     */
    @Override
    public List<CarMarc> findAll() {
        return marcs.findAll();
    }

    /**
     * Найти авто марку по ID
     * @param id идентификотор авто марки
     * @return авто марка.
     */
    @Override
    public Optional<CarMarc> findById(int id) {
        return marcs.findById(id);
    }

}

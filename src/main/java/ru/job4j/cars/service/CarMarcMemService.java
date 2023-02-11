package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarMarc;
import ru.job4j.cars.model.repository.CarMarcMemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarMarcMemService implements CarMarcAbstractService {
    private final CarMarcMemRepository marcs;

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

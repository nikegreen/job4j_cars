package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.model.repository.CarModelMemRepository;
import java.util.List;
import java.util.Optional;

/**
 * Сервис моделей авто в ОЗУ
 */
@Service
@RequiredArgsConstructor
public class CarModelMemService implements CarModelAbstractService {
    private final CarModelMemRepository models;

    /**
     * Список моделей отсортированных по id.
     * @return список моделей авто марки.
     */
    @Override
    public List<CarModel> findAll() {
        return models.findAll();
    }

    /**
     * Найти модель авто марки по ID
     * @param id идентификотор модели авто марки
     * @return модель авто марки.
     */
    @Override
    public Optional<CarModel> findById(int id) {
        return models.findById(id);
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по идентфикатору авто марки.
     * @param id идентификотор авто марки
     * @return список моделей авто марки.
     */
    @Override
    public List<CarModel> findAllByMarcId(int id) {
        return models.findAllByMarcId(id);
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по типу кузова авто.
     * @param id идентификотор типа кузова авто
     * @return список всех моделей всех авто марок
     * с выбранным типом кузова.
     */
    @Override
    public List<CarModel> findAllByBodyId(int id) {
        return models.findAllByBodyId(id);
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по марке и типу кузова авто.
     * @param marcId идентификотор авто марки
     * @param bodyId идентификотор типа кузова авто
     * @return список всех моделей всех авто марок
     * с выбранной маркой и типом кузова.
     */
    @Override
    public List<CarModel> findAllByMarcIdAndBodyId(int marcId, int bodyId) {
        return models.findAllByMarcIdAndBodyId(marcId, bodyId);
    }
}

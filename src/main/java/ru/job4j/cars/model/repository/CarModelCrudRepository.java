package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CarModelCrudRepository implements  CarModelAbstractRepository {
    CrudRepository crudRepository;

    /**
     * Список  отсортированных по id.
     * @return список моделей авто марки.
     */
    @Override
    public List<CarModel> findAll() {
        return crudRepository.findAll(CarModel.class);
    }

    /**
     * Найти модель авто марки по ID
     * @param id идентификотор модели авто марки
     * @return модель авто марки.
     */
    @Override
    public Optional<CarModel> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, CarModel.class));
    }

    /**
     * Список моделей отсортированных по id
     * для выбранной по идентфикатору авто марки.
     * @param id идентификотор авто марки
     * @return список моделей авто марки.
     */
    @Override
    public List<CarModel> findAllByMarcId(int id) {
        return crudRepository.tx(session -> session.createQuery(
                        "from CarModel i where i.marcId = :fId order by i.id", CarModel.class)
                .setParameter("fId", id).list());
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
        return crudRepository.tx(session -> session.createQuery(
                        "from CarModel i where i.bodyId = :fId order by i.id", CarModel.class)
                .setParameter("fId", id).list());
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
        return crudRepository.tx(session -> session.createQuery(
                "from CarModel i where i.marcId = :fMarcId and i.bodyId = :fBodyId order by i.id",
                        CarModel.class)
                .setParameter("fMarcId", marcId)
                .setParameter("fBodyId", bodyId)
                .list());
    }
}

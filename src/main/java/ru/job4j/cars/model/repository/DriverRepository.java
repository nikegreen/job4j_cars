package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Driver;
import java.util.List;
import java.util.Optional;

/**
 * Хранилище с информацией об водителях
 */
@Repository
@AllArgsConstructor
public class DriverRepository implements DriverAbstractRepository {
    /**
     * HQL
     */
    public static final String FIND_ALL_BY_NAME =
            "from Driver i where i.name like :fKey order by id";

    /**
     * Hibernate CRUD хранилище
     */
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе водителей.
     * @param driver водитель.
     * @return водитель с id.
     */
    @Override
    public Driver create(Driver driver) {
        return  crudRepository.create(driver);
    }

    /**
     * Обновить в базе водителя.
     * @param driver водитель.
     */
    @Override
    public void update(Driver driver) {
        crudRepository.update(driver);
    }

    /**
     * Удалить водителя по id.
     * @param id ID
     */
    @Override
    public void delete(int id) {
        Driver driver = new Driver();
        driver.setId(id);
        crudRepository.delete(driver);
    }

    /**
     * Список водителей отсортированных по id.
     * @return список водителей.
     */
    @Override
    public List<Driver> findAllOrderById() {
        return crudRepository.findAll(Driver.class);
    }

    /**
     * Найти водителя по ID
     * @return водитель.
     */
    @Override
    public Optional<Driver> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, Driver.class));
    }

    /**
     * Список водителей по имени LIKE %key%
     * @param key ключевая строка
     * @return список водителей.
     */
    @Override
    public List<Driver> findByLikeName(String key) {
        return crudRepository.tx(session -> session.createQuery(
                        FIND_ALL_BY_NAME, Driver.class)
                .setParameter("fKey", '%' + key + '%').list());
    }
}

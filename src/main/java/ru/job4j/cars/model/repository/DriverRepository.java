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
public class DriverRepository {
    private final CrudRepository crudRepository;

    /**
     * Сохранить в базе водителей.
     * @param driver водитель.
     * @return водитель с id.
     */
    public Driver create(Driver driver) {
        return  crudRepository.create(driver);
    }

    /**
     * Обновить в базе водителя.
     * @param driver водитель.
     */
    public void update(Driver driver) {
        crudRepository.update(driver);
    }

    /**
     * Удалить водителя по id.
     * @param id ID
     */
    public void delete(int id) {
        Driver driver = new Driver();
        driver.setId(id);
        crudRepository.delete(driver);
    }

    /**
     * Список водителей отсортированных по id.
     * @return список водителей.
     */
    public List<Driver> findAllOrderById() {
        return crudRepository.findAll(Driver.class);
    }

    /**
     * Найти водителя по ID
     * @return водитель.
     */
    public Optional<Driver> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, Driver.class));
    }

    /**
     * Список водителей по имени LIKE %key%
     * @param key ключевая строка
     * @return список водителей.
     */
    public List<Driver> findByLikeName(String key) {
        return crudRepository.tx(session -> session.createQuery(
                        "from Driver i where i.name like :fKey order by id", Driver.class)
                .setParameter("fKey", '%' + key + '%').list());
    }
}

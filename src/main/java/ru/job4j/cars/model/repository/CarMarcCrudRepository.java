package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarMarc;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CarMarcCrudRepository implements CarMarcAbstractRepository {
    private final CrudRepository crudRepository;

    /**
     * Список авто марок отсортированных по id.
     * @return список авто марок.
     */
    @Override
    public List<CarMarc> findAll() {
        return crudRepository.findAll(CarMarc.class);
    }

    /**
     * Найти авто марку по ID
     * @param id идентификотор авто марки
     * @return авто марка.
     */
    @Override
    public Optional<CarMarc> findById(int id) {
        return Optional.ofNullable(crudRepository.findById(id, CarMarc.class));
    }

    /**
     * Найти авто марку по имени
     * @param name имя авто марки
     * @return ID авто марки, 0 если нет такого имени.
     */
    public int findIdByName(String name) {
        return crudRepository.tx(
                session -> session.createQuery(
                "from CarMarc i where i.name = :fName", CarMarc.class)
                .setParameter("fName", name)
                        .uniqueResultOptional()
                        .orElse(new CarMarc())
                        .getId()
        );
    }

}

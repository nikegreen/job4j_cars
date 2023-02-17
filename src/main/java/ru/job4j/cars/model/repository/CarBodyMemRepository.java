package ru.job4j.cars.model.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CarBodyMemRepository implements CarBodyAbstractRepository {
    private final Map<Integer, CarBody> bodies = new ConcurrentHashMap<>();

    public CarBodyMemRepository() {
        List<String> bodyNames = List.of(
                "седан",
                "хэтчбек 3 дверей",
                "хэтчбек 5 дверей",
                "лифтбек",
                "внедорожник 3 двери",
                "внедорожник 5 двери",
                "универсал",
                "купе",
                "минивен",
                "пикап",
                "лимузин",
                "фургон",
                "кабриолет"
        );
        bodyNames.forEach(name -> {
            int index = bodies.size() + 1;
            CarBody carBody = new CarBody();
            carBody.setId(index);
            carBody.setName(name);
            bodies.put(index, carBody);
        });
    }


    /**
     * Список  отсортированных по id.
     * @return список типов корпусов автомобилей.
     */
    @Override
    public List<CarBody> findAll() {
        return bodies.values().stream().toList();
    }

    /**
     * Найти тип корпуса автомобиля по ID
     * @param id идентификотор типа корпуса
     * @return тип корпуса автомобиля.
     */
    @Override
    public Optional<CarBody> findById(int id) {
        return Optional.ofNullable(bodies.getOrDefault(id, null));
    }

    /**
     * Найти тип корпуса автомобиля по имени
     * @param bodyName имени типа корпуса
     * @return тип корпуса автомобиля.
     */
    @Override
    public Optional<CarBody>  findByName(String bodyName) {
        return bodies.values()
                .stream()
                .filter(body -> body.getName() != null && body.getName().equals(bodyName))
                .findFirst();
    }
}

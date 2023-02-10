package ru.job4j.cars.service;

import ru.job4j.cars.model.PostDto;
import java.util.List;
import java.util.Optional;

public interface PostDtoAbstractService {

    /**
     * Список всех объявлений (для отображения)
     * @return список всех объявлений (для отображения).
     */
    List<PostDto> findAll();

    /**
     * Найти объявления по ID
     * @param id - идентификатор объявления
     * @return объявление (для отображения).
     */
    Optional<PostDto> findById(int id);
}

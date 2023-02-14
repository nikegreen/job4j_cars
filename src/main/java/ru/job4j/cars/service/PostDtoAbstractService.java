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

    /**
     * Список всех объявлений по фильтру (для отображения)
     * @param filter - параметры фильтра тип: {@link ru.job4j.cars.model.UserDto}
     *               filter.statusId     - фильтрация по статусу. Если = 0, то без фильтрации.
     *               filter.car.marc.id  - фильтрация по марке. Если = 0, то без фильтрации.
     *               filter.car.model.id - фильтрация по модели. Если = 0, то без фильтрации.
     *               filter.car.bodyId   - фильтрация по модели. Если = 0, то без фильтрации.
     *               filter.price        - фильтрация по цене (цена авто равна или ниже в фильтре).
     * @return список всех отфильтрованных объявлений (для отображения).
     */
    List<PostDto> findAllByFilter(PostDto filter);
}

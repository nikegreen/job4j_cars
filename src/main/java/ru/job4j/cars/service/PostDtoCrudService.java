package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PostDto;
import ru.job4j.cars.model.repository.PostDtoCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Сервис DTO объявлений в БД
 */
@Service
@RequiredArgsConstructor
public class PostDtoCrudService implements PostDtoAbstractService {
    private final PostDtoCrudRepository posts;

    /**
     * Список всех объявлений (для отображения)
     * @return список всех объявлений (для отображения).
     */
    @Override
    public List<PostDto> findAll() {
        return posts.findAll();
    }

    /**
     * Найти объявления по ID
     * @param id - идентификатор объявления
     * @return объявление (для отображения).
     */
    @Override
    public Optional<PostDto> findById(int id) {
        return posts.findById(id);
    }

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
    @Override
    public List<PostDto> findAllByFilter(PostDto filter) {
        return posts.findAllByFilter(filter);
    }
}

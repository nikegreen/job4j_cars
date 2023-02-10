package ru.job4j.cars.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.model.PostDto;
import ru.job4j.cars.model.repository.PostDtoCrudRepository;

import java.util.List;
import java.util.Optional;

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
}

package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PostDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDtoMemRepository implements PostDtoAbstractRepository {
    private final PostMemRepository posts;

    /**
     * Список всех объявлений (для отображения)
     * @return список всех объявлений (для отображения).
     */
    @Override
    public List<PostDto> findAll() {
        List<PostDto> result = new ArrayList<>();
        posts.findAllOrderById().forEach(
                post -> result.add(PostDto.fromPost(post))
        );
        return result;
    }

    /**
     * Найти объявления по ID
     *
     * @param id - идентификатор объявления
     * @return объявление (для отображения).
     */
    public Optional<PostDto> findById(int id) {
        return  Optional.ofNullable(PostDto.fromPost(posts.findById(id).orElse(null)));
    }
}

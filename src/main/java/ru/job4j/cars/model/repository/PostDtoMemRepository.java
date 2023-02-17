package ru.job4j.cars.model.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;
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
                post -> {
                    PostDto postDto = PostDto.fromPost(post);
                    postDto.setStatusId(1);
                    result.add(postDto);
                }
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
        if (filter == null) {
            return findAll();
        }
        return findAll().stream().filter(
            postDto -> {
                if (postDto.getPrice() > filter.getPrice()) {
                    return false;
                }
                Car car = filter.getCar();
                Car carDto = postDto.getCar();
                if (car != null) {
                    if (car.getMarc() != null
                            && car.getMarc().getId() != 0) {
                        if (carDto.getMarc().getId() != car.getMarc().getId()) {
                            return false;
                        }
                    }
                    if (car.getModel() != null && car.getModel().getId() != 0) {
                        if (carDto.getModel().getId() != car.getModel().getId()) {
                            return false;
                        }
                    }
                    if (car.getBodyId() != 0) {
                        if (carDto.getBodyId() != car.getBodyId()) {
                            return false;
                        }
                    }
                    if (car.getEngine() != null && car.getEngine().getId() != 0) {
                        if (carDto.getEngine().getId() != car.getEngine().getId()) {
                            return false;
                        }
                    }
                }
                if (filter.getStatusId() != 0) {
                    if (postDto.getStatusId() != filter.getStatusId()) {
                        return false;
                    }
                }
                return true;
            }
        ).toList();
    }
}

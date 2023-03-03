package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.PostDto;
import ru.job4j.cars.service.*;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>IndexController class. Spring boot controller</p>
 * @author nikez
 * @version $Id: $Id
 */
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostCrudService posts;
    private final CarMarcCrudService marcs;
    private final CarModelCrudService models;
    private final CarBodyCrudService bodies;
    private final EngineCrudService engines;

    /**
     * <p>index.</p>
     * Main page web service
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/")
    String indexRoot() {
        return "redirect:/index";
    }

    /**
     * <p>index.</p>
     * Main page web service GET /index
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/index")
    String index(Model model,
                 HttpSession session) {
        PostDto filter = (PostDto) session.getAttribute("filter");
        model.addAttribute("filter", filter);
        model.addAttribute("marcs", marcs.findAll());
        model.addAttribute("models", models.findAll());
        model.addAttribute("bodies", bodies.findAll());
        model.addAttribute("engines", engines.findAllOrderById());
        model.addAttribute("posts", findAllByFilter(filter));
        model.addAttribute("user", session.getAttribute("user"));
        return "index";
    }

    /**
     * <p>index.</p>
     * Main page web service POST /index
     * @param postFilter - содержит заполненный фильтр соххраним в {@param session}
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @return строка "redirect:/index";
     */
    @PostMapping("/index")
    String index(@ModelAttribute PostDto postFilter,
                 HttpSession session) {
        session.setAttribute("filter", postFilter);
        return "redirect:/index";
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
    private List<PostDto> findAllByFilter(PostDto filter) {
        if (filter == null) {
            List<Post> postList = posts.findAllOrderById();
            Post post = postList.get(0);
            return postList.stream()
                    .map(post1 -> PostDto.fromPost(post1))
                    .toList();
        }
        return posts.findAllOrderById().stream()
                .map(post -> PostDto.fromPost(post))
                .filter(
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
                                if (car.getModel() != null) {
                                    if (car.getModel().getId() != 0
                                            && carDto.getModel().getId() != car.getModel().getId()) {
                                        return false;
                                    }
                                    if (car.getModel().getBodyId() != 0
                                            && carDto.getModel().getBodyId() != car.getModel().getBodyId()) {
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

package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.model.*;
import ru.job4j.cars.service.*;
import ru.job4j.cars.util.ErrorPage;
import ru.job4j.cars.util.PhotoUtil;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * контроллер для объявлений
 */
@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostCrudService posts;
    private final PriceHistoryCrudService histories;
    private final PhotoCrudService photos;
    private final UserCrudService users;
    private final CarMarcCrudService marcs;
    private final CarModelCrudService models;
    private final CarBodyCrudService bodies;
    private final EngineCrudService engines;
    private final PhotoUtil photoUtil;

    /**
     * Контроллер GET страницы создания объявления /create
     * передаём в страницу коллекции:
     *  marcs  - список марок авто
     *  models - список моделей авто
     *  bodies - список типов кузова авто
     *  engines - список типов двигателей
     * user - авторизованный пользователь
     * @param model тип {@link org.springframework.ui.Model}
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @return строка "create".
     */
    @GetMapping("/create")
    String createPage(Model model,
                 HttpSession session) {
        model.addAttribute("marcs", marcs.findAll());
        model.addAttribute("models", models.findAll());
        model.addAttribute("bodies", bodies.findAll());
        model.addAttribute("engines", engines.findAllOrderById());
        model.addAttribute("user", session.getAttribute("user"));
        return "create";
    }

    /**
     * Контроллер POST страницы создания объявления /create
     * получаем из страницы:
     * @param postDto - созданное объявление
     * @param model тип {@link org.springframework.ui.Model}
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @param ids - массив идентификаторов фотографий
     * @param files - массив файлов с фотографией
     * @return строку
     * "redirect:/index" - ОК
     * "error"           - ошибка
     * @throws IOException создание и удаление файлов
     */
    @PostMapping("/create")
    String create(@ModelAttribute("postDto") PostDto postDto,
                  Model model,
                  HttpSession session,
                  @RequestParam("photos.id") int[] ids,
                  @RequestParam("files") MultipartFile[] files
                    ) throws IOException {

        if (session.getAttribute("user") == null || session.getAttribute("user").equals("Гость")) {
            return ErrorPage.error(
                    model, "Объявление не создано! Авторизуйтесь.", "/login");
        }
        if (postDto == null) {
            return ErrorPage.error(
                    model, "Объявление не создано! Нет получена информация.", "/index");
        }
        User user = users.findByLogin((String) session.getAttribute("user"))
                .orElse(users.findByLogin("Гость").orElse(null));
        if (postDto.getText() == null) {
            postDto.setText("");
        }
        postDto.getCar().setName("");
        Driver driver = new Driver();
        driver.setUser(user);
        driver.setName(user.getLogin());
        postDto.getCar().setOwners(Set.of(driver));
        postDto.getCar().setModel(
                models.findById(postDto.getCar().getModel().getId()).orElse(null)
        );
        postDto.getCar().setMarc(
                marcs.findById(postDto.getCar().getModel().getMarcId()).orElse(null)
        );
        Post post = new Post();
        post.setUser(user);
        post.setCreated(LocalDateTime.now());
        post.setCar(postDto.getCar());
        post.setPriceHistories(List.of());
        post.setParticipates(List.of());
        post.setText(postDto.getText());
        post.setPhotos(List.of());
        post = posts.create(post);
        if (post == null || post.getId() == 0) {
            return ErrorPage.error(
                    model,
                    "Объявление не создано! Не сохранено в списке объявлений.",
                    "/index"
            );
        }
        PriceHistory priceHistory = new PriceHistory(
                0,
                0,
                postDto.getPrice(),
                post.getCreated(),
                post.getId()
        );
        priceHistory = histories.create(priceHistory);
        if (priceHistory == null) {
            return ErrorPage.error(
                    model,
                    "Объявление создано! Но не сохранена цена.",
                    "/index"
            );
        }
        String errorMessage = photoUtil.savePhotosFromPage(ids, files, post);
        if (!"".equals(errorMessage)) {
            return ErrorPage.error(
                    model, "Объявление создано! Но не сохранены Фотографии.<br>"
                    + System.lineSeparator() + errorMessage, "/index");
        }
        return "redirect:/index";
    }

    /**
     * Контроллер GET страницы просмотра объявления /view
     * передаём в страницу коллекции:
     *  marcs  - список марок авто
     *  models - список моделей авто
     *  bodies - список типов кузова авто
     *  engines - список типов двигателей
     *  histories - список ищменений цены в объявлении
     * user - авторизованный пользователь
     * post1 - просматриваемое объявление
     * @param postId - идентификатор просматриваемого объявления
     * @param model тип {@link org.springframework.ui.Model}
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @return строка "view".
     */
    @GetMapping("/view")
    String viewPage(
            @RequestParam(value = "post", required = true) int postId,
            Model model,
            HttpSession session) {
        model.addAttribute("marcs", marcs.findAll());
        model.addAttribute("models", models.findAll());
        model.addAttribute("bodies", bodies.findAll());
        model.addAttribute("engines", engines.findAllOrderById());
        model.addAttribute("user", session.getAttribute("user"));
        Post post = posts.findById(postId).orElse(null);
        model.addAttribute("post1", PostDto.fromPost(post));
        model.addAttribute("histories",
                post != null ? post.getPriceHistories() : List.of());
        return "view";
    }

    /**
     * Контроллер POST страницы просмотра объявления /view
     * @return строку "redirect:/index";
     */
    @PostMapping("/view")
    String view() {

        return "redirect:/index";
    }

    /**
     * Контроллер GET страницы редактирования объявления /edit
     * передаём в страницу коллекции:
     *  marcs  - список марок авто
     *  models - список моделей авто
     *  bodies - список типов кузова авто
     *  engines - список типов двигателей
     * user - авторизованный пользователь
     * post1 - просматриваемое объявление
     * @param postId - идентификатор просматриваемого объявления
     * @param model тип {@link org.springframework.ui.Model}
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @return строка "edit".
     */
    @GetMapping("/edit")
    String editPage(
            @RequestParam(value = "post", required = true) int postId,
            Model model,
            HttpSession session) {
        model.addAttribute("marcs", marcs.findAll());
        model.addAttribute("models", models.findAll());
        model.addAttribute("bodies", bodies.findAll());
        model.addAttribute("engines", engines.findAllOrderById());
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("post1", PostDto.fromPost(posts.findById(postId).orElse(null)));
        return "edit";
    }

    /**
     * Контроллер POST страницы редактирования объявления /edit
     * получаем из страницы:
     * @param postDto - созданное объявление
     * @param model тип {@link org.springframework.ui.Model}
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @param ids - массив идентификаторов фотографий
     * @param files - массив файлов с фотографией
     * @return строку
     * "redirect:/index" - ОК
     * "error"           - ошибка
     *
     * @throws IOException запись и удаление файлов
     */
    @PostMapping("/edit")
    String edit(@ModelAttribute("postDto") PostDto postDto,
                Model model,
                HttpSession session,
                @RequestParam("photos.id") int[] ids,
                @RequestParam("files") MultipartFile[] files
    ) throws IOException {
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("Гость")) {
            return ErrorPage.error(
                    model, "Объявление не создано! Авторизуйтесь.", "/login");
        }
        if (postDto == null) {
            return ErrorPage.error(
                    model, "Объявление не создано! Нет получена информация.", "/index");
        }
        User user = users.findByLogin((String) session.getAttribute("user"))
                .orElse(users.findByLogin("Гость").orElse(null));
        if (postDto.getText() == null) {
            postDto.setText("");
        }
        Post post = posts.findById(postDto.getId()).orElse(new Post());
        if (post.getId() == 0) {
            return ErrorPage.error(
                    model,
                    "Объявление не обновлено! Не найдено объявления с id="
                            + postDto.getId() + "<br>",
                    "/index"
            );
        }
        PostDto oldPostDto = PostDto.fromPost(post);
        post.setText(postDto.getText());
        posts.update(post);
        if (post == null || post.getId() == 0) {
            return ErrorPage.error(
                    model,
                    "Объявление не обновлено!",
                    "/index"
            );
        }
        String errorMessage = "";
        if (postDto.getPrice() != oldPostDto.getPrice()) {
            PriceHistory priceHistory = new PriceHistory(
                    0,
                    oldPostDto.getPrice(),
                    postDto.getPrice(),
                    LocalDateTime.now(),
                    post.getId()
            );
            PriceHistory priceHistory1 = histories.create(priceHistory);
            if (priceHistory1 == null) {
                errorMessage += "Не сохранено изменение цены.<br>";
            }
        }
        if (postDto.getStatusId() != oldPostDto.getStatusId()) {
            PriceHistory priceHistory = null;
            if (postDto.getStatusId() == 2) {
                priceHistory = new PriceHistory(
                        0,
                        postDto.getPrice(),
                        postDto.getPrice(),
                        LocalDateTime.now(),
                        post.getId()
                );
            } else {
                priceHistory = new PriceHistory(
                        0,
                        0,
                        postDto.getPrice(),
                        LocalDateTime.now(),
                        post.getId()
                );
            }
            priceHistory = histories.create(priceHistory);
            if (priceHistory == null) {
                    errorMessage += "Объявление создано! Но не сохранена цена.<br>";
            }
        }
        List<Photo> photoList = photos.findAllWherePost(post.getId());
        List<Integer> idList =  Arrays.stream(ids).boxed().toList();
        photoList.forEach(photo -> {
                    if (!idList.contains(photo.getId())) {
                        photoUtil.delete(photo);
                    }
                });
        String errorMessage1 = photoUtil.savePhotosFromPage(ids, files, post);
        errorMessage += errorMessage1;
        if (!"".equals(errorMessage)) {
            return ErrorPage.error(
                    model, "Объявление создано! Но не сохранены Фотографии.<br>"
                            + System.lineSeparator() + errorMessage, "/index");
        }
        return "redirect:/index";
    }
}

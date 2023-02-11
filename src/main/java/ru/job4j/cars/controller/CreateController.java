package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.service.*;
import ru.job4j.cars.util.ErrorPage;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class CreateController {
    private final PostMemService posts;
    private final CarMarcMemService marcs;
    private final CarModelMemService models;
    private final CarBodyMemService bodies;
    private final EngineMemService engines;

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

    @PostMapping("/create")
    String create(@ModelAttribute Post post,
                  Model model,
                  HttpSession session) {
        if (session.getAttribute("user") == null || session.getAttribute("user").equals("Гость")) {
            return ErrorPage.error(
                   session, "Объявление не создано! Авторизуйтесь.", "/login");
        }
        if (post == null) {
            return ErrorPage.error(
                   session, "Объявление не создано! Нет получена информация.", "/index");
        }
        post = posts.create(post);
        if (post == null || post.getId() == 0) {
            return ErrorPage.error(
                   session, "Объявление не создано! Не сохранено в списке объявлений.", "/index");
        }
//        model.addAttribute("marcs", marcs.findAll());
//        model.addAttribute("models", models.findAll());
//        model.addAttribute("bodies", bodies.findAll());
//        model.addAttribute("engines", engines.findAllOrderById());
//        model.addAttribute("posts", posts.findAll());
//        model.addAttribute("user", session.getAttribute("user"));
        return "redirect:/index";
    }
}

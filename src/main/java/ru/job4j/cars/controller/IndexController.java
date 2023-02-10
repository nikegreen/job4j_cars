package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cars.service.*;

import javax.servlet.http.HttpSession;

/**
 * <p>IndexController class. Spring boot controller</p>
 * @author nikez
 * @version $Id: $Id
 */
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostDtoMemService posts;
    private final CarMarcMemService marcs;
    private final CarModelMemService models;
    private final CarBodyMemService bodies;
    private final EngineMemService engines;

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
     * Main page web service
     * @return a {@link java.lang.String} object.
     */
    @GetMapping("/index")
    String index(Model model,
                 HttpSession session) {
        model.addAttribute("marcs", marcs.findAll());
        model.addAttribute("models", models.findAll());
        model.addAttribute("bodies", bodies.findAll());
        model.addAttribute("engines", engines.findAllOrderById());
        model.addAttribute("posts", posts.findAll());
        model.addAttribute("user", session.getAttribute("user"));
        return "index";
    }
}

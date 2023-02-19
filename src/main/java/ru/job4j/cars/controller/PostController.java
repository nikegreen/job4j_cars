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

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PhotoUtil photoUtil;
    private final PriceHistoryMemService histories;
    private final PhotoMemService photos;
    private final UserMemService users;
    private final PostMemService posts;
    private final CarMarcMemService marcs;
    private final CarModelMemService models;
    private final CarBodyMemService bodies;
    private final EngineMemService engines;
    //private final long imageFileSizeMax = Integer.MAX_VALUE;

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
        postDto.setText("");
        postDto.getCar().setName("");
        Driver driver = new Driver();
        driver.setUser(user);
        driver.setName(user.getLogin());
        postDto.getCar().setOwners(Set.of(driver));
        postDto.getCar().setBodyId(postDto.getCar().getModel().getBodyId());
        Post post = new Post();
        post.setUser(user);
        post.setCreated(LocalDateTime.now());
        post.setCar(postDto.getCar());
        post.setPriceHistories(List.of());
        post.setParticipates(List.of());
        post.setText(postDto.getText());
        post.setPhotos(List.of());
        post = posts.create(post); // or find
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
        String errorMessage = photoUtil.SavePhotosFromPage(model, ids, files, post, photos);
        if (!"".equals(errorMessage)) {
            return ErrorPage.error(
                    model, "Объявление создано! Но не сохранены Фотографии.<br>"
                    + System.lineSeparator() + errorMessage, "/index");
        }
        return "redirect:/index";
    }


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
        model.addAttribute("post1", PostDto.fromPost(posts.findById(postId).orElse(null)));
        return "view";
    }

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
}

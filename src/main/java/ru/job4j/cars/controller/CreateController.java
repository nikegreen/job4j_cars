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
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class CreateController {
    private final PhotoMemService photos;
    private final UserMemService users;
    private final PostMemService posts;
    private final CarMarcMemService marcs;
    private final CarModelMemService models;
    private final CarBodyMemService bodies;
    private final EngineMemService engines;
    private final long imageFileSizeMax = Integer.MAX_VALUE;
    private final String imagesPath = "C:\\projects\\job4j_cars\\target\\classes\\static\\images\\";

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
                   session, "Объявление не создано! Авторизуйтесь.", "/login");
        }
        if (postDto == null) {
            return ErrorPage.error(
                   session, "Объявление не создано! Нет получена информация.", "/index");
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
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setBefore(0);
        priceHistory.setAfter(postDto.getPrice());
        priceHistory.setCreated(post.getCreated());
        post.setPriceHistories(List.of(priceHistory));
        post.setParticipates(List.of());
        post.setText(postDto.getText());
        post.setPhotos(List.of());
        post = posts.create(post); // or find

        List<Photo> photoList = new ArrayList<>();
        int count = 0;
        for (int id: ids) {
            if (id > 0) {
                Optional<Photo> photo = photos.findById(id);
                photo.ifPresent(photo1 ->  photoList.add(photo.get()));
            } else {
                MultipartFile file = files[count];
                if (file.getSize() <= imageFileSizeMax) {
                    Photo photo = new Photo();
                    photo.setName("");
                    photo.setFileName("f");
                    photo.setPost(post);
                    photo = photos.create(photo);
                    byte[] data = file.getBytes();
                    String type = file.getContentType();
                    String fn = "" + file.getOriginalFilename();
                    String ext = fn.substring(fn.length() - 3);
                    photo.setFileName("f" + photo.getId() + "." + ext);
                    try {
                        File outputFile = new File(imagesPath + photo.getFileName());
//                    BufferedImage input = ImageIO.read(file.getInputStream());
//                    File outputFile = new File(imagesPath + photo.getFileName());
//                    ImageIO.write(input, "PNG", outputFile);
//                    photoList.add(photo); //data:image/jpeg;base64,
                        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                            byte[] decoded = Base64.getDecoder().decode(data);
                            outputStream.write(decoded);
                            outputStream.flush();
                            photoList.add(photo);
                        }
                    } catch (Exception e) {
                        return ErrorPage.error(
                                session,
                                "Объявление не создано! Не сохранено фото." + e.getMessage(),
                                "/index");
                    }
                    //File inputFile = new File("image.gif");
                    //BufferedImage input = ImageIO.read(inputFile);
//                    BufferedImage input = ImageIO.read(file.getInputStream());
//                    File outputFile = new File(imagesPath + photo.getFileName());
//                    ImageIO.write(input, "PNG", outputFile);
                }

            }
            count++;
        }
//        post.setPhotos(photos);

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

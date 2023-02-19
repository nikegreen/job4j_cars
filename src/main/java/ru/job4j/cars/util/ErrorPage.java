package ru.job4j.cars.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.job4j.cars.config.LoadConfig;
import javax.servlet.http.HttpSession;

public class ErrorPage {
    public static String error(Model model, String message, String nextPage, String user) {
        model.addAttribute("user", user);
        return error(model, message, nextPage);
    }

    public static String error(Model model, String message, String nextPage) {
        model.addAttribute("error", message);
        model.addAttribute("link", nextPage);
        return "error";
    }
}

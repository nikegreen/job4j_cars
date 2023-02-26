package ru.job4j.cars.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserCrudService;

/**
 * Контроллер - регистрация
 */
@Controller
@RequiredArgsConstructor
public class RegControl {
    private final UserCrudService users;

    /**
     * Страница регистрации POST /reg
     * @param user регистрируемый пользователь
     * @return строка:
     * "redirect:/login" - OK
     * "redirect:/reg?error=true" - Ошибка.
     */
    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        if (users.create(user) == null) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }

    /**
     * Страница регистрации GET /reg
     * @param error - статус предыдущей регистрации, если не была
     * @param model тип {@link org.springframework.ui.Model}
     * @return строку "reg"
     */
    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username is busy or database error!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }
}

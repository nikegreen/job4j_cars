package ru.job4j.cars.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserCrudService;

import java.util.Optional;

/**
 * контроллер Авторизации пользователя
 */
@Controller
@RequiredArgsConstructor
public class LoginControl {
    private final UserCrudService users;

    /**
     * Страница авторизации пользователя. GET /login
     * @param error - статус ошибки о предыдущей попытки авторизации
     *              не обязательный
     * @param logout - статус де-авторизации до этого окна авторизации
     *               не обязательный
     * @param model тип {@link org.springframework.ui.Model}
     * @return строку "login";
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Страница авторизации пользователя. POST /login
     * @param user  пользователь
     * @param model тип {@link org.springframework.ui.Model}
     * @param req тип {@link javax.servlet.http.HttpServletRequest}
     * @return строку:
     * "redirect:/index" - OK
     * "/login" - Ошибка.
     */
    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        Model model,
                        HttpServletRequest req) {
        Optional<User> user1 = users.findByLogin(user.getLogin());
        if (user1.isEmpty() || !user1.get().getPassword().equals(user.getPassword())) {
            model.addAttribute("errorMessage", "Username or Password is incorrect !!");
            return "/login";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user1.get().getLogin());
        model.addAttribute("user", user1.get().getLogin());
        return "redirect:/index";
    }

    /**
     * Страница де-авторизации пользователя. GET /logout
     * @param session тип {@link javax.servlet.http.HttpSession}
     * @return строку "redirect:/login?logout=true";
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(
            HttpSession session
    ) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}

package ru.job4j.cars.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.UserMemService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginControl {
    private final UserMemService users;
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


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(
            HttpSession session
//            HttpServletRequest request, HttpServletResponse response
    ) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}

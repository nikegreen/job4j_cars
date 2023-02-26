package ru.job4j.cars.util;

import org.springframework.ui.Model;

/**
 * Класс утилита подготовки данных для страницы об ошибках
 */
public class ErrorPage {
    private ErrorPage() {
    }

    /**
     * Функция подготовки данных для страницы об ошибках
     * @param model - модели данных
     * @param message - сообщение на странице с ошибкой
     * @param nextPage - ссылка для перехода на страницу после страницы с ошибкой
     * @param user - пользователь сайта
     * @return строка "error"
     */
    public static String error(Model model, String message, String nextPage, String user) {
        model.addAttribute("user", user);
        return error(model, message, nextPage);
    }

    /**
     * Функция подготовки данных для страницы об ошибках
     * @param model - модели данных
     * @param message - сообщение на странице с ошибкой
     * @param nextPage - ссылка для перехода на страницу после страницы с ошибкой
     * @return строка "error"
     */
    public static String error(Model model, String message, String nextPage) {
        model.addAttribute("error", message);
        model.addAttribute("link", nextPage);
        return "error";
    }
}

package ru.job4j.cars.util;

import javax.servlet.http.HttpSession;

public class ErrorPage {
    public static String error(HttpSession session, String message, String nextPage, String user) {
        session.setAttribute("user", user);
        return error(session, message, nextPage);
    }

    public static String error(HttpSession session, String message, String nextPage) {
        session.setAttribute("error", message);
        session.setAttribute("link", nextPage);
        return "error";
    }
}

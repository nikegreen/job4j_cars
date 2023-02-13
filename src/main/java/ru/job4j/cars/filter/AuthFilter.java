package ru.job4j.cars.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class AuthFilter implements Filter {
    private final List<String> pages = List.of(
            "index",
            "login",
            "logout",
            "error",
            "reg",
            "bootstrap/css/bootstrap.min.css",
            "bootstrap/css/bootstrap.rtl.min.css",
            "bootstrap/css/bootstrap-grid.min.css",
            "bootstrap/css/bootstrap-reboot.min.css",
            "bootstrap/css/bootstrap-utilities.min.css",
            "bootstrap/css/bootstrap.min.css.map",
            "bootstrap/css/bootstrap.rtl.min.css.map",
            "bootstrap/css/bootstrap-grid.min.css.map",
            "bootstrap/css/bootstrap-reboot.min.css.map",
            "bootstrap/css/bootstrap-utilities.min.css.map",
            "bootstrap/js/bootstrap.bundle.min.js",
            "bootstrap/js/bootstrap.bundle.min.js.map",
            "css/signin.css",
            "css/style.css",
            "js/form.js"
    );

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (grantedPages(uri) || images(uri)) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, res);
    }

    private boolean grantedPages(String uri) {
        boolean result = false;
        try {
            for (String page: pages) {
                if (uri.endsWith(page)) {
                    result = true;
                    break;
                }
            }
        } finally {
            return result;
        }
    }

    private boolean images(String uri) {
        boolean result = false;
        try {
            if (uri.endsWith(".png") || uri.endsWith(".jpg")) {
                String s = uri.substring(0, uri.length() - 4);
                int i = s.lastIndexOf("/");
                if (i < 6) {
                    return false;
                }
                s = s.substring(0, i);
                result = s.endsWith("images");
            }
        } finally {
            return result;
        }
    }
}

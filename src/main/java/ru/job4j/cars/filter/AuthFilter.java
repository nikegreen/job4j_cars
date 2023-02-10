package ru.job4j.cars.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("index")
                || uri.endsWith("login")
                || uri.endsWith("logout")
                || uri.endsWith("error")
                || uri.endsWith("reg")
                || uri.endsWith("bootstrap/css/bootstrap.min.css")
                || uri.endsWith("bootstrap/css/bootstrap.rtl.min.css")
                || uri.endsWith("bootstrap/css/bootstrap-grid.min.css")
                || uri.endsWith("bootstrap/css/bootstrap-reboot.min.css")
                || uri.endsWith("bootstrap/css/bootstrap-utilities.min.css")
                || uri.endsWith("bootstrap/css/bootstrap.min.css.map")
                || uri.endsWith("bootstrap/css/bootstrap.rtl.min.css.map")
                || uri.endsWith("bootstrap/css/bootstrap-grid.min.css.map")
                || uri.endsWith("bootstrap/css/bootstrap-reboot.min.css.map")
                || uri.endsWith("bootstrap/css/bootstrap-utilities.min.css.map")
                || uri.endsWith("bootstrap/js/bootstrap.bundle.min.js")
                || uri.endsWith("bootstrap/js/bootstrap.bundle.min.js.map")
                || uri.endsWith("css/signin.css")
                || uri.endsWith("css/style.css")
        ) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        chain.doFilter(req, res);
    }
}

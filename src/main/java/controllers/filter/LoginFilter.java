package controllers.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.ServerSocket;

@WebFilter(urlPatterns = "/library")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getSession() != null && request.getSession().getAttribute("login") != null) {
            chain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}

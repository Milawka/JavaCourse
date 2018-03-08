package controllers;

import services.BookService;
import services.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/library")
public class BookListServlet extends HttpServlet{
    private static BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", bookService.getAllBooks());

        getServletContext().getRequestDispatcher("/library.jsp").forward(req, resp);
    }

    }

package project.spring.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.spring.model.entities.Book;
import project.spring.model.service.BookService;

import java.util.List;


@Controller
public class BookController {


    @Autowired
    BookService bookService;

    @RequestMapping(value = "/library", method = RequestMethod.GET )
    public String showBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "library";
    };
}

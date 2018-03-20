package project.spring.model.service;

import project.spring.model.entities.Book;

import java.util.List;

public interface BookService {
    Book getBookById(Long id);
    List<Book> getAllBooks();
}


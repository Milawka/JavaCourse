package services;

import model.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getById(Long id);
}

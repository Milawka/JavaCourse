package services;

import model.dao.impl.BookDAOImpl;
import model.dao.interfaces.BookDAO;
import model.entities.Book;

import java.util.List;

public class BookServiceImpl implements BookService {

    public static BookDAO bookDAO =  new BookDAOImpl();

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAll();
    }

    @Override
    public Book getById(Long id) {
        return bookDAO.getById(id);
    }
}

package project.spring.model.repository;

import org.springframework.data.repository.CrudRepository;
import project.spring.model.entities.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book getBookById(Long id);

    @Override
    Iterable<Book> findAll();
}

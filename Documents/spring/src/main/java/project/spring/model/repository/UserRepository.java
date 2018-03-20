package project.spring.model.repository;

import org.springframework.data.repository.CrudRepository;
import project.spring.model.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User getById(Long id);

    User getByLogin(String login);

    @Override
    User save(User entity);
}

package project.spring.model.service;

import project.spring.model.entities.User;

public interface UserService {
    User findUserbyId(Long id);

    User findUserByLogin(String login);

    User createNewUser(String login, String password);
}

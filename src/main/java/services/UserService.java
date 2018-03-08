package services;

import model.entities.User;

public interface UserService {
    User auth(String login, String password);
}

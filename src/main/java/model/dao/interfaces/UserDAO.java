package model.dao.interfaces;

import model.entities.User;

public interface UserDAO extends DAO<User, Long> {
    User findUserByLoginAndPassword(String login, String password);
}

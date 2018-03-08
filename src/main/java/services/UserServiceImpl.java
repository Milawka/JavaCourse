package services;

import model.dao.impl.UserDAOImpl;
import model.dao.interfaces.UserDAO;
import model.entities.User;

public class UserServiceImpl implements UserService {
    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public User auth(String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);
        return user;
    }
}

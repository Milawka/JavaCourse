package model.dao.impl;

import model.dao.interfaces.UserDAO;
import model.entities.User;
import utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    public User findUserByLoginAndPassword(String login, String password) {

    User user = null;
    String sql = "SELECT * FROM users WHERE login = ? AND password = ?;";

        try {
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        resultSet.next();
        user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"));

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
      //todo
    }

        return user;
}


//todo
    @Override
    public User getById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }



}

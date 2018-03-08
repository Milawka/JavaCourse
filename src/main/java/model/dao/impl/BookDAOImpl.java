package model.dao.impl;

import model.dao.interfaces.BookDAO;
import model.entities.Book;
import utils.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{

    @Override
    public Book getById(Long id) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);             // подставляем вместо вопроса айди которое пришло из параметра метода
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            book = new Book(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("author"), resultSet.getString("reference"));
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //todo
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        
        List<Book> list = new ArrayList<>();
        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books;");

            while (resultSet.next()) {
                Book book = new Book(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("author"), resultSet.getString("reference"));

                list.add(book);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //todo
        }

        return list;
    }
}

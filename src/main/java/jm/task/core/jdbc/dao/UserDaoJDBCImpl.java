package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  {
    Util util = new Util();
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        Connection connection = util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "create table users (id integer primary key auto_increment not null, name varchar(124), lastName varchar(124), age int)";
        try {
            statement.execute(query);
        } catch (SQLException e){
            System.out.println("Такая таблица с именем уже существует");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {

            }
        }
    }

    public void dropUsersTable() {

        Connection connection = util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "DROP TABLE IF EXISTS users";
        try {
            statement.execute(query);
        } catch (SQLException e){
            System.out.println("Такая таблица с именем уже существует");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        long ageLong = (long) age;
        Connection connection = util.getConnection();
        PreparedStatement preparedStatement = null;
        String query = "insert into users(name, lastname, age) values (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, ageLong);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = util.getConnection();
        PreparedStatement preparedStatement = null;
        String query = "delete from users where id=?";
        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong (1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println("данного юзера не существует");
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<User> getAllUsers() {

        Connection connection = util.getConnection();

        List <User> allUsers = new ArrayList<>();
        String query = "select id, name, lastname, age from users";
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge((byte) resultSet.getLong("age"));

                allUsers.add(user);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        Connection connection = util.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String query = "delete from users";
        try {
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Такой таблицы не существует");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
            }
        }
    }
}


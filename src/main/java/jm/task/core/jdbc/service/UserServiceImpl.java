package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Util implements UserService {
    Util util = new Util();
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernateImpl = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDaoHibernateImpl.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        userDaoHibernateImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age){
        userDaoHibernateImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernateImpl.removeUserById(id);
    }

    public List<User> getAllUsers()  {
        return userDaoHibernateImpl.getAllUsers();
    }

    public void cleanUsersTable()  {
        userDaoHibernateImpl.cleanUsersTable();
    }
}
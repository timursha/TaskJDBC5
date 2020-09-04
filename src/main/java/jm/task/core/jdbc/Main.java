package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
////        us.createUsersTable();
////        us.createUsersTable();
////        us.cleanUsersTable();
        us.saveUser("ivan", "ivanov", (byte)3);
        System.out.println(us.getAllUsers().get(0).toString());
    }
}

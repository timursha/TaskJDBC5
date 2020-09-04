package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/dbJMtest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("error - no connection");
        }
        return connection;
    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration()
                        .setProperty("hibernate.connector.driver_class",         "com.mysql.сj.jdbc.Driver")
                        .setProperty("hibernate.connection.url",                 "jdbc:mysql://localhost:3306/dbJMtest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT")
                        .setProperty("hibernate.connection.username",         "root")
                        .setProperty("hibernate.connection.password",         "root")
                        .setProperty("hibernate.dialect",                     "org.hibernate.dialect.MySQL5Dialect")
//                        .setProperty("hibernate.hbm2dll.auto",                "update")
//                        .setProperty("hibernate.show_sql",                    "true")
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
//commit
    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS users (id integer primary key auto_increment not null, name varchar(124), lastName varchar(124), age int)";
        int a = session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users";
        int a = session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }


    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String queryString = "delete from users where id=" + id;
        int a = session.createSQLQuery(queryString).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List <User> listUsers;
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        String queryStr = "select * from users";
        Query query = session.createSQLQuery(queryStr);
        listUsers = query.list();
        session.getTransaction().commit();
        System.out.println(listUsers);
        session.close();
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        String queryStr = "delete from users ";
        Query query = session.createSQLQuery(queryStr);
        int a = query.executeUpdate();
        transaction.commit();
        session.close();
    }
}

package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/mybd";
    private static final String USER  = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties props = new Properties();
            props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            props.put(Environment.URL, URL);
            props.put(Environment.USER, USER);
            props.put(Environment.PASS, PASSWORD);
            props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

            sessionFactory = new Configuration()
                    .setProperties(props)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

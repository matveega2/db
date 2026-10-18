package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/mybd";
    private static final String USER  = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection;

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

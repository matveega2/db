package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();

        String name1 = "Marina";
        String name2 = "Irina";
        String name3 = "Danil";
        String name4 = "Maxim";

        List<User> users = new ArrayList<>();

        userService.createUsersTable();
        userService.cleanUsersTable();
        userService.saveUser(name1, "Ivanova", (byte) 23);
        System.out.printf("User с именем - %s добавлен в базу данных%n",name1);
        userService.saveUser(name2, "Kozlova", (byte) 31);
        System.out.printf("User с именем - %s добавлен в базу данных%n",name2);
        userService.saveUser(name3, "Petrov", (byte) 14);
        System.out.printf("User с именем - %s добавлен в базу данных%n",name3);
        userService.saveUser(name4, "Sidorov", (byte) 40);
        System.out.printf("User с именем - %s добавлен в базу данных%n",name4);

        users = userService.getAllUsers();
        System.out.println(users);

        userService.cleanUsersTable();
        userService.dropUsersTable();


        Util.closeConnection();
        Util.closeSessionFactory();
    }
}

package utils;

import java.sql.*;


public class MyDatabase {

    final String URL="jdbc:mysql://localhost:4444/efbaa";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private Connection connection;
    private static MyDatabase instance;

    public MyDatabase() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            throw new RuntimeException(e);        }
    }


    public static MyDatabase getInstance() {
        if(instance == null)
            instance = new MyDatabase();
        return instance;
    }


    public Connection getConnection() {
        return connection;
    }


}

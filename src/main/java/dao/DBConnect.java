package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    protected static final String jdbcUrl = "jdbc:mysql://localhost:3306/HelmetManager";
    protected static final String dbUser = "root";
    protected static final String dbPass = "hello";
    public Connection connect() {
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
}

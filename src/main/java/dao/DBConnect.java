package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    protected static final String jdbcUrl = "jdbc:mysql://localhost:3306/HelmetManager";
    protected static final String dbUser = "root";
    protected static final String dbPass = "hello";
    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
    }
}

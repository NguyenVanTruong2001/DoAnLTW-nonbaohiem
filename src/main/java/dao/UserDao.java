package dao;

import beans.UserBean;

import java.sql.*;

public class UserDao {
    public UserBean checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HelmetManager";
        String dbUser = "root";
        String dbPass = "hello";
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        UserBean user = null;
        if (result.next()) {
            user = new UserBean();
            user.setUserId(result.getInt("UserID"));
            user.setUsername(result.getString("Username"));
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(result.getString("Role"));
        }

        connection.close();

        return user;
    }

    public boolean register(String username, String email, String password) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/HelmetManager";
        String dbUser = "root";
        String dbPass = "hello";
        String sql1 = "INSERT INTO Users(`Username`, `Email`, `Password`) VALUE (?, ?, ?)";
        String sql2 = "SELECT * FROM Users WHERE Email = ?";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

        PreparedStatement statement = connection.prepareStatement(sql2);
        statement.setString(1, email);

        ResultSet result = statement.executeQuery();

        if (result.next()) return false;
        else {
            statement = connection.prepareStatement(sql1);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            statement.executeUpdate();

            return true;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        UserBean userBean = userDao.checkLogin("tigernixon@gmail.com", "nixon");
        System.out.println(userBean.toString());
        System.out.println(userDao.register("GarrettWinters", "garrettwinters@gmail.com", "winters"));
        System.out.println(userDao.register("AstonCox", "ashtoncox@gmail.com", "cox"));
    }
}

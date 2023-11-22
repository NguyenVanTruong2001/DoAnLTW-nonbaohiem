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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        UserBean userBean = userDao.checkLogin("tigernixon@gmail.com", "nixon");
        System.out.println(userBean.toString());
    }
}

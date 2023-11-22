package dao;

import beans.UsersBean;

import java.sql.*;

public class UsersDao {
    public UsersBean checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
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

        UsersBean user = null;
        if (result.next()) {
            user = new UsersBean();
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
        UsersDao usersDao = new UsersDao();
        UsersBean usersBean = usersDao.checkLogin("tigernixon@gmail.com", "nixon");
        System.out.println(usersBean.toString());
    }
}

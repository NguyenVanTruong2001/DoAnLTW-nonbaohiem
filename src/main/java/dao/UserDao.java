package dao;

import beans.UserBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public UserBean checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM users WHERE email = ? and password = ?";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        UserBean user = new UserBean(0, "", "", "", "");
        if (result.next()) {
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
        String sql1 = "INSERT INTO Users(`Username`, `Email`, `Password`) VALUE (?, ?, ?)";
        String sql2 = "SELECT * FROM Users WHERE Email = ?";

        Connection connection = new DBConnect().connect();

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

            connection.close();
            return true;
        }
    }

    public List<UserBean> getAllUsers() throws ClassNotFoundException, SQLException {
        List<UserBean> list = new ArrayList<>();
        String sql = "SELECT * FROM `Users`";

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            UserBean bean = new UserBean(0, "", "", "", "");
            bean.setUserId(result.getInt(1));
            bean.setUsername(result.getString(2));
            bean.setEmail(result.getString(3));
            bean.setPassword(result.getString(4));
            bean.setRole(result.getString(5));
            list.add(bean);
        }

        connection.close();
        return list;
    }

    public UserBean getUserById(int userId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM users WHERE `UserID` = ?";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);

        ResultSet result = statement.executeQuery();

        UserBean user = new UserBean(0, "", "", "", "");
        if (result.next()) {
            user.setUserId(userId);
            user.setUsername(result.getString(2));
            user.setEmail(result.getString(3));
            user.setPassword(result.getString(4));
            user.setRole(result.getString(5));
        }

        connection.close();
        return user;
    }

}

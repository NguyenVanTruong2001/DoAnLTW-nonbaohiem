package dao;

import java.sql.*;
import java.time.LocalDate;

public class CheckoutDao {
    public int checkout(int userId, String fullname, String telephone, String address, String paymentMethod) throws SQLException {
        String sql = "INSERT INTO Orders(`UserID`, `OrderDate`, `Fullname`, `Telephone`, `Address`, `PaymentMethod`) VALUE (?, ?, ?, ?, ?, ?)";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, userId);
        statement.setDate(2, Date.valueOf(LocalDate.now()));
        statement.setString(3, fullname);
        statement.setString(4, telephone);
        statement.setString(5, address);
        statement.setString(6, paymentMethod);

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) return resultSet.getInt(1);
        else return 0;


    }
}

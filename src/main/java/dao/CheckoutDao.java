package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CheckoutDao {
    public boolean checkout(int userId, String fullname, String telephone, String address, String paymentMethod) throws SQLException {
        String sql = "INSERT INTO Orders(`UserID`, `OrderDate`, `Fullname`, `Telephone`, `Address`, `PaymentMethod`) VALUE (?, ?, ?, ?, ?, ?)";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setDate(2, Date.valueOf(LocalDate.now()));
        statement.setString(3, fullname);
        statement.setString(4, telephone);
        statement.setString(5, address);
        statement.setString(6, paymentMethod);

        return statement.executeUpdate() > 0;
    }
}

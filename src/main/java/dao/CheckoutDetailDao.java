package dao;

import beans.CheckoutDetailBean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CheckoutDetailDao {
    public boolean addCheckoutDetail(CheckoutDetailBean checkoutDetail) throws SQLException {
        String sql = "INSERT INTO OrderDetails(`OrderID`, `ProductID`, `Quantity`) VALUE (?, ?, ?)";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, checkoutDetail.getUserId());
        statement.setInt(2, checkoutDetail.getProductId());
        statement.setInt(3, checkoutDetail.getQuantity());

        return statement.executeUpdate() > 0;
    }
}

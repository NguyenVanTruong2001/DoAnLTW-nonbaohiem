package dao;

import beans.CheckoutDetailBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckoutDetailDao {
    public void addCheckoutDetail(CheckoutDetailBean checkoutDetail) throws SQLException {
        String sql = "INSERT INTO OrderDetails(`OrderID`, `ProductID`, `Quantity`) VALUE (?, ?, ?)";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, checkoutDetail.getOrderId());
        statement.setInt(2, checkoutDetail.getProductId());
        statement.setInt(3, checkoutDetail.getQuantity());

        statement.executeUpdate();
    }
}
package dao;

import beans.CheckoutBean;
import beans.CheckoutDetailBean;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<CheckoutDetailBean> getAllCheckoutDetails() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM OrderDetails";

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<CheckoutDetailBean> checkoutDetailList = new ArrayList<>();

        while (result.next()) {
            CheckoutDetailBean checkoutDetail = new CheckoutDetailBean(0, 0, 0);
            checkoutDetail.setOrderId(result.getInt(1));
            checkoutDetail.setProductId(result.getInt(2));
            checkoutDetail.setQuantity(result.getInt(3));
            checkoutDetailList.add(checkoutDetail);
        }

        connection.close();
        return checkoutDetailList;
    }

    public List<CheckoutDetailBean> getCheckoutDetailByOrderId(int orderId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM OrderDetails WHERE `OrderID` = " + orderId;

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<CheckoutDetailBean> checkoutDetailList = new ArrayList<>();

        while (result.next()) {
            CheckoutDetailBean checkoutDetail = new CheckoutDetailBean(0, 0, 0);
            checkoutDetail.setOrderId(result.getInt(1));
            checkoutDetail.setProductId(result.getInt(2));
            checkoutDetail.setQuantity(result.getInt(3));
            checkoutDetailList.add(checkoutDetail);
        }

        connection.close();
        return checkoutDetailList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        for (CheckoutDetailBean bean: new CheckoutDetailDao().getCheckoutDetailByOrderId(1)) {
            System.out.println(bean);
        }
    }
}

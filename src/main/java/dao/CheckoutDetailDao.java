package dao;

import beans.CheckoutBean;
import beans.CheckoutDetailBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDetailDao {
    public int getTotalSoldProduct() throws ClassNotFoundException, SQLException {
        String sql = "SELECT SUM(OrderDetails.`Quantity`) FROM OrderDetails";

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (result.next())
            return result.getInt(1);
        else return 0;
    }

    public int getProfitThisMonthOfYear(int month, int year) throws ClassNotFoundException, SQLException {
        String sql = "SELECT sum(ProductPrice * Quantity) FROM ((OrderDetails" +
                " JOIN Orders O ON OrderDetails.OrderID = O.OrderID)" +
                " JOIN Products P ON OrderDetails.ProductID = P.ProductID)" +
                " WHERE month(OrderDate) = ? AND year(OrderDate) = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, month);
        statement.setInt(2, year);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(1);
        else return 0;
    }

    public int getProfitThisYear(int year) throws ClassNotFoundException, SQLException {
        String sql = "SELECT sum(ProductPrice * Quantity) FROM ((OrderDetails" +
                " JOIN Orders O ON OrderDetails.OrderID = O.OrderID)" +
                " JOIN Products P ON OrderDetails.ProductID = P.ProductID)" +
                " WHERE year(OrderDate) = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, year);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return result.getInt(1);
        else return 0;
    }

    public void addCheckoutDetail(CheckoutDetailBean checkoutDetail) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO OrderDetails VALUE (?, ?, ?)";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, checkoutDetail.getCheckoutBean().getOrderId());
        statement.setInt(2, checkoutDetail.getProductBean().getProductId());
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
            CheckoutDetailBean checkoutDetail = new CheckoutDetailBean();
            checkoutDetail.getCheckoutBean().setOrderId(result.getInt(1));
            checkoutDetail.getProductBean().setProductId(result.getInt(2));
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
            CheckoutDetailBean checkoutDetail = new CheckoutDetailBean();
            checkoutDetail.setCheckoutBean(new CheckoutDao().getCheckoutByOrderId(result.getInt(1)));
            checkoutDetail.setProductBean(new ProductDao().getProductById(result.getInt(2)));
            checkoutDetail.setQuantity(result.getInt(3));
            checkoutDetailList.add(checkoutDetail);
        }

        connection.close();
        return checkoutDetailList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(new CheckoutDetailDao().getProfitThisMonthOfYear(5, 2023));
        System.out.println(new CheckoutDetailDao().getProfitThisYear(2023));
    }
}

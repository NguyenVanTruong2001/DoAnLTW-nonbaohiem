package dao;

import beans.CheckoutBean;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckoutDao {
    public int checkout(int userId, String fullname, String telephone, String address, String paymentMethod) throws ClassNotFoundException, SQLException {
        int result = 0;
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
        if (resultSet.next()) {
            result = resultSet.getInt(1);
            connection.close();
            return result;
        } else {
            connection.close();
            return result;
        }
    }

    public List<CheckoutBean> getAllCheckouts() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Orders";

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<CheckoutBean> checkoutList = new ArrayList<>();

        while (result.next()) {
            CheckoutBean checkout = new CheckoutBean();
            checkout.setOrderId(result.getInt(1));
            checkout.setUserBean(new UserDao().getUserById(result.getInt(2)));
            checkout.setOrderDate(result.getDate(3));
            checkout.setFullname(result.getString(4));
            checkout.setTelephone(result.getString(5));
            checkout.setAddress(result.getString(6));
            checkout.setPaymentMethod(result.getString(7));
            checkout.setOrderState(result.getString(8));
            checkoutList.add(checkout);
        }

        connection.close();
        return checkoutList;
    }

    public List<CheckoutBean> getCheckoutByUserId(int userId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Orders WHERE `UserID` = " + userId;

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<CheckoutBean> checkoutList = new ArrayList<>();

        while (result.next()) {
            CheckoutBean checkout = new CheckoutBean();
            checkout.setOrderId(result.getInt(1));
            checkout.setUserBean(new UserDao().getUserById(result.getInt(2)));
            checkout.setOrderDate(result.getDate(3));
            checkout.setFullname(result.getString(4));
            checkout.setTelephone(result.getString(5));
            checkout.setAddress(result.getString(6));
            checkout.setPaymentMethod(result.getString(7));
            checkout.setOrderState(result.getString(8));
            checkoutList.add(checkout);
        }

        connection.close();
        return checkoutList;
    }

    public void updateCheckoutStateByOrderId(int orderId, String orderState) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Orders SET `OrderState` = ? WHERE `UserID` = ?";

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, orderId);
        statement.setString(2, orderState);

        statement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CheckoutDao checkoutDao = new CheckoutDao();
        for (CheckoutBean bean :
                checkoutDao.getAllCheckouts()) {
            System.out.println(bean.toString());
        }

        System.out.println();

        for (CheckoutBean bean :
                checkoutDao.getCheckoutByUserId(2)) {
            System.out.println(bean.toString());
        }
    }
}

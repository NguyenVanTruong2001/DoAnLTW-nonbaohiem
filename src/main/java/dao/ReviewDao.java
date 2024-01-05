package dao;

import beans.ProductBean;
import beans.ReviewBean;
import beans.UserBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public boolean getReview (int userId, int productId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Reviews WHERE `UserID` = ? AND `ProductID` = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setInt(2, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }

    public List<ReviewBean> getAllReviews() throws ClassNotFoundException, SQLException {
        List<ReviewBean> list = new ArrayList<>();
        String sql = "SELECT * FROM Reviews";

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            ReviewBean bean = new ReviewBean(new UserBean(), new ProductBean(), 0, "");
            bean.setUserBean(new UserDao().getUserById(result.getInt(1)));
            bean.setProductBean(new ProductDao().getProductById(result.getInt(2)));
            bean.setRating(result.getInt(3));
            bean.setComment(result.getString(4));
            list.add(bean);
        }

        connection.close();
        return list;
    }

    public List<ReviewBean> getReviewsByProduct(int productId) throws ClassNotFoundException, SQLException {
        List<ReviewBean> list = new ArrayList<>();
        String sql = "SELECT * FROM Reviews WHERE `ProductID` = " + productId;

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            ReviewBean bean = new ReviewBean(new UserBean(), new ProductBean(), 0, "");
            bean.setUserBean(new UserDao().getUserById(result.getInt(1)));
            bean.setProductBean(new ProductDao().getProductById(result.getInt(2)));
            bean.setRating(result.getInt(3));
            bean.setComment(result.getString(4));
            list.add(bean);
        }

        connection.close();
        return list;
    }

    public int countReviewsByProduct(int productId) throws ClassNotFoundException, SQLException {
        int i = 0;
        String sql = "SELECT count(*) FROM Reviews WHERE `ProductID` = " + productId;

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next())
            i = result.getInt(1);

        connection.close();
        return i;
    }

    public boolean addReview(int userId, int productId, int rating, String comment) throws SQLException, ClassNotFoundException {
        String sql1 = "INSERT INTO Reviews value (?, ?, ?, ?)";
        String sql2 = "SELECT * FROM Reviews WHERE `UserID` = ? AND `ProductID` = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql2);
        statement.setInt(1, userId);
        statement.setInt(2, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            connection.close();
            return false;
        }
        else {
            statement = connection.prepareStatement(sql1);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, rating);
            statement.setString(4, comment);
            statement.executeUpdate();

            connection.close();
            return true;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ReviewDao reviewDao = new ReviewDao();
        System.out.println(reviewDao.countReviewsByProduct(1));
    }
}

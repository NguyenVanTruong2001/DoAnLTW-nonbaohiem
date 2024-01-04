package dao;

import beans.ProductBean;
import beans.ReviewBean;
import beans.UserBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    public ReviewBean getReview (int userId, int productId) throws ClassNotFoundException, SQLException {
        ReviewBean bean = new ReviewBean();
        String sql = "SELECT * FROM Reviews WHERE `UserID` = ? AND `ProductID` = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        statement.setInt(2, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            UserBean userBean = new UserDao().getUserById(resultSet.getInt(1));
            ProductBean productBean = new ProductDao().getProductById(resultSet.getInt(2));
            int rating = resultSet.getInt(3);
            String comment = resultSet.getString(4);
            bean = new ReviewBean(userBean, productBean, rating, comment);
        }

        connection.close();
        return bean;
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

    public int addReview(int userId, int productId, int rating, String comment) {
        return 0;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ReviewDao reviewDao = new ReviewDao();
        System.out.println(reviewDao.countReviewsByProduct(1));
    }
}

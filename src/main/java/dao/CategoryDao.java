package dao;

import beans.CategoryBean;
import beans.ProductBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    String jdbcUrl = "jdbc:mysql://localhost:3306/HelmetManager";
    String dbUser = "root";
    String dbPass = "hello";

    public List<CategoryBean> getAllCategories() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Categories";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<CategoryBean> categoryList = new ArrayList<>();
        while (result.next()) {
            CategoryBean category = new CategoryBean();
            category.setCategoryId(result.getInt("CategoryID"));
            category.setCategoryName(result.getString("CategoryName"));
            categoryList.add(category);
        }

        connection.close();
        return categoryList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> list = categoryDao.getAllCategories();
        System.out.println(list);
    }
}

package dao;

import beans.CategoryBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<CategoryBean> getAllCategories() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Categories";

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<CategoryBean> categoryList = new ArrayList<>();
        while (result.next()) {
            CategoryBean category = new CategoryBean(0, "");
            category.setCategoryId(result.getInt(1));
            category.setCategoryName(result.getString(2));
            categoryList.add(category);
        }

        connection.close();
        return categoryList;
    }

    public CategoryBean getCategoryById(int categoryId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Categories WHERE `CategoryID` = " + categoryId;

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        CategoryBean category = new CategoryBean(0, "");
        while (result.next()) {
            category.setCategoryId(result.getInt(1));
            category.setCategoryName(result.getString(2));
        }

        connection.close();
        return category;
    }

    public int addCategory(String categoryName) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Categories(`CategoryName`) VALUE (?)";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, categoryName);
        int i =  statement.executeUpdate();

        connection.close();
        return i;
    }

    public int updateCategoryById(int categoryId, String categoryName) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Categories SET `CategoryName` = ? WHERE `CategoryID` = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, categoryName);
        statement.setInt(2, categoryId);
        int i =  statement.executeUpdate();

        connection.close();
        return i;
    }

    public int deleteCategoryById(int categoryId) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Categories WHERE `CategoryID` = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, categoryId);
        int i =  statement.executeUpdate();

        connection.close();
        return i;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryDao categoryDao = new CategoryDao();
        System.out.println(categoryDao.getCategoryById(1));
    }
}

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> list = categoryDao.getAllCategories();
        for (CategoryBean bean : list) {
            System.out.println(bean);
        }
    }
}

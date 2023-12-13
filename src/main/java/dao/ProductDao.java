package dao;

import beans.ProductBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public int countProducts() throws ClassNotFoundException, SQLException {
        int i = 0;
        String sql = "SELECT COUNT(*) FROM Products";

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if (result.next())
            i = result.getInt(1);

        connection.close();
        return i;
    }

    public List<ProductBean> getAllProducts() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products";

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean(0, 0, "", "", "", "", "", 0);
            product.setProductId(result.getInt(1));
            product.setCategoryId(result.getInt(2));
            product.setProductName(result.getString(3));
            product.setProductImage(result.getString(4));
            product.setProductDescription(result.getString(5));
            product.setProductBrand(result.getString(6));
            product.setProductSize(result.getString(7));
            product.setProductPrice(result.getInt(8));
            productList.add(product);
        }

        connection.close();
        return productList;
    }

    public List<ProductBean> getNewestProducts() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM (SELECT * FROM Products ORDER BY `ProductID` DESC LIMIT 5) Prd ORDER BY `ProductID`";

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean(0, 0, "", "", "", "", "", 0);
            product.setProductId(result.getInt(1));
            product.setCategoryId(result.getInt(2));
            product.setProductName(result.getString(3));
            product.setProductImage(result.getString(4));
            product.setProductDescription(result.getString(5));
            product.setProductBrand(result.getString(6));
            product.setProductSize(result.getString(7));
            product.setProductPrice(result.getInt(8));
            productList.add(product);
        }

        connection.close();
        return productList;
    }

    public List<ProductBean> getRecommendedProducts() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products WHERE `ProductID` IN (?, ?, ?, ?, ?)";
        List<Integer> list = rng();

        Connection connection = new DBConnect().connect();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, list.get(0));
        statement.setInt(2, list.get(1));
        statement.setInt(3, list.get(2));
        statement.setInt(4, list.get(3));
        statement.setInt(5, list.get(4));
        ResultSet result = statement.executeQuery();

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean(0, 0, "", "", "", "", "", 0);
            product.setProductId(result.getInt(1));
            product.setCategoryId(result.getInt(2));
            product.setProductName(result.getString(3));
            product.setProductImage(result.getString(4));
            product.setProductDescription(result.getString(5));
            product.setProductBrand(result.getString(6));
            product.setProductSize(result.getString(7));
            product.setProductPrice(result.getInt(8));
            productList.add(product);
        }

        connection.close();
        return productList;
    }

//    public List<ProductBean> getAllProducts(int page) throws ClassNotFoundException, SQLException {
//        String sql = "SELECT * FROM Products LIMIT 9 OFFSET " + (page - 1) * 9;
//
//        Connection connection = new DBConnect().connect();
//
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//
//        List<ProductBean> productList = new ArrayList<>();
//        while (result.next()) {
//            ProductBean product = new ProductBean(0, 0, "", "", "", "", "", 0);
//            product.setProductId(result.getInt(1));
//            product.setCategoryId(result.getInt(2));
//            product.setProductName(result.getString(3));
//            product.setProductImage(result.getString(4));
//            product.setProductDescription(result.getString(5));
//            product.setProductBrand(result.getString(6));
//            product.setProductSize(result.getString(7));
//            product.setProductPrice(result.getInt(8));
//            productList.add(product);
//        }
//
//        connection.close();
//        return productList;
//    }

    public List<ProductBean> getProductByCategory(int categoryId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products WHERE `CategoryID` = " + categoryId;

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean(0, 0, "", "", "", "", "", 0);
            product.setProductId(result.getInt(1));
            product.setCategoryId(result.getInt(2));
            product.setProductName(result.getString(3));
            product.setProductImage(result.getString(4));
            product.setProductDescription(result.getString(5));
            product.setProductBrand(result.getString(6));
            product.setProductSize(result.getString(7));
            product.setProductPrice(result.getInt(8));
            productList.add(product);
        }

        connection.close();
        return productList;
    }

    public ProductBean getProductById(int productId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products WHERE `ProductID` = " + productId;

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        ProductBean product = new ProductBean(0, 0, "", "", "", "", "", 0);
        while (result.next()) {
            product.setProductId(result.getInt(1));
            product.setCategoryId(result.getInt(2));
            product.setProductName(result.getString(3));
            product.setProductImage(result.getString(4));
            product.setProductDescription(result.getString(5));
            product.setProductBrand(result.getString(6));
            product.setProductSize(result.getString(7));
            product.setProductPrice(result.getInt(8));
        }

        connection.close();
        return product;
    }

    private List<Integer> rng() throws ClassNotFoundException, SQLException {
        List<Integer> list = new ArrayList<>();
        list.add((int) (Math.random() * countProducts()) + 1);

        while (list.size() < 5) {
            int num = (int) (Math.random() * countProducts()) + 1;
            if (list.isEmpty()) list.add(num);
            if (!list.contains(num)) list.add(num);
        }

        return list;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProductDao productDao = new ProductDao();
        for (ProductBean p : productDao.getNewestProducts()) {
            System.out.println(p.toString());
        }
    }
}

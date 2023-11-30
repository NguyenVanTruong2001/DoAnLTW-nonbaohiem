package dao;

import beans.ProductBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
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
//            product.setProductId(result.getInt("ProductID"));
//            product.setCategoryId(result.getInt("CategoryID"));
//            product.setProductName(result.getString("ProductName"));
//            product.setProductImage(result.getString("ProductImage"));
//            product.setProductDescription(result.getString("ProductDescription"));
//            product.setProductBrand(result.getString("ProductBrand"));
//            product.setProductSize(result.getString("ProductSize"));
//            product.setProductPrice(result.getInt("ProductPrice"));
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

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProductDao productDao = new ProductDao();
        System.out.println(productDao.getProductById(1));
    }
}

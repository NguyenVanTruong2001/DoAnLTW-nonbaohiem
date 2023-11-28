package dao;

import beans.ProductBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    String jdbcUrl = "jdbc:mysql://localhost:3306/HelmetManager";
    String dbUser = "root";
    String dbPass = "hello";

    public List<ProductBean> getAllProducts() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean();
            product.setProductId(result.getInt("ProductID"));
            product.setCategoryId(result.getInt("CategoryID"));
            product.setProductName(result.getString("ProductName"));
            product.setProductImage(result.getString("ProductImage"));
            product.setProductDescription(result.getString("ProductDescription"));
            product.setProductBrand(result.getString("ProductBrand"));
            product.setProductSize(result.getString("ProductSize"));
            product.setProductPrice(result.getInt("ProductPrice"));
            productList.add(product);
        }

        connection.close();
        return productList;
    }

//    public List<ProductBean> getAllProducts(int page) throws ClassNotFoundException, SQLException {
//        String sql = "SELECT * FROM Products LIMIT 9 OFFSET " + (page - 1) * 9;
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
//
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery(sql);
//
//        List<ProductBean> productList = new ArrayList<>();
//        while (result.next()) {
//            ProductBean product = new ProductBean();
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

    public ProductBean getProductsById(int productId) throws ClassNotFoundException, SQLException {
        return null;
    }

    public List<ProductBean> getProductByCategory(int categoryId, int page) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);

        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProductDao productDao = new ProductDao();
        List<ProductBean> productBeans = productDao.getAllProducts();
        System.out.println(productBeans);
    }
}

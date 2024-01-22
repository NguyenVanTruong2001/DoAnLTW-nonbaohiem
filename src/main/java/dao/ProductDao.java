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
            ProductBean product = new ProductBean();
            product.setProductId(result.getInt(1));
            product.setCategoryBean(new CategoryDao().getCategoryById(result.getInt(2)));
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
            ProductBean product = new ProductBean();
            product.setProductId(result.getInt(1));
            product.setCategoryBean(new CategoryDao().getCategoryById(result.getInt(2)));
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
            ProductBean product = new ProductBean();
            product.setProductId(result.getInt(1));
            product.setCategoryBean(new CategoryDao().getCategoryById(result.getInt(2)));
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

    public List<ProductBean> getProductByCategory(int categoryId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products WHERE `CategoryID` = " + categoryId;

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean();
            product.setProductId(result.getInt(1));
            product.setCategoryBean(new CategoryDao().getCategoryById(result.getInt(2)));
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

    public List<ProductBean> getProductByName(String name) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Products WHERE `ProductName` LIKE '%" + name + "%'";

        Connection connection = new DBConnect().connect();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        List<ProductBean> productList = new ArrayList<>();
        while (result.next()) {
            ProductBean product = new ProductBean();
            product.setProductId(result.getInt(1));
            product.setCategoryBean(new CategoryDao().getCategoryById(result.getInt(2)));
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

        ProductBean product = new ProductBean();
        while (result.next()) {
            product.setProductId(result.getInt(1));
            product.setCategoryBean(new CategoryDao().getCategoryById(result.getInt(2)));
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

    public int deleteProductById(int id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Products WHERE `ProductID` = " + id;

        Connection connection = new DBConnect().connect();
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql);

        connection.close();
        return i;
    }

    public int updateProductById(int productId, int categoryId, String name, String image, String description, String brand, String size, int price) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Products SET `CategoryID` = ?, `ProductName` = ?, `ProductImage` = ?, `ProductDescription` = ?, `ProductBrand` = ?, `ProductSize` = ?, `ProductPrice` = ? WHERE `ProductID` = ?";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, categoryId);
        statement.setString(2, name);
        statement.setString(3, image);
        statement.setString(4, description);
        statement.setString(5, brand);
        statement.setString(6, size);
        statement.setInt(7, price);
        statement.setInt(8, productId);
        int i = statement.executeUpdate();

        connection.close();
        return i;
    }

    public int addProduct(int categoryId, String name, String image, String description, String brand, String size, int price) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Products(`CategoryID`, `ProductName`, `ProductImage`, `ProductDescription`, `ProductBrand`, `ProductSize`, `ProductPrice`) VALUE (?, ?, ?, ?, ?, ?, ?)";

        Connection connection = new DBConnect().connect();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, categoryId);
        statement.setString(2, name);
        statement.setString(3, image);
        statement.setString(4, description);
        statement.setString(5, brand);
        statement.setString(6, size);
        statement.setInt(7, price);
        int i = statement.executeUpdate();

        connection.close();
        return i;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ProductDao productDao = new ProductDao();
        for (ProductBean p : productDao.getProductByName("royal")) {
            System.out.println(p.toString());
        }
    }
}

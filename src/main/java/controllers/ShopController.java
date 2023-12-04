package controllers;

import beans.CategoryBean;
import beans.ProductBean;
import dao.CategoryDao;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shop")
public class ShopController extends HttpServlet {
    public ShopController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("categoryId"));
        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        List<ProductBean> productList;
        List<CategoryBean> categoryList;
        try {
            if (id == 0) {
                categoryList = categoryDao.getAllCategories();
                productList = productDao.getAllProducts();
            } else {
                categoryList = categoryDao.getAllCategories();
                productList = productDao.getProductByCategory(id);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        ProductBean[] productBeans = productList.toArray(new ProductBean[0]);
        CategoryBean[] categoryBeans = categoryList.toArray(new CategoryBean[0]);
        req.setAttribute("productList", productBeans);
        req.setAttribute("categoryList", categoryBeans);
        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

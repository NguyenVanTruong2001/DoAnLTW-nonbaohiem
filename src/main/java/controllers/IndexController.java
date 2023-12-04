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
import java.util.List;

@WebServlet("/home")
public class IndexController extends HttpServlet {
    public IndexController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        List<CategoryBean> categoryList;
        List<ProductBean> productList, productList2;
        try {
            categoryList = categoryDao.getAllCategories();
            productList = productDao.getRecommendedProducts();
            productList2 = productDao.getNewestProducts();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        CategoryBean[] categoryBeans = categoryList.toArray(new CategoryBean[0]);
        ProductBean[] productBeans = productList.toArray(new ProductBean[0]);
        ProductBean[] productBeans2 = productList2.toArray(new ProductBean[0]);
        req.setAttribute("categoryList", categoryBeans);
        req.setAttribute("productList", productBeans);
        req.setAttribute("productList2", productBeans2);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

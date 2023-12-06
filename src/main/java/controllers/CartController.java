package controllers;

import beans.Cart;
import beans.CategoryBean;
import beans.ProductBean;
import dao.CategoryDao;
import dao.ProductDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    public CartController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> categoryBeans;

        try {
            categoryBeans = categoryDao.getAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        CategoryBean[] categoryList = categoryBeans.toArray(new CategoryBean[0]);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

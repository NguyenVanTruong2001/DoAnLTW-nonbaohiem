package controllers;

import beans.CategoryBean;
import dao.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/checkout-history")
public class CheckoutHistoryController extends HttpServlet {
    public CheckoutHistoryController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> categoryList;

        try {
            categoryList = categoryDao.getAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("checkoutHistory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

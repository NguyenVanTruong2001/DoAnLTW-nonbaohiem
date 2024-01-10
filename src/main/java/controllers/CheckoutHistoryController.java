package controllers;

import beans.CategoryBean;
import beans.CheckoutBean;
import beans.UserBean;
import dao.CategoryDao;
import dao.CheckoutDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@WebServlet("/checkout-history")
public class CheckoutHistoryController extends HttpServlet {
    public CheckoutHistoryController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        List<CategoryBean> categoryList;
        List<CheckoutBean> checkoutList;

        try {
            categoryList = new CategoryDao().getAllCategories();
            checkoutList = new CheckoutDao().getCheckoutByUserId(user.getUserId());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        Collections.reverse(checkoutList);
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("checkoutList", checkoutList);
        req.getRequestDispatcher("checkoutHistory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

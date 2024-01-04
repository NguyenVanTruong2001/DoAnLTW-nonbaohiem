package controllers;

import beans.ProductBean;
import beans.ReviewBean;
import beans.UserBean;
import dao.ProductDao;
import dao.ReviewDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/review-management")
public class ReviewManagementController extends HttpServlet {
    public ReviewManagementController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserBean> userList;
        List<ProductBean> productList;
        List<ReviewBean> reviewList;

        try {
            userList = new UserDao().getAllUsers();
            productList = new ProductDao().getAllProducts();
            reviewList = new ReviewDao().getAllReviews();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("reviewList", reviewList);
        req.getRequestDispatcher("review-management.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

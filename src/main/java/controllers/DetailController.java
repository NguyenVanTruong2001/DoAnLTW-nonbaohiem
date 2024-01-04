package controllers;

import beans.CategoryBean;
import beans.ProductBean;
import beans.ReviewBean;
import dao.CategoryDao;
import dao.ProductDao;
import dao.ReviewDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/detail")
public class DetailController extends HttpServlet {
    public DetailController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> categoryList;
        List<ProductBean> productList;
        List<ReviewBean> reviewList;

        int id = Integer.parseInt(req.getParameter("productId"));
        ProductBean productBean;
        try {
            productBean = productDao.getProductById(id);
            categoryList = categoryDao.getAllCategories();
            productList = productDao.getRecommendedProducts();
            reviewList = new ReviewDao().getReviewsByProduct(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("productBean", productBean);
        req.setAttribute("productList", productList);
        req.setAttribute("reviewList", reviewList);
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }
}

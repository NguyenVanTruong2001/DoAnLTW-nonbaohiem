package controllers.user;

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
        ProductBean productBean;
        List<CategoryBean> categoryList;
        List<ProductBean> productList;
        List<ReviewBean> reviewList;
        int countReviews;

        int id = Integer.parseInt(req.getParameter("productId"));
        try {
            productBean = new ProductDao().getProductById(id);
            categoryList = new CategoryDao().getAllCategories();
            productList = new ProductDao().getRecommendedProducts();
            reviewList = new ReviewDao().getReviewsByProduct(id);
            countReviews = new ReviewDao().countReviewsByProduct(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("productBean", productBean);
        req.setAttribute("productList", productList);
        req.setAttribute("reviewList", reviewList);
        req.setAttribute("countReviews", countReviews);
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }
}

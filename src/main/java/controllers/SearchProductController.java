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

@WebServlet("/search-product")
public class SearchProductController extends HttpServlet {
    public SearchProductController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<ProductBean> productList;
        List<CategoryBean> categoryList;

        try {
            productList = new ProductDao().getProductByName(query);
            categoryList = new CategoryDao().getAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("productList", productList);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }
}

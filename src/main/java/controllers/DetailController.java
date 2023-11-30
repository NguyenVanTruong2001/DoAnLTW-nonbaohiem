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

        int id = Integer.parseInt(req.getParameter("productId"));
        ProductBean productBean;
        CategoryBean categoryBean;
        try {
            productBean = productDao.getProductById(id);
            categoryList = categoryDao.getAllCategories();
            categoryBean = categoryDao.getCategoryById(productBean.getCategoryId());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        CategoryBean[] categoryBeans = categoryList.toArray(new CategoryBean[0]);
        req.setAttribute("categoryList", categoryBeans);
        req.setAttribute("productBean", productBean);
        req.setAttribute("categoryBean", categoryBean);
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }
}

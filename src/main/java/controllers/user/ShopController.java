package controllers.user;

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

@WebServlet("/shop")
public class ShopController extends HttpServlet {
    public ShopController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (req.getParameter("categoryId") == null) ? 0 : Integer.parseInt(req.getParameter("categoryId"));
        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        List<ProductBean> productList;
        List<CategoryBean> categoryList;
        try {
            categoryList = categoryDao.getAllCategories();
            if (id == 0) {
                productList = productDao.getAllProducts();
            } else {
                productList = productDao.getProductByCategory(id);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("productList", productList);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

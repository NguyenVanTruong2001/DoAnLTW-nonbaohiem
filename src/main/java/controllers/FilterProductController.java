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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/filter-product")
public class FilterProductController extends HttpServlet {
    public FilterProductController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        List<ProductBean> productList = new ArrayList<>();
        List<CategoryBean> categoryList = new ArrayList<>();

        switch (type) {
            case "price":
                int min = Integer.parseInt(req.getParameter("min"));
                int max = Integer.parseInt(req.getParameter("max"));
                try {
                    productList = new ProductDao().getProductByPrice(min, max);
                    categoryList = new CategoryDao().getAllCategories();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "brand":
                String brand = req.getParameter("brand");
                try {
                    productList = new ProductDao().getProductByBrand(brand);
                    categoryList = new CategoryDao().getAllCategories();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "size":
                String size = req.getParameter("size");
                try {
                    productList = new ProductDao().getProductBySize(size);
                    categoryList = new CategoryDao().getAllCategories();
                } catch (ClassNotFoundException | SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
        }

        req.setAttribute("productList", productList);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }
}

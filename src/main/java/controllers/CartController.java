package controllers;

import beans.Cart;
import beans.CategoryBean;
import beans.ProductBean;
import dao.CartDao;
import dao.CategoryDao;
import dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    public CartController() {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartDao cart = (CartDao) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartDao();
        }

        String command = req.getParameter("command");
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> categoryList;
        ProductBean product;
        try {
            categoryList = categoryDao.getAllCategories();
            product = new ProductDao().getProductById(productId);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        switch (command) {
            case "add":
                if (cart.getCartMap().containsKey(productId))
                    cart.addCart(productId, new Cart(product, cart.getCartMap().get(productId).getQuantity()));
                else
                    cart.addCart(productId, new Cart(product, quantity));
                break;
            case "plus":
                break;
            case "minus":
                break;
            case "delete":
                break;
            default:
                break;
        }

        CategoryBean[] categoryBeans = categoryList.toArray(new CategoryBean[0]);
        req.setAttribute("categoryList", categoryBeans);

        session.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}

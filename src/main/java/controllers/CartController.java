package controllers;

import beans.CategoryBean;
import beans.ProductBean;
import beans.ProductCart;
import dao.CategoryDao;
import dao.ProductDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    public CartController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> categoryList;

        try {
            categoryList = categoryDao.getAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("categoryList", categoryList);

        String command = req.getParameter("command");
        switch (command) {
            case "insert":
                insertToCart(req, resp);
                break;
            case "remove":
                removeFromCart(req, resp);
                break;
            case "delete":
                deleteFromCart(req, resp);
                break;
            case null:
                showCart(req, resp);
            default:
                break;
        }
    }

    private void insertToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        ProductBean product;

        try {
            product = new ProductDao().getProductById(productId);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = req.getSession();
        ProductCart productCart;
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
            productCart = new ProductCart(product, quantity);
            cart.put(productId, productCart);
        } else {
            if (cart.containsKey(productId)) {
                productCart = cart.get(productId);
                productCart.setQuantity(cart.get(productId).getQuantity() + quantity);
            } else {
                productCart = new ProductCart(product, quantity);
                cart.put(productId, productCart);
            }
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart");
    }

    private void removeFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        HttpSession session = req.getSession();
        ProductCart productCart;
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

        if (cart.containsKey(productId)) {
            productCart = cart.get(productId);
            productCart.setQuantity(cart.get(productId).getQuantity() - quantity);
        }

        if (cart.get(productId).getQuantity() <= 0)
            cart.remove(productId);

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart");
    }

    private void deleteFromCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));

        HttpSession session = req.getSession();
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

        cart.remove(productId);

        session.setAttribute("cart", cart);
        resp.sendRedirect("cart");

    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("cart.jsp");
        session.setAttribute("cart", cart);
        dispatcher.forward(req, resp);
    }
}

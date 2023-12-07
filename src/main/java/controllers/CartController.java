package controllers;

import beans.Cart;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<CategoryBean> categoryBeans;

        try {
            categoryBeans = categoryDao.getAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        CategoryBean[] categoryList = categoryBeans.toArray(new CategoryBean[0]);
        req.setAttribute("categoryList", categoryList);

        String command = req.getParameter("command");
        switch (command) {
            case "insert":
                addToCart(req, resp);
                break;
            default:
                break;
        }
        
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) {
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
//        Cart cart = (Cart) session.getAttribute("cart");
        HashMap<Integer, ProductCart> cartMap = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
        if (cartMap == null) {
            cartMap = new HashMap<>();
            productCart = new ProductCart(product, quantity);
            cartMap.put(productId, productCart);
        } else {
            if (cartMap.containsKey(productId)) {
                productCart = cartMap.get(productId);
                productCart.setQuantity(quantity + cartMap.get(productId).getQuantity());
            } else {
                productCart = new ProductCart(product, quantity);
                cartMap.put(productId, productCart);
            }
        }
//        cart.setMap(cartMap);
        session.setAttribute("cart", cartMap);

        for (Map.Entry<Integer, ProductCart> entry : cartMap.entrySet()) {
            System.out.println(entry.getValue().getProduct().toString());
            System.out.println(entry.getValue().getQuantity());
        }
    }
}

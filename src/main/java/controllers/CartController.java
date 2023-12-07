package controllers;

import beans.CategoryBean;
import beans.ProductBean;
import beans.ProductCart;
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

    private void addToCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                productCart.setQuantity(quantity + cart.get(productId).getQuantity());
            } else {
                productCart = new ProductCart(product, quantity);
                cart.put(productId, productCart);
            }
        }
        session.setAttribute("cart", cart);

        for (Map.Entry<Integer, ProductCart> entry : cart.entrySet()) {
            System.out.println(entry.getValue().getProduct().toString());
            System.out.println(entry.getValue().getQuantity());
        }
    }
}

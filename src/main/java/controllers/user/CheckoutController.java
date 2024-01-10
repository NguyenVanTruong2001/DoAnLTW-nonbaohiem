package controllers.user;

import beans.CategoryBean;
import beans.CheckoutDetailBean;
import beans.ProductCart;
import dao.CategoryDao;
import dao.CheckoutDao;
import dao.CheckoutDetailDao;

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

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {
    public CheckoutController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDao categoryDao = new CategoryDao();
        List<CategoryBean> categoryList;

        try {
            categoryList = categoryDao.getAllCategories();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String fullname = req.getParameter("fullname");
        String telephone = req.getParameter("telephone");
        String address = req.getParameter("address");
        String paymentMethod = req.getParameter("payment");

        HttpSession session = req.getSession();
        HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart");
        CheckoutDao checkoutDao = new CheckoutDao();
        CheckoutDetailDao checkoutDetailDao = new CheckoutDetailDao();
        int i;

        try {
            i = checkoutDao.checkout(userId, fullname, telephone, address, paymentMethod);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Map.Entry<Integer, ProductCart> entry : cart.entrySet()) {
            CheckoutDetailBean checkoutDetail = new CheckoutDetailBean();
            checkoutDetail.getProductBean().setProductId(entry.getKey());
            checkoutDetail.getCheckoutBean().setOrderId(i);
            checkoutDetail.setQuantity(entry.getValue().getQuantity());
            try {
                checkoutDetailDao.addCheckoutDetail(checkoutDetail);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        session.removeAttribute("cart");
        req.getRequestDispatcher("/checkout-success").forward(req, resp);
    }
}

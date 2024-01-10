package controllers.admin;

import beans.CheckoutBean;
import beans.CheckoutDetailBean;
import dao.CheckoutDao;
import dao.CheckoutDetailDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/orderDetail-management")
public class OrderDetailManagementController extends HttpServlet {

    public OrderDetailManagementController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("orderId"));
        CheckoutBean checkout;
        List<CheckoutDetailBean> checkoutDetailList;

        try {
            checkout = new CheckoutDao().getCheckoutByOrderId(id);
            checkoutDetailList = new CheckoutDetailDao().getCheckoutDetailByOrderId(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("checkout", checkout);
        req.setAttribute("checkoutDetailList", checkoutDetailList);
        req.getRequestDispatcher("orderDetail-management.jsp").forward(req, resp);
    }
}

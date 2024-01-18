package controllers;

import beans.CheckoutBean;
import dao.CheckoutDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/form-order")
public class CheckoutFormController extends HttpServlet {
    public CheckoutFormController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("orderId"));
        CheckoutBean bean;

        try {
            bean = new CheckoutDao().getCheckoutByOrderId(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("order", bean);
        req.getRequestDispatcher("update-order.jsp").forward(req, resp);
    }
}

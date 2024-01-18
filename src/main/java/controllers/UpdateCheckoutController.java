package controllers;

import dao.CheckoutDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-checkout")
public class UpdateCheckoutController extends HttpServlet {
    public UpdateCheckoutController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(req.getParameter("orderId"));
        String role = req.getParameter("role");
        String state = req.getParameter("orderState");
        int i;

        try {
            i = new CheckoutDao().updateCheckoutStateByOrderId(id, state);
            if (i > 0) {
                if (role.equals("User")) resp.sendRedirect("checkout-history");
                if (role.equals("Admin")) resp.sendRedirect("order-management");
            }
        } catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
            req.getRequestDispatcher("order-management").forward(req, resp);
        }
    }
}

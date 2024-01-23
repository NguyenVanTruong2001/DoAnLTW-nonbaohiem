package controllers;

import dao.CheckoutDetailDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
    public DashboardController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalSoldProduct, totalProfitThisMonthOfYear, totalProfitThisYear;
        int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        try {
            totalSoldProduct = new CheckoutDetailDao().getTotalSoldProduct();
            totalProfitThisMonthOfYear = new CheckoutDetailDao().getProfitThisMonthOfYear(thisMonth, thisYear);
            totalProfitThisYear = new CheckoutDetailDao().getProfitThisYear(thisYear);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("totalSoldProduct", totalSoldProduct);
        req.setAttribute("totalProfitThisMonthOfYear", totalProfitThisMonthOfYear);
        req.setAttribute("totalProfitThisYear", totalProfitThisYear);
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}

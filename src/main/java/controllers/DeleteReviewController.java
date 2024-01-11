package controllers;

import dao.ReviewDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-review")
public class DeleteReviewController extends HttpServlet {
    public DeleteReviewController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userid"));
        int productId = Integer.parseInt(req.getParameter("productid"));
        int i;

        try {
            i = new ReviewDao().deleteReviewById(userId, productId);
            if (i > 0) resp.sendRedirect("review-management");
        } catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
            req.getRequestDispatcher("review-management").forward(req, resp);
        }
    }
}

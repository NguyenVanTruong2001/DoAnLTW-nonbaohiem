package controllers;

import dao.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/update-category")
public class UpdateCategoryController extends HttpServlet {
    public UpdateCategoryController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(req.getParameter("categoryId"));
        String name = req.getParameter("categoryName");
        int i;

        try {
            i = new CategoryDao().updateCategoryById(id, name);
            if (i > 0) resp.sendRedirect("category-management");
        } catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
            req.getRequestDispatcher("form-category").forward(req, resp);
        }
    }
}

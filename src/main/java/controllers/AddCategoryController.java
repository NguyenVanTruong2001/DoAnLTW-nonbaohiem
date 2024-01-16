package controllers;

import dao.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add-category")
public class AddCategoryController extends HttpServlet {
    public AddCategoryController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String categoryName = req.getParameter("name");
        int i;

        try {
            i = new CategoryDao().addCategory(categoryName);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        if (i > 0) resp.sendRedirect("category-management");
        else {
            req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
            req.getRequestDispatcher("category-management.jsp").forward(req, resp);
        }
    }
}

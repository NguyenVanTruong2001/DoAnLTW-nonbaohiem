package controllers;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete-user")
public class DeleteUserController extends HttpServlet {
    public DeleteUserController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userid"));
        int i;

        try {
            i = new UserDao().deleteUserById(id);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        if (i > 0)
            resp.sendRedirect("user-management");
        else {
            req.setAttribute("message", "Thao tác không thành công. Hãy thử lại lần sau.");
            resp.sendRedirect("user-management");
        }
    }
}

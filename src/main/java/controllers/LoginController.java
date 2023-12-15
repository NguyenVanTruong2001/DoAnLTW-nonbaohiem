package controllers;

import beans.UserBean;
import dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    public LoginController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            UserBean user = new UserDao().checkLogin(email, password);

            if (user != null) {
                if (user.getRole().equals("User")) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("home").forward(req, resp);
                }

                if (user.getRole().equals("Admin")) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("dashboard").forward(req, resp);
                }
            }
            else {
                req.setAttribute("message", "E-mail hoặc mật khẩu không đúng. Hãy nhập lại.");

                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

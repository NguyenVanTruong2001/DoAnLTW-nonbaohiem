@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Thực hiện kiểm tra thông tin đăng nhập sử dụng JDBC
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://Downloads//web", "username", "password")) {
            String query = "SELECT * FROM Users WHERE Email=? AND Password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Đăng nhập thành công
                        response.sendRedirect("index.jsp");
                    } else {
                        // Đăng nhập thất bại
                        response.sendRedirect("login.jsp?error=1");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Thực hiện xác nhận thông qua email và lưu thông tin người dùng vào cơ sở dữ liệu (đây chỉ là ví dụ đơn giản)
        boolean emailConfirmed = sendConfirmationEmail(email);

        if (emailConfirmed) {
            // Lưu thông tin người dùng vào cơ sở dữ liệu ở đây

            response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        } else {
            response.sendRedirect("register.jsp?error=2"); // Chuyển hướng với mã lỗi 2 nếu xác nhận email thất bại
        }
    }

    private boolean sendConfirmationEmail(String email) {
        // Gửi email xác nhận đến địa chỉ email người dùng
        // Trả về true nếu gửi thành công, false nếu gặp lỗi
        return true;
    }
}

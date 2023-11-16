<%--
  Created by IntelliJ IDEA.
  User: phamn
  Date: 11/17/2023
  Time: 1:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng Ký</title>
</head>
<body>
<form class="text-center p-5 c" action="LoginServlet" method="post">
    <p class="h3 font-weight-bold mb-4">Đăng nhập</p>
    <div class="form-group">
        <input type="email" name="email" id="defaultLoginFormEmail" class="form-control border border-primary py-4" placeholder="Địa chỉ e-mail">
    </div>
    <div class="form-group">
        <input type="password" name="password" id="defaultLoginFormPassword" class="form-control border border-primary py-4" placeholder="Mật khẩu">
    </div>
    <div class="d-inline-block">
        <p>Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay!</a></p>
    </div>
    <div>
        <button  class="btn btn-primary btn-block mb-4" type="submit">Đăng nhập</button>
    </div>
<% if (request.getParameter("error") != null && request.getParameter("error").equals("1")) { %>
<p style="color: red;">Kiểm tra lại thông tin đăng nhập!</p>
<% } %>

</body>
</html>

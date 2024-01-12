<%@ page import="beans.UserBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% UserBean user = (UserBean) request.getAttribute("user"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <title>Cập nhật người dùng</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <style>
        body {
            margin: 50px auto 50px auto;
            width: 45%;
            background-color: #EDF1FF;
        }
    </style>
</head>
<body>
<form class="p-3 c" action="<c:url value="/update-user"/>" method="post">
    <h3 class="font-weight-bold mb-4 text-center">Cập nhật người dùng</h3>
    <div class="form-group">
        <label>Tên người dùng:</label>
        <input required type="text" name="username" class="form-control border border-primary py-4" value="<%= user.getUsername()%>">
    </div>
    <div class="form-group">
        <label>Địa chỉ e-mail:</label>
        <input required type="email" name="email" class="form-control border border-primary py-4" value="<%= user.getEmail()%>">
    </div>
    <div class="form-group">
        <label>Mật khẩu:</label>
        <input required type="password" name="password" class="form-control border border-primary py-4" value="<%= user.getPassword()%>">
    </div>
    <div class="form-group">
        <p>Quyền:</p>
    </div>
    <div class="form-group">
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="admin" name="role" value="Admin" required>
            <label class="custom-control-label" for="admin">Admin</label>
        </div>
    </div>
    <div class="form-group">
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="user" name="role" value="User" required>
            <label class="custom-control-label" for="user">User</label>
        </div>
    </div>
    <div>
        <button class="btn btn-primary btn-block mb-4" type="submit">Cập nhật</button>
    </div>
</form>
</body>
</html>

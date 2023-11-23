<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 22/11/2023
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String message = (String) request.getAttribute("message"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <title>Đăng nhập</title>
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
<form class="text-center p-5 c" action="<c:url value="/LoginController"/>" method="post">
    <h3 class="font-weight-bold mb-4">Đăng nhập</h3>
    <% if (message != null) { %>
        <div class="form-group alert alert-danger">
            <%= message %>
        </div>
    <% } %>
    <div class="form-group">
        <input autofocus type="email" name="email" class="form-control border border-primary py-4" placeholder="Địa chỉ e-mail">
    </div>
    <div class="form-group">
        <input type="password" name="password" class="form-control border border-primary py-4" placeholder="Mật khẩu">
    </div>
    <div class="d-inline-block">
        <p>Chưa có tài khoản? <a href="register.html">Đăng ký ngay!</a></p>
    </div>
    <div>
        <button class="btn btn-primary btn-block mb-4" type="submit">Đăng nhập</button>
    </div>
</form>
</body>
</html>

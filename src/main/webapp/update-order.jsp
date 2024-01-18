<%@ page import="beans.CheckoutBean" %>
<%@ page import="beans.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String message = (String) request.getAttribute("message"); %>
<% CheckoutBean order = (CheckoutBean) request.getAttribute("order"); %>
<% UserBean user = (UserBean) session.getAttribute("user"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <title>Cập nhật đơn hàng</title>
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
<form class="p-3 c" action="<c:url value="/update-checkout"/>" method="post">
    <h3 class="font-weight-bold mb-4 text-center">Cập nhật trạng thái đơn hàng</h3>
    <% if (message != null) { %>
    <div class="alert alert-danger">
        <%= message %>
    </div>
    <% } %>
    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
    <input type="hidden" name="role" value="<%= user.getRole() %>">
    <div class="form-group">
        <span>Trạng thái sản phẩm:</span>
    </div>
    <div class="form-group">
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="state1" name="orderState" value="Đang chờ duyệt" required>
            <label class="custom-control-label" for="state1">Đang chờ duyệt</label>
        </div>
    </div>
    <div class="form-group">
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="state2" name="orderState" value="Đang giao hàng" required>
            <label class="custom-control-label" for="state2">Đang giao hàng</label>
        </div>
    </div>
    <div class="form-group">
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="state3" name="orderState" value="Đã giao hàng" required>
            <label class="custom-control-label" for="state3">Đã giao hàng</label>
        </div>
    </div>
    <div class="form-group">
        <div class="custom-control custom-radio">
            <input type="radio" class="custom-control-input" id="state4" name="orderState" value="Đã hủy" required>
            <label class="custom-control-label" for="state4">Đã hủy</label>
        </div>
    </div>
    <div>
        <button class="btn btn-primary btn-block mb-4" type="submit">Cập nhật</button>
    </div>
</form>
</body>
</html>

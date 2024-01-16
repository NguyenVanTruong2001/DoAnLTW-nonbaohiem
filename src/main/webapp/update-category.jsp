<%@ page import="beans.CategoryBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String message = (String) request.getAttribute("message"); %>
<% CategoryBean bean = (CategoryBean) request.getAttribute("categoryBean"); %>

        <html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <title>Cập nhật loại sản phẩm</title>
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
<form class="p-3 c" action="<c:url value="/update-category"/>" method="post">
    <h3 class="font-weight-bold mb-4 text-center">Cập nhật sản phẩm</h3>
    <% if (message != null) { %>
    <div class="alert alert-danger">
        <%= message %>
    </div>
    <% } %>
    <input type="hidden" name="categoryId" value="<%= bean.getCategoryId()%>">
    <div class="form-group">
        <label>Tên loại sản phẩm:</label>
        <input required type="text" name="categoryName" class="form-control border border-primary py-4" value="<%= bean.getCategoryName()%>">
    </div>
    <div>
        <button class="btn btn-primary btn-block mb-4" type="submit">Cập nhật</button>
    </div>
</form>
</body>
</html>

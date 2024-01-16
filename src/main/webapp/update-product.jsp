<%@ page import="beans.ProductBean" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.CategoryBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ProductBean product = (ProductBean) request.getAttribute("product"); %>
<% List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("categoryList"); %>
<% String message = (String) request.getAttribute("message"); %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <title>Cập nhật sản phẩm</title>
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
<form class="p-3 c" action="<c:url value="/update-product"/>" method="post" enctype="multipart/form-data">
    <h3 class="font-weight-bold mb-4 text-center">Cập nhật sản phẩm</h3>
    <% if (message != null) { %>
    <div class="alert alert-danger">
        <%= message %>
    </div>
    <% } %>
    <input type="hidden" name="productId" value="<%= product.getProductId()%>">
    <div class="form-group">
        <span>Loại sản phẩm</span>
        <select type="text" class="form-control border-primary" name="categoryId" required>
            <% for (CategoryBean bean : categoryList) { %>
            <option value="<%= bean.getCategoryId() %>"><%= bean.getCategoryName() %></option>
            <% } %>
        </select>
    </div>
    <div class="form-group">
        <label>Tên sản phẩm:</label>
        <input required type="text" name="name" class="form-control border border-primary py-4" value="<%= product.getProductName()%>">
    </div>
    <span class="form-group">Hình ảnh sản phẩm:</span>
    <div class="form-group custom-file my-1">
        <input required type="file" name="image" class="custom-file-input" value="<%= product.getProductImage()%>">
        <label class="custom-file-label border border-primary">Chọn hình ảnh</label>
    </div>
    <div class="form-group">
        <label>Mô tả sản phẩm:</label>
        <textarea required type="text" name="description" rows="10" class="form-control border border-primary py-4"><%= product.getProductDescription()%></textarea>
    </div>
    <div class="form-group">
        <span>Thương hiệu</span>
        <select type="text" class="form-control border-primary" name="brand" required>
            <option value="Royal Helmet">Royal Helmet</option>
            <option value="Asia">Asia</option>
            <option value="Andes">Andes</option>
            <option value="Protec">Protec</option>
            <option value="Scoot and Ride">Scoot and Ride</option>
            <option value="ROC">ROC</option>
        </select>
    </div>
    <div class="form-group">
        <span>Kích thước</span>
        <select type="text" class="form-control border-primary" name="size" required>
            <option value="XS">XS</option>
            <option value="S">S</option>
            <option value="M">M</option>
            <option value="L">L</option>
            <option value="XL">XL</option>
            <option value="XXL">XXL</option>
        </select>
    </div>
    <div class="form-group">
        <label>Giá sản phẩm:</label>
        <input required type="number" min="1000" step="500" name="price" class="form-control border border-primary py-4" value="<%= product.getProductPrice()%>">
    </div>
    <div>
        <button class="btn btn-primary btn-block mb-4" type="submit">Cập nhật</button>
    </div>
</form>

<script>
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
</script>

</body>
</html>

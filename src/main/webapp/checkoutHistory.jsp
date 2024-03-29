<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.*" %>
<%@ page import="dao.CheckoutDetailDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.text.DecimalFormat" %>
<% DecimalFormat format = new DecimalFormat("#,###.#"); %>
<% List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("categoryList"); %>
<% List<CheckoutBean> checkoutList = (List<CheckoutBean>) request.getAttribute("checkoutList"); %>
<% List<CheckoutDetailBean> checkoutDetailList; %>
<% UserBean user = (UserBean) session.getAttribute("user"); %>
<% int totalPrice = 0; %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lịch sử đặt hàng</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<!-- Topbar Start -->
<div class="container-fluid bg-secondary">
    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="" class="text-decoration-none">
                <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-primary px-3 mr-1">G46</span>Helmet</h1>
            </a>
        </div>
        <div class="col-lg-6 col-6 text-left">
            <form action="<c:url value="/search-product"/>" method="post">
                <div class="input-group">
                    <input type="text" name="query" class="form-control border-primary" placeholder="Tìm kiếm sản phẩm...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid">
    <div class="row border-top px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                <h5 class="m-0">Danh mục sản phẩm</h5>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <nav class="collapse position-absolute navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0 bg-light" id="navbar-vertical" style="width: calc(100% - 30px); z-index: 1;">
                <div class="navbar-nav w-100 overflow-hidden">
                    <% for (CategoryBean c : categoryList) { %>
                    <a href="shop?categoryId=<%= c.getCategoryId()%>" class="nav-item nav-link"><%= c.getCategoryName()%></a>
                    <% } %>
                </div>
            </nav>
        </div>
        <div class="col-lg-9">
            <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                <a href="" class="text-decoration-none d-block d-lg-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-primary px-3 mr-1">G46</span>Helmet</h1>
                </a>
                <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                    <div class="navbar-nav mr-auto py-0">
                        <a href="home" class="nav-item nav-link">Trang chủ</a>
                        <a href="shop" class="nav-item nav-link">Sản phẩm</a>
                        <a href="cart" class="nav-item nav-link">Giỏ hàng</a>
                        <% if (session.getAttribute("user") != null) { %>
                        <a href="checkout-history" class="nav-item nav-link active">Lịch sử đặt hàng</a>
                        <% } %>
                    </div>
                    <div class="navbar-nav ml-auto py-0">
                        <% if (user != null) { %>
                        <span class="nav-item nav-link"> <%= user.getUsername()%> </span>
                        <a href="logout" class="nav-item nav-link">Đăng xuất</a>
                        <% } else { %>
                        <a href="login" class="nav-item nav-link">Đăng nhập</a>
                        <a href="register" class="nav-item nav-link">Đăng ký</a>
                        <% } %>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<!-- Navbar End -->


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Lịch sử đặt hàng</h1>
    </div>
</div>
<!-- Page Header End -->


<!-- Checkout Start -->
<div class="container-fluid p-5">
    <div class="lg-4">
        <% for (CheckoutBean bean : checkoutList) { %>
        <div class="card border-secondary mb-5">
            <div class="card-header bg-secondary border-0">
                <div class="float-left">
                    <h4 class="font-weight-semi-bold m-0">Mã đơn hàng: <%= bean.getOrderId() %></h4>
                    <p class="m-0">Ngày đặt hàng: <%= bean.getOrderDate() %></p>
                </div>
                <div class="float-right">
                    <% if (bean.getOrderState().equals("Đang chờ duyệt") || bean.getOrderState().equals("Đang giao hàng")) { %>
                    <a href="update-checkout?orderId=<%= bean.getOrderId() %>&role=<%= user.getRole() %>&orderState=Đã hủy" class="btn btn-danger">Hủy giao hàng</a>
                    <% } %>
                </div>
            </div>
            <div class="card-body">
                <h5 class="font-weight-medium mb-3">Thông tin khách hàng</h5>
                <p class="mb-3">Họ và tên: <%= bean.getFullname() %></p>
                <p class="mb-3">Số điện thoại: <%= bean.getTelephone() %></p>
                <p class="mb-3">Địa chỉ nhận hàng: <%= bean.getAddress() %></p>
                <p class="mb-3">Phương thức thanh toán: <%= bean.getPaymentMethod() %></p>
                <p class="mb-3">Trạng thái đơn hàng: <%= bean.getOrderState() %></p>
                <hr class="mt-0">
                <h5 class="font-weight-medium mb-3">Sản phẩm</h5>
                <table class="table table-borderless">
                    <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Tổng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% try {
                        checkoutDetailList = new CheckoutDetailDao().getCheckoutDetailByOrderId(bean.getOrderId());
                    } catch (ClassNotFoundException | SQLException e) {
                        throw new RuntimeException(e);
                    }
                        for (CheckoutDetailBean bean1 : checkoutDetailList) {
                            totalPrice += bean1.detailPrice(); %>
                    <tr>
                        <td><%= bean1.getProductBean().getProductName() %></td>
                        <td><%= bean1.getQuantity() %></td>
                        <td><%= format.format(bean1.getProductBean().getProductPrice()) %> &#8363;</td>
                        <td><%= format.format(bean1.detailPrice()) %> &#8363;</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <div class="card-footer border-secondary bg-transparent">
                <div class="d-flex justify-content-between mt-2">
                    <h5 class="font-weight-bold">Thành tiền:</h5>
                    <h5 class="font-weight-bold"><%= format.format(totalPrice) %> &#8363;</h5>
                </div>
            </div>
        </div>
        <% totalPrice = 0;
        }
        %>
    </div>
</div>
<!-- Checkout End -->


<!-- Back to Top -->
<a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>

<!-- Contact Javascript File -->
<script src="mail/jqBootstrapValidation.min.js"></script>
<script src="mail/contact.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>
</body>
</html>
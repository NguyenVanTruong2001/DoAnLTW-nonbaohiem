<%@ page import="beans.CategoryBean" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.ProductCart" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="beans.UserBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("categoryList"); %>
<% HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart"); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Đặt hàng</title>
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
                                <a href="checkout-history" class="nav-item nav-link">Lịch sử đặt hàng</a>
                            <% } %>
                        </div>
                        <div class="navbar-nav ml-auto py-0">
                            <% if (session.getAttribute("user") != null) {
                                UserBean user = (UserBean) session.getAttribute("user"); %>
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
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Đặt Hàng</h1>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- Checkout Start -->
    <form action="<c:url value="/checkout"/>" method="post">
        <div class="container-fluid p-5">
            <div class="row px-xl-5">
                <div class="col-lg-8">
                    <div class="mb-4">
                        <h4 class="font-weight-semi-bold mb-4">Thông tin khách hàng</h4>
                        <div class="col">
                            <% if (session.getAttribute("user") != null) {
                                UserBean user = (UserBean) session.getAttribute("user"); %>
                                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                                <% if (cart.isEmpty()) { %>
                                <div class="col-md-12 form-group alert alert-danger">
                                    <p class="text-center">Cần có ít nhất một sản phẩm trong giỏ hàng để có thể đặt hàng thành công.</p>
                                </div>
                                <% } %>
                            <% } else { %>
                                <div class="col-md-12 form-group alert alert-danger">
                                    <p class="text-center">Cần đăng nhập để có thể đặt hàng thành công.</p>
                                </div>
                            <% } %>
                            <div class="col-md-12 form-group">
                                <label>Họ và tên:</label>
                                <input class="form-control border-primary" type="text" name="fullname" required autofocus>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>Số điện thoại:</label>
                                <input class="form-control border-primary" type="tel" name="telephone" required>
                            </div>
                            <div class="col-md-12 form-group">
                                <label>Địa chỉ nhận hàng:</label>
                                <input class="form-control border-primary" type="text" name="address" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <div class="card border-secondary mb-5">
                        <div class="card-header bg-secondary border-0">
                            <h4 class="font-weight-semi-bold m-0">Thanh toán</h4>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <div class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="cash" name="payment" value="Tiền mặt" required>
                                    <label class="custom-control-label" for="cash">Tiền mặt</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="bank-transfer" name="payment" value="Chuyển khoản" required>
                                    <label class="custom-control-label" for="bank-transfer">Chuyển khoản</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="momo" name="payment" value="MoMo" required>
                                    <label class="custom-control-label" for="momo">MoMo</label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="custom-control custom-radio">
                                    <input type="radio" class="custom-control-input" id="paypal" name="payment" value="PayPal" required>
                                    <label class="custom-control-label" for="paypal">PayPal</label>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer border-secondary bg-transparent">
                            <a href="cart" class="btn btn-lg btn-block btn-secondary font-weight-light my-3 py-3">Trở về Giỏ hàng</a>
                            <% if (session.getAttribute("user") != null) {
                                if (!cart.isEmpty()) { %>
                                <button type="submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Đặt hàng</button>
                            <%  }
                               } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
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
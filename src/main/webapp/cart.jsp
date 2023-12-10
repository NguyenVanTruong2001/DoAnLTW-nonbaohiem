<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="beans.*" %>
<% DecimalFormat format = new DecimalFormat("#,###.#"); %>
<% CategoryBean[] categoryList = (CategoryBean[]) request.getAttribute("categoryList"); %>
<% HashMap<Integer, ProductCart> cart = (HashMap<Integer, ProductCart>) session.getAttribute("cart"); %>
<% int priceFinal = 0; %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Giỏ hàng</title>
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
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control border-primary" placeholder="Tìm kiếm sản phẩm...">
                        <div class="input-group-append">
                            <button class="btn btn-primary">
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
                            <a href="shop?categoryId=0" class="nav-item nav-link">Sản phẩm</a>
                            <a href="cart" class="nav-item nav-link active">Giỏ hàng</a>
                            <a href="checkout.html" class="nav-item nav-link">Đặt hàng</a>
                            <a href="checkoutHistory.html" class="nav-item nav-link">Lịch sử đặt hàng</a>
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
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Giỏ Hàng</h1>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- Cart Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-8 table-responsive mb-5">
                <% if (!cart.isEmpty()) { %>
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th>Sản Phẩm</th>
                            <th>Giá tiền</th>
                            <th>Số lượng</th>
                            <th>Tổng</th>
                            <th>Xóa</th>
                        </tr>
                    </thead>
                    <% for (Map.Entry<Integer, ProductCart> entry : cart.entrySet()) {
                        priceFinal += entry.getValue().totalPrice(); %>
                    <tbody class="align-middle">
                        <tr>
                            <td class="align-middle"><img src="<%= entry.getValue().getProduct().getProductImage()%>" alt="" style="width: 50px;"> <%= entry.getValue().getProduct().getProductName()%></td>
                            <td class="align-middle"><%= format.format(entry.getValue().getProduct().getProductPrice())%> &#8363;</td>
                            <td class="align-middle">
                                <div class="input-group quantity mx-auto" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <a href="cart?command=remove&productId=<%= entry.getValue().getProduct().getProductId()%>&quantity=1" class="btn btn-sm btn-primary btn-minus">
                                        <i class="fa fa-minus"></i>
                                        </a>
                                    </div>
                                    <input type="text" class="form-control form-control-sm bg-secondary text-center" readonly value="<%= entry.getValue().getQuantity()%>">
                                    <div class="input-group-btn">
                                        <a href="cart?command=insert&productId=<%= entry.getValue().getProduct().getProductId()%>&quantity=1" class="btn btn-sm btn-primary btn-plus">
                                            <i class="fa fa-plus"></i>
                                        </a>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle"><%= format.format(entry.getValue().totalPrice())%> &#8363;</td>
                            <td class="align-middle"><a href="cart?command=delete&productId=<%= entry.getValue().getProduct().getProductId()%>" class="btn btn-sm btn-primary"><i class="fa fa-times"></i></a></td>
                        </tr>
                    </tbody>
                    <% } %>
                </table>
                <% } else { %>
                <p class="text-primary text-center font-weight-bold" style="font-size: 24px">Giỏ hàng trống.</p>
                <% } %>
            </div>
            <div class="col-lg-4">
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Hóa đơn</h4>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Thành tiền:</h5>
                            <h5 class="font-weight-bold"><%= format.format(priceFinal)%> &#8363;</h5>
                        </div>
                        <a href="checkout.html" class="btn btn-block btn-primary my-3 py-3" style="font-size: 120%">Chuyển tới Đặt hàng</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Cart End -->


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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.UserBean" %>
<%@ page import="beans.CategoryBean" %>
<%@ page import="beans.ProductBean" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<% DecimalFormat format = new DecimalFormat("#,###.#"); %>
<% List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("categoryList"); %>
<% List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList"); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Sản phẩm</title>
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
                        <a href="shop" class="nav-item nav-link active">Sản phẩm</a>
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
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Sản Phẩm Của Chúng Tôi</h1>
    </div>
</div>
<!-- Page Header End -->


<!-- Shop Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">
        <!-- Shop Sidebar Start -->
        <div class="col-lg-3 col-md-12">
            <!-- Price Start -->
            <div class="border-bottom mb-4 pb-4">
                <h5 class="font-weight-semi-bold mb-4">Lọc theo giá</h5>
                <div class="navbar-nav">
                    <a href="filter-product?type=price&min=0&max=150000" class="nav-item nav-link"><150.000đ</a>
                    <a href="filter-product?type=price&min=150000&max=300000" class="nav-item nav-link">150.000đ - 300.000đ</a>
                    <a href="filter-product?type=price&min=300000&max=450000" class="nav-item nav-link">300.000đ - 450.000đ</a>
                    <a href="filter-product?type=price&min=450000&max=600000" class="nav-item nav-link">450.000đ - 600.000đ</a>
                    <a href="filter-product?type=price&min=600000&max=<%= Integer.MAX_VALUE%>" class="nav-item nav-link">>600.000đ</a>
                </div>
            </div>
            <!-- Price End -->

            <!-- Color Start -->
            <div class="border-bottom mb-4 pb-4">
                <h5 class="font-weight-semi-bold mb-4">Lọc theo thương hiệu</h5>
                <div class="navbar-nav">
                    <a href="filter-product?type=brand&brand=Royal Helmet" class="nav-item nav-link">Royal Helmet</a>
                    <a href="filter-product?type=brand&brand=Asia" class="nav-item nav-link">Asia</a>
                    <a href="filter-product?type=brand&brand=Andes" class="nav-item nav-link">Andes</a>
                    <a href="filter-product?type=brand&brand=Protec" class="nav-item nav-link">Protec</a>
                    <a href="filter-product?type=brand&brand=Scoot and Ride" class="nav-item nav-link">Scoot and Ride</a>
                    <a href="filter-product?type=brand&brand=ROC" class="nav-item nav-link">ROC</a>
                </div>
            </div>
            <!-- Color End -->

            <!-- Size Start -->
            <div class="mb-5">
                <h5 class="font-weight-semi-bold mb-4">Lọc theo kích cỡ</h5>
                <div class="navbar-nav">
                    <a href="filter-product?type=size&size=XS" class="nav-item nav-link">XS</a>
                    <a href="filter-product?type=size&size=S" class="nav-item nav-link">S</a>
                    <a href="filter-product?type=size&size=M" class="nav-item nav-link">M</a>
                    <a href="filter-product?type=size&size=L" class="nav-item nav-link">L</a>
                    <a href="filter-product?type=size&size=XL" class="nav-item nav-link">XL</a>
                    <a href="filter-product?type=size&size=XXL" class="nav-item nav-link">XXL</a>
                </div>
            </div>
            <!-- Size End -->
        </div>
        <!-- Shop Sidebar End -->


        <!-- Shop Product Start -->
        <div class="col-lg-9 col-md-12">
            <div class="row pb-3">
                <!-- limit 9 offset (page-1)*9 -->
                <% for (ProductBean p : productList) { %>
                <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100 align-middle" src="<%= p.getProductImage()%>" alt="">
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3"><%= p.getProductName()%></h6>
                            <div class="d-flex justify-content-center">
                                <h6 class="text-primary"><%= format.format(p.getProductPrice())%> &#8363;</h6>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="detail?productId=<%= p.getProductId()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi tiết</a>
                            <a href="cart?command=insert&productId=<%= p.getProductId()%>&quantity=1"
                               class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ hàng</a>
                        </div>
                    </div>
                </div>
                <% } %>
                <!-- limit 9 offset (page-1)*9 -->
                <!-- Paginating -->
                <div class="col-12 pb-1">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center mb-3">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <!-- Paginating -->
            </div>
        </div>
        <!-- Shop Product End -->
    </div>
</div>
<!-- Shop End -->


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
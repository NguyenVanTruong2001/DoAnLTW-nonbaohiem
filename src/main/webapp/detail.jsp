<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="beans.UserBean" %>
<%@ page import="beans.CategoryBean" %>
<%@ page import="beans.ProductBean" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.ReviewBean" %>
<% DecimalFormat format = new DecimalFormat("#,###.#"); %>
<% List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("categoryList"); %>
<% List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList"); %>
<% List<ReviewBean> reviewList = (List<ReviewBean>) request.getAttribute("reviewList"); %>
<% ProductBean productBean = (ProductBean) request.getAttribute("productBean"); %>
<% int countReviews = (int) request.getAttribute("countReviews"); %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Chi tiết sản phẩm</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">

    <style>
        div.stars { display: inline-block; }

        input.star { display: none; }

        label.star {
            float: right;
            font-size: 36px;
            margin-right: 10px;
            color: #6c757d;
        }

        input.star:checked ~ label.star:before {
            content: '★ ';
            color: #D19C97;
        }

        label.star:before {
            content: '★ ';
        }
    </style>
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
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Chi Tiết Sản Phẩm</h1>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- Shop Detail Start -->
    <div class="container-fluid py-5">
        <div class="row px-xl-5">
            <div class="col-lg-5 pb-5">
                <div id="product-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner border">
                        <div class="carousel-item active">
                            <img class="w-100 h-100" src="img/product-1.jpg" alt="Image">
                        </div>
                        <div class="carousel-item">
                            <img class="w-100 h-100" src="img/product-2.jpg" alt="Image">
                        </div>
                        <div class="carousel-item">
                            <img class="w-100 h-100" src="img/product-3.jpg" alt="Image">
                        </div>
                        <div class="carousel-item">
                            <img class="w-100 h-100" src="img/product-4.jpg" alt="Image">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#product-carousel" data-slide="prev">
                        <i class="fa fa-2x fa-angle-left text-dark"></i>
                    </a>
                    <a class="carousel-control-next" href="#product-carousel" data-slide="next">
                        <i class="fa fa-2x fa-angle-right text-dark"></i>
                    </a>
                </div>
            </div>

            <div class="col-lg-7 pb-5">
                <h2 class="font-weight-semi-bold mb-4"><%= productBean.getProductName() %></h2>
                <h4 class="font-weight-semi-bold mb-4 text-primary"><%= format.format(productBean.getProductPrice()) %> &#8363;</h4>
                <hr class="mt-0">
                <p class="mb-4">Loại: <%= productBean.getCategoryBean().getCategoryName() %></p>
                <p class="mb-4">Thương hiệu: <%= productBean.getProductBrand() %></p>
                <p class="mb-4">Kích cỡ: <%= productBean.getProductSize() %></p>
                <div class="d-flex mb-3">
                </div>
                <form class="d-flex align-items-center mb-4 pt-2" action="cart" method="post">
                    <input type="hidden" name="command" value="insert">
                    <input type="hidden" name="productId" value="<%= productBean.getProductId() %>">
                    <div class="input-group quantity mr-3" style="width: 130px;">
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-primary btn-minus" >
                            <i class="fa fa-minus"></i>
                            </button>
                        </div>
                        <input type="text" class="form-control bg-secondary text-center" name="quantity" value="1" readonly>
                        <div class="input-group-btn">
                            <button type="button" class="btn btn-primary btn-plus">
                                <i class="fa fa-plus"></i>
                            </button>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary px-3">
                        <i class="fa fa-shopping-cart mr-1"></i> Thêm vào giỏ hàng
                    </button>
                </form>
            </div>
        </div>
        <div class="row px-xl-5">
            <div class="col">
                <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                    <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Mô tả</a>
                    <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Đánh giá</a>
                </div>
                <div class="tab-content">
                    <div class="tab-pane fade show active" id="tab-pane-1">
                        <h4 class="mb-3">Mô tả sản phẩm</h4>
                        <div>
                            <p><%= productBean.getProductDescription() %></p>
                        </div>
                        <div class="d-flex flex-row">
                            <img src="img/product-1.jpg" alt="">
                            <img src="img/product-2.jpg" alt="">
                            <img src="img/product-3.jpg" alt="">
                            <img src="img/product-4.jpg" alt="">
                        </div>
                    </div>
                    <div class="tab-pane fade" id="tab-pane-3">
                        <div class="row">
                            <div class="col-md-6">
                                <h4 class="mb-4"><%= countReviews %> bài đánh giá</h4>
                                <% for (ReviewBean bean : reviewList) { %>
                                    <div class="media mb-4">
                                        <div class="media-body">
                                            <h6><%= bean.getUserBean().getUsername() %></h6>
                                            <div class="text-primary mb-2">
                                                <% switch (bean.getRating()) {
                                                    case 1: %>
                                                <p>
                                                    <i class="fas fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                </p>
                                                <% break;
                                                    case 2: %>
                                                <p>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                </p>
                                                <% break;
                                                    case 3: %>
                                                <p>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                </p>
                                                <% break;
                                                    case 4: %>
                                                <p>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                </p>
                                                <% break;
                                                    case 5: %>
                                                <p>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                    <i class="fas fa-star"></i>
                                                </p>
                                                <% break;
                                                    default:
                                                } %>
                                            </div>
                                            <p><%= bean.getComment() %></p>
                                        </div>
                                    </div>
                                <% } %>
                            </div>
                            <div class="col-md-6">
                                <h4 class="mb-4">Đánh giá</h4>
                                <% if (session.getAttribute("user") != null) {
                                    UserBean user = (UserBean) session.getAttribute("user"); %>
                                    <form action="<c:url value="/review"/>" method="post">
                                        <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                                        <input type="hidden" name="productId" value="<%= productBean.getProductId() %>">
                                        <div class="d-flex my-1">
                                            <p class="mb-0 mr-2">Đánh giá của bạn:</p>
                                        </div>
                                        <div class="stars my-1">
                                            <input class="star star-5" id="star-5" type="radio" name="star" value="5">
                                            <label class="star star-5" for="star-5"></label>
                                            <input class="star star-4" id="star-4" type="radio" name="star" value="4">
                                            <label class="star star-4" for="star-4"></label>
                                            <input class="star star-3" id="star-3" type="radio" name="star" value="3">
                                            <label class="star star-3" for="star-3"></label>
                                            <input class="star star-2" id="star-2" type="radio" name="star" value="2">
                                            <label class="star star-2" for="star-2"></label>
                                            <input class="star star-1" id="star-1" type="radio" name="star" value="1">
                                            <label class="star star-1" for="star-1"></label>
                                        </div>
                                        <div class="form-group my-1">
                                            <label for="comment">Nhận xét:</label>
                                            <textarea id="comment" name="comment" cols="30" rows="5" class="form-control border-primary"></textarea>
                                        </div>
                                        <div class="form-group my-4">
                                            <input type="submit" value="Gửi bài đánh giá" class="btn btn-primary px-3">
                                        </div>
                                    </form>
                                <% } else { %>
                                    <div class="form-group alert alert-danger">
                                        <p class="text-center">Bạn cần đăng nhập để có thể bình luận về sản phẩm này.</p>
                                    </div>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Shop Detail End -->


    <!-- Products Start -->
    <div class="container-fluid py-5">
        <div class="text-center mb-4">
            <h2 class="section-title px-5"><span class="px-2">Bạn cũng sẽ thích</span></h2>
        </div>
        <div class="row px-xl-5">
            <div class="col">
                <div class="owl-carousel related-carousel">
                    <% for (ProductBean p : productList) { %>
                    <div class="card product-item border-0">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <img class="img-fluid w-100" src="<%= p.getProductImage()%>" alt="">
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <h6 class="text-truncate mb-3"><%= p.getProductName()%></h6>
                            <div class="d-flex justify-content-center">
                                <h6><%= format.format(p.getProductPrice()) %> &#8363;</h6>
                            </div>
                        </div>
                        <div class="card-footer d-flex justify-content-between bg-light border">
                            <a href="detail?productId=<%= p.getProductId()%>" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i>Xem chi tiết</a>
                            <a href="cart?command=insert&productId=<%= p.getProductId()%>&quantity=1" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i>Thêm vào giỏ hàng</a>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
    <!-- Products End -->


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
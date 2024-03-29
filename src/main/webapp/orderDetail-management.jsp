<%@ page import="beans.CheckoutBean" %>
<%@ page import="java.util.List" %>
<%@ page import="beans.CheckoutDetailBean" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% DecimalFormat format = new DecimalFormat("#,###.#"); %>
<% CheckoutBean checkout = (CheckoutBean) request.getAttribute("checkout"); %>
<% List<CheckoutDetailBean> checkoutDetailList = (List<CheckoutDetailBean>) request.getAttribute("checkoutDetailList"); %>
<% int totalPrice = 0; %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Chi tiết đơn hàng</title>

    <!-- Custom fonts for this template -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="dashboard">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3" style="font-size: 90%">Quản trị viên</div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item">
            <a class="nav-link" href="dashboard">
                <i class="fas fa-fw fa-chart-area"></i>
                <span>Thống kê</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <div class="sidebar-heading">
            Quản lý
        </div>

        <!-- Nav Item - Users -->
        <li class="nav-item">
            <a class="nav-link" href="user-management">
                <i class="fas fa-fw fa-user-alt"></i>
                <span>Người dùng</span></a>
        </li>

        <!-- Nav Item - Products -->
        <li class="nav-item">
            <a class="nav-link" href="product-management">
                <i class="fas fa-fw fa-gifts"></i>
                <span>Sản phẩm</span></a>
        </li>

        <!-- Nav Item - Orders -->
        <li class="nav-item">
            <a class="nav-link" href="order-management">
                <i class="fas fa-fw fa-clipboard"></i>
                <span>Đơn hàng</span></a>
        </li>

        <!-- Nav Item - Review -->
        <li class="nav-item">
            <a class="nav-link" href="review-management">
                <i class="fas fa-fw fa-star"></i>
                <span>Đánh giá</span></a>
        </li>

        <!-- Nav Item - Categories -->
        <li class="nav-item">
            <a class="nav-link" href="category-management">
                <i class="fas fa-fw fa-hard-hat"></i>
                <span>Loại sản phẩm</span></a>
        </li>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">
                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">Admin</span>
                            <img alt="" class="img-profile rounded-circle"
                                 src="img/undraw_profile.svg">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                Đăng xuất
                            </a>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800 pb-3">Chi tiết đơn hàng</h1>
                <div class="d-flex flex-row">
                    <p class="mb-2 text-gray-600 pb-1 flex-fill">Mã đơn hàng: <%= checkout.getOrderId() %></p>
                    <p class="mb-2 text-gray-600 pb-1 flex-fill">Tên người dùng: <%= checkout.getUserBean().getUsername() %></p>
                    <p class="mb-2 text-gray-600 pb-1 flex-fill">Ngày đặt hàng: <%= checkout.getOrderDate() %></p>
                </div>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="container-fluid">
                            <h5 class="pb-4">Thông tin khách hàng</h5>
                            <p>Họ và tên: <%= checkout.getFullname() %></p>
                            <p>Số điện thoại: <%= checkout.getTelephone() %></p>
                            <p>Địa chỉ nhận hàng: <%= checkout.getAddress() %></p>
                            <p>Phương thức thanh toán: <%= checkout.getPaymentMethod() %></p>
                            <p>Trạng thái đơn hàng: <%= checkout.getOrderState() %></p>
                        </div>
                        <hr>
                        <div class="container-fluid">
                            <h5 class="pb-4">Hóa đơn</h5>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Mã sản phẩm</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Tổng</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (CheckoutDetailBean bean : checkoutDetailList) {
                                    totalPrice += bean.detailPrice(); %>
                                <tr>
                                    <td><%= bean.getProductBean().getProductId() %></td>
                                    <td><%= bean.getProductBean().getProductName() %></td>
                                    <td><%= format.format(bean.getProductBean().getProductPrice()) %> &#8363;</td>
                                    <td><%= bean.getQuantity() %></td>
                                    <td><%= format.format(bean.detailPrice()) %> &#8363;</td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                            <div class="container-fluid text-right">
                                <p>Thành tiền: <%= format.format(totalPrice) %> &#8363;</p>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Đăng xuất?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Chọn "Đăng xuất" để kết thúc phiên làm việc?</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>
                <a class="btn btn-primary" href="logout">Đăng xuất</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="lib/easing/easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="vendor/datatables/jquery.dataTables.min.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="js/demo/datatables-demo.js"></script>

</body>
</html>
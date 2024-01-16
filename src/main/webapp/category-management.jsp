<%@ page import="java.util.List" %>
<%@ page import="beans.CategoryBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<CategoryBean> categoryList = (List<CategoryBean>) request.getAttribute("categoryList"); %>
<% String message = (String) request.getAttribute("message"); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Loại sản phẩm</title>

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

        <!-- Nav Item - Images -->
        <li class="nav-item">
            <a class="nav-link" href="image-management.html">
                <i class="fas fa-fw fa-image"></i>
                <span>Hình ảnh</span></a>
        </li>

        <!-- Nav Item - Review -->
        <li class="nav-item">
            <a class="nav-link" href="review-management">
                <i class="fas fa-fw fa-star"></i>
                <span>Đánh giá</span></a>
        </li>

        <!-- Nav Item - Categories -->
        <li class="nav-item active">
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
                <div class="d-flex pb-3">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800 pb-3">Loại sản phẩm</h1>
                    <% if (message != null) { %>
                    <div class="alert alert-danger">
                        <%= message %>
                    </div>
                    <% } %>
                    <button class="btn btn-primary ml-auto" data-toggle="modal" data-target="#addCategory">
                        <i class="fas fa fa-plus"></i>
                        <span class="pl-2">Thêm sản phẩm</span>
                    </button>
                </div>

                <!-- The Modal -->
                <div class="modal fade" id="addCategory">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-header">
                                <h4 class="modal-title">Thêm loại sản phẩm</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <form action="<c:url value="/add-category"/>" method="post">
                                <!-- Modal body -->
                                <div class="modal-body">
                                    <div class="form-group">
                                        <span>Tên loại sản phẩm</span>
                                        <input type="text" class="form-control" name="name" required>
                                    </div>
                                    <div class="form-group float-right">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                                        <button type="submit" class="btn btn-primary">Thêm</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="categoryTable">
                                <thead>
                                <tr>
                                    <th>Mã loại sản phẩm</th>
                                    <th>Tên loại sản phẩm</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Mã loại sản phẩm</th>
                                    <th>Tên loại sản phẩm</th>
                                    <th>Thao tác</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <% for (CategoryBean bean : categoryList) { %>
                                <tr>
                                    <th><%= bean.getCategoryId() %></th>
                                    <td><%= bean.getCategoryName() %></td>
                                    <td>
                                        <a href="form-category?categoryId=<%= bean.getCategoryId() %>" class="btn btn-dark my-1" style="background-color: #1cc88a; color: #f8f9fc">
                                            <i class="fas fa-pen-alt"></i>
                                        </a>
                                        <a href="delete-category?categoryId=<%= bean.getCategoryId() %>" class="btn btn-dark" style="background-color: #e74a3b; color: #f8f9fc">
                                            <i class="fas fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
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
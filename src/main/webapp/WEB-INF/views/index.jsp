<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>

<body>
<div class="container">
    <header>
        <img style="width: 100%; height: 250px; object-fit: cover;"
             src="https://cdn2.cellphones.com.vn/insecure/rs:fill:690:300/q:90/plain/https://dashboard.cellphones.com.vn/storage/laptop-ai-banner-chung-slide.png" alt>
    </header>
    <div class="bg-danger">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarLeft">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active text-white" aria-current="page" href="/home/index">CellphoneS</a>
                        </li>
                    </ul>
                </div>
                <div class="collapse navbar-collapse" id="navbarRight">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link text-white disabled" aria-disabled="true" href="#">Đơn
                                hàng</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link text-white dropdown-toggle" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false"><i class="bi bi-person"></i></a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="/login">Đăng nhập</a></li>
                                <li><a class="dropdown-item" href="#">Đăng kí</a></li>
                                <li><a class="dropdown-item" href="#">Tra cứu thông tin</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="/logout">Đăng xuất</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>

        </nav>
    </div>

    <main class="row">

        <div class="col-3 mt-4">
            <div class="border border-secondary rounded my-4 p-3 mt-4">
                <form class="d-flex" action="/home/index" role="search">
                    <input class="form-control me-2" type="number" name="idHdct" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>

            </div>

            <div class="accordion accordion-flush" id="accordionFlushExample">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button style="box-shadow: none;" class="accordion-button collapsed" type="button"
                                data-bs-toggle="collapse" data-bs-target="#flush-collapseO" aria-expanded="false"
                                aria-controls="flush-collapseOne">
                            <i class="bi bi-cart-fill"></i> Bán hàng
                        </button>
                    </h2>
                    <div id="flush-collapseO" class="accordion-collapse collapse"
                         data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body" style="padding: 0;">
                            <ul class="list-group">
                                <a class="btn" href="/ban-hang/hien-thi" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-cart-check-fill"></i>  Bán hàng</li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button style="box-shadow: none;" class="accordion-button collapsed" type="button"
                                data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false"
                                aria-controls="flush-collapseOne">
                            <i class="bi bi-file-earmark-text-fill"></i> Quản lý hóa đơn
                        </button>
                    </h2>
                    <div id="flush-collapseOne" class="accordion-collapse collapse"
                         data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body" style="padding: 0;">
                            <ul class="list-group">
                                <a class="btn" href="/hoa-don/hien-thi" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-receipt"></i> Hóa đơn</li>
                                </a>
                                <a class="btn" href="/hoa-don-chi-tiet/hien-thi" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-journal-text"></i> Hóa đơn chi tiết
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button style="box-shadow: none;" class="accordion-button collapsed" type="button"
                                data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false"
                                aria-controls="flush-collapseTwo">
                            <i class="bi bi-box-seam-fill"></i> Quản lý sản phẩm
                        </button>
                    </h2>
                    <div id="flush-collapseTwo" class="accordion-collapse collapse"
                         data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body" style="padding: 0;">
                            <ul class="list-group">
                                <a href="/san-pham/hien-thi" class="btn" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-basket-fill"></i>Sản phẩm
                                    </li>
                                </a>
                                <a href="/san-pham-chi-tiet/hien-thi" class="btn" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-boxes"></i> Sản phẩm chi tiết
                                    </li>
                                </a>
                                <a href="/kich-thuoc/hien-thi" class="btn" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-arrows-angle-expand"></i> Kích thước
                                    </li>
                                </a>
                                <a href="/mau-sac/hien-thi" class="btn" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-palette-fill"></i> Màu sắc
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>



                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button style="box-shadow: none;" class="accordion-button collapsed" type="button"
                                data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false"
                                aria-controls="flush-collapseOne">
                            <i class="bi bi-people-fill"></i> Quản lý User
                        </button>
                    </h2>
                    <div id="flush-collapseThree" class="accordion-collapse collapse"
                         data-bs-parent="#accordionFlushExample">
                        <div class="accordion-body" style="padding: 0;">
                            <ul class="list-group">
                                <a class="btn" href="/nhan-vien/hien-thi" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-person-badge-fill"></i>  Nhân Viên
                                    </li>
                                </a>
                                <a class="btn" href="/khach-hang/hien-thi" style="padding: 0">
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <i class="bi bi-person-lines-fill"></i> Khách hàng
                                    </li>
                                </a>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="col-9 mt-4">
            <div class="mt-5" style="width: 90%; margin: 0 auto">
                <h2 class="mb-4 text-center">
                    <i class="fas fa-file-invoice"></i> DANH SÁCH HÓA ĐƠN
                </h2>
                <div class="table-responsive" style="max-height: 200px; overflow:auto;">
                    <table class="table table-bordered table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Tên NV</th>
                            <th>Tên KH</th>
                            <th>Ngày mua</th>
                            <th>ID SPCT</th>
                            <th>Tên SP</th>
                            <th>MS</th>
                            <th>KT</th>
                            <th>SL</th>
                            <th>Đơn giá</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="hdct" items="${dsHoaDon}">
                            <tr>
                                <td>${hdct.hoaDon.id}</td>
                                <td>${hdct.hoaDon.nhanVien.ten}</td>
                                <td>${hdct.hoaDon.khachHang.ten}</td>
                                <td>${hdct.hoaDon.ngayMuaHang}</td>
                                <td>${hdct.sanPhamChiTiet.id}</td>
                                <td>${hdct.sanPhamChiTiet.sanPham.ten}</td>
                                <td>${hdct.sanPhamChiTiet.mauSac.ten}</td>
                                <td>${hdct.sanPhamChiTiet.kichThuoc.ten}</td>
                                <td>${hdct.soLuong}</td>
                                <td>${hdct.donGia}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="d-flex justify-content-end m-3">
                    <a href="/ban-hang/add" class="btn btn-success">
                        <i class="fas fa-plus"></i> Thêm hóa đơn mới
                    </a>
                </div>
            </div>
        </div>
    </main>
    <footer class="bg-danger">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarText">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <li class="nav-item">
                            <a class="nav-link text-white" href="#"><i class="bi bi-phone"></i>Cellphones</a>
                        </li>

                    </ul>
                    <span class="navbar-text text-white mx-2">
                           <i class="bi bi-facebook"></i>
                        </span>

                    <span class="navbar-text text-white mx-2">
                            <i class="bi bi-instagram"></i>
                        </span>

                    <span class="navbar-text text-white mx-2">
                            <i class="bi bi-youtube"></i>
                        </span>
                </div>
            </div>
        </nav>
    </footer>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous">

</script>

</html>
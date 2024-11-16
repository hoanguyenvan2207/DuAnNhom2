<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách hóa đơn</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.5.2/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
        }
        th {
            position: sticky;
            top: 0;
            z-index: 10;
        }
    </style>
</head>
<body>
<div class="mt-2" style="width: 90%; margin: 0 auto">
    <a href="/home/index" class="btn btn-secondary btn-sm mx-4">
        <i class="fas fa-home"></i> Home
    </a>
    <h2 class="mb-4 text-center">
        <i class="fas fa-file-invoice"></i> DANH SÁCH HÓA ĐƠN
    </h2>
    <div class="d-flex justify-content-between align-items-center mb-3">
        <a href="/ban-hang/add" class="btn m-2 btn-primary">
            <i class="fas fa-plus"></i> Thêm hóa đơn mới
        </a>
        <form class="d-flex" role="search" action="/ban-hang/hien-thi">
            <input class="form-control me-2" style="width: 500px" type="search" placeholder="Nhập ID:" aria-label="Search" name="idHoaDon">
            <button class="btn btn-outline-success" type="submit">
                <i class="fas fa-search"></i> Search
            </button>
        </form>
    </div>

    <div class="table-responsive" style="max-height: 500px; overflow:auto;">
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
            <th>Trạng thái</th>
            <th>Hoạt động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${groupedData}">
            <c:set var="hoaDon" value="${entry.key}"/>
            <c:set var="sanPhamChiTietList" value="${entry.value}"/>

            <c:forEach var="hdct" items="${sanPhamChiTietList}">
                <tr>
                    <td><c:if test="${hdct == sanPhamChiTietList[0]}">${hoaDon.id}</c:if></td>
                    <td><c:if test="${hdct == sanPhamChiTietList[0]}">${hoaDon.nhanVien.ten}</c:if></td>
                    <td><c:if test="${hdct == sanPhamChiTietList[0]}">${hoaDon.khachHang.ten}</c:if></td>
                    <td><c:if test="${hdct == sanPhamChiTietList[0]}">${hoaDon.ngayMuaHang}</c:if></td>

                    <td>${hdct.sanPhamChiTiet.id}</td>
                    <td>${hdct.sanPhamChiTiet.sanPham.ten}</td>
                    <td>${hdct.sanPhamChiTiet.mauSac.ten}</td>
                    <td>${hdct.sanPhamChiTiet.kichThuoc.ten}</td>
                    <td>${hdct.soLuong}</td>
                    <td>${hdct.donGia}</td>

                    <td>
                        <c:if test="${hdct == sanPhamChiTietList[0]}">
                        <span class="badge ${hoaDon.trangThai ? 'badge-success' : 'badge-danger'}">
                                ${hoaDon.trangThai ? "Đã thanh toán" : "Chưa thanh toán"}
                        </span>
                        </c:if>
                    </td>

                    <td>
                        <c:if test="${hdct == sanPhamChiTietList[0]}">
                            <a href="/ban-hang/update/${hoaDon.id}" class="btn btn-warning btn-sm">
                                <i class="fas fa-edit"></i> Cập nhật
                            </a>
                            <a href="/ban-hang/detail?id=${hoaDon.id}" class="btn btn-info btn-sm">
                                <i class="fas fa-info-circle"></i> Chi tiết
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách khách hàng</title>

    <!-- Nhúng Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        h2 {
            color: #007bff;
        }
        .badge-success {
            background-color: #28a745;
        }
        .badge-danger {
            background-color: #dc3545;
        }
        .btn-outline-success {
            margin-left: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <a href="/home/index" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-home"></i> Home
    </a>
    <h2 class="mt-4 mb-3 text-center">DANH SÁCH KHÁCH HÀNG</h2>

    <form class="d-flex mb-3" role="search" action="/khach-hang/hien-thi">
        <input class="form-control me-2 w-25 mx-3" type="search" placeholder="Nhập ID khách hàng:" aria-label="Search" name="idKh">
        <button class="btn btn-outline-success" type="submit">
            <i class="fas fa-search"></i> Tìm kiếm
        </button>
    </form>

    <table class="table table-striped table-bordered table-hover">
        <thead class="thead-dark">
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>Tên</th>
            <th>SĐT</th>
            <th>Mã KH</th>
            <th>Trạng Thái</th>
            <th>Hoạt động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="stt" var="kh" items="${dsKhachHang}">
            <tr>
                <td>${stt.count}</td>
                <td>${kh.id}</td>
                <td>${kh.ten}</td>
                <td>${kh.sdt}</td>
                <td>${kh.maKh}</td>
                <td>
                    <span class="badge ${kh.trangThai ? 'badge-success' : 'badge-danger'}">
                            ${kh.trangThai ? "Hoạt động" : "Không hoạt động"}
                    </span>
                </td>
                <td>
                    <a href="/khach-hang/update/${kh.id}" class="btn btn-primary btn-sm">
                        <i class="fas fa-edit"></i> Cập nhật
                    </a>
                    <a href="/khach-hang/delete?id=${kh.id}" onclick="return confirm('Chắc chắn xóa khách hàng này?')" class="btn btn-danger btn-sm">
                        <i class="fas fa-trash-alt"></i> Xóa
                    </a>
                    <a href="/khach-hang/detail?id=${kh.id}" class="btn btn-success btn-sm">
                        <i class="fas fa-info-circle"></i> Chi tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/khach-hang/add" class="btn btn-success">
        <i class="fas fa-plus"></i> Thêm mới khách hàng
    </a>
</div>

<!-- Nhúng Bootstrap JS, Popper.js, và jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết khách hàng</title>
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
    </style>
</head>
<body>
<div class="container">
    <h2 class="mt-4 text-center">
        <i class="fas fa-user"></i> CHI TIẾT KHÁCH HÀNG
    </h2>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <td>${khachHang.id}</td>
        </tr>
        <tr>
            <th>Tên khách hàng</th>
            <td>${khachHang.ten}</td>
        </tr>
        <tr>
            <th>Số điện thoại</th>
            <td>${khachHang.sdt}</td>
        </tr>
        <tr>
            <th>Mã khách hàng</th>
            <td>${khachHang.maKh}</td>
        </tr>
        <tr>
            <th>Trạng thái</th>
            <td>${khachHang.trangThai ? "Hoạt động" : "Không hoạt động"}</td>
        </tr>
    </table>
    <a href="/khach-hang/hien-thi" class="btn btn-primary">
        <i class="fas fa-arrow-left"></i> Quay lại danh sách
    </a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

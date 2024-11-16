<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Tiết Kích Thước</title>
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
        }
        h2 {
            color: #007bff;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center">
        <i class="fas fa-arrows-alt"></i> CHI TIẾT KÍCH THƯỚC
    </h2>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <th scope="row">Mã:</th>
            <td>${kichThuoc.ma}</td>
        </tr>
        <tr>
            <th scope="row">Tên:</th>
            <td>${kichThuoc.ten}</td>
        </tr>
        <tr>
            <th scope="row">Trạng Thái:</th>
            <td>${kichThuoc.trangThai ? 'Hoạt động' : 'Ngừng hoạt động'}</td>
        </tr>
        </tbody>
    </table>
    <a href="/kich-thuoc/hien-thi" class="btn btn-secondary">
        <i class="fas fa-arrow-left"></i> Quay lại danh sách
    </a>
    <a href="/kich-thuoc/update/${kichThuoc.id}" class="btn btn-primary ml-2">
        <i class="fas fa-edit"></i> Sửa Kích Thước
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

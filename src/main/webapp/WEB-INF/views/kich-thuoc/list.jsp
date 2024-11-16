<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Kích Thước</title>
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
    <a href="/home/index" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-home"></i> Home
    </a>
    <h2 class="text-center">
        <i class="fas fa-arrows-alt"></i> DANH SÁCH KÍCH THƯỚC
    </h2>
    <form action="/kich-thuoc/search" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" class="form-control" name="ma" placeholder="Tìm kiếm..." value="${ma}">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i> Tìm kiếm
                </button>
            </div>
        </div>
    </form>

    <table class="table table-bordered table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Trạng Thái</th>
            <th>Hoạt Động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="kichThuoc" items="${dsKichThuoc}">
            <tr>
                <td>${kichThuoc.id}</td>
                <td>${kichThuoc.ma}</td>
                <td>${kichThuoc.ten}</td>
                <td>
                    <span class="badge ${kichThuoc.trangThai ? 'badge-success' : 'badge-danger'}">
                            ${kichThuoc.trangThai ? 'Hoạt động' : 'Ngừng hoạt động'}
                    </span>
                </td>
                <td>
                    <a href="/kich-thuoc/update/${kichThuoc.id}" class="btn btn-primary btn-sm">
                        <i class="fas fa-edit"></i> Cập nhật
                    </a>
                    <a href="/kich-thuoc/delete/${kichThuoc.id}" class="btn btn-danger btn-sm" onclick="return confirm('Chắc chắn xóa kích thước này?')">
                        <i class="fas fa-trash-alt"></i> Xóa
                    </a>
                    <a href="/kich-thuoc/detail/${kichThuoc.id}" class="btn btn-info btn-sm">
                        <i class="fas fa-info-circle"></i> Chi Tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/kich-thuoc/add" class="btn btn-success mb-3">
        <i class="fas fa-plus"></i> Thêm Mới Kích Thước
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

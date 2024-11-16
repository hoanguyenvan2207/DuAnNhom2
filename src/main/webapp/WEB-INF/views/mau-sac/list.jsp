<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Màu Sắc</title>
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
    <h2 class="text-center">DANH SÁCH MÀU SẮC</h2>

    <form action="/mau-sac/search" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" class="form-control" name="ma" placeholder="Tìm kiếm..." value="${ma}">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search"></i> Tìm kiếm
                </button>
            </div>
        </div>
    </form>

    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>STT</th>
            <th>ID</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Trạng Thái</th>
            <th>Hoạt động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach varStatus="stt" var="mauSac" items="${dsMauSac}">
            <tr>
                <td>${stt.index + 1}</td>
                <td>${mauSac.id}</td>
                <td>${mauSac.ma}</td>
                <td>${mauSac.ten}</td>
                <td>
                    <span class="badge ${mauSac.trangThai ? 'badge-success' : 'badge-danger'}">
                            ${mauSac.trangThai ? 'Hoạt động' : 'Không hoạt động'}
                    </span>
                </td>
                <td>
                    <a href="/mau-sac/update/${mauSac.id}" class="btn btn-warning">
                        <i class="fas fa-edit"></i> Sửa
                    </a>
                    <a href="/mau-sac/delete/${mauSac.id}" class="btn btn-danger"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">
                        <i class="fas fa-trash-alt"></i> Xóa
                    </a>
                    <a href="/mau-sac/detail/${mauSac.id}" class="btn btn-info">
                        <i class="fas fa-info-circle"></i> Chi tiết
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="/mau-sac/add" class="btn btn-success">
        <i class="fas fa-plus"></i> Thêm Mới Màu Sắc
    </a>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập Nhật Kích Thước</title>
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
    <a href="/kich-thuoc/hien-thi" class="btn btn-secondary btn-sm mb-4">
        <i class="fas fa-arrow-left"></i> Trở Về
    </a>
    <h2 class="text-center">
        <i class="fas fa-pencil-alt"></i> CẬP NHẬT KÍCH THƯỚC
    </h2>
    <form action="/kich-thuoc/update/${kichThuoc.id}" method="post">
        <div class="form-group">
            <label for="ma">Mã:</label>
            <input type="text" class="form-control" id="ma" name="ma" value="${kichThuoc.ma}" required>
        </div>
        <div class="form-group">
            <label for="ten">Tên:</label>
            <input type="text" class="form-control" id="ten" name="ten" value="${kichThuoc.ten}" required>
        </div>
        <div class="form-group">
            <label for="trang_thai">Trạng Thái:</label>
            <select class="form-control" id="trang_thai" name="trangThai">
                <option value="true" ${kichThuoc.trangThai ? 'selected' : ''}>Hoạt động</option>
                <option value="false" ${!kichThuoc.trangThai ? 'selected' : ''}>Ngừng hoạt động</option>
            </select>
        </div>
        <button type="submit" class="btn btn-success" onclick="return confirm('Cập nhật kích thước này?')">
            <i class="fas fa-check"></i> Cập Nhật Kích Thước
        </button>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

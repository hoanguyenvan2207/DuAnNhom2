<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật khách hàng</title>
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
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <a href="/khach-hang/hien-thi" class="btn btn-secondary btn-sm mb-3">
        <i class="fas fa-arrow-left"></i> Trở Về
    </a>
    <h2 class="text-center mb-4">CẬP NHẬT KHÁCH HÀNG</h2>
    <form:form action="/khach-hang/update" modelAttribute="khachHang" method="post">
        <input type="hidden" name="id" value="${khachHang.id}" />
        <div class="form-group">
            <label for="ten">Tên khách hàng:</label>
            <form:input path="ten" class="form-control" id="ten" placeholder="Nhập tên khách hàng" />
            <form:errors path="ten" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label for="sdt">Số điện thoại:</label>
            <form:input path="sdt" class="form-control" id="sdt" placeholder="Nhập số điện thoại" />
            <form:errors path="sdt" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label for="ma_kh">Mã khách hàng:</label>
            <form:input path="maKh" class="form-control" id="ma_kh" placeholder="Nhập mã khách hàng" />
            <form:errors path="maKh" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label for="trang_thai">Trạng thái:</label>
            <select class="form-control" id="trang_thai" name="trangThai">
                <option value="" selected disabled hidden>---Chọn---</option>
                <option value="true" ${khachHang.trangThai ? 'selected' : ''}>Hoạt động</option>
                <option value="false" ${!khachHang.trangThai ? 'selected' : ''}>Không hoạt động</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary" onclick="return confirm('Bạn có chắc muốn cập nhật khách hàng này?')">
            <i class="fas fa-save"></i> Cập nhật khách hàng
        </button>
    </form:form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

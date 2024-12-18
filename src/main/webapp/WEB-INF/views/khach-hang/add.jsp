<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm khách hàng</title>
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
    <a href="/khach-hang/hien-thi" class="btn btn-secondary btn-sm mt-4">
        <i class="fas fa-arrow-left"></i> Trở Về
    </a>
    <h2 class="mt-4 text-center">
        <i class="fas fa-user-plus"></i> THÊM KHÁCH HÀNG
    </h2>
    <form:form action="${khachHang.id == null ? '/khach-hang/add' : '/khach-hang/update'}" modelAttribute="khachHang" method="post">
        <div class="form-group">
            <label for="ten">Tên khách hàng:</label>
            <form:input path="ten" class="form-control" id="ten" />
            <form:errors path="ten" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label for="sdt">Số điện thoại:</label>
            <form:input path="sdt" class="form-control" id="sdt" />
            <form:errors path="sdt" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label for="ma_kh">Mã khách hàng:</label>
            <form:input path="maKh" class="form-control" id="ma_kh" />
            <form:errors path="maKh" cssClass="text-danger" />
        </div>
        <div class="form-group">
            <label for="trang_thai">Trạng thái:</label>
            <select class="form-control" id="trang_thai" name="trangThai">
                <option disabled selected hidden="hidden" value="">---Chọn trạng thái---</option>
                <option value="true">Hoạt động</option>
                <option value="false">Không hoạt động</option>
            </select>
            <form:errors path="trangThai" cssClass="text-danger" />
        </div>
        <button type="submit" class="btn btn-primary" onclick="return confirm('Bạn có chắc muốn thêm khách hàng này?')">
            <i class="fas fa-user-plus"></i> Thêm mới khách hàng
        </button>
    </form:form>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

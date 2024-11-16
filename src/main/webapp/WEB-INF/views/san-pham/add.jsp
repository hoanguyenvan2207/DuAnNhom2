<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="vi">
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
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Sản Phẩm</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body style="background-size:cover; background-position: center; background-attachment: fixed;  background-image: url('https://treobangron.com.vn/wp-content/uploads/2022/09/background-dep-3-2.jpg')">
<div class="container mt-4" style="width: 700px">
    <a href="/san-pham/hien-thi" class="btn btn-secondary btn-sm mt-4"><i class="fas fa-arrow-left"></i> Trở về</a>
    <h2 class="text-center">THÊM SẢN PHẨM</h2>
    <form:form action="/san-pham/create" modelAttribute="sanPham" method="post">
        <div class="form-group">
            <label for="ma">Mã:</label>
            <form:input path="ma" class="form-control" id="ma" />
            <form:errors path="ma" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="ten">Tên:</label>
            <form:input path="ten" class="form-control" id="ten"/>
            <form:errors path="ten" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="trang_thai">Trạng Thái:</label>
            <form:select path="trangThai" class="form-control" id="trang_thai">
                <option value="" disabled selected hidden="hidden">---Chọn trạng thái---</option>
                <option value="true">Đang Kinh Doanh</option>
                <option value="false">Ngừng Kinh Doanh</option>
            </form:select>
            <form:errors path="trangThai" cssClass="text-danger"/>
        </div>
        <button type="submit" class="btn btn-success" onclick="return confirm('Bạn có chắc muốn thêm sản phẩm này?')">
            <i class="fas fa-plus"></i> Thêm mới sản phẩm
        </button>
    </form:form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

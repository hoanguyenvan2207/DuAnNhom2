<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ page contentType="text/html; charset=UTF-8" language="java" %>--%>
<%--<!doctype html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<%--    <title>Danh sách sản phẩm và thêm mới hóa đơn</title>--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container mt-5">--%>
<%--    <div class="row">--%>
<%--        <!-- Phần danh sách sản phẩm -->--%>
<%--        <div class="col-md-8">--%>
<%--            <a href="/ban-hang/hien-thi"class="btn btn-primary">Quay lại danh sách</a>--%>
<%--            <h2 class="text-center mb-4">DANH SÁCH SẢN PHẨM</h2>--%>

<%--            <!-- Form tìm kiếm -->--%>
<%--            <form action="/ban-hang/add" class="mb-4" method="get">--%>
<%--                <div class="row g-3 align-items-center">--%>
<%--                    <div class="col-auto">--%>
<%--                        <label for="idSearch" class="col-form-label">Tìm kiếm sản phẩm</label>--%>
<%--                    </div>--%>
<%--                    <div class="col-auto">--%>
<%--                        <input type="number" name="idSearch" class="form-control" id="idSearch" placeholder="Nhập ID sản phẩm">--%>
<%--                    </div>--%>
<%--                    <div class="col-auto">--%>
<%--                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>

<%--            <!-- Bảng sản phẩm -->--%>
<%--            <div class="table-responsive" style="max-height: 300px; overflow:auto;">--%>
<%--                <table class="table table-bordered table-hover table-striped">--%>
<%--                    <thead class="table-dark">--%>
<%--                    <tr>--%>
<%--                        <th>ID</th>--%>
<%--                        <th>Tên SP</th>--%>
<%--                        <th>Màu sắc</th>--%>
<%--                        <th>Kích thước</th>--%>
<%--                        <th>Số lượng</th>--%>
<%--                        <th>Đơn giá</th>--%>
<%--                        <th>Hoạt động</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <c:forEach var="sanPhamChiTiet" items="${sanPhamChiTiets}">--%>
<%--                        <tr>--%>
<%--                            <td>${sanPhamChiTiet.id}</td>--%>
<%--                            <td>${sanPhamChiTiet.sanPham.ten}</td>--%>
<%--                            <td>${sanPhamChiTiet.mauSac.ten}</td>--%>
<%--                            <td>${sanPhamChiTiet.kichThuoc.ten}</td>--%>
<%--                            <td>${sanPhamChiTiet.soLuong}</td>--%>
<%--                            <td>${sanPhamChiTiet.donGia}</td>--%>
<%--                            <td>--%>
<%--                                <form action="/ban-hang/add-item" method="post">--%>
<%--                                    <input type="hidden" name="sanPhamChiTietId" value="${sanPhamChiTiet.id}">--%>
<%--                                    <div class="input-group">--%>
<%--                                        <input style="width: 60px;" type="number" name="soLuong" class="form-control" placeholder="Số lượng" min="1" max="${sanPhamChiTiet.soLuong}" required>--%>
<%--                                        <button type="submit" class="btn btn-info btn-sm ms-2">Thêm vào giỏ</button>--%>
<%--                                    </div>--%>
<%--                                </form>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>

<%--            <h3 class="mt-3 text-center">Giỏ hàng</h3>--%>
<%--            <div class="table-responsive" style="max-height: 220px; overflow:auto;">--%>
<%--                <table class="table table-bordered table-hover">--%>
<%--                    <thead class="table-secondary">--%>
<%--                    <tr>--%>
<%--                        <th>ID</th>--%>
<%--                        <th>Tên SP</th>--%>
<%--                        <th>MS</th>--%>
<%--                        <th>KT</th>--%>
<%--                        <th>SL</th>--%>
<%--                        <th>Giá</th>--%>
<%--                        <th>Tổng cộng</th>--%>
<%--                        <th>Thao tác</th>--%>
<%--                    </tr>--%>
<%--                    </thead>--%>
<%--                    <tbody>--%>
<%--                    <c:forEach var="cartItem" items="${cartItems}">--%>
<%--                        <tr>--%>
<%--                            <td>${cartItem.sanPhamChiTietId}</td>--%>
<%--                            <td>${cartItem.tenSanPham}</td>--%>
<%--                            <td>${cartItem.mauSac}</td>--%>
<%--                            <td>${cartItem.kichThuoc}</td>--%>
<%--                            <td>${cartItem.soLuong}</td>--%>
<%--                            <td>${cartItem.donGia}</td>--%>
<%--                            <td>${cartItem.soLuong * cartItem.donGia}</td>--%>
<%--                            <td>--%>
<%--                                <form action="/ban-hang/update-item" method="post">--%>
<%--                                    <div class="input-group">--%>
<%--                                        <input type="hidden" name="sanPhamChiTietId" value="${cartItem.sanPhamChiTietId}">--%>
<%--                                        <input style="width: 50px;" type="number" name="soLuongGio" class="form-control mx-1" placeholder="Số lượng" min="1" required>--%>
<%--                                        <button type="submit" class="btn btn-warning btn-sm mx-1">Sửa SL</button>--%>
<%--                                        <a href="/ban-hang/remove?id=${cartItem.sanPhamChiTietId}" class="btn btn-danger btn-sm mx-1">Xóa</a>--%>
<%--                                    </div>--%>
<%--                                </form>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                    </tbody>--%>
<%--                </table>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <!-- Phần thêm mới hóa đơn -->--%>
<%--        <div class="col-md-4 p-2 border border-3 rounded">--%>
<%--            <h2 class="text-center mb-5">THÊM MỚI HÓA ĐƠN</h2>--%>
<%--            <form action="/ban-hang/add" method="post">--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="khachHangId" class="form-label">Khách hàng</label>--%>
<%--                    <select name="khachHang.id" class="form-select" id="khachHangId">--%>
<%--                        <option selected disabled hidden>Chọn khách hàng</option>--%>
<%--                        <c:forEach var="khachHang" items="${khachHangs}">--%>
<%--                            <option value="${khachHang.id}">${khachHang.ten}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                        <span class="text-danger">${errorkhachHang}</span>--%>
<%--                </div>--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="idSanPham" class="form-label">Sản phẩm</label> <br>--%>
<%--                    <input type="text" class="form-control" id="idSanPham" value="${idSanPhamChiTiet}" placeholder="Chưa có sản phẩm nào!" readonly>--%>
<%--                    <span class="text-danger">${error}</span>--%>
<%--                </div>--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="soLuong" class="form-label">Số lượng</label>--%>
<%--                    <input type="text" name="soLuong" class="form-control" id="soLuong" value="${soLuongForm}" placeholder="Tổng số lượng: 0" required>--%>
<%--                </div>--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="ngayMua" class="form-label">Ngày mua</label>--%>
<%--                    <input type="text" class="form-control" id="ngayMua" name="hoaDon.ngayMuaHang" value="${ngayHienTai}" readonly>--%>
<%--                </div>--%>

<%--                <div class="mb-3">--%>
<%--                    <label for="nhanVienId" class="form-label">Nhân viên</label>--%>
<%--                    <select name="nhanVien.id" class="form-select" id="nhanVienId">--%>
<%--                        <option selected disabled hidden>Chọn nhân viên</option>--%>
<%--                        <c:forEach var="nhanVien" items="${nhanViens}">--%>
<%--                            <option value="${nhanVien.id}">${nhanVien.ten}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                        <span class="text-danger">${errornhanVien}</span>--%>
<%--                </div>--%>

<%--                <div class="text-center mt-5 p-3">--%>
<%--                    <button style="width: 150px" type="submit" name="thanh_toan" value="false" class="btn btn-secondary mx-3" onclick="return confirm('Lưu trữ hóa đơn này?')">Lưu trữ</button>--%>
<%--                    <button style="width: 150px" type="submit" name="thanh_toan" value="true" class="btn btn-primary mx-3" onclick="return confirm('Thanh toán hóa đơn này')">Thanh Toán</button>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--</div>--%>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách sản phẩm và thêm mới hóa đơn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <!-- Phần danh sách sản phẩm -->
        <div class="col-md-8">
            <a href="/ban-hang/hien-thi" class="btn btn-primary"><i class="fas fa-arrow-left"></i> Quay lại danh sách</a>
            <h2 class="text-center mb-4"><i class="fas fa-list"></i> DANH SÁCH SẢN PHẨM</h2>

            <!-- Form tìm kiếm -->
            <form action="/ban-hang/add" class="mb-4" method="get">
                <div class="row g-3 align-items-center">
                    <div class="col-auto">
                        <label for="idSearch" class="col-form-label">Tìm kiếm sản phẩm</label>
                    </div>
                    <div class="col-auto">
                        <input type="number" name="idSearch" class="form-control" id="idSearch" placeholder="Nhập ID sản phẩm">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i> Tìm kiếm</button>
                    </div>
                </div>
            </form>

            <!-- Bảng sản phẩm -->
            <div class="table-responsive" style="max-height: 300px; overflow:auto;">
                <table class="table table-bordered table-hover table-striped">
                    <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên SP</th>
                        <th>Màu sắc</th>
                        <th>Kích thước</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                        <th>Hoạt động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="sanPhamChiTiet" items="${sanPhamChiTiets}">
                        <tr>
                            <td>${sanPhamChiTiet.id}</td>
                            <td>${sanPhamChiTiet.sanPham.ten}</td>
                            <td>${sanPhamChiTiet.mauSac.ten}</td>
                            <td>${sanPhamChiTiet.kichThuoc.ten}</td>
                            <td>${sanPhamChiTiet.soLuong}</td>
                            <td>${sanPhamChiTiet.donGia}</td>
                            <td>
                                <form action="/ban-hang/add-item" method="post">
                                    <input type="hidden" name="sanPhamChiTietId" value="${sanPhamChiTiet.id}">
                                    <div class="input-group">
                                        <input style="width: 60px;" type="number" name="soLuong" class="form-control" placeholder="Số lượng" min="1" max="${sanPhamChiTiet.soLuong}" required>
                                        <button type="submit" class="btn btn-info btn-sm ms-2"><i class="fas fa-plus"></i> Thêm vào giỏ</button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <h3 class="mt-3 text-center"><i class="fas fa-shopping-cart"></i> Giỏ hàng</h3>
            <div class="table-responsive" style="max-height: 220px; overflow:auto;">
                <table class="table table-bordered table-hover">
                    <thead class="table-secondary">
                    <tr>
                        <th>ID</th>
                        <th>Tên SP</th>
                        <th>MS</th>
                        <th>KT</th>
                        <th>SL</th>
                        <th>Giá</th>
                        <th>Tổng cộng</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="cartItem" items="${cartItems}">
                        <tr>
                            <td>${cartItem.sanPhamChiTietId}</td>
                            <td>${cartItem.tenSanPham}</td>
                            <td>${cartItem.mauSac}</td>
                            <td>${cartItem.kichThuoc}</td>
                            <td>${cartItem.soLuong}</td>
                            <td>${cartItem.donGia}</td>
                            <td>${cartItem.soLuong * cartItem.donGia}</td>
                            <td>
                                <form action="/ban-hang/update-item" method="post">
                                    <div class="input-group">
                                        <input type="hidden" name="sanPhamChiTietId" value="${cartItem.sanPhamChiTietId}">
                                        <input style="width: 50px;" type="number" name="soLuongGio" class="form-control mx-1" placeholder="Số lượng" min="1" required>
                                        <button type="submit" class="btn btn-warning btn-sm mx-1"><i class="fas fa-edit"></i> Sửa SL</button>
                                        <a href="/ban-hang/remove?id=${cartItem.sanPhamChiTietId}" class="btn btn-danger btn-sm mx-1"><i class="fas fa-trash-alt"></i> Xóa</a>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Phần thêm mới hóa đơn -->
        <div class="col-md-4 p-2 border border-3 rounded">
            <h2 class="text-center mb-5"><i class="fas fa-file-invoice"></i> THÊM MỚI HÓA ĐƠN</h2>
            <form action="/ban-hang/add" method="post">

                <div class="mb-3">
                    <label for="khachHangId" class="form-label">Khách hàng</label>
                    <select name="khachHang.id" class="form-select" id="khachHangId">
                        <option selected disabled hidden>Chọn khách hàng</option>
                        <c:forEach var="khachHang" items="${khachHangs}">
                            <option value="${khachHang.id}">${khachHang.ten}</option>
                        </c:forEach>
                    </select>
                    <span class="text-danger">${errorkhachHang}</span>
                </div>

                <div class="mb-3">
                    <label for="idSanPham" class="form-label">Sản phẩm</label> <br>
                    <input type="text" class="form-control" id="idSanPham" value="${idSanPhamChiTiet}" placeholder="Chưa có sản phẩm nào!" readonly>
                    <span class="text-danger">${error}</span>
                </div>

                <div class="mb-3">
                    <label for="soLuong" class="form-label">Số lượng</label>
                    <input type="text" name="soLuong" class="form-control" id="soLuong" value="${soLuongForm}" placeholder="Tổng số lượng: 0" required>
                </div>

                <div class="mb-3">
                    <label for="ngayMua" class="form-label">Ngày mua</label>
                    <input type="text" class="form-control" id="ngayMua" name="hoaDon.ngayMuaHang" value="${ngayHienTai}" readonly>
                </div>

                <div class="mb-3">
                    <label for="nhanVienId" class="form-label">Nhân viên</label>
                    <select name="nhanVien.id" class="form-select" id="nhanVienId">
                        <option selected disabled hidden>Chọn nhân viên</option>
                        <c:forEach var="nhanVien" items="${nhanViens}">
                            <option value="${nhanVien.id}">${nhanVien.ten}</option>
                        </c:forEach>
                    </select>
                    <span class="text-danger">${errornhanVien}</span>
                </div>

                <div class="mb-3">
                    <label class="from-label">Tổng tiền</label>
                    <input type="text" class="form-control" value="${tongTien}" readonly placeholder="">
                </div>

                <div class="text-center mt-3 p-3">
                    <button style="width: 150px" type="submit" name="thanh_toan" value="false" class="btn btn-primary"><i class="fas fa-save"></i> Lưu hóa đơn</button>
                    <button style="width: 150px" type="submit" name="thanh_toan" value="true" class="btn btn-success" onclick="return confirm('Xác nhận thanh toán?')"><i class="fas fa-credit-card"></i> Thanh toán</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>

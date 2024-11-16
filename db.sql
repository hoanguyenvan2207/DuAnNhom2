CREATE DATABASE ASM_Java5
USE ASM_Java5

CREATE TABLE san_pham (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma NVARCHAR(50),
    ten NVARCHAR(255),
    trang_thai BIT
);

CREATE TABLE mau_sac (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma NVARCHAR(50),
    ten NVARCHAR(255),
    trang_thai BIT
);

CREATE TABLE kich_thuoc (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma NVARCHAR(50),
    ten NVARCHAR(255),
    trang_thai BIT
);

CREATE TABLE san_pham_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    maspct NVARCHAR(50),
    id_kich_thuoc INT,
    id_mau_sac INT,
    id_san_pham INT,
    so_luong INT,
    don_gia DECIMAL(18, 2),
    trang_thai BIT,
    FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(id),
    FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id),
    FOREIGN KEY (id_san_pham) REFERENCES san_pham(id)
);

CREATE TABLE khach_hang (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    sdt NVARCHAR(15),
    ma_kh NVARCHAR(50),
    trang_thai BIT
);

CREATE TABLE nhan_vien (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    ma_nv NVARCHAR(50),
    ten_dang_nhap NVARCHAR(50),
    mat_khau NVARCHAR(255),
    trang_thai BIT
);

CREATE TABLE hoa_don (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_nv INT,
    id_kh INT NOT NULL,
    ngay_mua_hang DATETIME,
    trang_thai BIT,
    FOREIGN KEY (id_nv) REFERENCES nhan_vien(id),
    FOREIGN KEY (id_kh) REFERENCES khach_hang(id)
);

CREATE TABLE hoa_don_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_hd INT,
    id_spct INT,
    so_luong INT NOT NULL,
    don_gia DECIMAL(18, 2),
    trang_thai BIT,
    FOREIGN KEY (id_hd) REFERENCES hoa_don(id),
    FOREIGN KEY (id_spct) REFERENCES san_pham_chi_tiet(id)
);

INSERT INTO san_pham (ma, ten, trang_thai) VALUES 
('SP001', N'Sản phẩm A', 1),
('SP002', N'Sản phẩm B', 1),
('SP003', N'Sản phẩm C', 1),
('SP004', N'Sản phẩm D', 1),
('SP005', N'Sản phẩm E', 0),
('SP006', N'Sản phẩm F', 1),
('SP007', N'Sản phẩm G', 1);

INSERT INTO mau_sac (ma, ten, trang_thai) VALUES 
('MS001', N'Đỏ', 1),
('MS002', N'Xanh', 1),
('MS003', N'Vàng', 1),
('MS004', N'Trắng', 1),
('MS005', N'Đen', 1),
('MS006', N'Tím', 0),
('MS007', N'Hồng', 1);

INSERT INTO kich_thuoc (ma, ten, trang_thai) VALUES 
('KT001', N'Nhỏ', 1),
('KT002', N'Vừa', 1),
('KT003', N'Lớn', 1),
('KT004', N'Rộng', 0),
('KT005', N'Cao', 1),
('KT006', N'Thấp', 1),
('KT007', N'Dài', 1);

INSERT INTO san_pham_chi_tiet (maspct, id_kich_thuoc, id_mau_sac, id_san_pham, so_luong, don_gia, trang_thai) VALUES 
('SPCT001', 1, 1, 1, 50, 100000, 1),
('SPCT002', 2, 2, 2, 30, 200000, 1),
('SPCT003', 3, 3, 3, 40, 150000, 1),
('SPCT004', 4, 4, 4, 20, 300000, 1),
('SPCT005', 5, 5, 5, 60, 250000, 0),
('SPCT006', 6, 6, 6, 70, 180000, 1),
('SPCT007', 7, 7, 7, 80, 220000, 1);

INSERT INTO khach_hang (ten, sdt, ma_kh, trang_thai) VALUES 
('Nguyen Van A', '0901123456', 'KH001', 1),
('Tran Thi B', '0901234567', 'KH002', 1),
('Le Van C', '0901345678', 'KH003', 1),
('Pham Thi D', '0901456789', 'KH004', 0),
('Nguyen Thi E', '0901567890', 'KH005', 1),
('Bui Van F', '0901678901', 'KH006', 1),
('Do Thi G', '0901789012', 'KH007', 1);

INSERT INTO nhan_vien (ten, ma_nv, ten_dang_nhap, mat_khau, trang_thai) VALUES 
('Nguyen Van H', 'NV001', 'nguyenvh', 'password1', 1),
('Le Thi I', 'NV002', 'lethi', 'password2', 1),
('Tran Van J', 'NV003', 'tranvj', 'password3', 1),
('Pham Thi K', 'NV004', 'phamtk', 'password4', 1),
('Nguyen Van L', 'NV005', 'nguyenl', 'password5', 0),
('Do Thi M', 'NV006', 'dotm', 'password6', 1),
('Bui Van N', 'NV007', 'buivn', 'password7', 1);

INSERT INTO hoa_don (id_nv, id_kh, ngay_mua_hang, trang_thai) VALUES 
(1, 1, '2024-01-01', 1),
(2, 2, '2024-02-01', 1),
(3, 3, '2024-03-01', 1),
(4, 4, '2024-04-01', 1),
(5, 5, '2024-05-01', 0),
(6, 6, '2024-06-01', 1),
(7, 7, '2024-07-01', 1);

INSERT INTO hoa_don_chi_tiet (id_hd, id_spct, so_luong, don_gia, trang_thai) VALUES 
(1, 1, 10, 100000, 1),
(2, 2, 5, 200000, 1),
(3, 3, 8, 150000, 1),
(4, 4, 7, 300000, 1),
(5, 5, 12, 250000, 0),
(6, 6, 9, 180000, 1),
(7, 7, 15, 220000, 1);


SELECT * FROM mau_sac

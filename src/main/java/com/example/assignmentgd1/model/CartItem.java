package com.example.assignmentgd1.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer sanPhamChiTietId;
    private String tenSanPham;
    private String mauSac;
    private String kichThuoc;
    private Integer soLuong;
    private Double donGia;
}

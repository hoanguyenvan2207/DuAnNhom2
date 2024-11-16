package com.example.assignmentgd1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham_chi_tiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String maspct;
    @ManyToOne
    @JoinColumn(name = "id_kich_thuoc")
    private KichThuoc kichThuoc;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    private Integer soLuong;

    private Double donGia;
    private Boolean trangThai;
}

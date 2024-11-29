package com.example.assignmentgd1.model;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.NotNull;
=======
>>>>>>> 544b846 (add unit test mauSac)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_nv")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "id_kh")
    private KhachHang khachHang;

    private LocalDate ngayMuaHang;

    private Boolean trangThai;

<<<<<<< HEAD
    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> dsHdct;

=======
    public HoaDon(Integer id, NhanVien nhanVien, KhachHang khachHang, LocalDate ngayMuaHang, Boolean trangThai) {
        this.id = id;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayMuaHang = ngayMuaHang;
        this.trangThai = trangThai;
    }

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> dsHdct;


>>>>>>> 544b846 (add unit test mauSac)
}

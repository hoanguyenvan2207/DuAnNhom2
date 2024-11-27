package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.NhanVien;
import com.example.assignmentgd1.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {
    @Autowired
    private NhanVienRepository nhanVienRepository;

    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.findAll();
    }

    public NhanVien getNhanVienById(Integer id) {
        Optional<NhanVien> optionalKhachHang = nhanVienRepository.findById(id);
        if (optionalKhachHang.isPresent()) {
            return optionalKhachHang.get();
        }
        return null;
    }

    public List<NhanVien> findNhanviensByMaNv(String ma) {
        if (ma.length()<5 || ma.length()>50)  throw new ArithmeticException("nhap ma > 5 va <50 ky tu");
        if (!ma.matches("^[a-zA-Z0-9]*$")) {
            throw new ArithmeticException("ma khong duoc chua ky tu dac biet");
        }
        return nhanVienRepository.findNhanViensByMaNv(ma);
    }

    public NhanVien saveNhanVien(NhanVien nhanVien) {
        if (nhanVien.getId() == null || nhanVien.getId() <= 0 || nhanVien.getId() >900) {
            throw new IllegalArgumentException("ID không hợp lệ.");
        }
        if (nhanVien == null) {
            throw new IllegalArgumentException("Thông tin nhân viên không được null.");
        }

        if (nhanVien.getMaNv() == null || nhanVien.getMaNv().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được rỗng.");
        }

        if (nhanVien.getTen() == null || nhanVien.getTen().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được rỗng.");
        }

        if (nhanVien.getTenDangNhap() == null || nhanVien.getTenDangNhap().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được rỗng.");
        }

        if (nhanVien.getMatKhau() == null || nhanVien.getMatKhau().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được rỗng.");
        }

        if (nhanVien.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái nhân viên không được null.");
        }

        if (!nhanVien.getTen().matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Tên không được chứa ký tự đặc biệt.");
        }

        return nhanVienRepository.save(nhanVien);
    }


    public NhanVien updateNhanVien(Integer id, NhanVien updatedNhanVien) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ.");
        }
        if (!updatedNhanVien.getTen().matches("^[a-zA-Z0-9]*$")) {
            throw new ArithmeticException("ten khong duoc chua ky tu dac biet");
        }
        if (updatedNhanVien == null) {
            throw new IllegalArgumentException("Thông tin nhân viên không được null.");
        }

        if (updatedNhanVien.getMaNv() == null || updatedNhanVien.getMaNv().isEmpty()) {
            throw new IllegalArgumentException("Mã nhân viên không được rỗng.");
        }

        if (updatedNhanVien.getTen() == null || updatedNhanVien.getTen().isEmpty()) {
            throw new IllegalArgumentException("Tên nhân viên không được rỗng.");
        }

        if (updatedNhanVien.getTenDangNhap() == null || updatedNhanVien.getTenDangNhap().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được rỗng.");
        }

        if (updatedNhanVien.getMatKhau() == null || updatedNhanVien.getMatKhau().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được rỗng.");
        }

        if (updatedNhanVien.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái nhân viên không được null.");
        }

        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(id);
        if (optionalNhanVien.isPresent()) {
            NhanVien existingNhanVien = optionalNhanVien.get();
            existingNhanVien.setMaNv(updatedNhanVien.getMaNv());
            existingNhanVien.setTen(updatedNhanVien.getTen());
            existingNhanVien.setTenDangNhap(updatedNhanVien.getTenDangNhap());
            existingNhanVien.setMatKhau(updatedNhanVien.getMatKhau());
            existingNhanVien.setTrangThai(updatedNhanVien.getTrangThai());
            return nhanVienRepository.save(existingNhanVien);
        } else {
            throw new IllegalArgumentException("Nhân viên không tồn tại với ID: " + id);
        }
    }

    public boolean deleteNhanVien(Integer id) {
        if (nhanVienRepository.existsById(id)) {
            nhanVienRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

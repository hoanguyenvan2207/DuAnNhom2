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
        return nhanVienRepository.findNhanViensByMaNv(ma);
    }

    public NhanVien saveNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    public NhanVien updateNhanVien(Integer id, NhanVien updatedNhanVien) {
        Optional<NhanVien> optionalNhanVien = nhanVienRepository.findById(id);
        if (optionalNhanVien.isPresent()) {
            NhanVien existingNhanVien = optionalNhanVien.get();
            existingNhanVien.setTen(updatedNhanVien.getTen());
            existingNhanVien.setMaNv(updatedNhanVien.getMaNv());
            existingNhanVien.setTenDangNhap(updatedNhanVien.getTenDangNhap());
            existingNhanVien.setMatKhau(updatedNhanVien.getMatKhau());
            existingNhanVien.setTrangThai(updatedNhanVien.getTrangThai());
            return nhanVienRepository.save(existingNhanVien);
        }
        return null;
    }

    public boolean deleteNhanVien(Integer id) {
        if (nhanVienRepository.existsById(id)) {
            nhanVienRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

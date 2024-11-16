package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.KhachHang;
import com.example.assignmentgd1.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    public KhachHang getKhachHangById(Integer id) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(id);
        if (optionalKhachHang.isPresent()) {
            return optionalKhachHang.get();
        }
        return null;
    }

    public List<KhachHang> getKhachHangsById(Integer id) {
        return khachHangRepository.findAllById(id);
    }

    public KhachHang saveKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    public KhachHang updateKhachHang(Integer id, KhachHang updatedKhachHang) {
        Optional<KhachHang> optionalKhachHang = khachHangRepository.findById(id);
        if (optionalKhachHang.isPresent()) {
            KhachHang existingKhachHang = optionalKhachHang.get();
            existingKhachHang.setTen(updatedKhachHang.getTen());
            existingKhachHang.setSdt(updatedKhachHang.getSdt());
            existingKhachHang.setMaKh(updatedKhachHang.getMaKh());
            existingKhachHang.setTrangThai(updatedKhachHang.getTrangThai());
            return khachHangRepository.save(existingKhachHang);
        }
        return null;
    }

    public boolean deleteKhachHang(Integer id) {
        if (khachHangRepository.existsById(id)) {
            khachHangRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

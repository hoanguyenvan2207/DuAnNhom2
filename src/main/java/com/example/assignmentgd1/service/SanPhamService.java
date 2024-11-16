package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.SanPham;
import com.example.assignmentgd1.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> getAllSanPham() {
        return sanPhamRepository.findAll();
    }

    public SanPham getSanPhamById(Integer id) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            return optionalSanPham.get();
        }
        return null;
    }
    public List<SanPham> searchSanPham(String keyword) {
        return sanPhamRepository.findByMaContainingIgnoreCaseOrTenContainingIgnoreCase(keyword, keyword);
    }

    public SanPham createSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    public SanPham updateSanPham(Integer id, SanPham updatedSanPham) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            SanPham existingSanPham = optionalSanPham.get();
            existingSanPham.setMa(updatedSanPham.getMa());
            existingSanPham.setTen(updatedSanPham.getTen());
            existingSanPham.setTrangThai(updatedSanPham.getTrangThai());
            return sanPhamRepository.save(existingSanPham);
        }
        return null;
    }

    public void deleteSanPham(Integer id) {
        sanPhamRepository.deleteById(id);
    }
}

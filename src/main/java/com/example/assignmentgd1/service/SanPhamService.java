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
        List<SanPham> sanPhams = sanPhamRepository.findAll();
        if (sanPhams == null || sanPhams.isEmpty()) {
            throw new IllegalArgumentException("Danh sách sản phẩm rỗng hoặc không tồn tại.");
        }
        return sanPhams;
    }

    public SanPham getSanPhamById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ.");
        }
        return sanPhamRepository.findById(id).orElse(null);
    }

    public List<SanPham> searchSanPham(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword không được null hoặc rỗng.");
        }
        return sanPhamRepository.findByMaContainingIgnoreCaseOrTenContainingIgnoreCase(keyword, keyword);
    }

    public class DuplicateProductException extends RuntimeException {
        public DuplicateProductException(String message) {
            super(message);
        }
    }


    public SanPham createSanPham(SanPham sanPham) {
        if (sanPham.getMa() == null || sanPham.getMa().isEmpty()) {
            throw new IllegalArgumentException("Mã sản phẩm không được rỗng.");
        }
        if (sanPham.getTen() == null || sanPham.getTen().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được rỗng.");
        }

        if (sanPham.getTen().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được rỗng được chỉ chứa khoảng trắng.");
        }

        if (sanPham.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái sản phẩm không được null.");
        }

        if (sanPhamRepository.existsByMa(sanPham.getMa())) {
            throw new DuplicateProductException("Mã sản phẩm đã tồn tại.");
        }

        if (sanPhamRepository.existsByTen(sanPham.getTen())) {
            throw new DuplicateProductException("Tên sản phẩm đã tồn tại.");
        }

        return sanPhamRepository.save(sanPham);
    }


    public SanPham updateSanPham(Integer id, SanPham updatedSanPham) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID không hợp lệ.");
        }

        if (updatedSanPham == null) {
            throw new IllegalArgumentException("Thông tin sản phẩm không được null.");
        }

        if (updatedSanPham.getMa() == null || updatedSanPham.getMa().isEmpty()) {
            throw new IllegalArgumentException("Mã sản phẩm không được rỗng.");
        }

        if (updatedSanPham.getTen() == null || updatedSanPham.getTen().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được rỗng.");
        }


        if (updatedSanPham.getTen().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được chứa chỉ khoảng trắng.");
        }

        if (updatedSanPham.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái sản phẩm không được null.");
        }

        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            SanPham existingSanPham = optionalSanPham.get();
            existingSanPham.setMa(updatedSanPham.getMa());
            existingSanPham.setTen(updatedSanPham.getTen());
            existingSanPham.setTrangThai(updatedSanPham.getTrangThai());
            return sanPhamRepository.save(existingSanPham);
        } else {
            throw new IllegalArgumentException("Sản phẩm không tồn tại với ID: " + id);
        }
    }


    public void deleteSanPham(Integer id) {
        sanPhamRepository.deleteById(id);
    }
}

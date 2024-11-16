package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {
    List<SanPham> findByMaContainingIgnoreCaseOrTenContainingIgnoreCase(String ma, String ten);
}

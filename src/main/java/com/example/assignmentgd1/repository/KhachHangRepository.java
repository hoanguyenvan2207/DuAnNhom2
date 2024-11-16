package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Integer> {
    List<KhachHang> findAllById(Integer id);
}

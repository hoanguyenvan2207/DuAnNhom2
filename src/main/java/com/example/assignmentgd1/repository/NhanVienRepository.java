package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    List<NhanVien> findNhanViensByMaNv(String maNv);

}

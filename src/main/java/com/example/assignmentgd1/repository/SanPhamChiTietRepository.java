package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamChiTietRepository extends JpaRepository<SanPhamChiTiet, Integer> {
    List<SanPhamChiTiet> getSanPhamChiTietsById(Integer id);

    List<SanPhamChiTiet> findSanPhamChiTietByMaspctContainsIgnoreCase(String maspct);

}

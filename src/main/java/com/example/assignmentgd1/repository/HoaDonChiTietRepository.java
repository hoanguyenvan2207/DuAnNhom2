package com.example.assignmentgd1.repository;

import com.example.assignmentgd1.model.HoaDonChiTiet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Integer> {
    List<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId);

    @Modifying
    @Transactional
    @Query("DELETE FROM HoaDonChiTiet h WHERE h.sanPhamChiTiet.id = :sanPhamChiTietId")
    void deleteBySanPhamChiTietId(@Param("sanPhamChiTietId") Integer sanPhamChiTietId);

    List<HoaDonChiTiet> findAllBySanPhamChiTietIdAndHoaDonId(Integer sanPhamChiTietId, Integer hoaDonId);
    List<HoaDonChiTiet> findBySanPhamChiTietIdAndHoaDonId(Integer sanPhamChiTietId, Integer hoaDonId);

    List<HoaDonChiTiet> findHoaDonChiTietsById(Integer id);
}


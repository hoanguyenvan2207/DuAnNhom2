package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.HoaDonChiTiet;
import com.example.assignmentgd1.repository.HoaDonChiTietRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonChiTietService {
    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        return hoaDonChiTietRepository.findAll();
    }

    public HoaDonChiTiet getHoaDonChiTietById(Integer id) {
        Optional<HoaDonChiTiet> optionalHoaDonChiTiet = hoaDonChiTietRepository.findById(id);
        return optionalHoaDonChiTiet.orElse(null);
    }

    public List<HoaDonChiTiet> findHoaDonChiTietsById(Integer id) {
        return hoaDonChiTietRepository.findHoaDonChiTietsById(id);
    }

    public HoaDonChiTiet saveHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    public List<HoaDonChiTiet> getHoaDonChiTietByHoaDonId(Integer hoaDonId) {
        return hoaDonChiTietRepository.findByHoaDonId(hoaDonId);
    }

    public List<HoaDonChiTiet> findBySanPhamChiTietIdAndHoaDonId(Integer sanPhamChiTietId, Integer hoaDonId) {
        return hoaDonChiTietRepository.findAllBySanPhamChiTietIdAndHoaDonId(sanPhamChiTietId, hoaDonId);
    }

    @Transactional
    public void deleteBySanPhamChiTietId(Integer sanPhamChiTietId) {
        hoaDonChiTietRepository.deleteBySanPhamChiTietId(sanPhamChiTietId);
    }
    public List<HoaDonChiTiet> findAllBySanPhamChiTietIdAndHoaDonId(Integer sanPhamChiTietId, Integer hoaDonId) {
        return hoaDonChiTietRepository.findAllBySanPhamChiTietIdAndHoaDonId(sanPhamChiTietId, hoaDonId);
    }

    public HoaDonChiTiet findSingleBySanPhamChiTietIdAndHoaDonId(Integer sanPhamChiTietId, Integer hoaDonId) {
        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepository.findBySanPhamChiTietIdAndHoaDonId(sanPhamChiTietId, hoaDonId);
        return hoaDonChiTietList.isEmpty() ? null : hoaDonChiTietList.get(0);
    }

}

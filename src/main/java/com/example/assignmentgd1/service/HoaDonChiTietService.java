package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.HoaDonChiTiet;
import com.example.assignmentgd1.repository.HoaDonChiTietRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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

    public HoaDonChiTiet addHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {

        if(hoaDonChiTiet.getTrangThai() == null){
            throw new IllegalArgumentException("Trạng thái không được để trống");
        }
        if(hoaDonChiTiet.getHoaDon() == null){
            throw new IllegalArgumentException("Hoá đơn không được để trống");
        }
        if(hoaDonChiTiet.getSanPhamChiTiet() == null){
            throw new IllegalArgumentException("Sản phẩm chi tiết không được để trống");
        }

        if (hoaDonChiTiet.getSoLuong() == null) {
            throw new IllegalArgumentException("Số lượng không được để trống");
        }

        if (!isValidNumber(hoaDonChiTiet.getSoLuong().toString())) {
            throw new IllegalArgumentException("Số lượng phải là một số hợp lệ");
        }
        if (hoaDonChiTiet.getSoLuong() < 0) {
            throw new IllegalArgumentException("Số lượng không được âm.");
        }

        if (hoaDonChiTiet.getDonGia() == null) {
            throw new IllegalArgumentException("Đơn giá không được để trống");
        }

        if (!isValidNumber(hoaDonChiTiet.getDonGia().toString())) {
            throw new IllegalArgumentException("Đơn giá phải là một số hợp lệ");
        }
        if (hoaDonChiTiet.getDonGia() < 0) {
            throw new IllegalArgumentException("Đơn giá không được âm");
        }

        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    public HoaDonChiTiet updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {

        if(hoaDonChiTiet.getTrangThai() == null){
            throw new IllegalArgumentException("Trạng thái không được để trống");
        }
        if(hoaDonChiTiet.getHoaDon() == null){
            throw new IllegalArgumentException("Hoá đơn không được để trống");
        }
        if(hoaDonChiTiet.getSanPhamChiTiet() == null){
            throw new IllegalArgumentException("Sản phẩm chi tiết không được để trống");
        }

        if (hoaDonChiTiet.getSoLuong() == null) {
            throw new IllegalArgumentException("Số lượng không được để trống");
        }

        if (!isValidNumber(hoaDonChiTiet.getSoLuong().toString())) {
            throw new IllegalArgumentException("Số lượng phải là một số hợp lệ");
        }
        if (hoaDonChiTiet.getSoLuong() <= 0) {
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
        }

        if (hoaDonChiTiet.getDonGia() == null) {
            throw new IllegalArgumentException("Đơn giá không được để trống");
        }

        if (!isValidNumber(hoaDonChiTiet.getDonGia().toString())) {
            throw new IllegalArgumentException("Đơn giá phải là một số hợp lệ");
        }
        if (hoaDonChiTiet.getDonGia() < 0) {
            throw new IllegalArgumentException("Đơn giá không được âm");
        }

        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    private boolean isValidNumber(String value) {
        String regex = "^-?\\d*\\.?\\d+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
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

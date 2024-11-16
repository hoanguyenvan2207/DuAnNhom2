package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.HoaDon;
import com.example.assignmentgd1.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    public HoaDon getHoaDonById(Integer id) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(id);
        if (optionalHoaDon.isPresent()) {
            return optionalHoaDon.get();
        }
        return null;
    }
    public List<HoaDon> findHoaDonsById(Integer id) {
        return hoaDonRepository.findHoaDonsById(id);
    }
    public void updateHoaDon(Integer idHoaDon,HoaDon hoaDon) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(idHoaDon);
        if (optionalHoaDon.isPresent()) {
            HoaDon hoaDon1 = optionalHoaDon.get();
            hoaDon1.setKhachHang(hoaDon.getKhachHang());
            hoaDon1.setNhanVien(hoaDon.getNhanVien());
            hoaDon1.setNgayMuaHang(hoaDon.getNgayMuaHang());
            hoaDon1.setTrangThai(hoaDon.getTrangThai());
            hoaDonRepository.save(hoaDon1);
     }
    }

    public HoaDon saveHoaDon(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }
}

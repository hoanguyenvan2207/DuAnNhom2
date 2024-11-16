package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.KichThuoc;
import com.example.assignmentgd1.model.MauSac;
import com.example.assignmentgd1.model.SanPham;
import com.example.assignmentgd1.model.SanPhamChiTiet;
import com.example.assignmentgd1.repository.KichThuocRepository;
import com.example.assignmentgd1.repository.MauSacRepository;
import com.example.assignmentgd1.repository.SanPhamChiTietRepository;
import com.example.assignmentgd1.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamChiTietService {
    @Autowired
    private SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private KichThuocRepository kichThuocRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    public List<SanPhamChiTiet> getAllSanPhamChiTiet() {
        return sanPhamChiTietRepository.findAll();
    }

    public SanPhamChiTiet getSanPhamChiTietById(Integer id) {
        Optional<SanPhamChiTiet> optionalSanPhamChiTiet = sanPhamChiTietRepository.findById(id);
        return optionalSanPhamChiTiet.orElse(null);
    }
    public List<SanPhamChiTiet> getListSanPhamChiTietTimKiem(Integer id) {
        return sanPhamChiTietRepository.getSanPhamChiTietsById(id);
    }

    public List<SanPhamChiTiet> searchSanPhamChiTiet(String ma) {
        return sanPhamChiTietRepository.findSanPhamChiTietByMaspctContainsIgnoreCase(ma);
    }


    public SanPhamChiTiet saveSanPhamChiTiet(SanPhamChiTiet sanPhamChiTiet) {
        return sanPhamChiTietRepository.save(sanPhamChiTiet);
    }

    public SanPhamChiTiet updateSanPhamChiTiet(Integer id, SanPhamChiTiet updatedSanPhamChiTiet) {
        return sanPhamChiTietRepository.findById(id)
                .map(existingSanPhamChiTiet -> {
                    existingSanPhamChiTiet.setMaspct(updatedSanPhamChiTiet.getMaspct());
                    existingSanPhamChiTiet.setSoLuong(updatedSanPhamChiTiet.getSoLuong());
                    existingSanPhamChiTiet.setDonGia(updatedSanPhamChiTiet.getDonGia());
                    existingSanPhamChiTiet.setTrangThai(updatedSanPhamChiTiet.getTrangThai());

                    existingSanPhamChiTiet.setKichThuoc(kichThuocRepository.findById(updatedSanPhamChiTiet.getKichThuoc().getId()).orElse(null));
                    existingSanPhamChiTiet.setMauSac(mauSacRepository.findById(updatedSanPhamChiTiet.getMauSac().getId()).orElse(null));
                    existingSanPhamChiTiet.setSanPham(sanPhamRepository.findById(updatedSanPhamChiTiet.getSanPham().getId()).orElse(null));

                    return sanPhamChiTietRepository.save(existingSanPhamChiTiet);
                }).orElse(null);
    }


    public void deleteSanPhamChiTiet(Integer id) {
        sanPhamChiTietRepository.deleteById(id);
    }

}

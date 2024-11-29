package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.KichThuoc;
import com.example.assignmentgd1.repository.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KichThuocService {
    @Autowired
    private KichThuocRepository kichThuocRepository;

    public List<KichThuoc> getAllKichThuoc() {
        if (kichThuocRepository.findAll().isEmpty()) {
            throw new IllegalArgumentException("List rỗng");
        }
        return kichThuocRepository.findAll();
    }

    public KichThuoc getKichThuocById(Integer id) {
        Optional<KichThuoc> optionalKichThuoc = kichThuocRepository.findById(id);
        if (optionalKichThuoc.isPresent()) {
            return optionalKichThuoc.get();
        }
        return null;
    }

    public List<KichThuoc> findKichThuocsByMa(String ma) {
        if (ma==null){
            throw new IllegalArgumentException("Mã không được trống");
        }
        if (ma.isEmpty()){
            throw new IllegalArgumentException("Mã không được rỗng");
        }if (!ma.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Mã chỉ được chứa ký tự chữ và số");
        }
        return kichThuocRepository.findKichThuocsByMaContainsIgnoreCase(ma);
    }

    public KichThuoc saveKichThuoc(KichThuoc kichThuoc) {
        if (kichThuoc.getMa() == null && kichThuoc.getTen() == null && kichThuoc.getTrangThai() == null) {
            throw new IllegalArgumentException("Mã, tên, trạng thái không được để trống");
        }
        if (kichThuoc.getMa() == null || kichThuoc.getMa().isEmpty()) {
            throw new IllegalArgumentException("Mã kích thước không được để trống");
        }
        if (kichThuoc.getTen() == null || kichThuoc.getTen().isEmpty()) {
            throw new IllegalArgumentException("Tên kích thước không được để trống");
        }
        if (kichThuoc.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống");
        }
        if (kichThuoc.getMa().length() < 3) {
            throw new IllegalArgumentException("Mã phải lớn hơn 3 kí tự");
        }
        if (kichThuoc.getTen().length() > 10) {
            throw new IllegalArgumentException("Tên phải nhỏ hơn 10 kí tự");
        }
        for (KichThuoc kt : kichThuocRepository.findAll()) {
            if (kt.getMa().equals(kichThuoc.getMa())) {
                throw new IllegalArgumentException("Mã kích thước đã tồn tại");
            }
        }
        return kichThuocRepository.save(kichThuoc);
    }

    public KichThuoc updateKichThuoc(Integer id, KichThuoc updatedKichThuoc) {
        if (updatedKichThuoc.getMa() == null) {
            throw new IllegalArgumentException("Mã không được để trống");
        }
        if (updatedKichThuoc.getMa().length() < 3) {
            throw new IllegalArgumentException("Mã phải lớn hơn 3 kí tự");
        }
        if (updatedKichThuoc.getTen() == null) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        if (updatedKichThuoc.getTen().length() > 10) {
            throw new IllegalArgumentException("Tên phải nhỏ hơn 10 kí tự");
        }
        if (updatedKichThuoc.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái không được để trống");
        }
        Optional<KichThuoc> optionalKichThuoc = kichThuocRepository.findById(id);
        if (optionalKichThuoc.isPresent()) {
            KichThuoc existingKichThuoc = optionalKichThuoc.get();
            existingKichThuoc.setMa(updatedKichThuoc.getMa());
            existingKichThuoc.setTen(updatedKichThuoc.getTen());
            existingKichThuoc.setTrangThai(updatedKichThuoc.getTrangThai());
            return kichThuocRepository.save(existingKichThuoc);
        }
        return null;
    }

    public boolean deleteKichThuoc(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID không tồn tại");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID phải lớn hơn 0");
        }
        if (kichThuocRepository.existsById(id)) {
            kichThuocRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

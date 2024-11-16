package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.KichThuoc;
import com.example.assignmentgd1.repository.KichThuocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KichThuocService {
    @Autowired
    private KichThuocRepository kichThuocRepository;

    public List<KichThuoc> getAllKichThuoc() {
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
        return kichThuocRepository.findKichThuocsByMaContainsIgnoreCase(ma);
    }

    public KichThuoc saveKichThuoc(KichThuoc kichThuoc) {
        return kichThuocRepository.save(kichThuoc);
    }

    public KichThuoc updateKichThuoc(Integer id, KichThuoc updatedKichThuoc) {
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
        if (kichThuocRepository.existsById(id)) {
            kichThuocRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

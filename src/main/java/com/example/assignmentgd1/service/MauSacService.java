package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.MauSac;
import com.example.assignmentgd1.repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacService {
    @Autowired
    private MauSacRepository mauSacRepository;

    public List<MauSac> getAllMauSac() {
        return mauSacRepository.findAll();
    }

    public MauSac getMauSacById(Integer id) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(id);
        if (optionalMauSac.isPresent()) {
            return optionalMauSac.get();
        }
        return null;
    }

    public MauSac createMauSac(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    public List<MauSac> getMauSacsByMa(String ma) {
        return mauSacRepository.findMauSacsByMaContainsIgnoreCase(ma);
    }

    public MauSac updateMauSac(Integer id, MauSac updatedMauSac) {
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(id);
        if (optionalMauSac.isPresent()) {
            MauSac existingMauSac = optionalMauSac.get();
            existingMauSac.setMa(updatedMauSac.getMa());
            existingMauSac.setTen(updatedMauSac.getTen());
            existingMauSac.setTrangThai(updatedMauSac.getTrangThai());
            return mauSacRepository.save(existingMauSac);
        }
        return null;
    }

    public void deleteMauSac(Integer id) {
        mauSacRepository.deleteById(id);
    }
}

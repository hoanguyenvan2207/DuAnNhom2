package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.MauSac;
import com.example.assignmentgd1.repository.MauSacRepository;
<<<<<<< HEAD
=======
import jakarta.persistence.EntityNotFoundException;
>>>>>>> 544b846 (add unit test mauSac)
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
<<<<<<< HEAD
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(id);
        if (optionalMauSac.isPresent()) {
            return optionalMauSac.get();
        }
        return null;
    }

=======
        if(id== null || id <=0){
            throw new IllegalArgumentException("ID không hợp lệ.");
        }


        Optional<MauSac> optionalMauSac = mauSacRepository.findById(id);
        return optionalMauSac.orElse(null);
    }

    public List<MauSac> findMSById(Integer id) {
        return mauSacRepository.findMauSacsByID(id);
    }



>>>>>>> 544b846 (add unit test mauSac)
    public MauSac createMauSac(MauSac mauSac) {
        return mauSacRepository.save(mauSac);
    }

    public List<MauSac> getMauSacsByMa(String ma) {
        return mauSacRepository.findMauSacsByMaContainsIgnoreCase(ma);
    }

    public MauSac updateMauSac(Integer id, MauSac updatedMauSac) {
<<<<<<< HEAD
=======
        if(id==null || id <= 0){
            throw new ArithmeticException("ID ko hop le");
        }

        if(updatedMauSac.getMa()== null || updatedMauSac.getMa().isEmpty()){
            throw new ArithmeticException("Mã ko được trống");
        }
        if (updatedMauSac.getMa().length()<5 || updatedMauSac.getMa().length()>50)  throw new ArithmeticException("nhap ma > 5 va <50 ky tu");

        if(updatedMauSac.getTen()== null || updatedMauSac.getTen().isEmpty()){
            throw new ArithmeticException("Tên không được trống");
        }


        if (updatedMauSac.getTrangThai() == null) {
            throw new ArithmeticException("Trạng thái Màu Sắc không được null.");
        }

        if (!updatedMauSac.getTen().matches("^[a-zA-Z0-9]*$")) {
            throw new ArithmeticException("Tên không được chứa ký tự đặc biệt.");
        }

        if (!updatedMauSac.getMa().matches("^[a-zA-Z0-9]*$")) {
            throw new ArithmeticException("Mã không được chứa ký tự đặc biệt.");
        }


>>>>>>> 544b846 (add unit test mauSac)
        Optional<MauSac> optionalMauSac = mauSacRepository.findById(id);
        if (optionalMauSac.isPresent()) {
            MauSac existingMauSac = optionalMauSac.get();
            existingMauSac.setMa(updatedMauSac.getMa());
            existingMauSac.setTen(updatedMauSac.getTen());
            existingMauSac.setTrangThai(updatedMauSac.getTrangThai());
            return mauSacRepository.save(existingMauSac);
<<<<<<< HEAD
        }
        return null;
=======
        }else {
            throw new ArithmeticException("Nhan vien ko ton tai voi ID: "+id);
        }

>>>>>>> 544b846 (add unit test mauSac)
    }

    public void deleteMauSac(Integer id) {
        mauSacRepository.deleteById(id);
    }
<<<<<<< HEAD
=======

    public List<MauSac> findByMaMauSac(String ma){
        if (ma.length()<5 || ma.length()>50)  throw new ArithmeticException("nhap ma > 5 va <50 ky tu");
        if (!ma.matches("^[a-zA-Z0-9]*$")){
            throw new ArithmeticException("ma khong duoc chua ky tu dac biet");
        }
        return mauSacRepository.findMauSacsByMaContainsIgnoreCase(ma);
    }

    public List<MauSac> findByTenMauSac(String ten){
        if (ten.length()<5 || ten.length()>50)  throw new ArithmeticException("nhap ten > 5 va <50 ky tu");
        if (!ten.matches("^[a-zA-Z0-9]*$")){
            throw new ArithmeticException("ten khong duoc chua ky tu dac biet");
        }
        return mauSacRepository.findMauSacsByMaContainsIgnoreCase(ten);
    }

    public MauSac saveMauSac(MauSac mauSac) {
        if(mauSac.getId()==null || mauSac.getId() <= 0){
            throw new IllegalArgumentException("ID ko hop le");
        }

        if(mauSac.getMa()== null || mauSac.getMa().isEmpty()){
            throw new IllegalArgumentException("Mã ko được trống");
        }

        if(mauSac.getTen()== null || mauSac.getTen().isEmpty()){
            throw new IllegalArgumentException("Tên không được trống");
        }
        if (mauSac.getTen().length()<3 || mauSac.getTen().length()>50)  throw new ArithmeticException("nhap ten > 5 va <50 ky tu");

        if (mauSac.getTrangThai() == null) {
            throw new IllegalArgumentException("Trạng thái Màu Sắc không được null.");
        }

        if (!mauSac.getTen().matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Tên không được chứa ký tự đặc biệt.");
        }

        if (!mauSac.getMa().matches("^[a-zA-Z0-9]*$")) {
            throw new IllegalArgumentException("Mã không được chứa ký tự đặc biệt.");
        }

        return mauSacRepository.save(mauSac);
    }
>>>>>>> 544b846 (add unit test mauSac)
}

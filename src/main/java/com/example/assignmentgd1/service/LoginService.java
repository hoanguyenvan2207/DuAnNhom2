package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.NhanVien;
import com.example.assignmentgd1.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private NhanVienRepository nhanVienRepository;

    public NhanVien authenticate(String tenDangNhap, String matKhau) {
        NhanVien nhanVien = nhanVienRepository.findByTenDangNhap(tenDangNhap);
        if (nhanVien != null && nhanVien.getMatKhau().equals(matKhau)) {
            return nhanVien;
        }
        return null;
    }
}

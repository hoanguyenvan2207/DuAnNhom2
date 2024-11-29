package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.NhanVien;
import com.example.assignmentgd1.repository.NhanVienRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@Rollback
@SpringBootTest
class NhanVienServiceTest {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    NhanVienService nhanVienService;
    ArithmeticException exception;
    List<NhanVien> nhanViens;
    @BeforeEach
    void setUp() {
         nhanViens = Arrays.asList(
                new NhanVien(1, "NV001", "Nguyen", "nguyen123", "password1", true),
                new NhanVien(2, "NV002", "Tran", "tran456", "password2", true),
                new NhanVien(3, "NV003", "Le", "le789", "password3", true),
                new NhanVien(4, "NV004", "Pham", "pham012", "password4", false),
                new NhanVien(5, "NV005", "Hoang", "hoang345", "password5", true)
        );

    }

    @AfterEach
    void tearDown() {
        nhanVienRepository.deleteAll();
    }

    @Test
    void TestSizeGetAllNhanVien() {
        nhanVienRepository.saveAll(nhanViens);
        Assertions.assertEquals(5,nhanVienRepository.findAll().size());
    }
    @Test
    void TestSizeGetAllNhanVen_nhanViens_khongCoDaTa() {

        Assertions.assertEquals(0,nhanVienRepository.findAll().size());
    }

    @Test
    void TestSizeGetAllNhanVen_KhiThem1() {
        nhanVienRepository.saveAll(nhanViens);
        nhanVienRepository.save(new NhanVien(6, "NV001", "Nguyen", "nguyen123", "password1", true));
        Assertions.assertEquals(6,nhanVienRepository.findAll().size());
    }

    @Test
    void TestSizeGetAllNhanVen_dataCoDungTruongTenKhong() {
        nhanVienRepository.saveAll(nhanViens);
        Assertions.assertEquals("NV001", nhanVienRepository.findAll().get(0).getTen());
    }
    @Test
    void TestSizeGetAllNhanVen_dataHienThiDungSoLuongNVFalse() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> nhanViensTrangThaiFalse = nhanVienRepository.findAll().stream()
                .filter(nv -> !nv.getTrangThai())
                .collect(Collectors.toList());
        Assertions.assertEquals(1, nhanViensTrangThaiFalse.size());
    }
    @Test
    void TestSizeGetAllNhanVen_dataHienThiDungSoLuongNVTrue() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> nhanViensTrangThaiFalse = nhanVienRepository.findAll().stream()
                .filter(nv -> nv.getTrangThai())
                .collect(Collectors.toList());
        Assertions.assertEquals(4, nhanViensTrangThaiFalse.size());
    }
    @Test
    void TestSizeGetAllNhanVen_dataCoDungTruongMaKhong() {
        nhanVienRepository.saveAll(nhanViens);
        Assertions.assertEquals("Tran", nhanVienRepository.findAll().get(1).getMaNv());
    }
    @Test
    void TestSizeGetAllNhanVen_dataCoDungTruongTenDangNhapKhong() {
        nhanVienRepository.saveAll(nhanViens);
        Assertions.assertEquals("le789", nhanVienRepository.findAll().get(2).getTenDangNhap());
    }
    @Test
    void TestSizeGetAllNhanVen_dataCoDungTruongMatKhauKhong() {
        nhanVienRepository.saveAll(nhanViens);
        Assertions.assertEquals("password3", nhanVienRepository.findAll().get(2).getMatKhau());
    }
    @Test
    void TestSizeGetAllNhanVen_dataCoDungTruongTrangThaiKhong() {
        nhanVienRepository.saveAll(nhanViens);
        Assertions.assertEquals(false, nhanVienRepository.findAll().get(3).getTrangThai());
    }


    /////////////////////findNhanViensByMaNv///////////////////////////////
    @Test
    void testGetNhanVienByMaKhiDSRong() {
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("aaa");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testGetNhanVienByMa_KhiKhongCoTrongDS() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("aaa");
        Assertions.assertTrue(result.isEmpty());
    }
    @Test
    void testGetNhanVienByMa_KhiCo1() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("Hoang");
        Assertions.assertEquals(1, result.size());
    }
    @Test
    void testGetNhanVienByMa_KhiCoNhieu() {
        nhanVienRepository.save(new NhanVien(6, "NV001", "Hoang", "nguyen123", "password1", true));
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("Hoang");
        Assertions.assertEquals(2, result.size());
    }
    @Test
    void testGetNhanVienByMa_KiemTraTruongTen() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("Hoang");
        for (NhanVien nhanVien : result) {
            Assertions.assertEquals("NV005", nhanVien.getTen());
        }
    }
    @Test
    void testGetNhanVienByMa_KiemTraTruongMa() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("Nguyen");
        for (NhanVien nhanVien : result) {
            Assertions.assertEquals("Nguyen", nhanVien.getMaNv());
        }
    }
    @Test
    void testGetNhanVienByMa_KiemTraMatKhau() {
        nhanVienRepository.saveAll(nhanViens);
        List<NhanVien> result = nhanVienRepository.findNhanViensByMaNv("Nguyen");
        for (NhanVien nhanVien : result) {
            Assertions.assertEquals("password1", nhanVien.getMatKhau());
        }
    }
    @Test
    void testGetNhanVienByMa_NhapMaCoKyTuDaBiet() {
        nhanVienRepository.saveAll(nhanViens);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            nhanVienService.findNhanviensByMaNv("@7*####");

        });
        Assertions.assertEquals("ma khong duoc chua ky tu dac biet", exception.getMessage());
    }
    @Test
    void testGetNhanVienByMa_NhapMaLonHon50KyTu() {
        nhanVienRepository.saveAll(nhanViens);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            nhanVienService.findNhanviensByMaNv
                    ("dddgggggggggggggggggggggggggggggggggggg" +
                    "ggggggggggggggggggggggggggggggggggggggggg" + "dddddddddddddddddddddddddddddddddddd");
        });
        Assertions.assertEquals("nhap ma > 5 va <50 ky tu", exception.getMessage());
    }
    @Test
    void testGetNhanVienByMa_NhapMa3KyTu() {
        nhanVienRepository.saveAll(nhanViens);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            nhanVienService.findNhanviensByMaNv("ddd");
        });
        Assertions.assertEquals("nhap ma > 5 va <50 ky tu", exception.getMessage());
    }
    /////////////////////////////////Add////////////////////////////////////////////////////////
    @Test
    void testAddNhanVien_TenChuaKyTuDacBiet() {
        NhanVien newNhanVien = new NhanVien("NV002@%#", "Nguyen Thi B", "nguyenthib", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "Tên không được chứa ký tự đặc biệt.");
    }

    @Test
    void testAddNhanVienVoiMaRong() {
        NhanVien newNhanVien = new NhanVien("", "Nguyen Thi B", "nguyenthib", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "Mã nhân viên không được rỗng.");
    }

    @Test
    void testAddNhanVienVoiTenRong() {
        NhanVien newNhanVien = new NhanVien("NV003", "", "nguyenthib", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "Tên nhân viên không được rỗng.");
    }

    @Test
    void testAddNhanVienVoiTenDangNhapRong() {
        NhanVien newNhanVien = new NhanVien("NV003", "Nguyen Thi B", "", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "Tên đăng nhập không được rỗng.");
    }

    @Test
    void testAddNhanVienVoiMatKhauRong() {
        NhanVien newNhanVien = new NhanVien("NV003", "Nguyen Thi B", "nguyenthib", "", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "Mật khẩu không được rỗng.");
    }

    @Test
    void testAddNhanVienVoiTrangThaiNull() {
        NhanVien newNhanVien = new NhanVien("NV003", "Nguyen Thi B", "nguyenthib", "password456", null);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "Trạng thái nhân viên không được null.");
    }

    @Test
    void testAddNhanVienVoiIdAm() {
        NhanVien newNhanVien = new NhanVien(-1, "NV003", "Nguyen Thi B", "nguyenthib", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "ID không hợp lệ.");
    }

    @Test
    void testAddNhanVienVoiIdBang0() {
        NhanVien newNhanVien = new NhanVien(0, "NV003", "Nguyen Thi B", "nguyenthib", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "ID không hợp lệ.");
    }

    @Test
    void testAddNhanVienVoiIdBangnull() {
        NhanVien newNhanVien = new NhanVien(null, "NV003", "Nguyen Thi B", "nguyenthib", "password456", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.saveNhanVien(newNhanVien),
                "ID không hợp lệ.");
    }

    @Test
    void testAddNhanVienThemThanhCongTangSL() {
        nhanVienRepository.saveAll(nhanViens);
        NhanVien newNhanVien = new NhanVien(6, "NV9999", "Nguyen Thi X", "vanx", "password456", true);
        nhanVienService.saveNhanVien(newNhanVien);
       Assertions.assertEquals(6,nhanVienService.getAllNhanVien().size());
    }

    /////////////////////////////////Update////////////////////////////////////////////////////////
    @Test
    void testUpdateNhanVien_TenChuaKyTuDacBiet() {
        nhanVienRepository.saveAll(nhanViens);
        NhanVien updatedNhanVien = new NhanVien("NV001%#", "Nguyen Van A", "nguyenvana1", "password123", true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            nhanVienService.updateNhanVien(1,updatedNhanVien);
        });
        Assertions.assertEquals("ten khong duoc chua ky tu dac biet", exception.getMessage());
    }


    @Test
    void testUpdateNhanVienKhongTonTai() {
        NhanVien updatedNhanVien = new NhanVien(9999, "NV9999", "Nguyen Van X", "vanx", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(9999, updatedNhanVien),
                "Nhân viên không tồn tại với ID: 9999");
    }
    @Test
    void testUpdateNhanVienVoiMaRong() {
        NhanVien updatedNhanVien = new NhanVien(1, "", "Nguyen Van A", "nguyenvana", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(1, updatedNhanVien),
                "Mã nhân viên không được rỗng.");
    }
    @Test
    void testUpdateNhanVienVoiTenRong() {
        NhanVien updatedNhanVien = new NhanVien(1, "NV001", "", "nguyenvana", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(1, updatedNhanVien),
                "Tên nhân viên không được rỗng.");
    }
    @Test
    void testUpdateNhanVienVoiTenDangNhapRong() {
        NhanVien updatedNhanVien = new NhanVien(1, "NV001", "Nguyen Van A", "", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(1, updatedNhanVien),
                "Tên đăng nhập không được rỗng.");
    }
    @Test
    void testUpdateNhanVienVoiMatKhauRong() {
        NhanVien updatedNhanVien = new NhanVien(1, "NV001", "Nguyen Van A", "nguyenvana", "", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(1, updatedNhanVien),
                "Mật khẩu không được rỗng.");
    }
    @Test
    void testUpdateNhanVienVoiTrangThaiNull() {
        NhanVien updatedNhanVien = new NhanVien(1, "NV001", "Nguyen Van A", "nguyenvana", "password123", null);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(1, updatedNhanVien),
                "Trạng thái nhân viên không được null.");
    }
    @Test
    void testUpdateNhanVienVoiIdAm() {
        NhanVien updatedNhanVien = new NhanVien(-1, "NV001", "Nguyen Van A", "nguyenvana", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(-1, updatedNhanVien),
                "ID không hợp lệ.");
    }
    @Test
    void testUpdateNhanVienVoiIdBang0() {
        NhanVien updatedNhanVien = new NhanVien(0, "NV001", "Nguyen Van A", "nguyenvana", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(0, updatedNhanVien),
                "ID không hợp lệ.");
    }

    @Test
    void testUpdateNhanVienVoiIdBangnull() {
        NhanVien updatedNhanVien = new NhanVien(null, "NV001", "Nguyen Van A", "nguyenvana", "password123", true);
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.updateNhanVien(1, updatedNhanVien),
                "ID không hợp lệ.");
    }
    /////////////////////////////////////findById//////////////////////////
    @Test
    void testGetNhanVienByIdHopLe() {
        NhanVien nhanVien = new NhanVien("NV001", "Nguyen", "nguyen123", "password1", true);
        nhanVienRepository.save(nhanVien);
        nhanVienRepository.flush();
        NhanVien foundNhanVien = nhanVienService.getNhanVienById(nhanVien.getId()+"");
        Assertions.assertEquals("NV001", foundNhanVien.getTen());

    }
    @Test
    void testGetNhanVienByIdKhongTonTai() {
        NhanVien nhanVien = nhanVienService.getNhanVienById("9999");
        assertNull(nhanVien, "ID không tồn tại.");
    }
    @Test
    void testGetNhanVienByIdLaNull() {
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.getNhanVienById(null),
                "ID không hợp lệ.");
    }
    @Test
    void testGetNhanVienByIdLaSoAm() {
        assertThrows(IllegalArgumentException.class, () -> nhanVienService.getNhanVienById(-1+""),
                "ID không hợp lệ.");
    }
    @Test
    void testGetNhanVienByIdLaSo0() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> nhanVienService.getNhanVienById(0+""));
        assertEquals("ID không hợp lệ.", exception.getMessage());
    }
    @Test
    void testGetNhanVienByIdVoiDuLieuLon() {
        NhanVien nhanVien = nhanVienService.getNhanVienById(Integer.MAX_VALUE+"");
        assertNull(nhanVien, "ID không hợp lệ.");
    }
    @Test
    void testGetNhanVienByIdHopLeVoiIDLaKyTu() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> nhanVienService.getNhanVienById("test"));
        assertEquals("ID không hợp lệ.", exception.getMessage());

    }
    @Test
    void testGetNhanVienByIdHopLeVoiIDLaKyTuDacBiet() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> nhanVienService.getNhanVienById("*^^&"));
        assertEquals("ID không hợp lệ.", exception.getMessage());

    }
    @Test
    void testGetNhanVienByIdHopLeVoiIDLaSoThuc() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> nhanVienService.getNhanVienById("4.5"));
        assertEquals("ID không hợp lệ.", exception.getMessage());

    }
    @Test
    void testGetNhanVienByIdKyTuChuCaiVaSo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> nhanVienService.getNhanVienById("123abc")
        );
        assertEquals("ID không hợp lệ.", exception.getMessage());
    }



}
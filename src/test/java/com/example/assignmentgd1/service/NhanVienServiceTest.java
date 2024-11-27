package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.NhanVien;
import com.example.assignmentgd1.repository.NhanVienRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
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


    ////////////////////////////////////////////////////
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
/////////////////////////////////ADD////////////////////////////////////////////////////////
    @Test
    void testAddNhanVienCoThayDoiSL() {
        nhanVienRepository.save(new NhanVien( "NV001", "Hoang", "nguyen123", "password1", true));
        nhanVienRepository.saveAll(nhanViens);
        assertEquals(6, nhanVienRepository.findAll().size());
    }
    @Test
    void addItemVoiNameLaSauRieng() {
        nhanVienRepository.save(new NhanVien(1, "NV001", "Hoang", "nguyen123", "password1", true));
        nhanVienRepository.saveAll(nhanViens);
        assertEquals(6, nhanVienRepository.findAll().size());
    }
//    @Test
//    void addItemVoiNameLaTaoVaLe() {
//        itemManager.addItem(new Item(1,"Le"));
//        itemManager.addItem(new Item(2,"Tao"));
//        assertEquals(2, itemManager.getItems().size());
//        assertEquals("Le", itemManager.getItems().get(0).getName());
//        assertEquals("Tao", itemManager.getItems().get(1).getName());
//    }
//    @Test
//    void addItemVoiNameLaTao_Le_Man() {
//        itemManager.addItem(new Item(1,"Le"));
//        itemManager.addItem(new Item(2,"Tao"));
//        itemManager.addItem(new Item(2,"Man"));
//        assertEquals(3, itemManager.getItems().size());
//        assertEquals("Le", itemManager.getItems().get(0).getName());
//        assertEquals("Tao", itemManager.getItems().get(1).getName());
//        assertEquals("Man", itemManager.getItems().get(2).getName());
//    }
//    @Test
//    void addItem_Name_null() {
//        Item item = new Item(2, "");
//        exception  = assertThrows(ArithmeticException.class, () -> itemManager.addItem(item));
//        assertEquals("Ten ban dang de trong", exception.getMessage());
//    }
//    @Test
//    void addItem_dai_hon_50_ky_tu() {
//        String longName =
//                "Agfffffffffggggggg" +
//                        "ggggggggggggggggg" +
//                        "hhhhhhhhhhhhhhhhhhh" +
//                        "ggggggggggggggg";
//        Item item = new Item(3, longName);
//        Exception exception = assertThrows(ArithmeticException.class, () -> itemManager.addItem(item));
//        assertEquals("Name ban nhap dai hon 50 ky tu", exception.getMessage());
//    }
//    @Test
//    void addItem_Co_Ky_Tu_Ko_La_Chu() {
//        Item item = new Item(4, "Tao12$#");
//        Exception exception = assertThrows(ArithmeticException.class, () -> itemManager.addItem(item));
//        assertEquals("Name phai la chu", exception.getMessage());
//    }
}
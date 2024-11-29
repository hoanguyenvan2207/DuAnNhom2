package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.MauSac;
import com.example.assignmentgd1.model.SanPhamChiTiet;
import com.example.assignmentgd1.repository.MauSacRepository;
import com.example.assignmentgd1.repository.SanPhamChiTietRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@Rollback
@SpringBootTest
class MauSacServiceTest {
    @Autowired
    MauSacRepository mauSacRepository;
    @Autowired
    MauSacService mauSacService;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    ArithmeticException exception;
    List<MauSac> mauSacList;

    @BeforeEach
    void setUp() {

        mauSacList = Arrays.asList(
                new MauSac(null,"MS1","do",true),
                new MauSac(null,"MS2","xanh",true),
                new MauSac(null,"MS3","vang",true),
                new MauSac(null,"MS4","tim",false)



        );
    }

    @AfterEach
    void tearDown() {
    mauSacRepository.deleteAll();
    }

//////////////////////// List<MauSac> getAllMauSac() /////////////////////////////////

    @Test
    void testGetAllmauSac() {
        mauSacRepository.saveAll(mauSacList);
        Assertions.assertEquals(4,mauSacRepository.findAll().size());
    }

    @Test
    void testGetAllmauSac_koDaTa(){
        Assertions.assertEquals(0,mauSacRepository.findAll().size());
    }

    @Test
    void testGetAllmauSac_them1(){
        mauSacRepository.saveAll(mauSacList);
        mauSacRepository.save(new MauSac(null,"MS5","hong",true));
        Assertions.assertEquals(5,mauSacRepository.findAll().size());
    }

    @Test
    void testGetAllmauSac_trangThaiTrue(){
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> mauSacTrue = mauSacRepository.findAll().stream()
                .filter(ms -> ms.getTrangThai())
                .collect(Collectors.toList());
        Assertions.assertEquals(3, mauSacTrue.size());
    }

    @Test
    void testGetAllmauSac_trangThaiFalse(){
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> mauSacTrue = mauSacRepository.findAll().stream()
                .filter(ms -> !ms.getTrangThai())
                .collect(Collectors.toList());
        Assertions.assertEquals(1, mauSacTrue.size());
    }

    @Test
    void testGetAllmauSac_TruongMaMauSacDungKo(){
        mauSacRepository.saveAll(mauSacList);
        Assertions.assertEquals("MS2", mauSacRepository.findAll().get(1).getMa());
    }

    @Test
    void testGetAllmauSac_TruongTenMauSacDungKo(){
        mauSacRepository.saveAll(mauSacList);
        Assertions.assertEquals("xanh", mauSacRepository.findAll().get(1).getTen());
    }

    @Test
    void testGetAllmauSac_TruongTrangThaiDungKo(){
        mauSacRepository.saveAll(mauSacList);
        Assertions.assertEquals(true, mauSacRepository.findAll().get(1).getTrangThai());
    }

    @Test
    void testGetAllmauSac_CoGiaTriNull(){
        MauSac mauSac = new MauSac();
        mauSac.setTrangThai(null);
        mauSac.setTen(null);
        mauSac.setMa(null);
        try{
            mauSacRepository.save(mauSac);
        } catch (Exception e) {
            assertTrue(e instanceof DataIntegrityViolationException);
        }
        List<MauSac> mauSacLists = mauSacService.getAllMauSac();
        Assertions.assertEquals(1, mauSacLists.size());
    }

    @Test
    void testGetAllmauSac_ListKoNull(){
        mauSacRepository.deleteAll();
        List<MauSac> result = mauSacService.getAllMauSac();
        assertNotNull(result);
        assertEquals(0, result.size());
    }


///////////////// List<MauSac> getMauSacsByMa(String ma)  ///////////////////////////

    @Test
    void testGetMauSacByMa_DSTrong(){
        List<MauSac> result = mauSacRepository.findMauSacsByMaContainsIgnoreCase("gg");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testGetMauSacByMa_KoCoTrongDS(){
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> result = mauSacRepository.findMauSacsByMaContainsIgnoreCase("gg");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testGetMauSacByMa_Co1Ma(){
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> result = mauSacRepository.findMauSacsByMaContainsIgnoreCase("MS1");
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void testGetMauSacByMa_Co2MaTrung(){
        mauSacRepository.save(new MauSac(null,"MS1","hong",true));
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> result = mauSacRepository.findMauSacsByMaContainsIgnoreCase("MS1");
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void testGetMauSacByMa_KiemTraMaMauSac(){
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> result = mauSacRepository.findMauSacsByMaContainsIgnoreCase("MS2");
        for(MauSac mauSac : result){
            Assertions.assertEquals("MS2", mauSac.getMa());
        }
    }

    @Test
    void testGetMauSacByMa_KiemTraTenMauSac(){
        mauSacRepository.saveAll(mauSacList);
        List<MauSac> result = mauSacRepository.findMauSacsByTen("xanh");
        for(MauSac mauSac : result){
            Assertions.assertEquals("xanh", mauSac.getTen());
        }
    }

    @Test
    void testGetMauSacByMa_NhapMaCoKyTuDacBiet() {
        mauSacRepository.saveAll(mauSacList);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.findByMaMauSac("@7*####");

        });
        Assertions.assertEquals("ma khong duoc chua ky tu dac biet", exception.getMessage());
    }

    @Test
    void testGetMauSacByMa_NhapMaLonHon50KyTu(){
        mauSacRepository.saveAll(mauSacList);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.findByMaMauSac
                    ("dddgggggggggggggggggggggggggggggggggggg" +
                            "ggggggggggggggggggggggggggggggggggggggggg" + "dddddddddddddddddddddddddddddddddddd");
        });
        Assertions.assertEquals("nhap ma > 5 va <50 ky tu", exception.getMessage());
    }

    @Test
    void testGetMauSacByMa_NhapMaNhoHon5KyTu(){
        mauSacRepository.saveAll(mauSacList);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.findByMaMauSac
                    ("ddd" );
        });
        Assertions.assertEquals("nhap ma > 5 va <50 ky tu", exception.getMessage());
    }

    @Test
    void testGetMauSacByMa_MaLaNull() {
        List<MauSac> result = mauSacRepository.findMauSacsByMaContainsIgnoreCase(null);
        assertTrue(result.isEmpty(),"DS phai rong");

    }

////////////////////  MauSac createMauSac(MauSac mauSac)   //////////////////////////////

    @Test
    void testAddMauSac_MaTrong(){
        MauSac newMauSac = new MauSac(5,"","hong",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac)
                ,"Mã ko được trống");
    }

    @Test
    void testAddMauSac_MaCoKyTuDacBiet() {
        MauSac newMauSac = new MauSac(5,"@7###","hong",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac)
                ,"Mã không được chứa ký tự đặc biệt.");
    }

    @Test
    void testAddMauSac_TenDeTrong(){
        MauSac newMauSac = new MauSac(5,"MS4","",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac)
                ,"Tên ko được trống");
    }

    @Test
    void testAddMauSac_TenCoKyTuDacBiet() {
        MauSac newMauSac = new MauSac(5,"MS4","@7###",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac)
                ,"Tên không được chứa ký tự đặc biệt");
    }

    @Test
    void testAddMauSac_NhapTenDuoi5KyTu(){
        MauSac newMauSac = new MauSac(5,"MS4","dd",true);
        assertThrows(ArithmeticException.class, () -> mauSacService.saveMauSac(newMauSac)
                ,"nhap ten > 5 va <50 ky tu");
    }

    @Test
    void testAddMauSac_TrangThaiNull(){
        MauSac newMauSac = new MauSac(5,"MS4","hai",null);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac)
                ,"Trạng thái Màu Sắc không được null.");
    }

    @Test
    void testAddMauSac_IdAm(){
        MauSac newMauSac = new MauSac(-1,"MS4","hai",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac),
                "ID ko hop le");
    }

    @Test
    void testAddMauSac_IdBang0(){
        MauSac newMauSac = new MauSac(0,"MS4","hai",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac),
                "ID ko hop le");
    }

    @Test
    void testAddMauSac_IdBangNull(){
        MauSac newMauSac = new MauSac(null,"MS4","hai",true);
        assertThrows(IllegalArgumentException.class, () -> mauSacService.saveMauSac(newMauSac),
                "ID ko hop le");
    }

    @Test
    void testAddMauSac_AddThanhCong(){
        mauSacRepository.saveAll(mauSacList);
        MauSac newMauSac = new MauSac(5,"MS5","hai444",true);
        mauSacService.saveMauSac(newMauSac);
        Assertions.assertEquals(5,mauSacService.getAllMauSac().size());
    }

  ///////////// MauSac updateMauSac(Integer id, MauSac updatedMauSac) //////////////////////////////////////////

    @Test
    void testUpdateMauSac_TenChuaKyTuDacBiet(){
        MauSac updateMS = new MauSac(1,"MS1111","do@@@",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(1,updateMS);});
        Assertions.assertEquals("Tên không được chứa ký tự đặc biệt.", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_UpdateMauSacKoTonTai(){
        MauSac updateMS = new MauSac(99,"MS5","do",true);
        assertThrows(ArithmeticException.class, () -> mauSacService.updateMauSac(99, updateMS),
                "Nhan vien ko ton tai voi ID: 99");
    }

    @Test
    void testUpdateMauSac_TenRong(){
        MauSac updateMS = new MauSac(1,"MS1111","",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(1,updateMS);});
        Assertions.assertEquals("Tên không được trống", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_MaCoKyTuDacBiet(){
        MauSac updateMS = new MauSac(1,"MS1@@","do",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(1,updateMS);});
        Assertions.assertEquals("Mã không được chứa ký tự đặc biệt.", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_MaRong(){
        MauSac updateMS = new MauSac(1,"","do",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(1,updateMS);});
        Assertions.assertEquals("Mã ko được trống", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_TrangThaiNull(){
        MauSac updateMS = new MauSac(1,"MS1111","do",null);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(1,updateMS);});
        Assertions.assertEquals("Trạng thái Màu Sắc không được null.", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_IdAm(){
        MauSac updateMS = new MauSac(-1,"MS1","do",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(-1,updateMS);});
        Assertions.assertEquals("ID ko hop le", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_IdNull(){
        MauSac updateMS = new MauSac(null,"MS1111","do",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(null,updateMS);});
        Assertions.assertEquals("ID ko hop le", exception.getMessage());

    }

    @Test
    void testUpdateMauSac_IdBang0(){
        MauSac updateMS = new MauSac(0,"MS1","do",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(0,updateMS);});
        Assertions.assertEquals("ID ko hop le", exception.getMessage());
    }

    @Test
    void testUpdateMauSac_UpdateMaCo4kyTu(){
        MauSac updateMS = new MauSac(1,"MS1","do",true);
        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            mauSacService.updateMauSac(1,updateMS);});
        Assertions.assertEquals("nhap ma > 5 va <50 ky tu", exception.getMessage());
    }

    /////////////////  MauSac getMauSacById(Integer id) //////////////

    @Test
    void testGetMauSacById_HopLe(){
        MauSac mauSac = mauSacService.getMauSacById(1);
        assertNotNull(mauSac);
    }

    @Test
    void testGetMauSacById_KhongTonTai(){
        MauSac mauSac = mauSacService.getMauSacById(999);
        assertNull(mauSac,"id ko ton tai");
    }

    @Test
    void testGetMauSacById_IdNull(){
        assertThrows(IllegalArgumentException.class, () -> mauSacService.getMauSacById(null)
                ,"Id ko hop le");
    }

    @Test
    void testGetMauSacById_IdAm(){
        assertThrows(IllegalArgumentException.class, () -> mauSacService.getMauSacById(-1)
                ,"Id ko hop le");
    }

    @Test
    void testGetMauSacById_IdBang0(){
        assertThrows(IllegalArgumentException.class, () -> mauSacService.getMauSacById(0)
                ,"Id ko hop le");
    }

    @Test
    void testGetMauSacById_IdLaDuLieuLon(){
        MauSac mauSac = mauSacService.getMauSacById(Integer.MAX_VALUE);
        assertNull(mauSac,"Id ko hop le");
    }

    @Test
    void testGetMauSacById_IdlaDuLieuNho(){
        assertThrows(IllegalArgumentException.class, () -> mauSacService.getMauSacById(Integer.MIN_VALUE)
                ,"Id ko hop le");
    }


    @Test
    void testGetMauSacById_IDLaChuoi() {
        try {
            List<MauSac> result = mauSacService.findMSById((Integer)(Object) "abc");
            fail("Mong đợi ngoại lệ ClassCastException");
        } catch (ClassCastException e) {
            assertTrue(e instanceof ClassCastException, "Mong đợi ClassCastException");
        } catch (Exception e) {
            fail("Mong đợi ngoại lệ ClassCastException");
        }
    }

    @Test
    void testGetMauSacById_IDLaSoThuc() {
        try {
            List<MauSac> result = mauSacService.findMSById((Integer) (Object) 123.45);
            fail("Mong đợi ngoại lệ ClassCastException");
        } catch (ClassCastException e) {
            assertTrue(e instanceof ClassCastException, "Mong đợi ClassCastException");
        } catch (Exception e) {
            fail("Mong đợi ngoại lệ ClassCastException");
        }
    }

    @Test
    void testGetMauSacById_IdBang1KoTonTai(){
        MauSac result = mauSacService.getMauSacById(1);
        assertNull(result);
    }

   }





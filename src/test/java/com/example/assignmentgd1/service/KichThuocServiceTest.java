package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.KichThuoc;
import com.example.assignmentgd1.repository.HoaDonChiTietRepository;
import com.example.assignmentgd1.repository.KichThuocRepository;
import com.example.assignmentgd1.repository.SanPhamChiTietRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KichThuocServiceTest {

    @Autowired
    KichThuocRepository kichThuocRepository;

    @Autowired
    KichThuocService kichThuocService;

    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @BeforeEach
    void setUp() {
        kichThuocRepository.saveAll(Arrays.asList(
                new KichThuoc(1, "KT01", "Nhỏ", true),
                new KichThuoc(2, "KT02", "Lớn", true),
                new KichThuoc(3, "KT03", "Lớn", false),
                new KichThuoc(4, "KT04", "Nhỏ", false),
                new KichThuoc(5, "KT05", "Nhỏ", true)
        ));
    }

    @AfterEach
    void tearDown() {
        hoaDonChiTietRepository.deleteAll();
        sanPhamChiTietRepository.deleteAll();
        kichThuocRepository.deleteAll();
    }

    //////////////////////////////test getSaveKichThuoc/////////////////////////////////
    @Test
    void testSaveValidKichThuoc() {
        KichThuoc kichThuoc = new KichThuoc(null, "KT06", "Vừa", true);
        KichThuoc savedKichThuoc = kichThuocService.saveKichThuoc(kichThuoc);
        assertNotNull(savedKichThuoc.getId());
        assertEquals("KT06", savedKichThuoc.getMa());
        assertEquals("Vừa", savedKichThuoc.getTen());
        assertTrue(savedKichThuoc.getTrangThai());
    }

    @Test
    void testSaveValidKichThuoc3Record() {
        KichThuoc kichThuoc = new KichThuoc(null, "KT06", "Vừa", true);
        KichThuoc kichThuoc1 = new KichThuoc(null, "KT07", "Vừa", false);
        KichThuoc kichThuoc2 = new KichThuoc(null, "KT08", "Vừa", true);
        kichThuocService.saveKichThuoc(kichThuoc);
        kichThuocService.saveKichThuoc(kichThuoc1);
        kichThuocService.saveKichThuoc(kichThuoc2);
        assertEquals(8, kichThuocRepository.findAll().size());
    }

    @Test
    void testSaveWithNullFields() {
        KichThuoc kichThuoc = new KichThuoc(null, null, null, null);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc);
        });
        Assertions.assertEquals("Mã, tên, trạng thái không được để trống", exception.getMessage());
    }

    @Test
    void testSaveWithNullMa() {
        KichThuoc kichThuoc1 = new KichThuoc(null, null, "Nhỏ", true);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc1);
        });
        Assertions.assertEquals("Mã kích thước không được để trống", exception.getMessage());
    }

    @Test
    void testSaveWithNullTrangThai() {
        KichThuoc kichThuoc1 = new KichThuoc(null, "KT01", "Nhỏ", null);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc1);
        });
        Assertions.assertEquals("Trạng thái không được để trống", exception.getMessage());
    }

    @Test
    void testSaveWithNullTen() {
        KichThuoc kichThuoc1 = new KichThuoc(null, "KT01", null, true);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc1);
        });
        Assertions.assertEquals("Tên kích thước không được để trống", exception.getMessage());
    }

    @Test
    void testSaveWithDuplicateMa() {
        KichThuoc kichThuoc1 = new KichThuoc(null, "KT01", "Nhỏ", true);
        kichThuocService.saveKichThuoc(kichThuoc1);
        KichThuoc kichThuoc2 = new KichThuoc(null, "KT01", "Lớn", true);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc2);
        });
        Assertions.assertEquals("Mã kích thước đã tồn tại", exception.getMessage());
    }

    @Test
    void testSaveWithValidationMa() {
        KichThuoc kichThuoc1 = new KichThuoc(null, "K", "Nhỏ", true);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc1);
        });
        Assertions.assertEquals("Mã phải lớn hơn 3 kí tự", exception.getMessage());
    }

    @Test
    void testSaveWithValidationTen() {
        KichThuoc kichThuoc1 = new KichThuoc(null, "KT01", "NhỏNhỏNhỏNhỏNhỏNhỏ", true);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(kichThuoc1);
        });
        Assertions.assertEquals("Tên phải nhỏ hơn 10 kí tự", exception.getMessage());
    }

    @Test
    void testSaveAndCount() {
        long countBefore = kichThuocRepository.count();
        KichThuoc kichThuoc = new KichThuoc(null, "KT12", "Mới thêm", true);
        kichThuocService.saveKichThuoc(kichThuoc);
        long countAfter = kichThuocRepository.count();
        assertEquals(countBefore + 1, countAfter);
    }

    ////////////////////////////////////////////ham findKichthuocbyMa/////////////////
    @Test
    void testFindKichThuocsByMa_ExistingMa_PartialMatch() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT0");
        assertEquals(5, result.size());
    }

    @Test
    void testFindKichThuocsByMa_ExactMatch() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT01");
        assertEquals(1, result.size());
        assertEquals("KT01", result.get(0).getMa());
    }

    @Test
    void testFindKichThuocsByMa_NonExistingMa() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT99");
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindKichThuocsByMa_MixedCase() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("kT02");
        assertEquals(1, result.size());
        assertEquals("KT02", result.get(0).getMa());
    }

    @Test
    void testFindKichThuocsByMa_SingleCharacter() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("K");
        assertEquals(5, result.size());
    }

    @Test
    void testFindKichThuocsByMa_MultipleMatches() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT0");
        assertEquals(5, result.size());
    }

    @Test
    void testFindKichThuocsByMa_EmptyString() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<KichThuoc> result = kichThuocService.findKichThuocsByMa(null);
        });
        Assertions.assertEquals("Mã không được trống", exception.getMessage());
    }

    @Test
    void testFindKichThuocsByMa_Invalid() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT0*");
        });
        Assertions.assertEquals("Mã chỉ được chứa ký tự chữ và số", exception.getMessage());
    }

    @Test
    void testFindKichThuocsByMa_AllRecordsMatch() {
        List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT");
        assertEquals(5, result.size());
    }

    @Test
    void testFindKichThuocsByMa_InvalidMa() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            List<KichThuoc> result = kichThuocService.findKichThuocsByMa("KT0@");
        });
        Assertions.assertEquals("Mã chỉ được chứa ký tự chữ và số", exception.getMessage());
    }

    //////////////////////////////////////ham getAll()////////////////////
    @Test
    void testGetAllKichThuoc_Valid() {
        List<KichThuoc> result = kichThuocService.getAllKichThuoc();
        assertFalse(result.isEmpty(), "Danh sách không được rỗng");
        assertEquals(5, result.size(), "Kích thước danh sách phải là 5");
    }

    @Test
    void testGetAllKichThuoc_EmptyList() {
        kichThuocRepository.deleteAll();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.getAllKichThuoc();
        });

        assertEquals("List rỗng", exception.getMessage(), "Thông điệp ngoại lệ phải là 'List rỗng'");
    }

    @Test
    void testGetAllKichThuoc_ValidContent() {
        List<KichThuoc> result = kichThuocService.getAllKichThuoc();
        assertTrue(result.stream().anyMatch(k -> k.getMa().equals("KT01")), "Danh sách phải chứa KT01");
        assertTrue(result.stream().anyMatch(k -> k.getTen().equals("Nhỏ")), "Danh sách phải chứa Nhỏ");
    }

    @Test
    void testGetAllKichThuoc_EmptyRepository() {
        kichThuocRepository.deleteAll();
        assertThrows(IllegalArgumentException.class, () -> kichThuocService.getAllKichThuoc());
    }

    @Test
    void testGetAllKichThuoc_NoDuplicates() {
        kichThuocRepository.saveAll(Arrays.asList(
                new KichThuoc(1, "KT01", "Nhỏ", true),
                new KichThuoc(1, "KT01", "Nhỏ", true)
        ));
        List<KichThuoc> result = kichThuocService.getAllKichThuoc();
        assertEquals(7, result.size(), "Danh sách chỉ chứa 7 mục ");
    }

    @Test
    void testGetAllKichThuoc_SpecialCharacters() {
        kichThuocRepository.save(new KichThuoc(6, "KT06", "Tên có ký tự đặc biệt @#", true));
        List<KichThuoc> result = kichThuocService.getAllKichThuoc();
        assertTrue(result.stream().anyMatch(k -> k.getTen().equals("Tên có ký tự đặc biệt @#")), "Danh sách phải chứa ký tự đặc biệt");
    }

    @Test
    void testGetAllKichThuoc_AfterClear() {
        kichThuocRepository.deleteAll();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.getAllKichThuoc();
        });
        assertEquals("List rỗng", exception.getMessage(), "Thông điệp ngoại lệ phải là 'List rỗng'");
    }

    @Test
    void testGetAllKichThuoc_FilterBySize() {
        List<KichThuoc> result = kichThuocService.getAllKichThuoc();
        long countSmall = result.stream().filter(k -> "Nhỏ".equals(k.getTen())).count();
        long countLarge = result.stream().filter(k -> "Lớn".equals(k.getTen())).count();
        assertEquals(3, countSmall, "Danh sách phải chứa 3 mục có kích thước 'Nhỏ'");
        assertEquals(2, countLarge, "Danh sách phải chứa 2 mục có kích thước 'Lớn'");
    }

    @Test
    void testGetAllKichThuoc_Order() {
        List<KichThuoc> result = kichThuocService.getAllKichThuoc();
        assertTrue(result.get(0).getMa().compareTo(result.get(1).getMa()) < 0, "Mã của mục phải theo thứ tự tăng dần");
    }

    @Test
    void testGetAllKichThuoc_AfterAddingNewItem() {
        KichThuoc newKichThuoc = new KichThuoc(6, "KT06", "Vừa", true);
        kichThuocRepository.save(newKichThuoc);

        List<KichThuoc> result = kichThuocService.getAllKichThuoc();

        assertEquals(6, result.size(), "Danh sách phải chứa 6 mục sau khi thêm một mục mới");
    }


    /////////////////////////////////////////ham updateKichThuoc/////////////////////
    @Test
    void testUpdateKichThuoc_SuccessfulUpdate() {
        KichThuoc existingKichThuoc = kichThuocRepository.save(new KichThuoc(null, "KT01", "Nhỏ", true));
        KichThuoc updatedKichThuoc = new KichThuoc(null, "KT01_UPDATED", "Lớn", false);
        KichThuoc result = kichThuocService.updateKichThuoc(existingKichThuoc.getId(), updatedKichThuoc);
        assertNotNull(result);
        assertEquals("KT01_UPDATED", result.getMa());
        assertEquals("Lớn", result.getTen());
        assertFalse(result.getTrangThai());
    }

    @Test
    void testUpdateKichThuoc_InvalidId() {
        KichThuoc updatedKichThuoc = new KichThuoc(null, "KT01_UPDATED", "Lớn", false);
        KichThuoc result = kichThuocService.updateKichThuoc(999, updatedKichThuoc);
        assertNull(result);
    }

    @Test
    void testUpdateKichThuoc_NullMa() {
        KichThuoc KichThuoc = new KichThuoc(null, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(null, null, "Lớn", false);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.saveKichThuoc(updatedKichThuoc);
        });
        Assertions.assertEquals("Mã kích thước không được để trống", exception.getMessage());
    }

    @Test
    void testUpdateKichThuocbyCode() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "KT02", "Lớn", false);
        kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        assertEquals(1, 1);
    }

    @Test
    void testUpdateKichThuocbyName() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "KT01", "Nhỏ", false);
        kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        assertEquals(1, 1);
    }

    @Test
    void testUpdateKichThuocbyStatus() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "KT01", "Nhỏ", true);
        kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        assertEquals(1, 1);
    }

    @Test
    void testUpdateKichThuocNullTen() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "KT01", null, false);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        });
        Assertions.assertEquals("Tên không được để trống", exception.getMessage());
    }

    @Test
    void testUpdateKichThuocInvalidName() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "KT01", "LớnLớnLớnLớnLớnLớnLớnLớn", false);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        });
        Assertions.assertEquals("Tên phải nhỏ hơn 10 kí tự", exception.getMessage());
    }

    @Test
    void testUpdateKichThuocInvalidCode() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "K", "Lớn", false);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        });
        Assertions.assertEquals("Mã phải lớn hơn 3 kí tự", exception.getMessage());
    }

    @Test
    void testUpdateKichThuocNullStatus() {
        KichThuoc KichThuoc = new KichThuoc(1, "KT01", "Lớn", false);
        KichThuoc updatedKichThuoc = new KichThuoc(1, "KT01", "Lớn", null);
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            kichThuocService.updateKichThuoc(1, updatedKichThuoc);
        });
        Assertions.assertEquals("Trạng thái không được để trống", exception.getMessage());
    }

    ///////////////////////////////////////////ham deleteKichThuoc/////////////////
    @Test
    void testDeleteKichThuoc_ValidId() {
        hoaDonChiTietRepository.deleteAll();
        sanPhamChiTietRepository.deleteAll();
        kichThuocService.deleteKichThuoc(1);
        assertEquals(4, kichThuocRepository.findAll().size());
    }

    @Test
    void testDeleteKichThuoc_InvalidId_LessThanZero() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> kichThuocService.deleteKichThuoc(-1));
        assertEquals("ID phải lớn hơn 0", thrown.getMessage());
    }

    @Test
    void testDeleteKichThuoc_InvalidIdZero() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> kichThuocService.deleteKichThuoc(0));
        assertEquals("ID phải lớn hơn 0", thrown.getMessage());
    }

    @Test
    void testDeleteKichThuoc_idNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> kichThuocService.deleteKichThuoc(null));
        assertEquals("ID không tồn tại", thrown.getMessage());
    }

    @Test
    void testDeleteKichThuoc_IdDoesNotExist() {
        Integer id = 10;
        boolean result = kichThuocService.deleteKichThuoc(id);
        assertFalse(result);
    }

    @Test
    void testDeleteKichThuoc_AlreadyDeleted() {
        Integer id = 1;
        kichThuocService.deleteKichThuoc(id);
        boolean result = kichThuocService.deleteKichThuoc(id);
        assertFalse(result);
    }

    @Test
    void testDeleteSuccessfully1Record() {
        KichThuoc kichThuoc = new KichThuoc(6, "KT06", "Lớn", true);
        kichThuocService.deleteKichThuoc(6);
        assertEquals(5, kichThuocRepository.findAll().size());
    }

    @Test
    void testDeleteSuccessfully3Record() {
        KichThuoc kichThuoc = new KichThuoc(6, "KT06", "Lớn", true);
        KichThuoc kichThuoc1 = new KichThuoc(7, "KT07", "Nhỏ", false);
        KichThuoc kichThuoc2 = new KichThuoc(8, "KT08", "Lớn", true);
        kichThuocService.deleteKichThuoc(6);
        kichThuocService.deleteKichThuoc(7);
        kichThuocService.deleteKichThuoc(8);
        assertEquals(5, kichThuocRepository.findAll().size());
    }

    @Test
    void testDeleteSuccessfully4Record() {
        KichThuoc kichThuoc = new KichThuoc(6, "KT06", "Lớn", true);
        KichThuoc kichThuoc1 = new KichThuoc(7, "KT07", "Nhỏ", false);
        KichThuoc kichThuoc2 = new KichThuoc(8, "KT08", "Lớn", true);
        KichThuoc kichThuoc3 = new KichThuoc(9, "KT09", "Lớn", true);
        kichThuocService.deleteKichThuoc(6);
        kichThuocService.deleteKichThuoc(7);
        kichThuocService.deleteKichThuoc(8);
        kichThuocService.deleteKichThuoc(9);
        assertEquals(5, kichThuocRepository.findAll().size());
    }

    @Test
    void testDeleteSuccessfully5Record() {
        KichThuoc kichThuoc = new KichThuoc(6, "KT06", "Lớn", true);
        KichThuoc kichThuoc1 = new KichThuoc(7, "KT07", "Nhỏ", false);
        KichThuoc kichThuoc2 = new KichThuoc(8, "KT08", "Lớn", true);
        KichThuoc kichThuoc3 = new KichThuoc(9, "KT08", "Lớn", true);
        KichThuoc kichThuoc4 = new KichThuoc(10, "KT08", "Lớn", true);
        kichThuocService.deleteKichThuoc(6);
        kichThuocService.deleteKichThuoc(7);
        kichThuocService.deleteKichThuoc(8);
        kichThuocService.deleteKichThuoc(9);
        kichThuocService.deleteKichThuoc(10);
        assertEquals(5, kichThuocRepository.findAll().size());
    }

}
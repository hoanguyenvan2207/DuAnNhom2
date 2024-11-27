package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.SanPham;
import com.example.assignmentgd1.repository.SanPhamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class SanPhamServiceTest {

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @BeforeEach
    void setUp() {
//        sanPhamService = new SanPhamService();
    }

    @AfterEach
    void tearDown() {
//        sanPhamRepository.deleteAll();
    }

    @Test
    void testGetAllSanPhamVoiSizeLonHon0() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        assertNotNull(sanPhams);
        assertTrue(sanPhams.size() > 0, "Danh sách sản phẩm không có sản phẩm.");
    }

    @Test
    void testGetAllSanPhamDayDuSPTrongCSDL() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        assertNotNull(sanPhams, "Danh sách sản phẩm không được null.");
        assertEquals(7, sanPhams.size(), "Số lượng sản phẩm không đúng.");
        for (SanPham sp : sanPhams) {
            assertNotNull(sp.getMa(), "Mã sản phẩm không được null.");
            assertNotNull(sp.getTen(), "Tên sản phẩm không được null.");
        }
    }

    @Test
    void testGetAllSanPhamVoiSanPhamKhongDu() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        assertEquals(7, sanPhams.size(), "Số lượng sản phẩm trong CSDL không khớp.");
        int expectedSize = 5;
        assertNotEquals(expectedSize, sanPhams.size(),
                "Số lượng sản phẩm hiện tại là " + sanPhams.size() + ", nhưng mong muốn là " + expectedSize + ". Do đó test bị sai.");
    }

    @Test
    void testGetAllSanPhamVoiTrangThaiHoatDong() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        long activeCount = sanPhams.stream().filter(SanPham::getTrangThai).count();
        assertTrue(activeCount > 0, "Không có sản phẩm nào đang hoạt động.");
    }

    @Test
    void testGetAllSanPhamVoiMaKhongRong() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        for (SanPham sp : sanPhams) {
            assertFalse(sp.getMa().isEmpty(), "Mã sản phẩm không được rỗng.");
        }
    }

    @Test
    void testGetAllSanPhamVoiTenKhongRong() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        for (SanPham sp : sanPhams) {
            assertFalse(sp.getTen().isEmpty(), "Tên sản phẩm không được rỗng.");
        }
    }

    @Test
    void testGetAllSanPhamVoiMaDaiHon3KyTu() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        for (SanPham sp : sanPhams) {
            assertTrue(sp.getMa().length() > 3, "Mã sản phẩm có ít hơn 3 ký tự.");
        }
    }

    @Test
    void testGetAllSanPhamVoiTenKhongCoKyTuDacBiet() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        for (SanPham sp : sanPhams) {
            assertFalse(sp.getTen().matches("^[a-zA-Z0-9\\s]+$"), "Tên sản phẩm chứa ký tự đặc biệt.");
        }
    }

    @Test
    void testGetAllSanPhamVoiTenKhongTrungLap() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        long distinctCount = sanPhams.stream().map(SanPham::getTen).distinct().count();
        assertEquals(sanPhams.size(), distinctCount, "Có sản phẩm trùng tên.");
    }

    @Test
    void testGetAllSanPhamVoiIdKhongAm() {
        List<SanPham> sanPhams = sanPhamService.getAllSanPham();
        for (SanPham sp : sanPhams) {
            assertTrue(sp.getId() > 0, "ID sản phẩm không được là số âm.");
        }
    }


    //============================= Hàm getSanPhamById =====================================\\

    @Test
    void testGetSanPhamByIdHopLe() {
        SanPham sanPham = sanPhamService.getSanPhamById(1);
        assertNotNull(sanPham, "Sản phẩm không được null khi ID hợp lệ.");
    }

    @Test
    void testGetSanPhamByIdKhongTonTai() {
        SanPham sanPham = sanPhamService.getSanPhamById(9999);
        assertNull(sanPham, "ID không tồn tại.");
    }

    @Test
    void testGetSanPhamByIdLaNull() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.getSanPhamById(null),
                "ID không hợp lệ.");
    }

    @Test
    void testGetSanPhamByIdLaSoAm() {
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.getSanPhamById(-1),
                "ID không hợp lệ.");
    }

    @Test
    void testGetSanPhamByIdLaSo0() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> sanPhamService.getSanPhamById(0));
        assertEquals("ID không hợp lệ.", exception.getMessage());
    }


    @Test
    void testGetSanPhamByIdVoiDuLieuLon() {
        SanPham sanPham = sanPhamService.getSanPhamById(Integer.MAX_VALUE);
        assertNull(sanPham, "ID không hợp lệ.");
    }

    @Test
    void testGetSanPhamByIdHopLeVoiTrangThaiHoatDong() {
        SanPham sanPham = sanPhamService.getSanPhamById(1);
        assertTrue(sanPham.getTrangThai(), "Sản phẩm với ID hợp lệ phải có trạng thái hoạt động.");
    }

    @Test
    void testGetSanPhamByIdKhongCoTenRong() {
        SanPham sanPham = sanPhamService.getSanPhamById(1);
        assertFalse(sanPham.getTen().isEmpty(), "Tên sản phẩm không được rỗng khi ID hợp lệ.");
    }

    @Test
    void testGetSanPhamByIdVoiTenDungDinhDang() {
        SanPham sanPham = sanPhamService.getSanPhamById(1);
        assertFalse(sanPham.getTen().matches("^[a-zA-Z0-9\\s]+$"), "Tên sản phẩm phải đúng định dạng.");
    }

    @Test
    void testGetSanPhamByIdVoiMaDaiHon3KyTu() {
        SanPham sanPham = sanPhamService.getSanPhamById(1);
        assertTrue(sanPham.getMa().length() > 3, "Mã sản phẩm phải dài hơn 3 ký tự.");
    }

    //************************ Hàm searchSanPham ******************************\\

    @Test
    void testSearchSanPhamVoiKeywordChinhXac() {
        List<SanPham> sanPhams = sanPhamService.searchSanPham("SP001");
        assertFalse(sanPhams.isEmpty(), "Kết quả không được rỗng khi keyword là 'SP001'.");
        assertEquals(1, sanPhams.size(), "Phải chỉ có 1 sản phẩm khớp với 'SP001'.");
        assertEquals("SP001", sanPhams.get(0).getMa(), "Mã sản phẩm phải khớp với 'SP001'.");
    }


    @Test
    void testSearchSanPhamVoiKeywordKhongTonTai() {
        List<SanPham> sanPhams = sanPhamService.searchSanPham("XYZ123");
        assertTrue(sanPhams.isEmpty(), "Kết quả phải rỗng khi không có sản phẩm khớp với keyword.");
    }

    @Test
    void testSearchSanPhamVoiKeywordNull() {
        assertThrows(IllegalArgumentException.class,
                () -> sanPhamService.searchSanPham(null), "Keyword không được null.");
    }

    @Test
    void testSearchSanPhamVoiKeywordRong() {
        assertThrows(IllegalArgumentException.class,
                () -> sanPhamService.searchSanPham("   "), "Keyword không được null hoặc rỗng.");
    }


    @Test
    void testSearchSanPhamVoiKeywordKhongPhanBietChuHoaChuThuong() {
        List<SanPham> sanPhams1 = sanPhamService.searchSanPham("sp001");
        List<SanPham> sanPhams2 = sanPhamService.searchSanPham("SP001");
        assertEquals(sanPhams1.size(), sanPhams2.size(), "Tìm kiếm không phân biệt chữ hoa chữ thường.");
    }

    @Test
    void testSearchSanPhamVoiKeywordChinhXacTrongTen() {
        List<SanPham> sanPhams = sanPhamService.searchSanPham("D");
        assertEquals(1, sanPhams.size(), "Chỉ có 1 sản phẩm chứa từ khóa 'D' trong tên.");
        assertEquals("Sản phẩm D", sanPhams.get(0).getTen(), "Tên sản phẩm phải là 'Sản phẩm D'.");
    }


    @Test
    void testSearchSanPhamVoiKeywordTrongMa() {
        List<SanPham> sanPhams = sanPhamService.searchSanPham("SP");
        assertFalse(sanPhams.isEmpty(), "Kết quả phải chứa sản phẩm có từ khóa trong mã.");
    }

    @Test
    void testSearchSanPhamVoiKeywordCoKyTuDacBiet() {
        List<SanPham> sanPhams = sanPhamService.searchSanPham("@#$");
        assertTrue(sanPhams.isEmpty(), "Kết quả phải rỗng khi tìm kiếm với ký tự đặc biệt.");
    }

    @Test
    void testSearchSanPhamVoiKeywordDaiHon() {
        String keyword = "X".repeat(100);
        List<SanPham> sanPhams = sanPhamService.searchSanPham(keyword);
        assertTrue(sanPhams.isEmpty(), "Kết quả phải rỗng khi từ khóa quá dài không khớp.");
    }

    @Test
    void testSearchSanPhamVoiKeywordCoKhoangTrang() {
        List<SanPham> sanPhams = sanPhamService.searchSanPham(" SP001 ");
        assertTrue(sanPhams.isEmpty(), "Kết quả không nên bị ảnh hưởng bởi khoảng trắng ở đầu hoặc cuối.");
    }


    // ===================== Hàm createSanPham ======================

    @Test
    void testCreateSanPhamHopLe() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP008");
        sanPham.setTen("Áo thun");
        sanPham.setTrangThai(true);
        SanPham result = sanPhamService.createSanPham(sanPham);
        assertNotNull(result, "Sản phẩm không được null.");
        assertEquals("SP008", result.getMa(), "Mã sản phẩm không đúng.");
        assertEquals("Áo thun", result.getTen(), "Tên sản phẩm không đúng.");
    }

    @Test
    void testCreateSanPhamVoiMaRong() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("");
        sanPham.setTen("Áo thun");
        sanPham.setTrangThai(true);

        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(sanPham),
                "Mã sản phẩm không được rỗng.");
    }

    @Test
    void testCreateSanPhamVoiTenRong() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP009");
        sanPham.setTen("");
        sanPham.setTrangThai(true);

        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(sanPham),
                "Tên sản phẩm không được rỗng.");
    }

    @Test
    void testCreateSanPhamVoiTenChiChuaKhoangTrang() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP022");
        sanPham.setTen("      ");
        sanPham.setTrangThai(true);

        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(sanPham),
                "Tên sản phẩm không được rỗng được chỉ chứa khoảng trắng.");
    }

    @Test
    void testCreateSanPhamVoiTrangThaiNull() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP010");
        sanPham.setTen("Áo khoác");
        sanPham.setTrangThai(null);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(sanPham),
                "Trạng thái sản phẩm không được null.");
    }

    @Test
    void testCreateSanPhamVoiThongTinHopLe() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP011");
        sanPham.setTen("Quần jean");
        sanPham.setTrangThai(true);

        SanPham result = sanPhamService.createSanPham(sanPham);

        assertNotNull(result, "Sản phẩm không được null.");
        assertEquals("SP011", result.getMa(), "Mã sản phẩm không đúng.");
        assertEquals("Quần jean", result.getTen(), "Tên sản phẩm không đúng.");
    }

    @Test
    void testCreateSanPhamVoiMaNull() {
        SanPham sanPham = new SanPham();
        sanPham.setMa(null);
        sanPham.setTen("Giày thể thao");
        sanPham.setTrangThai(true);

        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(sanPham),
                "Mã sản phẩm không được rỗng.");
    }

    @Test
    void testCreateSanPhamVoiMaNullvaTenDeTrong() {
        SanPham sanPham = new SanPham();
        sanPham.setMa(null);
        sanPham.setTen("");
        sanPham.setTrangThai(true);

        assertThrows(IllegalArgumentException.class, () -> sanPhamService.createSanPham(sanPham),
                "Mã sản phẩm không được rỗng.");
    }


    @Test
    void testCreateSanPhamVoiMaTrung() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP001");
        sanPham.setTen("Áo thun mới");
        sanPham.setTrangThai(true);

        assertThrows(SanPhamService.DuplicateProductException.class, () -> sanPhamService.createSanPham(sanPham),
                "Mã sản phẩm đã tồn tại.");
    }

    @Test
    void testCreateSanPhamVoiTenTrung() {
        SanPham sanPham = new SanPham();
        sanPham.setMa("SP015");
        sanPham.setTen("Sản phẩm A");
        sanPham.setTrangThai(true);

        assertThrows(SanPhamService.DuplicateProductException.class, () -> sanPhamService.createSanPham(sanPham),
                "Tên sản phẩm đã tồn tại.");
    }


    // ======================== Hàm updateSanPham ===============

    @Test
    void testUpdateSanPhamDauDanhSach() {
        SanPham sanPham = new SanPham(1, "SP001", "Áo thun", true);
        SanPham updatedSanPham = new SanPham(1, "SP001", "Sản Phẩm A", true);
        SanPham result = sanPhamService.updateSanPham(1, updatedSanPham);
        assertNotNull(result, "Cập nhật sản phẩm không thành công.");
        assertEquals("Sản Phẩm A", result.getTen(), "Tên sản phẩm không được cập nhật.");
    }

    @Test
    void testUpdateSanPhamCuoiDanhSach() {
        SanPham sanPham = new SanPham(7, "SP007", "Sản phẩm G", true);
        SanPham updatedSanPham = new SanPham(7, "SP007", "Áo ba lỗ", true);
        SanPham result = sanPhamService.updateSanPham(7, updatedSanPham);
        assertNotNull(result, "Cập nhật sản phẩm không thành công.");
        assertEquals("Áo ba lỗ", result.getTen(), "Tên sản phẩm không được cập nhật.");
    }



    @Test
    void testUpdateSanPhamVoiTenRong() {
        SanPham updatedSanPham = new SanPham(1, "SP001", "", true);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.updateSanPham(1, updatedSanPham),
                "Tên sản phẩm không được rỗng.");
    }



    @Test
    void testUpdateSanPhamVoiTrangThaiNull() {
        SanPham updatedSanPham = new SanPham(1, "SP001", "Áo thun mới", null);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.updateSanPham(1, updatedSanPham),
                "Trạng thái sản phẩm không được null.");
    }


    @Test
    void testUpdateSanPhamVoiIdAm1() {
        SanPham updatedSanPham = new SanPham(-1, "SP001", "Áo thun mới", true);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.updateSanPham(-1, updatedSanPham),
                "ID sản phẩm không được là số âm.");
    }

    @Test
    void testUpdateSanPhamVoiIdAm9999() {
        SanPham updatedSanPham = new SanPham(-9999, "SP9999", "Áo thun có cổ", true);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.updateSanPham(-9999, updatedSanPham),
                "ID sản phẩm không được là số âm.");
    }


    @Test
    void testUpdateSanPhamVoiIdBang0() {
        SanPham updatedSanPham = new SanPham(0, "SP001", "Áo thun mới", true);
        assertThrows(IllegalArgumentException.class, () -> sanPhamService.updateSanPham(0, updatedSanPham),
                "ID sản phẩm không hợp lệ.");
    }



    @Test
    void testUpdateSanPhamVoiTrangThaiKhongThayDoi() {
        SanPham existingSanPham = sanPhamService.getSanPhamById(1);
        SanPham updatedSanPham = new SanPham(1, "SP001", "Áo thun mới", existingSanPham.getTrangThai());
        SanPham result = sanPhamService.updateSanPham(1, updatedSanPham);
        assertEquals(existingSanPham.getTrangThai(), result.getTrangThai(), "Trạng thái không thay đổi.");
    }

    @Test
    void testUpdateSanPhamVoiThongTinHopLe() {
        SanPham updatedSanPham = new SanPham(5, "SP005", "Áo phao", true);
        SanPham result = sanPhamService.updateSanPham(5, updatedSanPham);
        assertNotNull(result, "Cập nhật sản phẩm không thành công.");
        assertEquals("Áo phao", result.getTen(), "Tên sản phẩm không đúng.");
        assertTrue(result.getTrangThai(), "Trạng thái sản phẩm không đúng.");
    }



    @Test
    void testUpdateSanPhamVoiIdKhongTonTai() {
        SanPham updatedSanPham = new SanPham();
        updatedSanPham.setMa("SP999");
        updatedSanPham.setTen("Áo thun mới");
        updatedSanPham.setTrangThai(true);

        assertThrows(IllegalArgumentException.class, () -> sanPhamService.updateSanPham(9999, updatedSanPham),
                "Sản phẩm không tồn tại với ID: 9999");
    }


}

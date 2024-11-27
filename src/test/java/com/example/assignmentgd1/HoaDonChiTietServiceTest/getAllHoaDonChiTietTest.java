package com.example.assignmentgd1.HoaDonChiTietServiceTest;

import com.example.assignmentgd1.model.HoaDonChiTiet;
import com.example.assignmentgd1.repository.HoaDonChiTietRepository;
import com.example.assignmentgd1.service.HoaDonChiTietService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class getAllHoaDonChiTietTest {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @BeforeEach
    void setUp() {
        hoaDonChiTietRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        hoaDonChiTietRepository.deleteAll();
    }

    @Test
    void testGetAllHoaDonChiTiet_WithData() {
        // Test khi có dữ liệu
        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet();
        hoaDonChiTiet1.setSoLuong(10);
        hoaDonChiTiet1.setDonGia(100.0);
        hoaDonChiTiet1.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet1);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertFalse(hoaDonChiTiets.isEmpty(), "Danh sách không được rỗng khi có dữ liệu");
    }

    @Test
    void testGetAllHoaDonChiTiet_WithoutData() {
        // Test khi không có dữ liệu
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertTrue(hoaDonChiTiets.isEmpty(), "Danh sách phải rỗng khi không có dữ liệu");
    }

    @Test
    void testGetAllHoaDonChiTiet_ValidObject() {
        // Kiểm tra kiểu dữ liệu trả về
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSoLuong(5);
        hoaDonChiTiet.setDonGia(50.0);
        hoaDonChiTiet.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertTrue(hoaDonChiTiets.get(0) instanceof HoaDonChiTiet, "Dữ liệu trả về phải là đối tượng HoaDonChiTiet");
    }

    @Test
    void testGetAllHoaDonChiTiet_WithNullValues() {
        // Kiểm tra dữ liệu có giá trị null
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSoLuong(null);
        hoaDonChiTiet.setDonGia(null);
        hoaDonChiTiet.setTrangThai(null);
        try {
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        } catch (Exception e){
            assertTrue(e instanceof DataIntegrityViolationException);
        }
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(0, hoaDonChiTiets.size());
    }

    @Test
    void testGetAllHoaDonChiTiet_WithBooleanValues() {
        // Kiểm tra trường boolean (trangThai)
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSoLuong(5);
        hoaDonChiTiet.setDonGia(200.0);
        hoaDonChiTiet.setTrangThai(false);
        hoaDonChiTietRepository.save(hoaDonChiTiet);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertFalse(hoaDonChiTiets.get(0).getTrangThai(), "Giá trị của trangThai phải đúng như dữ liệu đã lưu");
    }

    @Test
    void testGetAllHoaDonChiTiet_WithLargeValues() {
        // Kiểm tra với số lượng và đơn giá lớn
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSoLuong(1000);
        hoaDonChiTiet.setDonGia(1000000.0);
        hoaDonChiTiet.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(1000, hoaDonChiTiets.get(0).getSoLuong(), "Số lượng phải đúng");
        assertEquals(1000000.0, hoaDonChiTiets.get(0).getDonGia(), "Đơn giá phải đúng");
    }

    @Test
    void testGetAllHoaDonChiTiet_WithDuplicateData() {
        // Kiểm tra với dữ liệu trùng lặp
        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet();
        hoaDonChiTiet1.setSoLuong(5);
        hoaDonChiTiet1.setDonGia(100.0);
        hoaDonChiTiet1.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet1);

        HoaDonChiTiet hoaDonChiTiet2 = new HoaDonChiTiet();
        hoaDonChiTiet2.setSoLuong(5);
        hoaDonChiTiet2.setDonGia(100.0);
        hoaDonChiTiet2.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet2);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(2, hoaDonChiTiets.size(), "Danh sách phải có 2 bản ghi");
    }

    @Test
    void testGetAllHoaDonChiTiet_WithNegativeQuantity() {
        // Kiểm tra với số lượng âm
        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setSoLuong(-10);
        hoaDonChiTiet.setDonGia(50.0);
        hoaDonChiTiet.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertTrue(hoaDonChiTiets.get(0).getSoLuong() < 0, "Số lượng phải có giá trị âm nếu nhập giá trị âm");
    }

    @Test
    void testGetAllHoaDonChiTiet_NullRepository() {
        // Repository chưa khởi tạo
        hoaDonChiTietRepository = null;

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> hoaDonChiTietService.getAllHoaDonChiTiet(),
                "Expected NullPointerException, nhưng không ném ngoại lệ nào!");

        assertEquals("Cannot invoke \"com.example.assignmentgd1.repository.HoaDonChiTietRepository.findAll()\" because \"this.hoaDonChiTietRepository\" is null",
                exception.getMessage());
    }

    @Test
    void testGetAllHoaDonChiTiet_ReturnsListNotNull() {
        // Không trả về null
        List<HoaDonChiTiet> result = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertNotNull(result);
        assertEquals(0, result.size());
    }



}

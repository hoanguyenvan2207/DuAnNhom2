package com.example.assignmentgd1.HoaDonChiTietServiceTest;

import com.example.assignmentgd1.model.HoaDon;
import com.example.assignmentgd1.model.HoaDonChiTiet;
import com.example.assignmentgd1.model.SanPhamChiTiet;
import com.example.assignmentgd1.repository.HoaDonChiTietRepository;
import com.example.assignmentgd1.repository.HoaDonRepository;
import com.example.assignmentgd1.repository.SanPhamChiTietRepository;
import com.example.assignmentgd1.service.HoaDonChiTietService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class addHoaDonChiTietTest {
    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;

    @BeforeEach
    void setUp() {
        hoaDonChiTietRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        hoaDonChiTietRepository.deleteAll();
    }

    @Test
    void testAddHoaDonChiTiet_ValidData(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(1).get();
        HoaDon hoaDon = hoaDonRepository.findById(1).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(25);
        hoaDonChiTiet.setDonGia(25000.0);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size() + 1);
        assertEquals(25, hoaDonChiTiet.getSoLuong());
        assertEquals(25000.0, hoaDonChiTiet.getDonGia());
        assertEquals(true, hoaDonChiTiet.getTrangThai());

    }

    @Test
    void testAddHoaDonChiTiet_EmptyQuantity(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(1).get();
        HoaDon hoaDon = hoaDonRepository.findById(2).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(null);
        hoaDonChiTiet.setDonGia(25000.0);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Số lượng không được để trống", exception.getMessage());

    }

    @Test
    void testAddHoaDonChiTiet_SoLuongAm(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(2).get();
        HoaDon hoaDon = hoaDonRepository.findById(1).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(-36);
        hoaDonChiTiet.setDonGia(25000.0);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Số lượng không được âm.", exception.getMessage());
    }

    @Test
    void testAddHoaDonChiTiet_SoLuongChuKyTu(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(2).get();
        HoaDon hoaDon = hoaDonRepository.findById(3).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        // Cố tình truyền chuỗi thay vì số
        try {
            // Ép kiểu không hợp lệ (để mô phỏng lỗi nhập dữ liệu sai từ giao diện)
            hoaDonChiTiet.setSoLuong(Integer.valueOf("ba muoi 2"));
            // Số lượng sẽ bị trống nên bắt lỗi trống chứ không bắt lỗi chứa kí tự
        } catch (NumberFormatException e) {
            // Xác nhận rằng lỗi xảy ra trước khi gọi hàm add
            assertEquals("For input string: \"ba muoi 2\"", e.getMessage());
        }
        hoaDonChiTiet.setDonGia(25000.0);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Số lượng không được để trống", exception.getMessage());
    }

    @Test
    void testAddHoaDonChiTiet_EmptyPrice(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(3).get();
        HoaDon hoaDon = hoaDonRepository.findById(1).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(17);
        hoaDonChiTiet.setDonGia(null);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Đơn giá không được để trống", exception.getMessage());

    }

    @Test
    void testAddHoaDonChiTiet_DonGiaAm(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(2).get();
        HoaDon hoaDon = hoaDonRepository.findById(3).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(17);
        hoaDonChiTiet.setDonGia(-24000.0);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Đơn giá không được âm", exception.getMessage());
    }

    @Test
    void testAddHoaDonChiTiet_GiaChuKyTu(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(3).get();
        HoaDon hoaDon = hoaDonRepository.findById(2).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(17);
        // Cố tình truyền chuỗi thay vì số
        try {
            // Ép kiểu không hợp lệ (để mô phỏng lỗi nhập dữ liệu sai từ giao diện)
            hoaDonChiTiet.setDonGia(Double.valueOf("hai tram"));
            // Đơn giá sẽ bị trống nên bắt lỗi trống chứ không bắt lỗi chứa kí tự
        } catch (NumberFormatException e) {
            // Xác nhận rằng lỗi xảy ra trước khi gọi hàm add
            assertEquals("For input string: \"hai tram\"", e.getMessage());
        }
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Đơn giá không được để trống", exception.getMessage());
    }

    @Test
    void testAddHoaDonChiTiet_NullTrangThai(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(1).get();
        HoaDon hoaDon = hoaDonRepository.findById(2).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(17);
        hoaDonChiTiet.setDonGia(12000.0);
        hoaDonChiTiet.setTrangThai(null);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Trạng thái không được để trống", exception.getMessage());
    }

    @Test
    void testAddHoaDonChiTiet_NullHoaDon(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(4).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(null);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(17);
        hoaDonChiTiet.setDonGia(12000.0);
        hoaDonChiTiet.setTrangThai(false);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Hoá đơn không được để trống", exception.getMessage());
    }

    @Test
    void testAddHoaDonChiTiet_NullSPCT(){
        HoaDon hoaDon = hoaDonRepository.findById(3).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(null);
        hoaDonChiTiet.setSoLuong(17);
        hoaDonChiTiet.setDonGia(12000.0);
        hoaDonChiTiet.setTrangThai(false);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        });
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size());
        assertEquals("Sản phẩm chi tiết không được để trống", exception.getMessage());
    }
}

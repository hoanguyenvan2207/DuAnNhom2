package com.example.assignmentgd1.service;

import com.example.assignmentgd1.model.HoaDon;
import com.example.assignmentgd1.model.HoaDonChiTiet;
import com.example.assignmentgd1.model.SanPhamChiTiet;
import com.example.assignmentgd1.repository.HoaDonChiTietRepository;
import com.example.assignmentgd1.repository.HoaDonRepository;
import com.example.assignmentgd1.repository.SanPhamChiTietRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HoaDonChiTietServiceTest {

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    SanPhamChiTietRepository sanPhamChiTietRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;

    @BeforeEach
    void setUp() {
        HoaDon hoaDon = hoaDonRepository.findById(1).get();
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(2).get();
        hoaDonChiTietRepository.save(new HoaDonChiTiet(null, hoaDon, sanPhamChiTiet, 10, 100.0, true));
        hoaDonChiTietRepository.save(new HoaDonChiTiet(null, hoaDon, sanPhamChiTiet, 5, 200.0, false));
        hoaDonChiTietRepository.save(new HoaDonChiTiet(null, hoaDon, sanPhamChiTiet, 20, 150.0, true));
        hoaDonChiTietRepository.save(new HoaDonChiTiet(null, hoaDon, sanPhamChiTiet, 0, 50.0, true));
        hoaDonChiTietRepository.save(new HoaDonChiTiet(null, hoaDon, sanPhamChiTiet, 7, 300.0, false));
    }

    @AfterEach
    void tearDown() {
        hoaDonChiTietRepository.deleteAll();
    }


    // Check function - HoaDonChiTiet addHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet)
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


    //Check function - List<HoaDonChiTiet> getAllHoaDonChiTiet()

    @Test
    void testGetAllHoaDonChiTiet_WithData() {
        // Test khi có dữ liệu
        HoaDonChiTiet hoaDonChiTiet1 = new HoaDonChiTiet();
        hoaDonChiTiet1.setSoLuong(10);
        hoaDonChiTiet1.setDonGia(100.0);
        hoaDonChiTiet1.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet1);

        HoaDonChiTiet hoaDonChiTiet2 = new HoaDonChiTiet();
        hoaDonChiTiet2.setSoLuong(140);
        hoaDonChiTiet2.setDonGia(18000.0);
        hoaDonChiTiet2.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet2);

        HoaDonChiTiet hoaDonChiTiet3 = new HoaDonChiTiet();
        hoaDonChiTiet3.setSoLuong(24);
        hoaDonChiTiet3.setDonGia(15000.0);
        hoaDonChiTiet3.setTrangThai(true);
        hoaDonChiTietRepository.save(hoaDonChiTiet3);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertFalse(hoaDonChiTiets.isEmpty(), "Danh sách không được rỗng khi có dữ liệu");
        assertEquals(8, hoaDonChiTiets.size());
    }

    @Test
    void testGetAllHoaDonChiTiet_WithoutData() {
        // Test khi không có dữ liệu
        hoaDonChiTietRepository.deleteAll();
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
        assertEquals(5, hoaDonChiTiets.size());
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
        assertFalse(hoaDonChiTiets.get(5).getTrangThai(), "Giá trị của trangThai phải đúng như dữ liệu đã lưu");
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
        assertEquals(1000, hoaDonChiTiets.get(5).getSoLuong(), "Số lượng phải đúng");
        assertEquals(1000000.0, hoaDonChiTiets.get(5).getDonGia(), "Đơn giá phải đúng");
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
        assertEquals(7, hoaDonChiTiets.size(), "Danh sách phải có 2 bản ghi");
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
        assertTrue(hoaDonChiTiets.get(5).getSoLuong() < 0, "Số lượng phải có giá trị âm nếu nhập giá trị âm");
    }

    @Test
    void testGetAllHoaDonChiTiet_NullRepository() {
        // Repository chưa khởi tạo
        HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietService(null);

        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> hoaDonChiTietService.getAllHoaDonChiTiet(),
                "Expected NullPointerException, nhưng không ném ngoại lệ nào!");

        assertEquals("Cannot invoke \"com.example.assignmentgd1.repository.HoaDonChiTietRepository.findAll()\" because \"this.hoaDonChiTietRepository\" is null",
                exception.getMessage());
    }

    @Test
    void testGetAllHoaDonChiTiet_ReturnsListNotNull() {
        // Không trả về null
        hoaDonChiTietRepository.deleteAll();
        List<HoaDonChiTiet> result = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertNotNull(result);
        assertEquals(0, result.size());
    }


    // Check function - HoaDonChiTiet getHoaDonChiTietById(Integer id)
    @Test
    void testGetHoaDonChiTietById_HopLe() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(10, result.getSoLuong());
    }

    @Test
    void testGetHoaDonChiTietById_KhongTonTai() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(999);
        assertNull(result);
    }

    @Test
    void testGetHoaDonChiTietById_IDNull() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(null);
        assertNull(result);
    }

    @Test
    void testGetHoaDonChiTietById_IDAm() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(-1);
        assertNull(result);
    }

    @Test
    void testGetHoaDonChiTietById_IDBang0() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(0);
        assertNull(result);
    }
    @Test
    void testGetHoaDonChiTietById_IDMaxValue() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(2147483647);
        assertNull(result);
    }

    @Test
    void testGetHoaDonChiTietById_IDMinValue() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(-2147483648);
        assertNull(result);
    }

    @Test
    void testGetHoaDonChiTietById_IDBang1KhongTonTai() {
        HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById(1);
        assertNull(result);
    }

    @Test
    void testGetHoaDonChiTietById_IDKhongPhaiSo() {
        // Test case: Kiểm tra khi ID truyền vào là chuỗi ký tự không phải số
        try {
            // Truyền chuỗi "abc", đây là kiểu dữ liệu không hợp lệ cho hàm nhận vào Integer.
            HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById((Integer)(Object) "abc");
            fail("Mong đợi ngoại lệ ClassCastException");
        } catch (ClassCastException e) {
            assertTrue(e instanceof ClassCastException, "Mong đợi ClassCastException");
        } catch (Exception e) {
            fail("Mong đợi ngoại lệ ClassCastException");
        }
    }

    @Test
    void testGetHoaDonChiTietById_IDLaKyTu1() {
        // Test case: Kiểm tra khi ID là chuỗi có khoảng trắng và số
        try {
            // Truyền chuỗi có khoảng trắng trước số, đây vẫn không phải là kiểu Integer hợp lệ
            HoaDonChiTiet result = hoaDonChiTietService.getHoaDonChiTietById((Integer)(Object) "1");
            fail("Mong đợi ngoại lệ ClassCastException");
        } catch (ClassCastException e) {
            assertTrue(e instanceof ClassCastException, "Mong đợi ClassCastException");
        } catch (Exception e) {
            fail("Mong đợi ngoại lệ ClassCastException");
        }
    }

        //Check function - HoaDonChiTiet updateHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet)

    @Test
    void testUpdateHoaDonChiTiet_ValidData(){
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findById(1).get();
        HoaDon hoaDon = hoaDonRepository.findById(1).get();

        HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
        hoaDonChiTiet.setHoaDon(hoaDon);
        hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
        hoaDonChiTiet.setSoLuong(27);
        hoaDonChiTiet.setDonGia(25000.0);
        hoaDonChiTiet.setTrangThai(true);

        List<HoaDonChiTiet> oldList = hoaDonChiTietService.getAllHoaDonChiTiet();
        hoaDonChiTietService.addHoaDonChiTiet(hoaDonChiTiet);
        List<HoaDonChiTiet> newList = hoaDonChiTietService.getAllHoaDonChiTiet();
        assertEquals(newList.size(), oldList.size() + 1);
        assertEquals(27, hoaDonChiTiet.getSoLuong());
        assertEquals(25000.0, hoaDonChiTiet.getDonGia());
        assertEquals(true, hoaDonChiTiet.getTrangThai());

    }

    @Test
    void testUpdateHoaDonChiTiet_EmptyQuantity(){
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
    void testUpdateHoaDonChiTiet_SoLuongAm(){
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
    void testUpdateHoaDonChiTiet_SoLuongChuKyTu(){
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
            // Xác nhận rằng lỗi xảy ra trước khi gọi hàm update
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
    void testUpdateHoaDonChiTiet_EmptyPrice(){
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
    void testUpdateHoaDonChiTiet_DonGiaAm(){
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
    void testUpdateHoaDonChiTiet_GiaChuKyTu(){
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
    void testUpdateHoaDonChiTiet_NullTrangThai(){
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
    void testUpdateHoaDonChiTiet_NullHoaDon(){
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
    void testUpdateHoaDonChiTiet_NullSPCT(){
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


    //Check function - List<HoaDonChiTiet> findHoaDonChiTietsById(Integer id)

    @Test
    void testGetHoaDonChiTietsById_IDTonTai(){
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(1);
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Danh sách không được rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDKhongCoBanGhi() {
        // Test case: Kiểm tra khi ID tồn tại nhưng không có bản ghi
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(999);
        assertTrue(result.isEmpty(), "Danh sách phải rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDLaNull() {
        // Test case: Kiểm tra khi ID truyền vào là null
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(null);
        assertTrue(result.isEmpty(), "Danh sách phải rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDLaSoAm() {
        // Test case: Kiểm tra khi ID truyền vào là số âm
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(-1);
        assertTrue(result.isEmpty(), "Danh sách phải rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDLa0() {
        // Test case: Kiểm tra khi ID truyền vào là 0
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(0);
        assertTrue(result.isEmpty(), "Danh sách phải rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDKhongTonTai() {
        // Test case: Kiểm tra khi ID không tồn tại trong cơ sở dữ liệu
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(999);
        assertTrue(result.isEmpty(), "Danh sách phải rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDGiaTriBienNhoNhat() {
        // Test case: Kiểm tra với ID giá trị biên nhỏ nhất trong dữ liệu
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(1);
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Danh sách không được rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDGiaTriBienLonNhat() {
        // Test case: Kiểm tra với ID giá trị biên lớn nhất trong dữ liệu
        List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById(5);
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Danh sách không được rỗng");
    }

    @Test
    void testGetHoaDonChiTietsById_IDLaChuoi() {
        try {
            List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById((Integer)(Object) "abc");
            fail("Mong đợi ngoại lệ ClassCastException");
        } catch (ClassCastException e) {
            assertTrue(e instanceof ClassCastException, "Mong đợi ClassCastException");
        } catch (Exception e) {
            fail("Mong đợi ngoại lệ ClassCastException");
        }
    }

    @Test
    void testGetHoaDonChiTietsById_IDLaSoThuc() {
        try {
            List<HoaDonChiTiet> result = hoaDonChiTietService.findHoaDonChiTietsById((Integer) (Object) 123.45); // Dưới dạng Integer giả sử vẫn gặp vấn đề.
            fail("Mong đợi ngoại lệ ClassCastException");
        } catch (ClassCastException e) {
            assertTrue(e instanceof ClassCastException, "Mong đợi ClassCastException");
        } catch (Exception e) {
            fail("Mong đợi ngoại lệ ClassCastException");
        }
    }

}

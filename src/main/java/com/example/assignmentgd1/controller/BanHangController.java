
package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.*;
import com.example.assignmentgd1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ban-hang")
@SessionAttributes("cartItems")
public class BanHangController {

    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private KhachHangService khachHangService;

    private List<CartItem> cartItems = new ArrayList<>();

    public Integer getTongSoLuong() {
        return cartItems.stream().mapToInt(CartItem::getSoLuong).sum();
    }
    private Integer idUpdate = 0;
    public Double getTongTien(){
        return cartItems.stream()
                .mapToDouble(item -> item.getSoLuong() * item.getDonGia())
                .sum();
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String ngayHienTai = LocalDate.now().format(formatter);

    @GetMapping("/hien-thi")
    public String showDs(Model model,
                         @RequestParam(value = "idHoaDon", required = false) Integer idHoaDon) {
        List<HoaDonChiTiet> dsHoaDonChiTiet;
        if (idHoaDon != null) {
            dsHoaDonChiTiet = hoaDonChiTietService.getHoaDonChiTietByHoaDonId(idHoaDon);
        } else {
            dsHoaDonChiTiet = hoaDonChiTietService.getAllHoaDonChiTiet();
        }

        dsHoaDonChiTiet.sort(Comparator.comparing(hdct -> hdct.getHoaDon().getId()));

        Map<HoaDon, List<HoaDonChiTiet>> groupedData = dsHoaDonChiTiet.stream()
                .collect(Collectors.groupingBy(hdct -> hdct.getHoaDon()));

        List<Map.Entry<HoaDon, List<HoaDonChiTiet>>> sortedEntries = new ArrayList<>(groupedData.entrySet());
        sortedEntries.sort(Comparator.comparing(entry -> entry.getKey().getId()));

        Map<HoaDon, List<HoaDonChiTiet>> sortedGroupedData = new LinkedHashMap<>();
        for (Map.Entry<HoaDon, List<HoaDonChiTiet>> entry : sortedEntries) {
            sortedGroupedData.put(entry.getKey(), entry.getValue());
        }

        model.addAttribute("groupedData", sortedGroupedData);
        return "ban-hang/list";
    }

    @GetMapping("/detail")
    public String chiTiet(Model model, @RequestParam("id") Integer id) {

        HoaDon hoaDon = hoaDonService.getHoaDonById(id);

        List<HoaDonChiTiet> hdctList = hoaDonChiTietService.getHoaDonChiTietByHoaDonId(id);

        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("hdctList", hdctList);

        return "ban-hang/detail";
    }


    @GetMapping("/add")
    public String themMoiHoaDon(Model model, @RequestParam(value = "idSearch", required = false) Integer idSearch) {
        if (idSearch != null) {
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getListSanPhamChiTietTimKiem(idSearch));
        } else {
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getAllSanPhamChiTiet());
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("idSanPhamChiTiet", "Tổng mặt hàng: " + cartItems.size() + " mặt hàng");
        model.addAttribute("soLuongForm", "Tổng số lượng: " + getTongSoLuong() + " sản phẩm!");
        model.addAttribute("tongTien", "Tổng tiền: " + getTongTien() + " VND.");
        model.addAttribute("ngayHienTai", ngayHienTai);
        model.addAttribute("nhanViens", nhanVienService.getAllNhanVien());
        model.addAttribute("khachHangs", khachHangService.getAllKhachHang());
        return "ban-hang/add";
    }

    @PostMapping("/add-item")
    public String addItemToCart(@RequestParam("sanPhamChiTietId") Integer sanPhamChiTietId,
                                @RequestParam("soLuong") Integer soLuong, Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(sanPhamChiTietId);

        if (soLuong > sanPhamChiTiet.getSoLuong()) {
            model.addAttribute("error", "Số lượng không đủ.");
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getAllSanPhamChiTiet());
            return "ban-hang/add";
        }

        sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - soLuong);
        sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);


        boolean productExistsInCart = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getSanPhamChiTietId().equals(sanPhamChiTietId)) {
                cartItem.setSoLuong(cartItem.getSoLuong() + soLuong);
                productExistsInCart = true;
                break;
            }
        }

        if (!productExistsInCart) {
            CartItem cartItem = new CartItem(sanPhamChiTietId, sanPhamChiTiet.getSanPham().getTen(),
                    sanPhamChiTiet.getMauSac().getTen(), sanPhamChiTiet.getKichThuoc().getTen(),
                    soLuong, sanPhamChiTiet.getDonGia());
            cartItems.add(cartItem);
        }

        return "redirect:/ban-hang/add";
    }


    @PostMapping("/add-items")
    public String addItemToCarts(@RequestParam("sanPhamChiTietId") Integer sanPhamChiTietId,
                                 @RequestParam("soLuong") Integer soLuong,
                                 @RequestParam("hoaDonId") Integer hoaDonId,
                                 Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(sanPhamChiTietId);

        if (soLuong > sanPhamChiTiet.getSoLuong()) {
            model.addAttribute("error", "Số lượng không đủ.");
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getAllSanPhamChiTiet());
            return "ban-hang/update/" + hoaDonId;
        }

        sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - soLuong);
        sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);

        boolean itemExistsInCart = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getSanPhamChiTietId().equals(sanPhamChiTietId)) {
                cartItem.setSoLuong(cartItem.getSoLuong() + soLuong);
                itemExistsInCart = true;

                HoaDon hoaDon = hoaDonService.getHoaDonById(hoaDonId);
                if (hoaDon != null) {
                    HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietService.findSingleBySanPhamChiTietIdAndHoaDonId(sanPhamChiTietId, hoaDonId);
                    if (hoaDonChiTiet != null) {
                        hoaDonChiTiet.setSoLuong(hoaDonChiTiet.getSoLuong() + soLuong);
                        hoaDonChiTietService.saveHoaDonChiTiet(hoaDonChiTiet);
                    }
                }
                break;
            }
        }

        if (!itemExistsInCart) {
            CartItem cartItem = new CartItem(sanPhamChiTietId, sanPhamChiTiet.getSanPham().getTen(),
                    sanPhamChiTiet.getMauSac().getTen(), sanPhamChiTiet.getKichThuoc().getTen(),
                    soLuong, sanPhamChiTiet.getDonGia());
            cartItems.add(cartItem);

            HoaDon hoaDon = hoaDonService.getHoaDonById(hoaDonId);
            if (hoaDon != null) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setHoaDon(hoaDon);
                hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
                hoaDonChiTiet.setSoLuong(soLuong);
                hoaDonChiTiet.setDonGia(sanPhamChiTiet.getDonGia());
                hoaDonChiTietService.saveHoaDonChiTiet(hoaDonChiTiet);
            }
        }

        return "redirect:/ban-hang/update/" + hoaDonId;
    }



    @PostMapping("/update-item")
    public String updateItemInCart(@RequestParam("sanPhamChiTietId") Integer id,
                                   @RequestParam("soLuongGio") Integer soLuong) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getSanPhamChiTietId().equals(id)) {
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(id);
                sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + cartItem.getSoLuong() - soLuong);
                sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);
                cartItem.setSoLuong(soLuong);
                break;
            }
        }
        return "redirect:/ban-hang/add";
    }

    @PostMapping("/update-items")
    public String updateItemInCarts(@RequestParam("idSpct") Integer idSpct,
                                    @RequestParam("slGio") Integer soLuong) {
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getSanPhamChiTietId().equals(idSpct)) {

                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(idSpct);

                int currentQuantity = sanPhamChiTiet.getSoLuong();
                sanPhamChiTiet.setSoLuong(currentQuantity + cartItem.getSoLuong() - soLuong);
                sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);

                cartItem.setSoLuong(soLuong);

                List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.findBySanPhamChiTietIdAndHoaDonId(cartItem.getSanPhamChiTietId(), idUpdate);
                if (!hoaDonChiTiets.isEmpty()) {
                    for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                        hoaDonChiTiet.setSoLuong(soLuong);
                        hoaDonChiTietService.saveHoaDonChiTiet(hoaDonChiTiet);
                    }
                }
                break;
            }
        }
        return "redirect:/ban-hang/update/" + idUpdate;
    }

    @GetMapping("/remove")
    public String removeItemFromCart(@RequestParam("id") Integer id) {
        cartItems.removeIf(cartItem -> {
            if (cartItem.getSanPhamChiTietId().equals(id)) {
                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(id);
                sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + cartItem.getSoLuong());
                sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);
                return true;
            }
            return false;
        });
        return "redirect:/ban-hang/add";
    }

    @GetMapping("/removee")
    public String removeItemFromCarts(@RequestParam("id") Integer id,
                                      Model model) {

        if (cartItems.size() <= 1) {

            return "redirect:/ban-hang/update/" + idUpdate + "?error=not_remove!";
        }

        Optional<CartItem> itemToRemove = cartItems.stream()
                .filter(cartItem -> cartItem.getSanPhamChiTietId().equals(id))
                .findFirst();

        if (itemToRemove.isPresent()) {
            CartItem cartItem = itemToRemove.get();

            hoaDonChiTietService.deleteBySanPhamChiTietId(cartItem.getSanPhamChiTietId());

            SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(id);
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() + cartItem.getSoLuong());
            sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);

            cartItems.remove(cartItem);
        }

        return "redirect:/ban-hang/update/" + idUpdate;
    }


    @PostMapping("/add")
    public String addHoaDonFromCart(@ModelAttribute HoaDon hoaDon,
                                    @RequestParam("thanh_toan") Boolean thanhToan,
                                    Model model) {

        boolean hasError = false;

        if (cartItems.isEmpty()) {
            model.addAttribute("error", "Hãy chọn sản phẩm!");
            hasError = true;
        }

        if (hoaDon.getKhachHang() == null || hoaDon.getKhachHang().getId() == null) {
            model.addAttribute("errorkhachHang", "Hãy chọn khách hàng!");
            hasError = true;
        }

        if (hoaDon.getNhanVien() == null || hoaDon.getNhanVien().getId() == null) {
            model.addAttribute("errornhanVien", "Hãy chọn nhân viên!");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("idSanPhamChiTiet", "Tổng mặt hàng: " + cartItems.size() + " mặt hàng");
            model.addAttribute("soLuongForm", "Tổng số lượng: " + getTongSoLuong() + " sản phẩm!");
            model.addAttribute("tongTien", "Tổng tiền: " + getTongTien() + " VND.");
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getAllSanPhamChiTiet());
            model.addAttribute("ngayHienTai", ngayHienTai);
            model.addAttribute("nhanViens", nhanVienService.getAllNhanVien());
            model.addAttribute("khachHangs", khachHangService.getAllKhachHang());
            return "ban-hang/add";
        }

        hoaDon.setNgayMuaHang(LocalDate.now());
        hoaDon.setTrangThai(thanhToan);
        hoaDonService.saveHoaDon(hoaDon);

        for (CartItem cartItem : cartItems) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTietService.getSanPhamChiTietById(cartItem.getSanPhamChiTietId()));
            hoaDonChiTiet.setSoLuong(cartItem.getSoLuong());
            hoaDonChiTiet.setDonGia(cartItem.getDonGia());
            hoaDonChiTiet.setTrangThai(thanhToan);

            hoaDonChiTietService.saveHoaDonChiTiet(hoaDonChiTiet);
        }

        cartItems.clear();
        return "redirect:/ban-hang/hien-thi";
    }

    @GetMapping("/update/{id}")
    public String updateHoaDonForm(@PathVariable("id") Integer id,
                                   @RequestParam(value = "idSearch", required = false) Integer idSearch,
                                   Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonById(id);

        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getHoaDonChiTietByHoaDonId(id);

        cartItems.clear();

        for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
            CartItem cartItem = new CartItem(
                    hoaDonChiTiet.getSanPhamChiTiet().getId(),
                    hoaDonChiTiet.getSanPhamChiTiet().getSanPham().getTen(),
                    hoaDonChiTiet.getSanPhamChiTiet().getMauSac().getTen(),
                    hoaDonChiTiet.getSanPhamChiTiet().getKichThuoc().getTen(),
                    hoaDonChiTiet.getSoLuong(),
                    hoaDonChiTiet.getDonGia()
            );
            cartItems.add(cartItem);
        }

        if (idSearch != null) {
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getListSanPhamChiTietTimKiem(idSearch));
        } else {
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getAllSanPhamChiTiet());
        }

        model.addAttribute("hoaDon", hoaDon);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("idSanPhamChiTiet", "Tổng mặt hàng: " + cartItems.size() + " mặt hàng");
        model.addAttribute("soLuongForm", "Tổng số lượng: " + getTongSoLuong() + " sản phẩm!");
        model.addAttribute("tongTien", "Tổng tiền: " + getTongTien() + " VND.");
        model.addAttribute("ngayHienTai", ngayHienTai);
        model.addAttribute("nhanViens", nhanVienService.getAllNhanVien());
        model.addAttribute("khachHangs", khachHangService.getAllKhachHang());
        idUpdate = id;
        return "ban-hang/update";
    }
    @ModelAttribute("cartItems")
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    @GetMapping("/cancel-update")
    public String cancelUpdate(Model model) {
        cartItems.clear();  // Xóa giỏ hàng
        return "redirect:/ban-hang/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String updateHoaDonFromCart(@ModelAttribute HoaDon hoaDon,
                                       @PathVariable("id") Integer idHoaDon,
                                       Model model) {

        boolean hasError = false;

        if (cartItems.isEmpty()) {
            model.addAttribute("error", "Hãy chọn sản phẩm!");
            hasError = true;
        }

        if (hoaDon.getKhachHang() == null || hoaDon.getKhachHang().getId() == null) {
            model.addAttribute("errorkhachHang", "Hãy chọn khách hàng!");
            hasError = true;
        }

        if (hoaDon.getNhanVien() == null || hoaDon.getNhanVien().getId() == null) {
            model.addAttribute("errornhanVien", "Hãy chọn nhân viên!");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("idSanPhamChiTiet", "Tổng mặt hàng: " + cartItems.size() + " mặt hàng");
            model.addAttribute("soLuongForm", "Tổng số lượng: " + getTongSoLuong() + " sản phẩm!");
            model.addAttribute("sanPhamChiTiets", sanPhamChiTietService.getAllSanPhamChiTiet());
            model.addAttribute("ngayHienTai", LocalDate.now());
            model.addAttribute("nhanViens", nhanVienService.getAllNhanVien());
            model.addAttribute("khachHangs", khachHangService.getAllKhachHang());
            return "ban-hang/update";
        }

        hoaDonService.updateHoaDon(idHoaDon, hoaDon);

        for (CartItem cartItem : cartItems) {
            List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.findAllBySanPhamChiTietIdAndHoaDonId(cartItem.getSanPhamChiTietId(), hoaDon.getId());

            if (!hoaDonChiTiets.isEmpty()) {
                for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                    hoaDonChiTiet.setSoLuong(cartItem.getSoLuong());
                    hoaDonChiTiet.setDonGia(cartItem.getDonGia());
                    hoaDonChiTiet.setHoaDon(hoaDon);
                    hoaDonChiTietService.saveHoaDonChiTiet(hoaDonChiTiet);
                }
            } else {

                SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(cartItem.getSanPhamChiTietId());
                if (sanPhamChiTiet != null) {

                    HoaDonChiTiet newHoaDonChiTiet = new HoaDonChiTiet();
                    newHoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
                    newHoaDonChiTiet.setHoaDon(hoaDon);
                    newHoaDonChiTiet.setSoLuong(cartItem.getSoLuong());
                    newHoaDonChiTiet.setDonGia(cartItem.getDonGia());
                    hoaDonChiTietService.saveHoaDonChiTiet(newHoaDonChiTiet);
                }
            }
        }
        cartItems.clear();

        return "redirect:/ban-hang/hien-thi";
    }

}

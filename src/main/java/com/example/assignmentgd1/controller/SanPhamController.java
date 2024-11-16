package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.SanPham;
import com.example.assignmentgd1.service.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        List<SanPham> dsSanPham = sanPhamService.getAllSanPham();
        model.addAttribute("dsSanPham", dsSanPham);
        return "san-pham/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<SanPham> dsSanPham = sanPhamService.searchSanPham(keyword);
        model.addAttribute("dsSanPham", dsSanPham);
        model.addAttribute("keyword", keyword); // Để hiển thị lại từ khóa tìm kiếm
        return "san-pham/list"; // Trả về danh sách sản phẩm
    }


    @GetMapping("/detail")
    public String detail(@RequestParam("id") Integer id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        return "san-pham/detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("sanPham", new SanPham());
        return "san-pham/add";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute SanPham sanPham, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "san-pham/add";
        }
        sanPhamService.createSanPham(sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute SanPham sanPham, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "san-pham/update";
        }
        sanPhamService.updateSanPham(id, sanPham);
        return "redirect:/san-pham/hien-thi";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        SanPham sanPham = sanPhamService.getSanPhamById(id);
        model.addAttribute("sanPham", sanPham);
        return "san-pham/update";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sanPhamService.deleteSanPham(id);
        return "redirect:/san-pham/hien-thi";
    }
}

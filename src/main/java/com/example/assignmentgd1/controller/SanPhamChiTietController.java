package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.SanPhamChiTiet;
import com.example.assignmentgd1.service.KichThuocService;
import com.example.assignmentgd1.service.MauSacService;
import com.example.assignmentgd1.service.SanPhamChiTietService;
import com.example.assignmentgd1.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/san-pham-chi-tiet")
public class SanPhamChiTietController {
    @Autowired
    private SanPhamChiTietService sanPhamChiTietService;

    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private KichThuocService kichThuocService;

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        List<SanPhamChiTiet> dsSanPhamChiTiet = sanPhamChiTietService.getAllSanPhamChiTiet();
        model.addAttribute("dsSanPhamChiTiet", dsSanPhamChiTiet);
        return "san-pham-chi-tiet/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<SanPhamChiTiet> dsSanPhamChiTiet = sanPhamChiTietService.searchSanPhamChiTiet(keyword);
        model.addAttribute("dsSanPhamChiTiet", dsSanPhamChiTiet);
        model.addAttribute("keyword", keyword);
        return "san-pham-chi-tiet/list";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(id);
        model.addAttribute("sanPhamChiTiet", sanPhamChiTiet);
        return "san-pham-chi-tiet/detail";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("sanPhamChiTiet", new SanPhamChiTiet());
        model.addAttribute("dsKichThuoc", kichThuocService.getAllKichThuoc());
        model.addAttribute("dsMauSac", mauSacService.getAllMauSac());
        model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
        return "san-pham-chi-tiet/add";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SanPhamChiTiet sanPhamChiTiet) {
        sanPhamChiTietService.saveSanPhamChiTiet(sanPhamChiTiet);
        return "redirect:/san-pham-chi-tiet/hien-thi";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietService.getSanPhamChiTietById(id);
        model.addAttribute("sanPhamChiTiet", sanPhamChiTiet);
        model.addAttribute("dsKichThuoc", kichThuocService.getAllKichThuoc());
        model.addAttribute("dsMauSac", mauSacService.getAllMauSac());
        model.addAttribute("dsSanPham", sanPhamService.getAllSanPham());
        return "san-pham-chi-tiet/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute SanPhamChiTiet sanPhamChiTiet) {
        sanPhamChiTietService.updateSanPhamChiTiet(id, sanPhamChiTiet);
        return "redirect:/san-pham-chi-tiet/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        sanPhamChiTietService.deleteSanPhamChiTiet(id);
        return "redirect:/san-pham-chi-tiet/hien-thi";
    }
}

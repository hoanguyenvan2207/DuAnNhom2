package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.KhachHang;
import com.example.assignmentgd1.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam(value = "idKh", required = false) Integer idKh) {
        if (idKh != null) {
            model.addAttribute("dsKhachHang", khachHangService.getKhachHangsById(idKh));
        } else {
            model.addAttribute("dsKhachHang", khachHangService.getAllKhachHang());
        }
        return "khach-hang/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("khachHang", new KhachHang());
        return "khach-hang/add";
    }

    @PostMapping("/add")
    public String addKhachHang(@Valid @ModelAttribute KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "khach-hang/add";
        }
        khachHangService.saveKhachHang(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Integer id, Model model) {
        KhachHang khachHang = khachHangService.getKhachHangById(id);
        model.addAttribute("khachHang", khachHang);
        return "khach-hang/update";
    }

    @PostMapping("/update")
    public String updateKhachHang(@Valid @ModelAttribute KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "khach-hang/update";
        }
        khachHangService.updateKhachHang(khachHang.getId(), khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/delete")
    public String deleteKhachHang(@RequestParam("id") Integer id) {
        khachHangService.deleteKhachHang(id);
        return "redirect:/khach-hang/hien-thi";
    }
    
    @GetMapping("/detail")
    public String viewDetail(@RequestParam("id") Integer id, Model model) {
        KhachHang khachHang = khachHangService.getKhachHangById(id);
        model.addAttribute("khachHang", khachHang);
        return "khach-hang/detail";
    }
}

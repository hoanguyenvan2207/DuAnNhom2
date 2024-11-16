package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hoa-don-chi-tiet")
public class HoaDonChiTietController {
    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(value = "ma", required = false) Integer idHoaDon) {
        if (idHoaDon != null) {
            model.addAttribute("dsHoaDonChiTiet", hoaDonChiTietService.findHoaDonChiTietsById(idHoaDon));
        } else {
            model.addAttribute("dsHoaDonChiTiet", hoaDonChiTietService.getAllHoaDonChiTiet());
        }
        return "hoa-don-chi-tiet/list";
    }


    @GetMapping("/detail")
    public String chiTiet(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("hoaDonChiTiet", hoaDonChiTietService.getHoaDonChiTietById(id));
        return "hoa-don-chi-tiet/detail";
    }
}

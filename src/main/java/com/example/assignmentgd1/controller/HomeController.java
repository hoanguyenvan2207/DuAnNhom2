package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/index")
    public String showHome(Model model,
                           @RequestParam(value = "idHdct", required = false) Integer idHoaDon) {
        if (idHoaDon != null) {
            model.addAttribute("dsHoaDon", hoaDonChiTietService.getHoaDonChiTietByHoaDonId(idHoaDon));
        } else {
            model.addAttribute("dsHoaDon", hoaDonChiTietService.getAllHoaDonChiTiet());
        }
        return "index";
    }

}

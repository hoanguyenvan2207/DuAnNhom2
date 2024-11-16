package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.HoaDon;
import com.example.assignmentgd1.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    HoaDonService hoaDonService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model,
                          @RequestParam(value = "ma", required = false) Integer idSearch) {
        if (idSearch != null) {
            model.addAttribute("dsHoaDon", hoaDonService.findHoaDonsById(idSearch));
        } else {
            model.addAttribute("dsHoaDon", hoaDonService.getAllHoaDon());
        }
        return "hoa-don/list";
    }

    @GetMapping("/detail")
    public String chiTiet(Model model, @RequestParam("id") Integer id) {
        model.addAttribute("hoaDon", hoaDonService.getHoaDonById(id));
        return "hoa-don/detail";
    }

}

package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.MauSac;
import com.example.assignmentgd1.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mau-sac")
public class MauSacController {
    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("dsMauSac", mauSacService.getAllMauSac());
       return "mau-sac/list";
    }

    @GetMapping("/search")
    public String timKiemMauSac(@RequestParam String ma, Model model) {
        model.addAttribute("dsMauSac", mauSacService.getMauSacsByMa(ma));
        return "mau-sac/list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("mauSac", new MauSac());
        return "mau-sac/add";
    }

    @PostMapping("/add")
    public String addMauSac(@ModelAttribute MauSac mauSac) {
        mauSacService.createMauSac(mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        MauSac mauSac = mauSacService.getMauSacById(id);
        model.addAttribute("mauSac", mauSac);
        return "mau-sac/update";
    }

    @PostMapping("/update/{id}")
    public String updateMauSac(@PathVariable Integer id, @ModelAttribute MauSac mauSac) {
        mauSacService.updateMauSac(id, mauSac);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String deleteMauSac(@PathVariable Integer id) {
        mauSacService.deleteMauSac(id);
        return "redirect:/mau-sac/hien-thi";
    }

    @GetMapping("/detail/{id}")
    public String detailMauSac(@PathVariable Integer id, Model model) {
        MauSac mauSac = mauSacService.getMauSacById(id);
        model.addAttribute("mauSac", mauSac);
        return "mau-sac/detail";
    }
}

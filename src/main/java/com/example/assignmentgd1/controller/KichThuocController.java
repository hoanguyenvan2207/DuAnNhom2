package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.KichThuoc;
import com.example.assignmentgd1.service.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kich-thuoc")
public class KichThuocController {

    @Autowired
    private KichThuocService kichThuocService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("dsKichThuoc", kichThuocService.getAllKichThuoc());
        return "kich-thuoc/list"; // trang hiển thị danh sách
    }

    @GetMapping("/search")
    public String search(@RequestParam("ma") String ma, Model model) {
        model.addAttribute("dsKichThuoc", kichThuocService.findKichThuocsByMa(ma));
        model.addAttribute("ma", ma);
        return "kich-thuoc/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("kichThuoc", new KichThuoc()); // khởi tạo đối tượng mới
        return "kich-thuoc/add"; // trang thêm kích thước
    }

    @PostMapping("/add")
    public String save(@ModelAttribute KichThuoc kichThuoc) {
        kichThuocService.saveKichThuoc(kichThuoc);
        return "redirect:/kich-thuoc/hien-thi"; // quay lại danh sách sau khi thêm
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        KichThuoc kichThuoc = kichThuocService.getKichThuocById(id);
        if (kichThuoc != null) {
            model.addAttribute("kichThuoc", kichThuoc);
            return "kich-thuoc/update"; // trang cập nhật kích thước
        }
        return "redirect:/kich-thuoc/hien-thi"; // nếu không tìm thấy, quay lại danh sách
    }

    @PostMapping("/update/{id}")
    public String updateKichThuoc(@PathVariable Integer id, @ModelAttribute KichThuoc updatedKichThuoc) {
        kichThuocService.updateKichThuoc(id, updatedKichThuoc);
        return "redirect:/kich-thuoc/hien-thi"; // quay lại danh sách sau khi cập nhật
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        kichThuocService.deleteKichThuoc(id);
        return "redirect:/kich-thuoc/hien-thi"; // quay lại danh sách sau khi xóa
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        KichThuoc kichThuoc = kichThuocService.getKichThuocById(id);
        model.addAttribute("kichThuoc", kichThuoc);
        return "kich-thuoc/detail"; // trang chi tiết kích thước
    }
}

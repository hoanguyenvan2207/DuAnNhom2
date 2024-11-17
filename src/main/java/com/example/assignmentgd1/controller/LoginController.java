package com.example.assignmentgd1.controller;

import com.example.assignmentgd1.model.NhanVien;
import com.example.assignmentgd1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @GetMapping("/login")
    public String loginPage() {
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("tenDangNhap") String tenDangNhap,
                        @RequestParam("matKhau") String matKhau,
                        Model model,
                        RedirectAttributes redirectAttributes) {

        NhanVien nhanVien = loginService.authenticate(tenDangNhap, matKhau);

        if (nhanVien != null) {
            redirectAttributes.addFlashAttribute("nhanVien", nhanVien);
            return "redirect:/home/index";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác.");
            return "login/login";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        return "login/login";
    }
}

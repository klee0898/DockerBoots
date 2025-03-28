package com.mbc.day05.controller;

import com.mbc.day05.domain.Admin;
import com.mbc.day05.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    // 로그인 페이지 이동
    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }
    // 로그인 인증 처리
    @PostMapping("/admin/login")
    public String login(@RequestParam String adminId
            , @RequestParam String password, Model model, HttpSession session) {
        Admin admin = adminService.login(adminId, password);
        if (admin != null) { // 로그인 성공
            session.setAttribute("admin", admin); // 세션에 admin객체 저장
            return "redirect:/admin/dashboard";
        }else{
            model.addAttribute("error", "아이디 또는 비밀번호가 틀립니다.");
            return "/admin/login";
        }
    }

    // 관리자 대시보드 이동
    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null){
            return "redirect:/admin/login";
        }
        model.addAttribute("admin", admin);

        return "admin/dashboard";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

}

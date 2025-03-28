package com.mbc.day05.service;

import com.mbc.day05.domain.Admin;
import com.mbc.day05.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin login(String adminId, String password){
        Admin admin = adminMapper.findByAdminId(adminId);
        // 비밀번호 검증되면 admin 리턴
        if(admin != null && passwordEncoder.matches(password, admin.getPassword())){
            return admin;
        }
        // 비밀번호 검증 실패시 null 리턴
        return null;
    }
}

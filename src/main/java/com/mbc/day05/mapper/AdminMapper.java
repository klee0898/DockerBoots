package com.mbc.day05.mapper;

import com.mbc.day05.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    @Select("select * from tbl_admin where admin_id = #{adminId}")
    Admin findByAdminId(String adminId);
}

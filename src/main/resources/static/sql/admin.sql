drop table tbl_admin;
CREATE TABLE tbl_admin (
       admin_id VARCHAR(50) PRIMARY KEY,  -- 관리자 ID (PK)
       password VARCHAR(255) NOT NULL,   -- 비밀번호 (암호화 저장을 고려해 길이 255 설정)
       name VARCHAR(100) NOT NULL        -- 관리자 이름
);

-- 비번 : 1212
INSERT INTO tbl_admin (admin_id, password, name)
VALUES ('admin', '$2b$12$SoB//LEIlBbDGBUhswi24Op96Wbiqf8VPc2cBk6SNnt7WicfDO0rW', 'Super Admin');

select * from tbl_admin;
commit;
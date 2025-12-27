use inventropy;

-- 用户账号表(tb_user_account)
/*
id(key) username(unique) password user_type
*/
CREATE TABLE tb_user_account (
                                 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                                 username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名（唯一）',
                                 password VARCHAR(255) NOT NULL COMMENT '密码',
                                 user_type VARCHAR(20) NOT NULL COMMENT '用户类型（teacher/admin）',
    -- 无时间字段
                                 INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号表';

-- 用户信息表(tb_user_info)
/*
id(foreign(tb_user_account(id)) on delete cascade) name gender age phone(unique) college
*/
CREATE TABLE tb_user_info (
                              id INT PRIMARY KEY COMMENT '主键ID，与tb_user_account.id一致，一对一',
                              name VARCHAR(50) NOT NULL COMMENT '姓名',
                              gender CHAR(1) COMMENT '性别（M/F）',
                              age TINYINT UNSIGNED COMMENT '年龄',
                              phone VARCHAR(20) UNIQUE COMMENT '手机号（唯一）',
    -- 外键约束
                              FOREIGN KEY (id) REFERENCES tb_user_account(id) ON DELETE CASCADE,
                              INDEX idx_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';
-- 项目表(tb_project_info)
/*
id(key) project_name project_type applicant applicant_id(foreign(tb_user_info(id))) funds remaining_funds content start_time deadline update_time status(0-待审核, 1-已驳回, 2-已逾期, 3-进行中, 4-已结项, 5-已废弃) reason
*/
CREATE TABLE tb_project_info (
                                 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '项目ID',
                                 project_name VARCHAR(200) NOT NULL COMMENT '项目名称',
                                 project_type VARCHAR(50) COMMENT '项目类型',
                                 applicant VARCHAR(50) NOT NULL COMMENT '申请人姓名',
                                 applicant_id INT NOT NULL COMMENT '申请人ID（关联tb_user_info.id）',
                                 funds DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '项目总经费（元）',
                                 remaining_funds DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '当前剩余经费（元）',
                                 content TEXT COMMENT '项目内容',
    -- 【关键变更】业务计划时间：使用DATETIME存储，查询时格式化为YYYY-MM-DDTHH:mm:ss
                                 start_time DATETIME COMMENT '计划开始时间，业务日期。查询时需用DATE_FORMAT(start_time, \"%Y-%m-%dT%H:%i:%s\")输出',
                                 deadline DATETIME COMMENT '计划截止日期，业务日期。查询时需用DATE_FORMAT(deadline, \"%Y-%m-%dT%H:%i:%s\")输出',
    -- 系统时间：使用TIMESTAMP，自动记录更新时间
                                 update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统最后更新时间，自动生成',
                                 status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-待审核,1-已驳回,2-已逾期,3-进行中,4-已结项,5-已废弃',
                                 reason TEXT COMMENT '最近一次驳回的详细理由',
    -- 外键约束
                                 FOREIGN KEY (applicant_id) REFERENCES tb_user_info(id),
    -- 索引
                                 INDEX idx_applicant_id (applicant_id),
                                 INDEX idx_status (status),
                                 INDEX idx_deadline (deadline)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目表';
-- 经费记录表(tb_funds_log)
/*
# id(key) project_id(foreign(project_id)) applicant_id(foreign(user_info_id)) applied_funds reason status(0待审核,1已驳回,2已通过) applied_time approver_id(foreign(use_account_id)) comment  update_time
*/
CREATE TABLE tb_funds_log (
                              id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
                              project_id INT NOT NULL COMMENT '关联的项目ID',
                              applicant_id INT NOT NULL COMMENT '申请人ID（关联tb_user_info.id）',
                              applied_funds DECIMAL(12, 2) NOT NULL COMMENT '申请经费金额（元）',
                              reason TEXT COMMENT '申请理由',
                              status TINYINT NOT NULL DEFAULT 0 COMMENT '审批状态：0-待审核,1-已驳回,2-已通过',
    -- 系统时间：申请时间自动生成
                              applied_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间，自动生成',
                              approver_id INT COMMENT '审批人ID（关联tb_user_account.id，管理员）',
                              comment TEXT COMMENT '审批意见',
    -- 系统时间：更新时间自动生成
                              update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间，自动生成',
    -- 外键约束
                              FOREIGN KEY (project_id) REFERENCES tb_project_info(id),
                              FOREIGN KEY (applicant_id) REFERENCES tb_user_info(id),
                              FOREIGN KEY (approver_id) REFERENCES tb_user_account(id),
    -- 索引
                              INDEX idx_project_id (project_id),
                              INDEX idx_applicant_id (applicant_id),
                              INDEX idx_applied_time (applied_time),
                              INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='经费申请与审批记录表';

-- 项目审批记录表(tb_project_log)
/*
id(key) project_id(foreign(project_id))  old_status new_status reason create_time approver_id(foreign(use_account_id))
*/
CREATE TABLE tb_project_log (
                                id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
                                project_id INT NOT NULL COMMENT '关联的项目ID',
                                old_status TINYINT NOT NULL COMMENT '变更前的状态',
                                new_status TINYINT NOT NULL COMMENT '变更后的状态',
                                reason TEXT NOT NULL COMMENT '状态变更的详细理由（如驳回原因）',
    -- 系统时间：创建时间自动生成
                                create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '审批操作时间，自动生成',
                                approver_id INT NOT NULL COMMENT '审批人ID（关联tb_user_account.id，管理员）',
    -- 外键约束
                                FOREIGN KEY (project_id) REFERENCES tb_project_info(id),
                                FOREIGN KEY (approver_id) REFERENCES tb_user_account(id),
    -- 索引
                                INDEX idx_project_id (project_id),
                                INDEX idx_approver_id (approver_id),
                                INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目状态审批流水日志表';

ALTER TABLE tb_user_info ADD COLUMN college VARCHAR(100) NOT NULL ;



DELIMITER //
CREATE PROCEDURE get_project_info_with_status_update()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_project_id INT;
    DECLARE v_old_status INT;
    DECLARE cur CURSOR FOR
        SELECT id, status
        FROM tb_project_info
        WHERE deadline < NOW() AND status != 2;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 打开游标，记录需要更新的项目
    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_project_id, v_old_status;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 更新项目状态
        UPDATE tb_project_info
        SET status = 2
        WHERE id = v_project_id;

        -- 记录日志（在存储过程中直接插入，避免触发器）
        INSERT INTO tb_project_log(
            project_id,
            old_status,
            new_status,
            reason,
            approver_id
        ) VALUES (
                     v_project_id,
                     v_old_status,
                     2,
                     '超时过期',
                     1  -- 系统操作
                 );
    END LOOP;

    CLOSE cur;

    -- 查询数据
    SELECT * FROM tb_project_info;
END //
DELIMITER ;




#初始化
-- 1. 删除已存在的数据库（如果存在）并重新创建
DROP DATABASE IF EXISTS inventropy;
CREATE DATABASE inventropy CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE inventropy;

-- 2. 用户账号表
CREATE TABLE tb_user_account (
                                 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
                                 username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名（唯一）',
                                 password VARCHAR(255) NOT NULL COMMENT '密码',
                                 user_type VARCHAR(20) NOT NULL COMMENT '用户类型（teacher/admin）',
                                 INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账号表';

-- 3. 用户信息表
CREATE TABLE tb_user_info (
                              id INT PRIMARY KEY COMMENT '主键ID，与tb_user_account.id一致，一对一',
                              name VARCHAR(50) NOT NULL COMMENT '姓名',
                              gender CHAR(1) COMMENT '性别（M/F）',
                              age TINYINT UNSIGNED COMMENT '年龄',
                              phone VARCHAR(20) UNIQUE COMMENT '手机号（唯一）',
                              college VARCHAR(100) NOT NULL COMMENT '学院/部门',
                              FOREIGN KEY (id) REFERENCES tb_user_account(id) ON DELETE CASCADE,
                              INDEX idx_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 4. 项目表
CREATE TABLE tb_project_info (
                                 id INT PRIMARY KEY AUTO_INCREMENT COMMENT '项目ID',
                                 project_name VARCHAR(200) NOT NULL COMMENT '项目名称',
                                 project_type VARCHAR(50) COMMENT '项目类型',
                                 applicant VARCHAR(50) NOT NULL COMMENT '申请人姓名',
                                 applicant_id INT NOT NULL COMMENT '申请人ID（关联tb_user_info.id）',
                                 funds DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '项目总经费（元）',
                                 remaining_funds DECIMAL(12, 2) NOT NULL DEFAULT 0.00 COMMENT '当前剩余经费（元）',
                                 content TEXT COMMENT '项目内容',
                                 start_time DATETIME COMMENT '计划开始时间',
                                 deadline DATETIME COMMENT '计划截止日期',
                                 update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统最后更新时间',
                                 status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-待审核,1-已驳回,2-已逾期,3-进行中,4-已结项,5-已废弃',
                                 reason TEXT COMMENT '最近一次驳回的详细理由',
                                 FOREIGN KEY (applicant_id) REFERENCES tb_user_info(id),
                                 INDEX idx_applicant_id (applicant_id),
                                 INDEX idx_status (status),
                                 INDEX idx_deadline (deadline)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目表';

-- 5. 经费记录表
CREATE TABLE tb_funds_log (
                              id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
                              project_id INT NOT NULL COMMENT '关联的项目ID',
                              applicant_id INT NOT NULL COMMENT '申请人ID（关联tb_user_info.id）',
                              applied_funds DECIMAL(12, 2) NOT NULL COMMENT '申请经费金额（元）',
                              reason TEXT COMMENT '申请理由',
                              status TINYINT NOT NULL DEFAULT 0 COMMENT '审批状态：0-待审核,1-已驳回,2-已通过',
                              applied_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间',
                              approver_id INT COMMENT '审批人ID（关联tb_user_account.id，管理员）',
                              comment TEXT COMMENT '审批意见',
                              update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
                              FOREIGN KEY (project_id) REFERENCES tb_project_info(id),
                              FOREIGN KEY (applicant_id) REFERENCES tb_user_info(id),
                              FOREIGN KEY (approver_id) REFERENCES tb_user_account(id),
                              INDEX idx_project_id (project_id),
                              INDEX idx_applicant_id (applicant_id),
                              INDEX idx_applied_time (applied_time),
                              INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='经费申请与审批记录表';

-- 6. 项目审批记录表
CREATE TABLE tb_project_log (
                                id INT PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
                                project_id INT NOT NULL COMMENT '关联的项目ID',
                                old_status TINYINT NOT NULL COMMENT '变更前的状态',
                                new_status TINYINT NOT NULL COMMENT '变更后的状态',
                                reason TEXT NOT NULL COMMENT '状态变更的详细理由',
                                create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '审批操作时间',
                                approver_id INT NOT NULL COMMENT '审批人ID（关联tb_user_account.id，管理员）',
                                FOREIGN KEY (project_id) REFERENCES tb_project_info(id),
                                FOREIGN KEY (approver_id) REFERENCES tb_user_account(id),
                                INDEX idx_project_id (project_id),
                                INDEX idx_approver_id (approver_id),
                                INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目状态审批流水日志表';

-- 7. 创建存储过程
DELIMITER //
CREATE PROCEDURE get_project_info_with_status_update()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_project_id INT;
    DECLARE v_old_status INT;
    DECLARE cur CURSOR FOR
        SELECT id, status
        FROM tb_project_info
        WHERE deadline < NOW() AND status != 2;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 打开游标，记录需要更新的项目
    OPEN cur;

    read_loop: LOOP
        FETCH cur INTO v_project_id, v_old_status;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 更新项目状态
        UPDATE tb_project_info
        SET status = 2
        WHERE id = v_project_id;

        -- 记录日志（在存储过程中直接插入，避免触发器）
        INSERT INTO tb_project_log(
            project_id,
            old_status,
            new_status,
            reason,
            approver_id
        ) VALUES (
                     v_project_id,
                     v_old_status,
                     2,
                     '超时过期',
                     1  -- 系统操作
                 );
    END LOOP;

    CLOSE cur;

    -- 查询数据
    SELECT * FROM tb_project_info;
END //
DELIMITER ;

-- 8. 插入初始管理员账号（建议在实际使用时修改密码）
-- 默认管理员账号：admin，密码：123456
INSERT INTO tb_user_account (username, password, user_type) VALUES
    ('admin', '123456', 'admin'); -- 实际使用时应替换为BCrypt加密后的密码

INSERT Into tb_user_info(id,name, gender, age, phone,college) values
                                                           (1,'管理员1号','M',18,1339876987,'管理学院');
-- 如果需要初始化测试教师账号
-- INSERT INTO tb_user_account (username, password, user_type) VALUES
-- ('teacher1', '$2a$10$YourHashedPasswordHere', 'teacher');

-- 9. 注意：实际使用时，需要在代码中为用户信息表插入对应的记录
-- 例如：INSERT INTO tb_user_info (id, name, college, ...) VALUES (1, '管理员', '信息学院', ...);

-- 10. 查看表结构（可选）
SHOW TABLES;

-- 11. 查看存储过程（可选）
SHOW PROCEDURE STATUS WHERE Db = 'inventropy';

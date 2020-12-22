package com.kalvin.kvf.modules.zg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kalvin.kvf.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统基本表
 * </p>
 * @since 2020-12-15 17:20:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_system")
public class System extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 系统名称
     */
    private String sysName;

    /**
     * 上线日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate onDate;

    /**
     * 系统功能描述
     */
    private String sysDesc;

    /**
     * 系统状态（0-运行中 1-已下线 2-在建）
     */
    private Integer sysStatus;

    /**
     * 系统负责人
     */
    private Long sysHeadId;
    /**
     * 系统负责人name
     */
    private String sysHeadName;

    /**
     * 运维负责人
     */
    private Long sysOperId;
    /**
     * 运维负责人Name
     */
    private String sysOperName;

    /**
     * 下线日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate outDate;

    /**
     * 系统资料存放地址
     */
    private String dataAddres;

    /**
     * 系统应用架构类型（1-双活 2-冷备 3-单工）
     */
    private Integer appType;

    /**
     * 应用流量控制类型（1-F5 2-单流）
     */
    private Integer flowType;

    /**
     * 系统级别(1-一级 2-二级 3-三级)
     */
    private Integer sysLevel;

    /**
     * 系统灾备类型（0-无 1-同城灾备 2-异地灾备）
     */
    private Integer imSystemType;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 
     */
    private Long createUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

    /**
     * 
     */
    private Long updateUser;

    /**
     * 0-正常 1-删除
     */
    private Integer dr;

}

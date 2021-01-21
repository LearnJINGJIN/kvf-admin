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
 * 外包项目清单
 * </p>
 * @since 2021-01-14 09:59:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_outsource_project")
public class OutsourceProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 项目类别（1-研发类 2-运维类 3-咨询类）
     */
    private Integer proType;

    /**
     * 项目负责id
     */
    private Long headId;
    /**
     * 项目负责人name
     */
    private String headName;

    /**
     * 是否重大项目（0-是 1-否）
     */
    private Integer proImp;

    /**
     * 是否驻场外包（0-是 1-否）
     */
    private Integer isOtcPack;

    /**
     * 是否跨境外包（0-是 1-否）
     */
    private Integer isCrossPack;

    /**
     * 是否关联外包（0-是 1-否）
     */
    private Integer isAssPack;

    /**
     * 外包公司id
     */
    private Long companyId;
    /**
     * 外包公司name
     */
    private String companyName;

    /**
     * 开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    /**
     * 截止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 机构自有人力投入
     */
    private Integer orgHumanImput;

    /**
     * 外包人力投入
     */
    private Integer outHumanImput;

    /**
     * 外包服务内容
     */
    private String content;
    private Integer isJob;

    /**
     * 0-正常 1-已删除
     */
    private Integer dr;

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
    private Integer updateUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}

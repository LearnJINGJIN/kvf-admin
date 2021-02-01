package com.kalvin.kvf.modules.zg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.kalvin.kvf.common.entity.BaseEntity;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 合同管理
 * </p>
 * @since 2021-01-26 10:36:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_outsource_contract")
public class OutsourceContract extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    private Long proId;


    /**
     * 合同编号（当日日期+每日从001开始）
     */
    private String contractNo;

    /**
     * 合同名称
     */
    private String contractName;

    /**
     * 合同类型(1-软硬件采购 2-集成服务 3-等保服务 4-软件开发 5-软件采购 6-硬件采购 7-维保服务 8-外包人员采购 9-其它)
     */
    private Integer contractType;

    /**
     * 合同付款方式（1-按月结算 2-分段结算 3-完工后一次结算 4-按季度结算 5-其它）
     */
    private Integer payTerms;

    /**
     * 外包公司id
     */
    private Long companyId;
    /**
     * 外包公司名称
     */
    private String companyName;

    /**
     * 合同签订日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate signDate;

    /**
     * 合同截止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    /**
     * 合同金额
     */
    private BigDecimal amount;

    /**
     * 甲方联系人
     */
    private Long aPerson;
    /**
     * 甲方联系人名称
     */
    private String personName;

    /**
     * 甲方联系电话
     */
    private String aPhone;

    /**
     * 乙方联系人
     */
    private String bPerson;

    /**
     * 合同名称
     */
    private String fileName;
    /**
     * 合同地址
     */
    private String fileAddrs;
    /**
     * 乙方联系人电话
     */
    private String bPhone;

    /**
     * 已付款金额
     */
    private BigDecimal amountPaid;

    /**
     * 未付款金额
     */
    private BigDecimal outAmount;

    /**
     * 合同是否履行完成
     */
    private Integer isFullContract;

    /**
     * 描述
     */
    private String remarks;

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
    private Long updateUser;

    /**
     * 
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateDate;

}

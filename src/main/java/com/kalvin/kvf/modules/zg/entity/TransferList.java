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

/**
 * <p>
 * 交接清单表
 * </p>
 * @since 2021-03-16 11:41:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("zg_transfer_list")
public class TransferList extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登记编码
     */
    private String registerCode;

    /**
     * 事项类型(0-设备 1-工作任务 2-其它)
     */
    private Integer itemType;

    /**
     * 交接内容
     */
    private String transferContent;

    /**
     * 移交执行情况（0-完成 1-未完成）
     */
    private Integer handoverExec;

    /**
     * 交接人
     */
    private String transferUser;

    /**
     * 交接日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate transferDate;

}

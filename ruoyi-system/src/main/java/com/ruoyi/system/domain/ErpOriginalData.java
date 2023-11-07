package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 原材料信息对象 erp_original_data
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Data
public class ErpOriginalData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String coder;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    /** 供应商 */
    @Excel(name = "供应商")
    private String suppier;

    /** 联系人 */
    @Excel(name = "联系人")
    private String buyer;

    /** 发货地址 */
    @Excel(name = "发货地址")
    private String deliverySite;

    /** 是否特殊（不能复用）  0 no  1yes */
    @Excel(name = "是否特殊", readConverterExp = "不=能复用")
    private Integer special;

    /** 物流运输天数 */
    @Excel(name = "物流运输天数")
    private BigDecimal transport;

    /** 库存预留天数 */
    @Excel(name = "库存预留天数")
    private BigDecimal bufferDay;

    /** $column.columnComment */
    @Excel(name = "raw", readConverterExp = "$column.readConverterExp()")
    private BigDecimal raw;

    /** $column.columnComment */
    @Excel(name = "rds", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rds;

    /** $column.columnComment */
    @Excel(name = "wip", readConverterExp = "$column.readConverterExp()")
    private String wip;

    /** 库存 */
    @Excel(name = "service")
    private BigDecimal service;

    /** 库存 */
    @Excel(name = "库存")
    private BigDecimal total;

    /** 计划者coder */
    @Excel(name = "计划者coder")
    private String plannerCode;

    /** 0非删除 1删除 */
    @Excel(name = "0非删除 1删除")
    private Integer isDelete;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long addBy;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long updateBu;


}

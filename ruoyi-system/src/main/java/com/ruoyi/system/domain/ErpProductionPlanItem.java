package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.system.domain.vo.PlanVo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用于保存 每天生产计划所消耗的原材料数对象 erp_production_plan_item
 * 
 * @author ruoyi
 * @date 2023-10-03
 */
@Data
public class ErpProductionPlanItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 产品线（类别）code */
    @Excel(name = "产品线", readConverterExp = "类=别")
    private String productLineCode;

    /** 产品型号code */
    @Excel(name = "产品型号code")
    private String productModelCode;

    /** 产品个数 */
    @Excel(name = "产品个数")
    private Long count;

    /** 原材料code */
    @Excel(name = "原材料code")
    private String originalCoder;

    /** 用到的个数 */
    @Excel(name = "用到的个数")
    private BigDecimal number;

    /** 用到这个原材料的日期 */
    @Excel(name = "用到这个原材料的日期")
    private Long date;

    /** 0 非删除 1删除 */
    @Excel(name = "0 非删除 1删除")
    private Integer isDelete;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date addTime;



}

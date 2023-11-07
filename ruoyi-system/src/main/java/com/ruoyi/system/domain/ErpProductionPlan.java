package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 生产计划对象 erp_production_plan
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Data
public class ErpProductionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 产品线（类别）id */
    @Excel(name = "产品线", readConverterExp = "类别")
    private String productLineCode;

    /** 产品型号id */
    @Excel(name = "产品型号id")
    private String productModelCode;

    /** 个数 */
    @Excel(name = "个数")
    private Long count;

    /** 出产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 状态 1待生产 2已完成 3延期 */
    @Excel(name = "状态 1待生产 2已完成 3延期")
    private Long status;

    /** 0 非删除 1删除 */
    @Excel(name = "0 非删除 1删除")
    private Integer isDelete;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long addBy;

}

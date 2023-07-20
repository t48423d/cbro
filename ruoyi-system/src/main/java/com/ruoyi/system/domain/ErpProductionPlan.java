package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ErpProductionPlan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 产品线（类别）id */
    @Excel(name = "产品线", readConverterExp = "类=别")
    private Long productLineId;

    /** 产品型号id */
    @Excel(name = "产品型号id")
    private Long productModelId;

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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductLineId(Long productLineId) 
    {
        this.productLineId = productLineId;
    }

    public Long getProductLineId() 
    {
        return productLineId;
    }
    public void setProductModelId(Long productModelId) 
    {
        this.productModelId = productModelId;
    }

    public Long getProductModelId() 
    {
        return productModelId;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setAddBy(Long addBy) 
    {
        this.addBy = addBy;
    }

    public Long getAddBy() 
    {
        return addBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productLineId", getProductLineId())
            .append("productModelId", getProductModelId())
            .append("count", getCount())
            .append("date", getDate())
            .append("status", getStatus())
            .append("isDelete", getIsDelete())
            .append("addTime", getAddTime())
            .append("addBy", getAddBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}

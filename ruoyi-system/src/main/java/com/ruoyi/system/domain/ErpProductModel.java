package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 产品型号对象 erp_product_model
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public class ErpProductModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long id;

    /** model编号 */
    @Excel(name = "model编号")
    private String coder;

    /** 生产线id */
    @Excel(name = "生产线id")
    private Long productLineId;

    /** 型号名称（没有可以不填 */
    @Excel(name = "型号名称", readConverterExp = "型号名称（没有可以不填")
    private String name;

    /** 0 非删除 1删除 */
    @Excel(name = "0 非删除 1删除")
    private Integer isDelete;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long addBy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCoder(String coder) 
    {
        this.coder = coder;
    }

    public String getCoder() 
    {
        return coder;
    }
    public void setProductLineId(Long productLineId) 
    {
        this.productLineId = productLineId;
    }

    public Long getProductLineId() 
    {
        return productLineId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
            .append("coder", getCoder())
            .append("productLineId", getProductLineId())
            .append("name", getName())
            .append("isDelete", getIsDelete())
            .append("addTime", getAddTime())
            .append("addBy", getAddBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}

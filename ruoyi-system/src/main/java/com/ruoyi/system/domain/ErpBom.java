package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品由 具体原件组成关联对象 erp_bom
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public class ErpBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 具体型号商品 id */
    @Excel(name = "具体型号商品 id")
    private Long productModelId;

    /** 商品编号 冗余字段 */
    @Excel(name = "商品编号 冗余字段")
    private String productModelCoder;

    /** 原材料id */
    @Excel(name = "原材料id")
    private Long originalId;

    /** 原材料 coder 冗余字段 */
    @Excel(name = "原材料 coder 冗余字段")
    private String originalCoder;

    /** 用到的个数 */
    @Excel(name = "用到的个数")
    private BigDecimal number;

    /** 0 非删除 1删除 */
    @Excel(name = "0 非删除 1删除")
    private Integer isDelete;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date addTime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long addBy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductModelId(Long productModelId) 
    {
        this.productModelId = productModelId;
    }

    public Long getProductModelId() 
    {
        return productModelId;
    }
    public void setProductModelCoder(String productModelCoder) 
    {
        this.productModelCoder = productModelCoder;
    }

    public String getProductModelCoder() 
    {
        return productModelCoder;
    }
    public void setOriginalId(Long originalId) 
    {
        this.originalId = originalId;
    }

    public Long getOriginalId() 
    {
        return originalId;
    }
    public void setOriginalCoder(String originalCoder) 
    {
        this.originalCoder = originalCoder;
    }

    public String getOriginalCoder() 
    {
        return originalCoder;
    }
    public void setNumber(BigDecimal number) 
    {
        this.number = number;
    }

    public BigDecimal getNumber() 
    {
        return number;
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
            .append("productModelId", getProductModelId())
            .append("productModelCoder", getProductModelCoder())
            .append("originalId", getOriginalId())
            .append("originalCoder", getOriginalCoder())
            .append("number", getNumber())
            .append("isDelete", getIsDelete())
            .append("addTime", getAddTime())
            .append("addBy", getAddBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}

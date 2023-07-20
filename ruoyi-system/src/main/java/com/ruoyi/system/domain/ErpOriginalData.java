package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long transport;

    /** 库存预留天数 */
    @Excel(name = "库存预留天数")
    private Long bufferDay;

    /** $column.columnComment */
    @Excel(name = "raw", readConverterExp = "$column.readConverterExp()")
    private BigDecimal raw;

    /** $column.columnComment */
    @Excel(name = "rds", readConverterExp = "$column.readConverterExp()")
    private Long rds;

    /** $column.columnComment */
    @Excel(name = "wip", readConverterExp = "$column.readConverterExp()")
    private String wip;

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
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }
    public void setSuppier(String suppier) 
    {
        this.suppier = suppier;
    }

    public String getSuppier() 
    {
        return suppier;
    }
    public void setBuyer(String buyer) 
    {
        this.buyer = buyer;
    }

    public String getBuyer() 
    {
        return buyer;
    }
    public void setDeliverySite(String deliverySite) 
    {
        this.deliverySite = deliverySite;
    }

    public String getDeliverySite() 
    {
        return deliverySite;
    }
    public void setSpecial(Integer special) 
    {
        this.special = special;
    }

    public Integer getSpecial() 
    {
        return special;
    }
    public void setTransport(Long transport) 
    {
        this.transport = transport;
    }

    public Long getTransport() 
    {
        return transport;
    }
    public void setBufferDay(Long bufferDay) 
    {
        this.bufferDay = bufferDay;
    }

    public Long getBufferDay() 
    {
        return bufferDay;
    }
    public void setRaw(BigDecimal raw) 
    {
        this.raw = raw;
    }

    public BigDecimal getRaw() 
    {
        return raw;
    }
    public void setRds(Long rds) 
    {
        this.rds = rds;
    }

    public Long getRds() 
    {
        return rds;
    }
    public void setWip(String wip) 
    {
        this.wip = wip;
    }

    public String getWip() 
    {
        return wip;
    }
    public void setTotal(BigDecimal total) 
    {
        this.total = total;
    }

    public BigDecimal getTotal() 
    {
        return total;
    }
    public void setPlannerCode(String plannerCode) 
    {
        this.plannerCode = plannerCode;
    }

    public String getPlannerCode() 
    {
        return plannerCode;
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
    public void setUpdateBu(Long updateBu) 
    {
        this.updateBu = updateBu;
    }

    public Long getUpdateBu() 
    {
        return updateBu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("coder", getCoder())
            .append("remark", getRemark())
            .append("desc", getDesc())
            .append("suppier", getSuppier())
            .append("buyer", getBuyer())
            .append("deliverySite", getDeliverySite())
            .append("special", getSpecial())
            .append("transport", getTransport())
            .append("bufferDay", getBufferDay())
            .append("raw", getRaw())
            .append("rds", getRds())
            .append("wip", getWip())
            .append("total", getTotal())
            .append("plannerCode", getPlannerCode())
            .append("isDelete", getIsDelete())
            .append("addTime", getAddTime())
            .append("addBy", getAddBy())
            .append("updateTime", getUpdateTime())
            .append("updateBu", getUpdateBu())
            .toString();
    }
}

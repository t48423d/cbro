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
 * 商品由 具体原件组成关联对象 erp_bom
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Data
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

    private Integer version;

    private Integer isDefault;

}

package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ProductLineDTO;

import java.util.List;

/**
 * 产品线 （产品大类）Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-03
 */
public interface ProductLineMapper 
{
    /**
     * 查询产品线 （产品大类）
     * 
     * @param id 产品线 （产品大类）主键
     * @return 产品线 （产品大类）
     */
    public ProductLineDTO selectProductLineById(Long id);

    /**
     * 查询产品线 （产品大类）列表
     * 
     * @param productLine 产品线 （产品大类）
     * @return 产品线 （产品大类）集合
     */
    public List<ProductLineDTO> selectProductLineList(ProductLineDTO productLine);

    /**
     * 新增产品线 （产品大类）
     * 
     * @param productLine 产品线 （产品大类）
     * @return 结果
     */
    public int insertProductLine(ProductLineDTO productLine);

    /**
     * 修改产品线 （产品大类）
     * 
     * @param productLine 产品线 （产品大类）
     * @return 结果
     */
    public int updateProductLine(ProductLineDTO productLine);

    /**
     * 删除产品线 （产品大类）
     * 
     * @param id 产品线 （产品大类）主键
     * @return 结果
     */
    public int deleteProductLineById(Long id);

    /**
     * 批量删除产品线 （产品大类）
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductLineByIds(Long[] ids);
}

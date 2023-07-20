package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProductLineMapper;
import com.ruoyi.system.domain.ProductLineDTO;
import com.ruoyi.system.service.IProductLineService;

/**
 * 产品线 （产品大类）Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-03
 */
@Service
public class ProductLineServiceImpl implements IProductLineService 
{
    @Autowired
    private ProductLineMapper productLineMapper;

    /**
     * 查询产品线 （产品大类）
     * 
     * @param id 产品线 （产品大类）主键
     * @return 产品线 （产品大类）
     */
    @Override
    public ProductLineDTO selectProductLineById(Long id)
    {
        return productLineMapper.selectProductLineById(id);
    }

    /**
     * 查询产品线 （产品大类）列表
     * 
     * @param productLine 产品线 （产品大类）
     * @return 产品线 （产品大类）
     */
    @Override
    public List<ProductLineDTO> selectProductLineList(ProductLineDTO productLine)
    {
        return productLineMapper.selectProductLineList(productLine);
    }

    /**
     * 新增产品线 （产品大类）
     * 
     * @param productLine 产品线 （产品大类）
     * @return 结果
     */
    @Override
    public int insertProductLine(ProductLineDTO productLine)
    {
        productLine.setAddTime(new Date());
        Long userId = SecurityUtils.getUserId();
        productLine.setAddBy(userId);
        return productLineMapper.insertProductLine(productLine);
    }

    /**
     * 修改产品线 （产品大类）
     * 
     * @param productLine 产品线 （产品大类）
     * @return 结果
     */
    @Override
    public int updateProductLine(ProductLineDTO productLine)
    {
        productLine.setUpdateTime(DateUtils.getNowDate());
        Long userId = SecurityUtils.getUserId();
        productLine.setUpdateBy(userId);
        return productLineMapper.updateProductLine(productLine);
    }

    /**
     * 批量删除产品线 （产品大类）
     * 
     * @param ids 需要删除的产品线 （产品大类）主键
     * @return 结果
     */
    @Override
    public int deleteProductLineByIds(Long[] ids)
    {
        return productLineMapper.deleteProductLineByIds(ids);
    }

    /**
     * 删除产品线 （产品大类）信息
     * 
     * @param id 产品线 （产品大类）主键
     * @return 结果
     */
    @Override
    public int deleteProductLineById(Long id)
    {
        return productLineMapper.deleteProductLineById(id);
    }
}

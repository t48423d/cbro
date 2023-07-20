package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ErpProductModelMapper;
import com.ruoyi.system.domain.ErpProductModel;
import com.ruoyi.system.service.IErpProductModelService;

/**
 * 产品型号Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Service
public class ErpProductModelServiceImpl implements IErpProductModelService 
{
    @Autowired
    private ErpProductModelMapper erpProductModelMapper;

    /**
     * 查询产品型号
     * 
     * @param id 产品型号主键
     * @return 产品型号
     */
    @Override
    public ErpProductModel selectErpProductModelById(Long id)
    {
        return erpProductModelMapper.selectErpProductModelById(id);
    }

    /**
     * 查询产品型号列表
     * 
     * @param erpProductModel 产品型号
     * @return 产品型号
     */
    @Override
    public List<ErpProductModel> selectErpProductModelList(ErpProductModel erpProductModel)
    {
        return erpProductModelMapper.selectErpProductModelList(erpProductModel);
    }

    /**
     * 新增产品型号
     * 
     * @param erpProductModel 产品型号
     * @return 结果
     */
    @Override
    public int insertErpProductModel(ErpProductModel erpProductModel)
    {
        return erpProductModelMapper.insertErpProductModel(erpProductModel);
    }

    /**
     * 修改产品型号
     * 
     * @param erpProductModel 产品型号
     * @return 结果
     */
    @Override
    public int updateErpProductModel(ErpProductModel erpProductModel)
    {
        erpProductModel.setUpdateTime(DateUtils.getNowDate());
        return erpProductModelMapper.updateErpProductModel(erpProductModel);
    }

    /**
     * 批量删除产品型号
     * 
     * @param ids 需要删除的产品型号主键
     * @return 结果
     */
    @Override
    public int deleteErpProductModelByIds(Long[] ids)
    {
        return erpProductModelMapper.deleteErpProductModelByIds(ids);
    }

    /**
     * 删除产品型号信息
     * 
     * @param id 产品型号主键
     * @return 结果
     */
    @Override
    public int deleteErpProductModelById(Long id)
    {
        return erpProductModelMapper.deleteErpProductModelById(id);
    }
}

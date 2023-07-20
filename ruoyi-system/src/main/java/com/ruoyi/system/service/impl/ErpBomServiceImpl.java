package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ErpBomMapper;
import com.ruoyi.system.domain.ErpBom;
import com.ruoyi.system.service.IErpBomService;

/**
 * 商品由 具体原件组成关联Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Service
public class ErpBomServiceImpl implements IErpBomService 
{
    @Autowired
    private ErpBomMapper erpBomMapper;

    /**
     * 查询商品由 具体原件组成关联
     * 
     * @param id 商品由 具体原件组成关联主键
     * @return 商品由 具体原件组成关联
     */
    @Override
    public ErpBom selectErpBomById(Long id)
    {
        return erpBomMapper.selectErpBomById(id);
    }

    /**
     * 查询商品由 具体原件组成关联列表
     * 
     * @param erpBom 商品由 具体原件组成关联
     * @return 商品由 具体原件组成关联
     */
    @Override
    public List<ErpBom> selectErpBomList(ErpBom erpBom)
    {
        return erpBomMapper.selectErpBomList(erpBom);
    }

    /**
     * 新增商品由 具体原件组成关联
     * 
     * @param erpBom 商品由 具体原件组成关联
     * @return 结果
     */
    @Override
    public int insertErpBom(ErpBom erpBom)
    {
        return erpBomMapper.insertErpBom(erpBom);
    }

    /**
     * 修改商品由 具体原件组成关联
     * 
     * @param erpBom 商品由 具体原件组成关联
     * @return 结果
     */
    @Override
    public int updateErpBom(ErpBom erpBom)
    {
        erpBom.setUpdateTime(DateUtils.getNowDate());
        return erpBomMapper.updateErpBom(erpBom);
    }

    /**
     * 批量删除商品由 具体原件组成关联
     * 
     * @param ids 需要删除的商品由 具体原件组成关联主键
     * @return 结果
     */
    @Override
    public int deleteErpBomByIds(Long[] ids)
    {
        return erpBomMapper.deleteErpBomByIds(ids);
    }

    /**
     * 删除商品由 具体原件组成关联信息
     * 
     * @param id 商品由 具体原件组成关联主键
     * @return 结果
     */
    @Override
    public int deleteErpBomById(Long id)
    {
        return erpBomMapper.deleteErpBomById(id);
    }
}

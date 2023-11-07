package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.ErpBom;
import com.ruoyi.system.domain.ErpProductModel;

/**
 * 产品型号Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface ErpProductModelMapper 
{
    /**
     * 查询产品型号
     * 
     * @param id 产品型号主键
     * @return 产品型号
     */
    public ErpProductModel selectErpProductModelById(Long id);

    /**
     * 查询产品型号列表
     * 
     * @param erpProductModel 产品型号
     * @return 产品型号集合
     */
    public List<ErpProductModel> selectErpProductModelList(ErpProductModel erpProductModel);

    /**
     * 新增产品型号
     * 
     * @param erpProductModel 产品型号
     * @return 结果
     */
    public int insertErpProductModel(ErpProductModel erpProductModel);

    /**
     * 修改产品型号
     * 
     * @param erpProductModel 产品型号
     * @return 结果
     */
    public int updateErpProductModel(ErpProductModel erpProductModel);

    /**
     * 删除产品型号
     * 
     * @param id 产品型号主键
     * @return 结果
     */
    public int deleteErpProductModelById(Long id);

    /**
     * 批量删除产品型号
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteErpProductModelByIds(Long[] ids);

    List<String> getModelCoders();

    void batchInsert(List<ErpProductModel> list);
}

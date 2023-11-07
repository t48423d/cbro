package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ErpProductionPlanItem;

/**
 * 用于保存 每天生产计划所消耗的原材料数Service接口
 * 
 * @author ruoyi
 * @date 2023-10-03
 */
public interface IErpProductionPlanItemService 
{
    /**
     * 查询用于保存 每天生产计划所消耗的原材料数
     * 
     * @param id 用于保存 每天生产计划所消耗的原材料数主键
     * @return 用于保存 每天生产计划所消耗的原材料数
     */
    public ErpProductionPlanItem selectErpProductionPlanItemById(Long id);

    /**
     * 查询用于保存 每天生产计划所消耗的原材料数列表
     * 
     * @param erpProductionPlanItem 用于保存 每天生产计划所消耗的原材料数
     * @return 用于保存 每天生产计划所消耗的原材料数集合
     */
    public List<ErpProductionPlanItem> selectErpProductionPlanItemList(ErpProductionPlanItem erpProductionPlanItem);

    List<ErpProductionPlanItem> selectErpProductionPlanItemListGroupByProduct(ErpProductionPlanItem erpProductionPlanItem);

    /**
     * 新增用于保存 每天生产计划所消耗的原材料数
     * 
     * @param erpProductionPlanItem 用于保存 每天生产计划所消耗的原材料数
     * @return 结果
     */
    public int insertErpProductionPlanItem(ErpProductionPlanItem erpProductionPlanItem);

    /**
     * 修改用于保存 每天生产计划所消耗的原材料数
     * 
     * @param erpProductionPlanItem 用于保存 每天生产计划所消耗的原材料数
     * @return 结果
     */
    public int updateErpProductionPlanItem(ErpProductionPlanItem erpProductionPlanItem);

    /**
     * 批量删除用于保存 每天生产计划所消耗的原材料数
     * 
     * @param ids 需要删除的用于保存 每天生产计划所消耗的原材料数主键集合
     * @return 结果
     */
    public int deleteErpProductionPlanItemByIds(Long[] ids);

    /**
     * 删除用于保存 每天生产计划所消耗的原材料数信息
     * 
     * @param id 用于保存 每天生产计划所消耗的原材料数主键
     * @return 结果
     */
    public int deleteErpProductionPlanItemById(Long id);
}

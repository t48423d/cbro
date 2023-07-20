package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ErpProductionPlan;

/**
 * 生产计划Service接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface IErpProductionPlanService 
{
    /**
     * 查询生产计划
     * 
     * @param id 生产计划主键
     * @return 生产计划
     */
    public ErpProductionPlan selectErpProductionPlanById(Long id);

    /**
     * 查询生产计划列表
     * 
     * @param erpProductionPlan 生产计划
     * @return 生产计划集合
     */
    public List<ErpProductionPlan> selectErpProductionPlanList(ErpProductionPlan erpProductionPlan);

    /**
     * 新增生产计划
     * 
     * @param erpProductionPlan 生产计划
     * @return 结果
     */
    public int insertErpProductionPlan(ErpProductionPlan erpProductionPlan);

    /**
     * 修改生产计划
     * 
     * @param erpProductionPlan 生产计划
     * @return 结果
     */
    public int updateErpProductionPlan(ErpProductionPlan erpProductionPlan);

    /**
     * 批量删除生产计划
     * 
     * @param ids 需要删除的生产计划主键集合
     * @return 结果
     */
    public int deleteErpProductionPlanByIds(Long[] ids);

    /**
     * 删除生产计划信息
     * 
     * @param id 生产计划主键
     * @return 结果
     */
    public int deleteErpProductionPlanById(Long id);
}

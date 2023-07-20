package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ErpProductionPlanMapper;
import com.ruoyi.system.domain.ErpProductionPlan;
import com.ruoyi.system.service.IErpProductionPlanService;

/**
 * 生产计划Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Service
public class ErpProductionPlanServiceImpl implements IErpProductionPlanService 
{
    @Autowired
    private ErpProductionPlanMapper erpProductionPlanMapper;

    /**
     * 查询生产计划
     * 
     * @param id 生产计划主键
     * @return 生产计划
     */
    @Override
    public ErpProductionPlan selectErpProductionPlanById(Long id)
    {
        return erpProductionPlanMapper.selectErpProductionPlanById(id);
    }

    /**
     * 查询生产计划列表
     * 
     * @param erpProductionPlan 生产计划
     * @return 生产计划
     */
    @Override
    public List<ErpProductionPlan> selectErpProductionPlanList(ErpProductionPlan erpProductionPlan)
    {
        return erpProductionPlanMapper.selectErpProductionPlanList(erpProductionPlan);
    }

    /**
     * 新增生产计划
     * 
     * @param erpProductionPlan 生产计划
     * @return 结果
     */
    @Override
    public int insertErpProductionPlan(ErpProductionPlan erpProductionPlan)
    {
        return erpProductionPlanMapper.insertErpProductionPlan(erpProductionPlan);
    }

    /**
     * 修改生产计划
     * 
     * @param erpProductionPlan 生产计划
     * @return 结果
     */
    @Override
    public int updateErpProductionPlan(ErpProductionPlan erpProductionPlan)
    {
        erpProductionPlan.setUpdateTime(DateUtils.getNowDate());
        return erpProductionPlanMapper.updateErpProductionPlan(erpProductionPlan);
    }

    /**
     * 批量删除生产计划
     * 
     * @param ids 需要删除的生产计划主键
     * @return 结果
     */
    @Override
    public int deleteErpProductionPlanByIds(Long[] ids)
    {
        return erpProductionPlanMapper.deleteErpProductionPlanByIds(ids);
    }

    /**
     * 删除生产计划信息
     * 
     * @param id 生产计划主键
     * @return 结果
     */
    @Override
    public int deleteErpProductionPlanById(Long id)
    {
        return erpProductionPlanMapper.deleteErpProductionPlanById(id);
    }
}

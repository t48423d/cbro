package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ErpOriginalDataMapper;
import com.ruoyi.system.domain.ErpOriginalData;
import com.ruoyi.system.service.IErpOriginalDataService;

/**
 * 原材料信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@Service
public class ErpOriginalDataServiceImpl implements IErpOriginalDataService 
{
    @Autowired
    private ErpOriginalDataMapper erpOriginalDataMapper;

    /**
     * 查询原材料信息
     * 
     * @param id 原材料信息主键
     * @return 原材料信息
     */
    @Override
    public ErpOriginalData selectErpOriginalDataById(Long id)
    {
        return erpOriginalDataMapper.selectErpOriginalDataById(id);
    }

    /**
     * 查询原材料信息列表
     * 
     * @param erpOriginalData 原材料信息
     * @return 原材料信息
     */
    @Override
    public List<ErpOriginalData> selectErpOriginalDataList(ErpOriginalData erpOriginalData)
    {
        return erpOriginalDataMapper.selectErpOriginalDataList(erpOriginalData);
    }

    /**
     * 新增原材料信息
     * 
     * @param erpOriginalData 原材料信息
     * @return 结果
     */
    @Override
    public int insertErpOriginalData(ErpOriginalData erpOriginalData)
    {
        Long userId = SecurityUtils.getUserId();
        erpOriginalData.setAddTime(new Date());
        erpOriginalData.setAddBy(userId);
        return erpOriginalDataMapper.insertErpOriginalData(erpOriginalData);
    }

    /**
     * 修改原材料信息
     * 
     * @param erpOriginalData 原材料信息
     * @return 结果
     */
    @Override
    public int updateErpOriginalData(ErpOriginalData erpOriginalData)
    {
        erpOriginalData.setUpdateTime(DateUtils.getNowDate());
        erpOriginalData.setUpdateBu(SecurityUtils.getUserId());
        return erpOriginalDataMapper.updateErpOriginalData(erpOriginalData);
    }

    /**
     * 批量删除原材料信息
     * 
     * @param ids 需要删除的原材料信息主键
     * @return 结果
     */
    @Override
    public int deleteErpOriginalDataByIds(Long[] ids)
    {
        return erpOriginalDataMapper.deleteErpOriginalDataByIds(ids);
    }

    /**
     * 删除原材料信息信息
     * 
     * @param id 原材料信息主键
     * @return 结果
     */
    @Override
    public int deleteErpOriginalDataById(Long id)
    {
        return erpOriginalDataMapper.deleteErpOriginalDataById(id);
    }

    @Override
    public List<ErpOriginalData> getListByName(String keyword) {
        return null;
    }
}

package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ErpOriginalData;

/**
 * 原材料信息Service接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface IErpOriginalDataService 
{
    /**
     * 查询原材料信息
     * 
     * @param id 原材料信息主键
     * @return 原材料信息
     */
    public ErpOriginalData selectErpOriginalDataById(Long id);

    /**
     * 查询原材料信息列表
     * 
     * @param erpOriginalData 原材料信息
     * @return 原材料信息集合
     */
    public List<ErpOriginalData> selectErpOriginalDataList(ErpOriginalData erpOriginalData);

    /**
     * 新增原材料信息
     * 
     * @param erpOriginalData 原材料信息
     * @return 结果
     */
    public int insertErpOriginalData(ErpOriginalData erpOriginalData);

    /**
     * 修改原材料信息
     * 
     * @param erpOriginalData 原材料信息
     * @return 结果
     */
    public int updateErpOriginalData(ErpOriginalData erpOriginalData);

    /**
     * 批量删除原材料信息
     * 
     * @param ids 需要删除的原材料信息主键集合
     * @return 结果
     */
    public int deleteErpOriginalDataByIds(Long[] ids);

    /**
     * 删除原材料信息信息
     * 
     * @param id 原材料信息主键
     * @return 结果
     */
    public int deleteErpOriginalDataById(Long id);
}

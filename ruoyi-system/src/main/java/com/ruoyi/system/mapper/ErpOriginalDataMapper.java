package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ErpOriginalData;

/**
 * 原材料信息Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface ErpOriginalDataMapper 
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
     * 删除原材料信息
     * 
     * @param id 原材料信息主键
     * @return 结果
     */
    public int deleteErpOriginalDataById(Long id);

    /**
     * 批量删除原材料信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteErpOriginalDataByIds(Long[] ids);

    public int  batchInsert(List<ErpOriginalData> list);

    void updateItem(ErpOriginalData data);

    void updateBatch(List<ErpOriginalData> list);
}

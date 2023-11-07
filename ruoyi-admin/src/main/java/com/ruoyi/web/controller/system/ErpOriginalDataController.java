package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ErpOriginalData;
import com.ruoyi.system.service.IErpOriginalDataService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 原材料信息Controller
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/system/data")
public class ErpOriginalDataController extends BaseController
{
    @Autowired
    private IErpOriginalDataService erpOriginalDataService;

    /**
     * 查询原材料信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpOriginalData erpOriginalData)
    {
        startPage();
        List<ErpOriginalData> list = erpOriginalDataService.selectErpOriginalDataList(erpOriginalData);
        return getDataTable(list);
    }

    public List<ErpOriginalData> getListByName(String keyword){
        return erpOriginalDataService.getListByName(keyword);
    }

    /**
     * 导出原材料信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:data:export')")
    @Log(title = "原材料信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpOriginalData erpOriginalData)
    {
        List<ErpOriginalData> list = erpOriginalDataService.selectErpOriginalDataList(erpOriginalData);
        ExcelUtil<ErpOriginalData> util = new ExcelUtil<ErpOriginalData>(ErpOriginalData.class);
        util.exportExcel(response, list, "原材料信息数据");
    }

    /**
     * 获取原材料信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(erpOriginalDataService.selectErpOriginalDataById(id));
    }

    /**
     * 新增原材料信息
     */
    @PreAuthorize("@ss.hasPermi('system:data:add')")
    @Log(title = "原材料信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpOriginalData erpOriginalData)
    {
        return toAjax(erpOriginalDataService.insertErpOriginalData(erpOriginalData));
    }

    /**
     * 修改原材料信息
     */
    @PreAuthorize("@ss.hasPermi('system:data:edit')")
    @Log(title = "原材料信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpOriginalData erpOriginalData)
    {
        return toAjax(erpOriginalDataService.updateErpOriginalData(erpOriginalData));
    }

    /**
     * 删除原材料信息
     */
    @PreAuthorize("@ss.hasPermi('system:data:remove')")
    @Log(title = "原材料信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(erpOriginalDataService.deleteErpOriginalDataByIds(ids));
    }
}

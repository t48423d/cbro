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
import com.ruoyi.system.domain.ErpProductionPlanItem;
import com.ruoyi.system.service.IErpProductionPlanItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用于保存 每天生产计划所消耗的原材料数Controller
 * 
 * @author ruoyi
 * @date 2023-10-03
 */
@RestController
@RequestMapping("/system/item")
public class ErpProductionPlanItemController extends BaseController
{
    @Autowired
    private IErpProductionPlanItemService erpProductionPlanItemService;

    /**
     * 查询用于保存 每天生产计划所消耗的原材料数列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductionPlanItem erpProductionPlanItem)
    {
        startPage();
        if (erpProductionPlanItem.getIsDelete() == null){
            erpProductionPlanItem.setIsDelete(0);
        }
        //List<ErpProductionPlanItem> list = erpProductionPlanItemService.selectErpProductionPlanItemList(erpProductionPlanItem);
        List<ErpProductionPlanItem> list = erpProductionPlanItemService.selectErpProductionPlanItemListGroupByProduct(erpProductionPlanItem);
        return getDataTable(list);
    }

    /**
     * 导出用于保存 每天生产计划所消耗的原材料数列表
     */
    @PreAuthorize("@ss.hasPermi('system:item:export')")
    @Log(title = "用于保存 每天生产计划所消耗的原材料数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductionPlanItem erpProductionPlanItem)
    {
        List<ErpProductionPlanItem> list = erpProductionPlanItemService.selectErpProductionPlanItemList(erpProductionPlanItem);
        ExcelUtil<ErpProductionPlanItem> util = new ExcelUtil<ErpProductionPlanItem>(ErpProductionPlanItem.class);
        util.exportExcel(response, list, "用于保存 每天生产计划所消耗的原材料数数据");
    }

    /**
     * 获取用于保存 每天生产计划所消耗的原材料数详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(erpProductionPlanItemService.selectErpProductionPlanItemById(id));
    }

    /**
     * 新增用于保存 每天生产计划所消耗的原材料数
     */
    @PreAuthorize("@ss.hasPermi('system:item:add')")
    @Log(title = "用于保存 每天生产计划所消耗的原材料数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductionPlanItem erpProductionPlanItem)
    {
        return toAjax(erpProductionPlanItemService.insertErpProductionPlanItem(erpProductionPlanItem));
    }

    /**
     * 修改用于保存 每天生产计划所消耗的原材料数
     */
    @PreAuthorize("@ss.hasPermi('system:item:edit')")
    @Log(title = "用于保存 每天生产计划所消耗的原材料数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductionPlanItem erpProductionPlanItem)
    {
        return toAjax(erpProductionPlanItemService.updateErpProductionPlanItem(erpProductionPlanItem));
    }

    /**
     * 删除用于保存 每天生产计划所消耗的原材料数
     */
    @PreAuthorize("@ss.hasPermi('system:item:remove')")
    @Log(title = "用于保存 每天生产计划所消耗的原材料数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(erpProductionPlanItemService.deleteErpProductionPlanItemByIds(ids));
    }
}

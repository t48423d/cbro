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
import com.ruoyi.system.domain.ErpProductModel;
import com.ruoyi.system.service.IErpProductModelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品型号Controller
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/system/productModel")
public class ErpProductModelController extends BaseController
{
    @Autowired
    private IErpProductModelService erpProductModelService;

    /**
     * 查询产品型号列表
     */
    @PreAuthorize("@ss.hasPermi('system:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpProductModel erpProductModel)
    {
        startPage();
        List<ErpProductModel> list = erpProductModelService.selectErpProductModelList(erpProductModel);
        return getDataTable(list);
    }

    /**
     * 导出产品型号列表
     */
    @PreAuthorize("@ss.hasPermi('system:model:export')")
    @Log(title = "产品型号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpProductModel erpProductModel)
    {
        List<ErpProductModel> list = erpProductModelService.selectErpProductModelList(erpProductModel);
        ExcelUtil<ErpProductModel> util = new ExcelUtil<ErpProductModel>(ErpProductModel.class);
        util.exportExcel(response, list, "产品型号数据");
    }

    /**
     * 获取产品型号详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:model:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(erpProductModelService.selectErpProductModelById(id));
    }

    /**
     * 新增产品型号
     */
    @PreAuthorize("@ss.hasPermi('system:model:add')")
    @Log(title = "产品型号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpProductModel erpProductModel)
    {
        return toAjax(erpProductModelService.insertErpProductModel(erpProductModel));
    }

    /**
     * 修改产品型号
     */
    @PreAuthorize("@ss.hasPermi('system:model:edit')")
    @Log(title = "产品型号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpProductModel erpProductModel)
    {
        return toAjax(erpProductModelService.updateErpProductModel(erpProductModel));
    }

    /**
     * 删除产品型号
     */
    @PreAuthorize("@ss.hasPermi('system:model:remove')")
    @Log(title = "产品型号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(erpProductModelService.deleteErpProductModelByIds(ids));
    }
}

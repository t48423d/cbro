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
import com.ruoyi.system.domain.ErpBom;
import com.ruoyi.system.service.IErpBomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品由 具体原件组成关联Controller
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
@RestController
@RequestMapping("/system/bom")
public class ErpBomController extends BaseController
{
    @Autowired
    private IErpBomService erpBomService;

    /**
     * 查询商品由 具体原件组成关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:bom:list')")
    @GetMapping("/list")
    public TableDataInfo list(ErpBom erpBom)
    {
        startPage();
        List<ErpBom> list = erpBomService.selectErpBomList(erpBom);
        return getDataTable(list);
    }

    /**
     * 导出商品由 具体原件组成关联列表
     */
    @PreAuthorize("@ss.hasPermi('system:bom:export')")
    @Log(title = "商品由 具体原件组成关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ErpBom erpBom)
    {
        List<ErpBom> list = erpBomService.selectErpBomList(erpBom);
        ExcelUtil<ErpBom> util = new ExcelUtil<ErpBom>(ErpBom.class);
        util.exportExcel(response, list, "商品由 具体原件组成关联数据");
    }

    /**
     * 获取商品由 具体原件组成关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:bom:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(erpBomService.selectErpBomById(id));
    }

    /**
     * 新增商品由 具体原件组成关联
     */
    @PreAuthorize("@ss.hasPermi('system:bom:add')")
    @Log(title = "商品由 具体原件组成关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ErpBom erpBom)
    {
        return toAjax(erpBomService.insertErpBom(erpBom));
    }

    /**
     * 修改商品由 具体原件组成关联
     */
    @PreAuthorize("@ss.hasPermi('system:bom:edit')")
    @Log(title = "商品由 具体原件组成关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ErpBom erpBom)
    {
        return toAjax(erpBomService.updateErpBom(erpBom));
    }

    /**
     * 删除商品由 具体原件组成关联
     */
    @PreAuthorize("@ss.hasPermi('system:bom:remove')")
    @Log(title = "商品由 具体原件组成关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(erpBomService.deleteErpBomByIds(ids));
    }
}

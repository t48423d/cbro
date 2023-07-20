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
import com.ruoyi.system.domain.ProductLineDTO;
import com.ruoyi.system.service.IProductLineService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品线 （产品大类）Controller
 * 
 * @author ruoyi
 * @date 2023-06-03
 */
@RestController
@RequestMapping("/system/productLine")
public class ProductLineController extends BaseController
{
    @Autowired
    private IProductLineService productLineService;

    /**
     * 查询产品线 （产品大类）列表
     */
    @PreAuthorize("@ss.hasPermi('system:line:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProductLineDTO productLine)
    {
        startPage();
        List<ProductLineDTO> list = productLineService.selectProductLineList(productLine);
        return getDataTable(list);
    }

    /**
     * 导出产品线 （产品大类）列表
     */
    @PreAuthorize("@ss.hasPermi('system:line:export')")
    @Log(title = "产品线 （产品大类）", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProductLineDTO productLine)
    {
        List<ProductLineDTO> list = productLineService.selectProductLineList(productLine);
        ExcelUtil<ProductLineDTO> util = new ExcelUtil<ProductLineDTO>(ProductLineDTO.class);
        util.exportExcel(response, list, "产品线 （产品大类）数据");
    }

    /**
     * 获取产品线 （产品大类）详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:line:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(productLineService.selectProductLineById(id));
    }

    /**
     * 新增产品线 （产品大类）
     */
    @PreAuthorize("@ss.hasPermi('system:line:add')")
    @Log(title = "产品线 （产品大类）", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProductLineDTO productLine)
    {
        return toAjax(productLineService.insertProductLine(productLine));
    }

    /**
     * 修改产品线 （产品大类）
     */
    @PreAuthorize("@ss.hasPermi('system:line:edit')")
    @Log(title = "产品线 （产品大类）", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProductLineDTO productLine)
    {
        return toAjax(productLineService.updateProductLine(productLine));
    }

    /**
     * 删除产品线 （产品大类）
     */
    @PreAuthorize("@ss.hasPermi('system:line:remove')")
    @Log(title = "产品线 （产品大类）", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(productLineService.deleteProductLineByIds(ids));
    }
}

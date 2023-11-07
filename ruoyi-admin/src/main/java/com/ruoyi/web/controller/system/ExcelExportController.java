package com.ruoyi.web.controller.system;

import com.mchange.lang.IntegerUtils;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.ErpProductionPlanItem;
import com.ruoyi.system.domain.PlanRequest;
import com.ruoyi.system.domain.vo.PlanVo;
import com.ruoyi.system.service.impl.ErpProductionPlanItemServiceImpl;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/excelExport")
public class ExcelExportController extends BaseController {

    @Autowired
    ErpProductionPlanItemServiceImpl erpProductionPlanItemService;

    @RequestMapping("/oneDayPlan")
    public TableDataInfo oneDayPlan(@RequestBody PlanRequest param){
        getToDay(param);
        List<PlanVo> list = erpProductionPlanItemService.oneDayPlan(param);
        return  getDataTable(list);
    }



    @RequestMapping("/daysPlan")
    public void  daysPlan(@Param("day") Integer day,@Param("count") Integer count , HttpServletResponse response) throws IOException {
        PlanRequest param = new PlanRequest();
        param.setDay(day);
        param.setCount(count);
        getToDay(param);
        List<PlanVo> list = erpProductionPlanItemService.daysPlan(param, response);
    }

    private Integer getToDay(PlanRequest param) {
        if(param.getDay() ==null){
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            System.out.println("当前日期为：" + sdf.format(currentDate));
            param.setDay(Integer.valueOf(sdf.format(currentDate)));
        }
        return param.getDay();
    }
}

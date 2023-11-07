package com.ruoyi.web.controller.system;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.bean.BaseResult;
import com.ruoyi.web.controller.service.BomServiceImpl;
import com.ruoyi.web.controller.service.PlanServiceImpl;
import com.ruoyi.web.controller.service.StockServiceImpl;
import com.ruoyi.web.utils.ExcelBomUtils;
import com.ruoyi.web.utils.ExcelOriginalDataUtils;
import com.ruoyi.web.utils.ExcelProductionPlanUtils;
import com.ruoyi.web.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class ExcelmportController {

    @Autowired
    private BomServiceImpl  bomService;

    @Autowired
    private StockServiceImpl stockService;

    @Autowired
    private PlanServiceImpl planService;

    /**
     * 原料库存
     * @param file
     * @param response
     * @throws Exception
     */
    @RequestMapping("/importStock")
    public BaseResult<String> importStock(@RequestPart("file") MultipartFile file,
                                          HttpServletResponse response) throws Exception {
        JSONArray array = ExcelOriginalDataUtils.readMultipartFile(file);
        System.out.println("导入数据为:" + array);
        List<Map<String, String>> maps = this.jsonToMap(array);
        stockService.saveData(maps);

        return BaseResult.success("导入成功");
    }

    /**
     * 产品组合
     * @param file
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/importBom")
    public BaseResult<String> importBom(@RequestPart("file") MultipartFile file,
                             HttpServletResponse response) throws Exception {
        JSONArray array = ExcelBomUtils.readMultipartFile(file);
        // System.out.println("导入数据为:" + array);
        // 转换成List<Map<String, String>>
        List<Map<String, String>> maps = this.jsonToMap(array);

        bomService.saveData(maps);

        return BaseResult.success("导入成功");
    }


    /**
     * 生产计划
     * @param file
     * @param response
     * @throws Exception
     */
    @RequestMapping("/importSchedule")
    public BaseResult<String> importSchedule(@RequestPart("file") MultipartFile file,
                           HttpServletResponse response) throws Exception {
        JSONArray array = ExcelProductionPlanUtils.readMultipartFile(file);
//        System.out.println("导入数据为:" + array);
        List<Map<String, String>> maps = this.jsonToMap(array);
        planService.saveData(maps);

        return BaseResult.success("导入成功");
    }



    // jsonArrray 转 ListMap
    public List<Map<String, String>> jsonToMap(JSONArray jArray){
        List<Map<String, String>> list = new ArrayList<>();
        for (Object o : jArray) {
            // 复制数组
            Map<String, String> tmp = JSON.parseObject(JSON.toJSONString(o), Map.class);
            list.add(tmp);
        }
        return list;
    }


    //导出
}

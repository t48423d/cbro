package com.ruoyi.web.controller.service;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.system.domain.ErpBom;
import com.ruoyi.system.domain.ErpProductionPlan;
import com.ruoyi.system.domain.ErpProductionPlanItem;
import com.ruoyi.system.mapper.ErpBomMapper;
import com.ruoyi.system.mapper.ErpProductionPlanItemMapper;
import com.ruoyi.system.mapper.ErpProductionPlanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlanServiceImpl {

    @Resource
    ErpBomMapper erpBomMapper;

    @Resource
    ErpProductionPlanMapper erpProductionPlanMapper;

    @Resource
    ErpProductionPlanItemMapper erpProductionPlanItemMapper;

    public void saveData(List<Map<String, String>> maps) throws ParseException {
        List<ErpProductionPlan> list = new ArrayList<>();
        Date addTime = new Date();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, String> map = maps.get(i);
            String modelCode = map.get("Model");
            for (String key : map.keySet()) {
                // 如果包含 -  就表示是 时间格式 例如 2023-01-01
                if(key.contains("-")){
                    if(map.get(key) != null && !map.get(key).equals("")){
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = format.parse(key);

                        ErpProductionPlan tmp = new ErpProductionPlan();
                        tmp.setCount(Long.valueOf(map.get(key)));
                        tmp.setProductModelCode(modelCode);
                        tmp.setDate(date);
                        tmp.setAddBy(1L);
                        tmp.setAddTime(addTime);
                        tmp.setUpdateBy("1");
                        tmp.setIsDelete(0);
                        log.info("do ：{}", JSON.toJSONString(tmp));
                        list.add(tmp);
                    }
                }
            }
        }

        if(list.size() == 0){
            return ;
        }
        Set<Date> dates = list.stream().map(ErpProductionPlan::getDate).collect(Collectors.toSet());
        List<Date> dates2 = dates.stream().collect(Collectors.toList());
        // 为了防止同一天因为改表导入了两次， 先把 导入天数的数据清了
        erpProductionPlanMapper.setDeleteInDate(dates2);

        List<Long> numbers = new ArrayList<>();
        for (Date item : dates2) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String number = sdf.format(item);
            numbers.add(Long.valueOf(number));
        }
        // 先把这一天的item清了
        erpProductionPlanItemMapper.setDeleteInDate(numbers);

        // 批量保存 list 到erpProductionPlan 表
        erpProductionPlanMapper.saveBatch(list);

        // 再处理 erpProductionPlanItem 表
        // 此处通过 产品code 查bom表 查出每个产品配料 并通过grouping出用了多少原材料
        List<String> productModelCodes = list.stream().map(ErpProductionPlan::getProductModelCode).collect(Collectors.toList());

        // 把用到的型号的 bom取出来
        List<ErpBom> bomList = erpBomMapper.getListInModelCodes(productModelCodes);
        //按照型号group 好
        Map<String, List<ErpBom>> bomMaps = bomList.stream().collect(Collectors.groupingBy(ErpBom::getProductModelCoder));

        List<ErpProductionPlanItem> planItemList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        for (ErpProductionPlan erpProductionPlan : list) {
            Long count = erpProductionPlan.getCount();
            String productModelCode = erpProductionPlan.getProductModelCode();
            String number = sdf.format(erpProductionPlan.getDate());
            Long itemDate = Long.valueOf(number);

            if(bomMaps.get(productModelCode) != null) {
                List<ErpBom> erpBoms = bomMaps.get(productModelCode);
                for (ErpBom erpBomItem : erpBoms) {
                    ErpProductionPlanItem tmp = new ErpProductionPlanItem();
                    // todo 这里填充数据
                    tmp.setProductModelCode(erpProductionPlan.getProductModelCode());
                    tmp.setCount(count);
                    tmp.setOriginalCoder(erpBomItem.getOriginalCoder());
                    tmp.setNumber(erpBomItem.getNumber().multiply(new BigDecimal(count)));
                    tmp.setDate(itemDate);
                    tmp.setIsDelete(0);
                    tmp.setAddTime(addTime);
                    planItemList.add(tmp);
                }
            }
        }
        erpProductionPlanItemMapper.saveBatch(planItemList);
    }
}

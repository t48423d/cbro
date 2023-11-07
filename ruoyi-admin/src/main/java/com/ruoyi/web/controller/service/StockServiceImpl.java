package com.ruoyi.web.controller.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ErpOriginalData;
import com.ruoyi.system.mapper.ErpBomMapper;
import com.ruoyi.system.mapper.ErpOriginalDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class StockServiceImpl {

    @Resource
    private ErpOriginalDataMapper erpOriginalDataMapper;



    public void saveData(List<Map<String, String>> importMap) {
        Map<String, ErpOriginalData> excelMap = this.transforExcel(importMap);// 转化格式
        Map<String, ErpOriginalData> dbMap  = this.getDbData(); // 数据库的数据
        Map<String, ErpOriginalData> insertMap = new HashMap<>(); // 新增的数据
        Map<String, ErpOriginalData> updateMap = new HashMap<>(); // 更新的数据
        Map<String, ErpOriginalData> deleteMap = new HashMap<>(); // 删除的数据

        // 对比
        this.compareData(excelMap, dbMap, insertMap, updateMap, deleteMap);

        // 新增
        this.doAdd(insertMap);
//        // 更新
        this.doUpdate(updateMap);
//        // 删除
//        this.doDelete(deleteMap);
    }


    private void doAdd(Map<String, ErpOriginalData> insertMap) {
        List<ErpOriginalData> erpOriginalDatas = new ArrayList<>();

        for (String key : insertMap.keySet()) {
            ErpOriginalData data = insertMap.get(key);
            erpOriginalDatas.add(data);
        }
        if(erpOriginalDatas.size() > 0){
            erpOriginalDataMapper.batchInsert(erpOriginalDatas);
        }
    }

    private void doUpdate(Map<String, ErpOriginalData> updateMap) {
        List<ErpOriginalData> erpOriginalDatas = new ArrayList<>();

        for (String key : updateMap.keySet()) {
            ErpOriginalData data = updateMap.get(key);
            erpOriginalDatas.add(data);
        }
        if(erpOriginalDatas.size() > 0){
            System.out.println("更新个数" + erpOriginalDatas.size());
//            erpOriginalDataMapper.updateBatch(erpOriginalDatas);
            for (ErpOriginalData data : erpOriginalDatas) {
                erpOriginalDataMapper.updateItem(data);
            }
        }
    }


    private Map<String, ErpOriginalData> getDbData() {
        ErpOriginalData where = new  ErpOriginalData();
        where.setIsDelete(0);
        List<ErpOriginalData> erpOriginalDataList = erpOriginalDataMapper.selectErpOriginalDataList(where);
        Map<String, ErpOriginalData> list  = new HashMap<>();
        for (ErpOriginalData item : erpOriginalDataList) {
            list.put(item.getCoder(), item);
        }
        return list;
    }


    // 增删改的对比
    private void compareData(Map<String, ErpOriginalData> excelMap,
                             Map<String, ErpOriginalData> dbMap,
                             Map<String, ErpOriginalData> insertMap,
                             Map<String, ErpOriginalData> updateMap,
                             Map<String, ErpOriginalData> deleteMap) {
        // 循环导入的key
        for (String key : excelMap.keySet()) {
            // 如果db里没有就是 新增
            if(dbMap.get(key) == null){
                // 如果导入的参数是空 也跳过
                if(excelMap.get(key) == null){
                    continue;
                }
                // 加入到新增里去
                insertMap.put(key, excelMap.get(key));
            } else {
                // 如果db里有  那就对比下一不一样  不一样就是更新 如果是0 就是删除

                ErpOriginalData excelData = excelMap.get(key);
                ErpOriginalData dbData = dbMap.get(key);

                if (excelData.getDesc().equals(dbData.getDesc()) &&
                    excelData.getSuppier().equals(dbData.getSuppier()) &&
                    excelData.getBuyer().equals(dbData.getBuyer()) &&
                    excelData.getDeliverySite().equals(dbData.getDeliverySite()) &&
                    excelData.getSpecial().equals(dbData.getSpecial()) &&
                    excelData.getTransport().equals(dbData.getTransport()) &&
                    excelData.getBufferDay().equals(dbData.getBufferDay()) &&
                    excelData.getRaw().compareTo(dbData.getRaw()) == 0 &&
                    excelData.getRds().equals(dbData.getRds()) &&
                    excelData.getWip().equals(dbData.getWip()) &&
                    excelData.getService().compareTo(dbData.getService()) == 0 &&
                    excelData.getTotal().compareTo(dbData.getTotal()) == 0 &&
                    excelData.getPlannerCode().equals(dbData.getPlannerCode())
                ) {
                    // db有且一样 什么都不做
                } else {
                    //是更新
                    updateMap.put(key, excelMap.get(key));
                }

                // db里有  上传里也有 那就是 从dbmap里删掉， 留下的都是之后要删除的
                dbMap.remove(key);
            }
        }

        for (String key : dbMap.keySet()) {
            deleteMap.put(key,  null);
        }
    }


    private Map<String, ErpOriginalData> transforExcel(List<Map<String, String>> importMap) {
        Map<String, ErpOriginalData> list = new HashMap<>();
        for (Map<String, String> item : importMap) {
            ErpOriginalData tmp = new ErpOriginalData();
            tmp.setCoder(item.get("Items"));
            tmp.setRemark(item.get("CN Desc."));
            tmp.setDesc(item.get("Desc."));
            tmp.setSuppier(item.get("Suppier"));
            tmp.setBuyer(item.get("Buyer"));
            tmp.setDeliverySite(item.get("Delivery  Site"));
            if(item.get("Special").equals("Y")) {
                tmp.setSpecial(1);
            } else {
                tmp.setSpecial(0);
            }
            BigDecimal number;
            if(StringUtils.isNotEmpty(item.get("Transportation time/day"))) {
                number = BigDecimal.valueOf(Double.valueOf(item.get("Transportation time/day")));
                BigDecimal transDay = number.setScale(7, BigDecimal.ROUND_HALF_UP);// 保留小数点7位
                tmp.setTransport(transDay);
            } else {
                tmp.setTransport(new BigDecimal(0));
            }

            if(StringUtils.isNotEmpty(item.get("Buffer/day"))) {
                number = BigDecimal.valueOf(Double.valueOf(item.get("Buffer/day")));
                BigDecimal day = number.setScale(7, BigDecimal.ROUND_HALF_UP);// 保留小数点7位
                tmp.setBufferDay(day);
            } else {
                tmp.setBufferDay(new BigDecimal(0));
            }

            number = BigDecimal.valueOf(Double.valueOf(item.get("RAW")));
            BigDecimal raw = number.setScale(7,BigDecimal.ROUND_HALF_UP);// 保留小数点7位
            tmp.setRaw(raw);

            if(StringUtils.isNotEmpty(item.get("Service"))){
                number = BigDecimal.valueOf(Double.valueOf(item.get("Service")));
                BigDecimal service = number.setScale(7,BigDecimal.ROUND_HALF_UP);// 保留小数点7位
                tmp.setService(service);
                tmp.setTotal(raw.add(service));
            } else {
                tmp.setService(new BigDecimal(0));
                tmp.setTotal(raw);
            }

            number = BigDecimal.valueOf(Double.valueOf(item.get("RDS")));
            BigDecimal rds = number.setScale(7,BigDecimal.ROUND_HALF_UP);// 保留小数点7位
            tmp.setRds(rds);

            tmp.setWip(item.get("WIP"));
            tmp.setPlannerCode(item.get("Planner Code"));
            list.put(item.get("Items"), tmp);
        }

        return list;
    }
}

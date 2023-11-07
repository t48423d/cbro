package com.ruoyi.web.controller.service;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.ErpBom;
import com.ruoyi.system.domain.ErpProductModel;
import com.ruoyi.system.mapper.ErpBomMapper;
import com.ruoyi.system.mapper.ErpProductModelMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class BomServiceImpl {
    @Resource
    private ErpBomMapper erpBomMapper;

    @Resource
    private ErpProductModelMapper erpProductModelMapper;

//    组装
    public void saveData(List<Map<String, String>> importMap) {
        Map<String, String> excelMap = this.transforExcel(importMap);// 转化格式
        Map<String, String> dbMap  = this.getDbData(); // 数据库的数据
        Map<String, String> insertMap = new HashMap<>(); // 新增的数据
        Map<String, String> updateMap = new HashMap<>(); // 更新的数据
        Map<String, String> deleteMap = new HashMap<>(); // 删除的数据

        // 对比
        this.compareData(excelMap, dbMap, insertMap, updateMap, deleteMap);

        // 新增
        this.doAdd(insertMap);
        // 更新
        this.doUpdate(updateMap);
        // 删除
        this.doDelete(deleteMap);
    }


    // 增删改的对比
    private void compareData(Map<String, String> excelMap, Map<String, String> dbMap, Map<String, String> insertMap, Map<String, String> updateMap, Map<String, String> deleteMap) {
        // 循环导入的key
        for (String key : excelMap.keySet()) {
            // 如果db里没有就是 新增
            if(dbMap.get(key) == null){
                // 如果导入的参数是0 也跳过
                if(excelMap.get(key).equals("0")){
                    continue;
                }
                // 加入到新增里去
                insertMap.put(key, excelMap.get(key));
            } else {
                // 如果db里有  那就对比下一不一样  不一样就是更新 如果是0 就是删除
                if (excelMap.get(key) != null) {
                    if (Double.valueOf(dbMap.get(key)).compareTo(Double.valueOf(excelMap.get(key))) == 0 ) {
                        // db有且一样 什么都不做
                    } else {
                        //是更新
                        updateMap.put(key, excelMap.get(key));
                    }
                }
                // db里有  上传里也有 那就是 从dbmap里删掉， 留下的都是之后要删除的
                dbMap.remove(key);
            }
        }

        for (String key : dbMap.keySet()) {
            log.info("======================"+key);
            deleteMap.put(key, "");
        }
    }

    private void doAdd(Map<String, String> insertMap) {
        List<ErpBom> erpBoms = new ArrayList<>();
        Date date = new Date();
        List<ErpProductModel> models = new ArrayList<>();
        List<String> hadCoders = erpProductModelMapper.getModelCoders();
        log.info("=========hadCoders============="+JSON.toJSONString(hadCoders));

        for (String key : insertMap.keySet()) {

            ErpBom erpBom = new ErpBom();
            String[] s = key.split("_");
            if(StringUtils.isEmpty(s[0]) || StringUtils.isEmpty(s[1])){
                continue;
            }
            if(s[1].equals("item")){continue;}
            //判断是否包含 数据库里
            if(!hadCoders.contains(s[1])){
                // 判断是否已经有了
                if(!models.contains(s[1])) {
                    ErpProductModel tmp = new ErpProductModel();
                    tmp.setCoder(s[1]);
                    tmp.setName("bom导入型号");
                    tmp.setProductLineId(1L);
                    tmp.setAddBy(1L);
                    tmp.setIsDelete(0);
                    tmp.setAddTime(new Date());
                    models.add(tmp);
                }
            }
            erpBom.setOriginalCoder(s[0]);
            erpBom.setProductModelCoder(s[1]);
            BigDecimal number = BigDecimal.valueOf(Double.valueOf(insertMap.get(key)));
            BigDecimal b = number.setScale(7,BigDecimal.ROUND_HALF_UP);// 保留小数点7位
            erpBom.setNumber(b);
            erpBom.setCreateTime(date);
            erpBoms.add(erpBom);

        }
        if(erpBoms.size() > 0) {
            erpBomMapper.batchInsert(erpBoms);
        }
        if(models.size() > 0){
            erpProductModelMapper.batchInsert(models);
        }
    }

    private void doUpdate(Map<String, String> updateMap) {
        List<ErpBom> erpBoms = new ArrayList<>();
        Date date = new Date();
        for (String key : updateMap.keySet()) {
            ErpBom erpBom = new ErpBom();
            String[] s = key.split("_");
            if(StringUtils.isEmpty(s[0]) || StringUtils.isEmpty(s[1])){
                continue;
            }
            if(s[1].equals("item")){continue;}
            erpBom.setOriginalCoder(s[0]);
            erpBom.setProductModelCoder(s[1]);
            BigDecimal number = BigDecimal.valueOf(Double.valueOf(updateMap.get(key)));
            erpBom.setNumber(number);
            erpBom.setCreateTime(date);
            erpBoms.add(erpBom);
        }
        if(erpBoms.size() > 0) {
            // erpBomMapper.updateBatch(erpBoms); // 不知道为什么用不了
            for (ErpBom erpBom : erpBoms) {
                erpBomMapper.updateItems(erpBom);
            }
        }
    }

    private void doDelete(Map<String, String> deleteMap) {
        List<ErpBom> erpBoms = new ArrayList<>();
        for (String key : deleteMap.keySet()) {
            ErpBom erpBom = new ErpBom();
            String[] s = key.split("_");
            if(StringUtils.isEmpty(s[0]) || StringUtils.isEmpty(s[1])){
                continue;
            }
            if(s[1].equals("item")){continue;}
            erpBom.setOriginalCoder(s[0]);
            erpBom.setProductModelCoder(s[1]);
            erpBom.setIsDelete(1);
            erpBom.setUpdateTime(new Date());
            erpBoms.add(erpBom);
        }
        if(erpBoms.size() > 0) {
            // erpBomMapper.changeDelBatch(erpBoms);// 不知道为什么用不了
            for (ErpBom erpBom : erpBoms) {
                erpBomMapper.deleteItem(erpBom);
            }
        }
    }


    private Map<String, String> transforExcel(List<Map<String, String>> importMap) {
        Map<String, String> list  = new HashMap<>(); // 导入数据
        String itemCode = "";
        for (Map<String, String> item : importMap) {
            itemCode = "";// 每一行循环的时候 itemCode重置
            for (String key : item.keySet()) {

                if(key.equals("rowNum")){continue;}
                if(key.equals("item_code")){
                    itemCode = item.get(key);
                }
                list.put(itemCode.replace(".0", "") + "_" + key.replace(".0", ""), item.get(key));
            }
        }
        return list;
    }

    //    取数据库数据
    public Map<String, String> getDbData() {
        ErpBom where = new ErpBom();
        where.setIsDelete(0);
        List<ErpBom> erpBoms = erpBomMapper.selectErpBomList(where);
        Map<String, String> list  = new HashMap<>(); 
        // 用原材料code + _ + 产品code 作为key
        for (ErpBom erpBom : erpBoms) {
            list.put(erpBom.getOriginalCoder() + "_" + erpBom.getProductModelCoder(), erpBom.getNumber().setScale(7,BigDecimal.ROUND_HALF_UP).toPlainString());
        }

        return list;
    }

}

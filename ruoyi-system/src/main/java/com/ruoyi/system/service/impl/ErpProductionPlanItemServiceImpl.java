package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.system.domain.ErpOriginalData;
import com.ruoyi.system.domain.PlanRequest;
import com.ruoyi.system.domain.vo.PlanVo;
import com.ruoyi.system.mapper.ErpOriginalDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ErpProductionPlanItemMapper;
import com.ruoyi.system.domain.ErpProductionPlanItem;
import com.ruoyi.system.service.IErpProductionPlanItemService;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于保存 每天生产计划所消耗的原材料数Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-03
 */
@Slf4j
@Service
public class ErpProductionPlanItemServiceImpl implements IErpProductionPlanItemService 
{
    @Resource
    private ErpProductionPlanItemMapper erpProductionPlanItemMapper;

    @Resource
    private ErpOriginalDataMapper erpOriginalDataMapper;
    /**
     * 查询用于保存 每天生产计划所消耗的原材料数
     * 
     * @param id 用于保存 每天生产计划所消耗的原材料数主键
     * @return 用于保存 每天生产计划所消耗的原材料数
     */
    @Override
    public ErpProductionPlanItem selectErpProductionPlanItemById(Long id)
    {
        return erpProductionPlanItemMapper.selectErpProductionPlanItemById(id);
    }

    /**
     * 查询用于保存 每天生产计划所消耗的原材料数列表
     * 
     * @param erpProductionPlanItem 用于保存 每天生产计划所消耗的原材料数
     * @return 用于保存 每天生产计划所消耗的原材料数
     */
    @Override
    public List<ErpProductionPlanItem> selectErpProductionPlanItemList(ErpProductionPlanItem erpProductionPlanItem)
    {
        return erpProductionPlanItemMapper.selectErpProductionPlanItemList(erpProductionPlanItem);
    }

    @Override
    public List<ErpProductionPlanItem> selectErpProductionPlanItemListGroupByProduct(ErpProductionPlanItem erpProductionPlanItem)
    {
        return erpProductionPlanItemMapper.selectErpProductionPlanItemListGroupByProduct(erpProductionPlanItem);
    }

    /**
     * 新增用于保存 每天生产计划所消耗的原材料数
     * 
     * @param erpProductionPlanItem 用于保存 每天生产计划所消耗的原材料数
     * @return 结果
     */
    @Override
    public int insertErpProductionPlanItem(ErpProductionPlanItem erpProductionPlanItem)
    {
        return erpProductionPlanItemMapper.insertErpProductionPlanItem(erpProductionPlanItem);
    }

    /**
     * 修改用于保存 每天生产计划所消耗的原材料数
     * 
     * @param erpProductionPlanItem 用于保存 每天生产计划所消耗的原材料数
     * @return 结果
     */
    @Override
    public int updateErpProductionPlanItem(ErpProductionPlanItem erpProductionPlanItem)
    {
        return erpProductionPlanItemMapper.updateErpProductionPlanItem(erpProductionPlanItem);
    }

    /**
     * 批量删除用于保存 每天生产计划所消耗的原材料数
     * 
     * @param ids 需要删除的用于保存 每天生产计划所消耗的原材料数主键
     * @return 结果
     */
    @Override
    public int deleteErpProductionPlanItemByIds(Long[] ids)
    {
        return erpProductionPlanItemMapper.deleteErpProductionPlanItemByIds(ids);
    }

    /**
     * 删除用于保存 每天生产计划所消耗的原材料数信息
     * 
     * @param id 用于保存 每天生产计划所消耗的原材料数主键
     * @return 结果
     */
    @Override
    public int deleteErpProductionPlanItemById(Long id)
    {
        return erpProductionPlanItemMapper.deleteErpProductionPlanItemById(id);
    }

    public List<PlanVo> oneDayPlan(PlanRequest param) {

        Integer day = param.getDay();
        // groupby code   sum number
        List<ErpProductionPlanItem> list = erpProductionPlanItemMapper.oneDayPlan(param);
        return null;

    }

    public List<PlanVo> daysPlan(PlanRequest param, HttpServletResponse response) {
        List<Integer> days = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Integer count = 7;
        if(param.getCount() != null){
            count = param.getCount();
        }
        Date date = new Date();
        if(param.getDay() != null){
            try {
                date = sdf.parse(param.getDay().toString());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        long time = date.getTime();
        for (int i = 0; i < count; i++) {
            time = time + 1000 * 60 * 60 * 24;
            date.setTime(time);
            String format = sdf.format(date);
            days.add(Integer.parseInt(format));
        }
        log.info("日期 {}" , JSON.toJSONString(days));
        param.setDays(days);

        // groupby code  date , sum number
        List<ErpProductionPlanItem> list = erpProductionPlanItemMapper.daysPlan(param);

        try {
            buildExcel(list, response, param.getDays());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void buildExcel(List<ErpProductionPlanItem> list,
                           HttpServletResponse response,
                           List<Integer> days) throws IOException {

        // 创建Excel文档
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // 创建表头
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("原料code");
        header.createCell(1).setCellValue("Desc.");
        header.createCell(2).setCellValue("Supplier");
        header.createCell(3).setCellValue("Planner");
        header.createCell(4).setCellValue("Site");
        header.createCell(5).setCellValue("Buyer");
        header.createCell(6).setCellValue("Special");
        header.createCell(7).setCellValue("type");

        // 日期找 列
        Map<Long, Integer > dateMap = new HashMap<>();
//        dateMap.put( 0L, 0);
        int i = 8;
        for (Integer day : days) {
            dateMap.put( Long.valueOf(day), i);
            header.createCell(i).setCellValue(day+"");
            i++;
        }
        log.info("=====dateMap :{}", JSON.toJSONString(dateMap));
        Map<String, List<ErpProductionPlanItem>> originKeyList = list.stream().collect(Collectors.groupingBy(ErpProductionPlanItem::getOriginalCoder));
        List<String> codes = new  ArrayList<>();
        for (String key : originKeyList.keySet()) {
            codes.add(key);
        }


        ErpOriginalData where = new ErpOriginalData();
        where.setIsDelete(0);
        List<ErpOriginalData> originals = erpOriginalDataMapper.selectErpOriginalDataList(where);
        Map<String, BigDecimal> coderTotalMap = originals.stream().collect(Collectors.toMap(ErpOriginalData::getCoder, ErpOriginalData::getTotal));
//        if(codes.size() > 100){
//
//        } else {
//
//        }
        Map<String, ErpOriginalData> originalDataMap = originals.stream().collect(Collectors.toMap(ErpOriginalData::getCoder, a -> a));

        // 填充数据
        int rowIndex = 1;
        for (String key : originKeyList.keySet()) {
            List<ErpProductionPlanItem> plans = originKeyList.get(key);
            plans = plans.stream().sorted(Comparator.comparing(ErpProductionPlanItem::getDate)).collect(Collectors.toList());
            // 当天消耗
            XSSFRow row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(key);// 编号
            if(originalDataMap.get(key) == null) {
                continue;
            }


            ErpOriginalData oData = originalDataMap.get(key);
            BigDecimal startNumber = coderTotalMap.get(key);

            if(true){
                // 以日期来导
                row.createCell(7).setCellValue("Daily Demand");// 类型
                setOrgindata(row, oData);

                XSSFRow row2 = sheet.createRow(rowIndex++);
                row2.createCell(7).setCellValue("Beginning Stock");// 类型
                setOrgindata(row2, oData);

                XSSFRow row3 = sheet.createRow(rowIndex++);
                row3.createCell(7).setCellValue("Incoming");// 类型
                setOrgindata(row3, oData);

                XSSFRow row4 = sheet.createRow(rowIndex++);
                row4.createCell(7).setCellValue("Ending Stock");// 类型
                setOrgindata(row4, oData);

                XSSFRow row5 = sheet.createRow(rowIndex++);
                row5.createCell(7).setCellValue("Difference");// 类型
                setOrgindata(row5, oData);
                for (Long dayDate : dateMap.keySet()) {
                    Integer cell = dateMap.get(dayDate);
                    ErpProductionPlanItem oneDayData = null;
                    for (ErpProductionPlanItem plan : plans) {
                        if(dayDate.equals(plan.getDate())){
                            oneDayData = plan;
                            break;
                        }
                    }
                    if(oneDayData == null){// 当天没数据
                        // 开始数据就是 结束数据 当日变化为0
                        row.createCell(cell).setCellValue(0 + "\t");
                        row2.createCell(cell).setCellValue(startNumber.stripTrailingZeros().toPlainString() + "\t");
                        row4.createCell(cell).setCellValue(startNumber.stripTrailingZeros().toPlainString() + "\t");
                    } else {// 当天有数据
                        // 如果有数据
                        row.createCell(cell).setCellValue(oneDayData.getNumber().stripTrailingZeros().toPlainString() + "\t");
                        row2.createCell(cell).setCellValue(startNumber.stripTrailingZeros().toPlainString() + "\t");
                        startNumber = startNumber.subtract(oneDayData.getNumber());
                        row4.createCell(cell).setCellValue(startNumber.stripTrailingZeros().toPlainString() + "\t");
                    }
                }
            } else {

                setOrgindata(row, oData);
                row.createCell(7).setCellValue("Daily Demand");// 类型

                // 之前如果没有计划为空的模式
                for (ErpProductionPlanItem plan : plans) {
                    Integer cell = dateMap.get(plan.getDate());
                    row.createCell(cell).setCellValue(plan.getNumber().stripTrailingZeros().toPlainString() + "\t");
                }

                // 当天起始/结束库存
                XSSFRow row2 = sheet.createRow(rowIndex++);
                XSSFRow row3 = sheet.createRow(rowIndex++);
                XSSFRow row4 = sheet.createRow(rowIndex++);
                XSSFRow row5 = sheet.createRow(rowIndex++);
                row2.createCell(7).setCellValue("Beginning Stock");// 类型
                setOrgindata(row2, oData);
                row3.createCell(7).setCellValue("Incoming");// 类型
                setOrgindata(row3, oData);
                row4.createCell(7).setCellValue("Ending Stock");// 类型
                setOrgindata(row4, oData);
                row5.createCell(7).setCellValue("Difference");// 类型
                setOrgindata(row5, oData);

                if (startNumber == null) {
                    continue;
                }

                for (ErpProductionPlanItem plan : plans) {
                    // log.info("plan.getDate() :{}", plan.getDate());
                    Integer cell = dateMap.get(plan.getDate());
                    row2.createCell(cell).setCellValue(startNumber.stripTrailingZeros().toPlainString() + "\t");
                    startNumber = startNumber.subtract(plan.getNumber());
                    row4.createCell(cell).setCellValue(startNumber.stripTrailingZeros().toPlainString() + "\t");
                }
            }
        }


        // 设置响应头信息
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=future_"+days.size()+"day.xlsx");

        // 将Excel文档写入响应流中
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private void setOrgindata(XSSFRow row, ErpOriginalData oData) {
        row.createCell(0).setCellValue(oData.getCoder());
        row.createCell(1).setCellValue(oData.getDesc());// 介绍
        row.createCell(2).setCellValue(oData.getSuppier());// 供应商
        row.createCell(3).setCellValue(oData.getPlannerCode());// 计划
        row.createCell(4).setCellValue(oData.getDeliverySite());// 产地
        row.createCell(5).setCellValue(oData.getBuyer());// 卖家
        row.createCell(6).setCellValue(oData.getSpecial() == 1?"y":"n");// 特殊
    }
}

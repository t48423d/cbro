package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ErpBom;

/**
 * 商品由 具体原件组成关联Service接口
 * 
 * @author ruoyi
 * @date 2023-06-04
 */
public interface IErpBomService 
{
    /**
     * 查询商品由 具体原件组成关联
     * 
     * @param id 商品由 具体原件组成关联主键
     * @return 商品由 具体原件组成关联
     */
    public ErpBom selectErpBomById(Long id);

    /**
     * 查询商品由 具体原件组成关联列表
     * 
     * @param erpBom 商品由 具体原件组成关联
     * @return 商品由 具体原件组成关联集合
     */
    public List<ErpBom> selectErpBomList(ErpBom erpBom);

    /**
     * 新增商品由 具体原件组成关联
     * 
     * @param erpBom 商品由 具体原件组成关联
     * @return 结果
     */
    public int insertErpBom(ErpBom erpBom);

    /**
     * 修改商品由 具体原件组成关联
     * 
     * @param erpBom 商品由 具体原件组成关联
     * @return 结果
     */
    public int updateErpBom(ErpBom erpBom);

    /**
     * 批量删除商品由 具体原件组成关联
     * 
     * @param ids 需要删除的商品由 具体原件组成关联主键集合
     * @return 结果
     */
    public int deleteErpBomByIds(Long[] ids);

    /**
     * 删除商品由 具体原件组成关联信息
     * 
     * @param id 商品由 具体原件组成关联主键
     * @return 结果
     */
    public int deleteErpBomById(Long id);
}

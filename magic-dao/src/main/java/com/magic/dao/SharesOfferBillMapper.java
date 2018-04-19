package com.magic.dao;

import com.magic.entity.Dictionary;
import com.magic.entity.SharesOfferBill;
import com.magic.vo.SharesOfferBillVo;

import java.util.List;

public interface SharesOfferBillMapper {
    int deleteByPrimaryKey(String id);

    int insert(SharesOfferBill record);

    int insertSelective(SharesOfferBill record);

    int updateByPrimaryKeySelective(SharesOfferBill record);

    int updateByPrimaryKey(SharesOfferBill record);

    /**
     * 获取报价单
     * 根据主键
     * @param id
     * @return
     */
    SharesOfferBill selectByPrimaryKey(String id);

    /**
     * 获取报价单
     * 根据实体参数
     * @param dictionary
     * @return
     */
    List<SharesOfferBillVo> selectBySelective(SharesOfferBillVo dictionary);

    /**
     * 获取报价单数据总数
     * 根据查询实体的参数
     * @param dictionary
     * @return
     */
    int selectCountBySelective(SharesOfferBillVo dictionary);
}
package com.magic.service;

import com.magic.common.ResultVo;
import com.magic.entity.SharesAccountInfo;
import com.magic.entity.SharesHistoryData;
import com.magic.entity.SharesWarehouseInfo;
import com.magic.utils.PageBean;
import com.magic.vo.SharesOfferBillVo;
import com.magic.vo.SharesWarehouseInfoVo;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/19
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
public interface SharesWarehouseInfoService {
    /**
     * 添加持仓信息
     * @param sharesWarehouseInfo
     * @return
     */
    public ResultVo<SharesWarehouseInfo> saveSharesWarehouseInfo(SharesWarehouseInfo sharesWarehouseInfo);

    /**
     * 修改持仓信息
     * @param sharesWarehouseInfo
     * @return
     */
    public ResultVo<SharesWarehouseInfo> updateSharesWarehouseInfo(SharesWarehouseInfo sharesWarehouseInfo);

    /**
     * 删除持仓信息
     * 逻辑删除
     * @param id
     * @return
     */
    public ResultVo<SharesWarehouseInfo> deleteSharesWarehouseInfoForUpdate(String id);

    /**
     * 删除持仓信息
     * 物理删除
     * @param id
     * @return
     */
    public ResultVo<SharesWarehouseInfo> deleteSharesWarehouseInfoForDelete(String id);

    /**
     * 查询持仓信息
     * 分页专用
     * @param sharesWarehouseInfoVo
     * @param pageBean
     * @return
     */
    public PageBean<SharesWarehouseInfoVo> querySharesWarehouseInfo(SharesWarehouseInfoVo sharesWarehouseInfoVo, PageBean pageBean);

    /**
     * 查询持仓信息
     * 根据id
     * @param id
     * @return
     */
    public ResultVo<SharesWarehouseInfoVo> querySharesWarehouseInfo(String id);

    /**
     * 查询持仓信息
     * 根据查询实体
     * @param sharesWarehouseInfoVo
     * @return
     */
    public ResultVo<SharesWarehouseInfoVo> querySharesWarehouseInfo(SharesWarehouseInfoVo sharesWarehouseInfoVo);

    /**
     * 验证报价单交易
     * @param sharesHistoryData
     */
    public boolean validationDeal(SharesHistoryData sharesHistoryData, SharesOfferBillVo sharesOfferBillVo);

    /**
     * 进行交易
     * @param sharesHistoryData
     * @param sharesOfferBillVo
     * @param sharesAccountInfo
     * @return
     */
    public ResultVo deal(SharesHistoryData sharesHistoryData, SharesOfferBillVo sharesOfferBillVo, SharesAccountInfo sharesAccountInfo);
}

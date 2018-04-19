package com.magic.service;

import com.magic.common.ResultVo;
import com.magic.entity.SharesOfferBill;
import com.magic.utils.PageBean;
import com.magic.vo.SharesOfferBillVo;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/19
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public interface SharesOfferBillService {

    /**
     * 添加报价单
     * @param sharesOfferBill
     * @return
     */
    public ResultVo<SharesOfferBill> saveSharesOfferBill(SharesOfferBill sharesOfferBill);

    /**
     * 修改报价单
     * @param sharesOfferBill
     * @return
     */
    public ResultVo<SharesOfferBill> updateSharesOfferBill(SharesOfferBill sharesOfferBill);

    /**
     * 删除报价单
     * 逻辑删除
     * @param id
     * @return
     */
    public ResultVo<SharesOfferBill> deleteSharesOfferBillForUpdate(String id);

    /**
     * 删除报价单
     * 物理删除
     * @param id
     * @return
     */
    public ResultVo<SharesOfferBill> deleteSharesOfferBillForDelete(String id);

    /**
     * 查询报价单
     * 分页专用
     * @param sharesOfferBillVo
     * @param pageBean
     * @return
     */
    public PageBean<SharesOfferBillVo> querySharesOfferBill(SharesOfferBillVo sharesOfferBillVo, PageBean pageBean);

    /**
     * 查询报价单
     * 根据id
     * @param id
     * @return
     */
    public ResultVo<SharesOfferBillVo> querySharesOfferBill(String id);

    /**
     * 查询报价单
     * 根据查询实体
     * @param sharesOfferBillVo
     * @return
     */
    public ResultVo<SharesOfferBillVo> querySharesOfferBill(SharesOfferBillVo sharesOfferBillVo);

    /**
     * 取消报价单
     * @param id
     * @return
     */
    public ResultVo cancelSharesOfferBill(String id);
}

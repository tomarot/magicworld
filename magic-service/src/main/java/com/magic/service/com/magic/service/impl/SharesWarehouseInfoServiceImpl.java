package com.magic.service.com.magic.service.impl;

import com.magic.annotation.SystemServiceLog;
import com.magic.common.ResultVo;
import com.magic.common.WebConstants;
import com.magic.dao.SharesAccountInfoMapper;
import com.magic.dao.SharesOfferBillMapper;
import com.magic.dao.SharesWarehouseInfoMapper;
import com.magic.entity.SharesAccountInfo;
import com.magic.entity.SharesHistoryData;
import com.magic.entity.SharesWarehouseInfo;
import com.magic.service.SharesWarehouseInfoService;
import com.magic.utils.DoubleUtils;
import com.magic.utils.PageBean;
import com.magic.utils.UUIDUtils;
import com.magic.vo.SharesOfferBillVo;
import com.magic.vo.SharesWarehouseInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/19
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SharesWarehouseInfoServiceImpl implements SharesWarehouseInfoService {

    @Autowired
    private SharesWarehouseInfoMapper sharesWarehouseInfoMapper;
    @Autowired
    private SharesAccountInfoMapper sharesAccountInfoMapper;
    @Autowired
    private SharesOfferBillMapper sharesOfferBillMapper;

    @Override
    public ResultVo<SharesWarehouseInfo> saveSharesWarehouseInfo(SharesWarehouseInfo sharesWarehouseInfo) {
        return null;
    }

    @Override
    public ResultVo<SharesWarehouseInfo> updateSharesWarehouseInfo(SharesWarehouseInfo sharesWarehouseInfo) {
        return null;
    }

    @Override
    public ResultVo<SharesWarehouseInfo> deleteSharesWarehouseInfoForUpdate(String id) {
        return null;
    }

    @Override
    public ResultVo<SharesWarehouseInfo> deleteSharesWarehouseInfoForDelete(String id) {
        return null;
    }

    @Override
    public PageBean<SharesWarehouseInfoVo> querySharesWarehouseInfo(SharesWarehouseInfoVo sharesWarehouseInfoVo, PageBean pageBean) {
        return null;
    }

    @Override
    public ResultVo<SharesWarehouseInfoVo> querySharesWarehouseInfo(String id) {
        return null;
    }

    @Override
    public ResultVo<SharesWarehouseInfoVo> querySharesWarehouseInfo(SharesWarehouseInfoVo sharesWarehouseInfoVo) {
        ResultVo<SharesWarehouseInfoVo> resultVo = new ResultVo<SharesWarehouseInfoVo>();
        List<SharesWarehouseInfoVo> resultList = sharesWarehouseInfoMapper.selectBySelective(sharesWarehouseInfoVo);
        resultVo.setDataList(resultList);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @SystemServiceLog(module = "magic",option = "交易验证",description = "根据报价单对单日价格是否满足交易条件进行验证")
    public boolean validationDeal(SharesHistoryData sharesHistoryData, SharesOfferBillVo sharesOfferBillVo) {
        //获取报价单类型
        if(WebConstants.GP.SharesOfferBill.BILLTYPE_BUY.equals(sharesOfferBillVo.getQuotationbilltype())){//买入单
            //获取当日价格的最高价
            Double highPrice = sharesHistoryData.getHighPrice();
            //获取报价单价格
            Double billPrice = sharesOfferBillVo.getPrice();
            int result = highPrice.compareTo(billPrice);
            if(result>=0){//最高价格大于等于报价单价格，验证交易达成。
                return true;
            }
        }else{//卖出单
            //获取当日价格的最低价
            Double highPrice = sharesHistoryData.getLowPrice();
            //获取报价单价格
            Double billPrice = sharesOfferBillVo.getPrice();
            int result = highPrice.compareTo(billPrice);
            if(result<=0){//最低价格小于等于报价单价格，验证交易达成
                return true;
            }
        }
        return false;
    }

    @Override
    @SystemServiceLog(module = "magic",option = "根据报价单进行交易",description = "根据报价单进行交易")
    @Transactional
    public ResultVo deal(SharesHistoryData sharesHistoryData, SharesOfferBillVo sharesOfferBillVo, SharesAccountInfo sharesAccountInfo) {
        ResultVo vo = new ResultVo();
        //获取持仓信息
        SharesWarehouseInfoVo sharesWarehouseInfoVo = null;//当前持仓信息
        SharesWarehouseInfoVo selectVo = new SharesWarehouseInfoVo();
        List<SharesWarehouseInfoVo> warehouseInfoVoList = sharesWarehouseInfoMapper.selectBySelective(selectVo);
        if(warehouseInfoVoList!=null&&warehouseInfoVoList.size()>0){
            sharesWarehouseInfoVo = warehouseInfoVoList.get(0);
        }
        //更新持仓信息
        if(WebConstants.GP.SharesOfferBill.BILLTYPE_BUY.equals(sharesOfferBillVo.getQuotationbilltype())){//买入单
            SharesWarehouseInfo sharesWarehouseInfo = new SharesWarehouseInfo();
            if(sharesWarehouseInfoVo==null){
                sharesWarehouseInfo.setAccountid(sharesAccountInfo.getId());
                sharesWarehouseInfo.setId(UUIDUtils.getUUID());
                sharesWarehouseInfo.setIsdel(WebConstants.NO);
                sharesWarehouseInfo.setCreatetime(new Date());
                sharesWarehouseInfo.setNum(sharesOfferBillVo.getNum());
                sharesWarehouseInfo.setCost(sharesOfferBillVo.getPrice());
                sharesWarehouseInfo.setSharesid(sharesHistoryData.getSharesId());
                sharesWarehouseInfoMapper.insertSelective(sharesWarehouseInfo);
            }else{
                //计算新成本价
                Integer newNum = sharesWarehouseInfoVo.getNum()+sharesOfferBillVo.getNum();
                Double newCost = sharesWarehouseInfoVo.getCost()* sharesWarehouseInfoVo.getNum()+sharesOfferBillVo.getPrice()*sharesOfferBillVo.getNum()/newNum;

                sharesWarehouseInfo.setAccountid(sharesAccountInfo.getId());
                sharesWarehouseInfo.setId(sharesWarehouseInfoVo.getId());
                sharesWarehouseInfo.setIsdel(WebConstants.NO);
                sharesWarehouseInfo.setUpdatetime(new Date());
                sharesWarehouseInfo.setNum(newNum);
                sharesWarehouseInfo.setCost(newCost);
                sharesWarehouseInfo.setSharesid(sharesHistoryData.getSharesId());
                sharesWarehouseInfoMapper.updateByPrimaryKeySelective(sharesWarehouseInfo);
            }
            //更新账户信息
            Double money = sharesOfferBillVo.getPrice()*sharesOfferBillVo.getNum();
            sharesAccountInfo.setAvailablemoney(DoubleUtils.sub(sharesAccountInfo.getAvailablemoney(),money));
            sharesAccountInfo.setUpdatetime(new Date());
            sharesAccountInfoMapper.updateByPrimaryKeySelective(sharesAccountInfo);
            //添加操作记录

            //删除报价单
            sharesOfferBillVo.setState(WebConstants.NO);
            sharesOfferBillMapper.updateByPrimaryKeySelective(sharesOfferBillVo);
        }else{

        }
        return vo;
    }

}

package com.magic.service.com.magic.service.impl;

import com.magic.common.ResultVo;
import com.magic.common.WebConstants;
import com.magic.entity.SharesAccountInfo;
import com.magic.entity.SharesHistoryData;
import com.magic.entity.SharesWarehouseInfo;
import com.magic.service.*;
import com.magic.vo.KChartDataVo;
import com.magic.vo.SharesOfferBillVo;
import com.magic.vo.SharesWarehouseInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/27
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SharesGameRecordServiceImpl implements SharesGameRecordService {
    @Autowired
    public SharesHistoryDataService sharesHistoryDataService;
    @Autowired
    public SharesAccountInfoService sharesAccountInfoService;
    @Autowired
    public SharesWarehouseInfoService sharesWarehouseInfoService;
    @Autowired
    public SharesOfferBillService sharesOfferBillService;

    @Override
    public ResultVo getGameInfoData(String gameCode,String next){
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        Map<String,Object> resultMap = new HashMap<String,Object>();
        //k线数据
        KChartDataVo kChartDataVo = sharesHistoryDataService.queryGameKChartData(gameCode,next);
        resultMap.put("kChartData",kChartDataVo);
        //最新价格信息
        SharesHistoryData sharesHistoryData = null;
        List<SharesHistoryData> list = kChartDataVo.getDataList();
        if(list!=null&&list.size()>0){
            sharesHistoryData = list.get(list.size()-1);
        }
        resultMap.put("currentKData",sharesHistoryData);

        //获取账号信息
        ResultVo<SharesAccountInfo> vo = sharesAccountInfoService.querySharesAccountInfoForGameCode(gameCode);
        SharesAccountInfo sharesAccountInfo = (SharesAccountInfo) vo.getData();
        resultMap.put("sharesAccountInfo",sharesAccountInfo);

        //获取报价单信息
        SharesOfferBillVo sharesOfferBillVo = new SharesOfferBillVo();
        sharesOfferBillVo.setAccountid(sharesAccountInfo.getId());
        sharesOfferBillVo.setState(WebConstants.YES);
        ResultVo<SharesOfferBillVo> resultSharesOfferVo= sharesOfferBillService.querySharesOfferBill(sharesOfferBillVo);
        List<SharesOfferBillVo> resultSharesOfferVolist = resultSharesOfferVo.getDataList();
        if(resultSharesOfferVolist==null||resultSharesOfferVolist.size()==0){
            resultMap.put("sharesOfferBillInfo",null);
        }
        else if(resultSharesOfferVolist!=null&&resultSharesOfferVolist.size()>0){
            sharesOfferBillVo = resultSharesOfferVolist.get(0);
            resultMap.put("sharesOfferBillInfo",resultSharesOfferVolist.get(0));
        }else{
            try {
                throw new Exception("发现多条报价单数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //进行交易
        if("y".equals(next)&&resultSharesOfferVolist!=null&&resultSharesOfferVolist.size()>0){
            if(sharesWarehouseInfoService.validationDeal(sharesHistoryData,sharesOfferBillVo)){
                ResultVo resultVo1 = sharesWarehouseInfoService.deal(sharesHistoryData,sharesOfferBillVo,sharesAccountInfo);
                //交易成功清除报价单
                resultMap.put("sharesOfferBillInfo",null);
            }
        }


        //获取持仓信息
        SharesWarehouseInfoVo sharesWarehouseInfoVo = new SharesWarehouseInfoVo();
        sharesWarehouseInfoVo.setAccountid(sharesAccountInfo.getId());
        ResultVo<SharesWarehouseInfoVo> SharesWarehouseInfoVo= sharesWarehouseInfoService.querySharesWarehouseInfo(sharesWarehouseInfoVo);
        List<SharesWarehouseInfoVo> SharesWarehouseInfoVolist = SharesWarehouseInfoVo.getDataList();
        if(SharesWarehouseInfoVolist==null||SharesWarehouseInfoVolist.size()==0){
            resultMap.put("sharesWarehouseInfo",null);
        }
        else if(SharesWarehouseInfoVolist!=null&&SharesWarehouseInfoVolist.size()==1){
            resultMap.put("sharesWarehouseInfo",SharesWarehouseInfoVolist.get(0));
        }else{
            try {
                throw new Exception("发现多条持仓数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resultVo.setData(resultMap);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }
}

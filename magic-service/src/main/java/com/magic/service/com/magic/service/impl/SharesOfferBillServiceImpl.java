package com.magic.service.com.magic.service.impl;

import com.magic.annotation.SystemServiceLog;
import com.magic.common.ResultVo;
import com.magic.common.WebConstants;
import com.magic.dao.SharesOfferBillMapper;
import com.magic.entity.SharesOfferBill;
import com.magic.service.SharesOfferBillService;
import com.magic.utils.PageBean;
import com.magic.utils.UUIDUtils;
import com.magic.vo.SharesOfferBillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
public class SharesOfferBillServiceImpl implements SharesOfferBillService {

    @Autowired
    private SharesOfferBillMapper sharesOfferBillMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    @SystemServiceLog(module = "magic",option = "增改报价单",description = "增改报价单")
    public ResultVo<SharesOfferBill> saveSharesOfferBill(SharesOfferBill sharesOfferBill) {
        ResultVo<SharesOfferBill> resultVo = new ResultVo<SharesOfferBill>();
        //验证当前账号中是否已经有存在的
        SharesOfferBillVo vo = new SharesOfferBillVo();
        vo.setAccountid(sharesOfferBill.getAccountid());
        vo.setState(WebConstants.YES);
        List<SharesOfferBillVo>  billList = sharesOfferBillMapper.selectBySelective(vo);
        if(billList!=null&&billList.size()>0){
            resultVo.setMessage("存在未成交的报价单");
            return resultVo;
        }

        sharesOfferBill.setId(UUIDUtils.getUUID());
        sharesOfferBill.setCreatetime(new Date());
        sharesOfferBill.setIsdel(WebConstants.NO);
        sharesOfferBill.setState(WebConstants.YES);
        sharesOfferBillMapper.insertSelective(sharesOfferBill);
        resultVo.setData(sharesOfferBill);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    public ResultVo<SharesOfferBill> updateSharesOfferBill(SharesOfferBill sharesOfferBill) {
        return null;
    }

    @Override
    public ResultVo<SharesOfferBill> deleteSharesOfferBillForUpdate(String id) {
        return null;
    }

    @Override
    public ResultVo<SharesOfferBill> deleteSharesOfferBillForDelete(String id) {
        return null;
    }

    @Override
    public PageBean<SharesOfferBillVo> querySharesOfferBill(SharesOfferBillVo sharesOfferBillVo, PageBean pageBean) {
        return null;
    }

    @Override
    public ResultVo<SharesOfferBillVo> querySharesOfferBill(String id) {
        return null;
    }

    @Override
    public ResultVo<SharesOfferBillVo> querySharesOfferBill(SharesOfferBillVo sharesOfferBillVo) {
        ResultVo<SharesOfferBillVo> resultVo = new ResultVo<SharesOfferBillVo>();
        List<SharesOfferBillVo> resultList = sharesOfferBillMapper.selectBySelective(sharesOfferBillVo);
        resultVo.setDataList(resultList);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @Transactional
    @SystemServiceLog(module = "magic",option = "取消报价单",description = "取消报价单")
    public ResultVo cancelSharesOfferBill(String id) {
        ResultVo resultVo = new ResultVo();

        SharesOfferBill sharesOfferBill = sharesOfferBillMapper.selectByPrimaryKey(id);
        sharesOfferBill.setState(WebConstants.NO);
        sharesOfferBillMapper.updateByPrimaryKeySelective(sharesOfferBill);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }
}

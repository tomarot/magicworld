package com.magic.service.com.magic.service.impl;

import com.magic.annotation.SystemServiceLog;
import com.magic.common.ResultVo;
import com.magic.common.WebConstants;
import com.magic.dao.SharesAccountInfoMapper;
import com.magic.dao.SharesGameRecordMapper;
import com.magic.entity.SharesAccountInfo;
import com.magic.entity.SharesGameRecord;
import com.magic.service.SharesAccountInfoService;
import com.magic.utils.PageBean;
import com.magic.utils.UUIDUtils;
import com.magic.vo.SharesAccountInfoVo;
import com.magic.vo.SharesGameRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
public class SharesAccountInfoServiceImpl implements SharesAccountInfoService {

    @Autowired
    private SharesAccountInfoMapper sharesAccountInfoMapper;
    @Autowired
    private SharesGameRecordMapper sharesGameRecordMapper;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    @SystemServiceLog(module = "magic",option = "添加账户",description = "添加账户")
    public ResultVo<SharesAccountInfo> saveSharesAccountInfo(SharesAccountInfo sharesAccountInfo) {
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        sharesAccountInfo.setId(UUIDUtils.getUUID());
        sharesAccountInfo.setCreatetime(new Date());
        sharesAccountInfoMapper.insertSelective(sharesAccountInfo);
        resultVo.setData(sharesAccountInfo);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    @SystemServiceLog(module = "magic",option = "修改账户",description = "修改账户")
    public ResultVo<SharesAccountInfo> updateSharesAccountInfo(SharesAccountInfo sharesAccountInfo) {
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        sharesAccountInfo.setUpdatetime(new Date());
        int resultNum = sharesAccountInfoMapper.updateByPrimaryKeySelective(sharesAccountInfo);
        resultVo.setData(resultNum);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    @SystemServiceLog(module = "magic",option = "删除账户（逻辑删除）",description = "删除账户（逻辑删除）根据数据主键")
    public ResultVo<SharesAccountInfo> deleteSharesAccountInfoForUpdate(String id) {
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        SharesAccountInfo sharesAccountInfo = sharesAccountInfoMapper.selectByPrimaryKey(id);
        sharesAccountInfo.setUpdatetime(new Date());
        sharesAccountInfo.setIsdel(WebConstants.YES);
        int resultNum = sharesAccountInfoMapper.updateByPrimaryKeySelective(sharesAccountInfo);
        resultVo.setData(resultNum);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    @SystemServiceLog(module = "magic",option = "删除账户（物理删除）",description = "删除账户（物理删除）根据数据主键")
    public ResultVo<SharesAccountInfo> deleteSharesAccountInfoForDelete(String id) {
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        int resultNum = sharesAccountInfoMapper.deleteByPrimaryKey(id);
        resultVo.setData(resultNum);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    public PageBean<SharesAccountInfoVo> querySharesAccountInfo(SharesAccountInfoVo sharesAccountInfoVo, PageBean pageBean) {
        PageBean<SharesAccountInfoVo> resultPageBean = new PageBean<SharesAccountInfoVo>();
        sharesAccountInfoVo.setPage(pageBean.getStartRowNum());
        sharesAccountInfoVo.setLimit(pageBean.getEndRowNum());
        sharesAccountInfoVo.setIsdel(WebConstants.NO);
        List<SharesAccountInfoVo> resultList = sharesAccountInfoMapper.selectBySelective(sharesAccountInfoVo);
        int count = sharesAccountInfoMapper.selectCountBySelective(sharesAccountInfoVo);
        resultPageBean.setRows(resultList);
        resultPageBean.setTotal(count);
        resultPageBean.setResultCode(WebConstants.layuiRequestCode);
        return resultPageBean;
    }

    @Override
    public ResultVo<SharesAccountInfo> querySharesAccountInfo(String id) {
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        SharesAccountInfo vo = sharesAccountInfoMapper.selectByPrimaryKey(id);
        resultVo.setData(vo);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    public ResultVo<SharesAccountInfoVo> querySharesAccountInfo(SharesAccountInfoVo sharesAccountInfoVo) {
        ResultVo<SharesAccountInfoVo> resultVo = new ResultVo<SharesAccountInfoVo>();
        List<SharesAccountInfoVo> resultList = sharesAccountInfoMapper.selectBySelective(sharesAccountInfoVo);
        resultVo.setDataList(resultList);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }

    @Override
    public ResultVo<SharesAccountInfo> querySharesAccountInfoForGameCode(String gameCode) {
        ResultVo<SharesAccountInfo> resultVo = new ResultVo<SharesAccountInfo>();
        //获得游戏记录id
        SharesGameRecordVo sharesGameRecordVo = new SharesGameRecordVo();
        sharesGameRecordVo.setGamecode(gameCode);
        sharesGameRecordVo.setId(WebConstants.NO);
        List<SharesGameRecord> gameList = sharesGameRecordMapper.selectBySelective(sharesGameRecordVo);
        SharesGameRecord sharesGameRecord = gameList.get(0);
        //获得账号信息
        SharesAccountInfo sharesAccountInfo = sharesAccountInfoMapper.selectByPrimaryKey(sharesGameRecord.getAccountid());
        resultVo.setData(sharesAccountInfo);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }
}

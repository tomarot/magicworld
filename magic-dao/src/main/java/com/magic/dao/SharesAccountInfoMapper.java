package com.magic.dao;

import com.magic.entity.SharesAccountInfo;
import com.magic.vo.SharesAccountInfoVo;

import java.util.List;

public interface SharesAccountInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SharesAccountInfo record);

    int insertSelective(SharesAccountInfo record);

    SharesAccountInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SharesAccountInfo record);

    int updateByPrimaryKey(SharesAccountInfo record);

    List<SharesAccountInfoVo> selectBySelective(SharesAccountInfoVo sharesAccountInfoVo);

    int selectCountBySelective(SharesAccountInfoVo sharesAccountInfoVo);


}
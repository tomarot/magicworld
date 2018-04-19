package com.magic.dao;

import com.magic.entity.SharesWarehouseInfo;
import com.magic.vo.SharesWarehouseInfoVo;

import java.util.List;

public interface SharesWarehouseInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SharesWarehouseInfo record);

    int insertSelective(SharesWarehouseInfo record);

    SharesWarehouseInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SharesWarehouseInfo record);

    int updateByPrimaryKey(SharesWarehouseInfo record);

    List<SharesWarehouseInfoVo> selectBySelective(SharesWarehouseInfoVo sharesWarehouseInfoVo);
}
package com.magic.dao;

import com.magic.entity.SharesDealHistory;

public interface SharesDealHistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(SharesDealHistory record);

    int insertSelective(SharesDealHistory record);

    SharesDealHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SharesDealHistory record);

    int updateByPrimaryKey(SharesDealHistory record);
}
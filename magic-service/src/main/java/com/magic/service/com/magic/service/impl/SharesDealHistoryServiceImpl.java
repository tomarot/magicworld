package com.magic.service.com.magic.service.impl;

import com.magic.common.ResultVo;
import com.magic.dao.SharesDealHistoryMapper;
import com.magic.entity.SharesDealHistory;
import com.magic.service.SharesDealHistoryService;
import com.magic.utils.PageBean;
import com.magic.vo.SharesDealHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/19
 * Time: 22:45
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SharesDealHistoryServiceImpl implements SharesDealHistoryService {

    @Autowired
    private SharesDealHistoryMapper sharesDealHistoryMapper;

    @Override
    public ResultVo<SharesDealHistory> saveSharesDealHistory(SharesDealHistory sharesDealHistory) {
        return null;
    }

    @Override
    public ResultVo<SharesDealHistory> updateSharesDealHistory(SharesDealHistory sharesDealHistory) {
        return null;
    }

    @Override
    public ResultVo<SharesDealHistory> deleteSharesDealHistoryForUpdate(String id) {
        return null;
    }

    @Override
    public ResultVo<SharesDealHistory> deleteSharesDealHistoryForDelete(String id) {
        return null;
    }

    @Override
    public PageBean<SharesDealHistoryVo> querySharesDealHistory(SharesDealHistoryVo sharesDealHistoryVo, PageBean pageBean) {
        return null;
    }

    @Override
    public ResultVo<SharesDealHistoryVo> querySharesDealHistory(String id) {
        return null;
    }

    @Override
    public ResultVo<SharesDealHistoryVo> querySharesDealHistory(SharesDealHistoryVo sharesDealHistoryVo) {
        return null;
    }
}

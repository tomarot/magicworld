package com.magic.service.com.magic.service.impl;

import com.magic.dao.SharesDao;
import com.magic.entity.Shares;
import com.magic.service.SharesService;
import com.magic.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 17-9-15
 * Time: 下午5:01
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SharesServiceImpl implements SharesService {

    @Autowired
    private SharesDao sharesDao;
    @Override
    public List<Shares> querySharesList() {
        return sharesDao.querySharesList();
    }

    @Override
    public PageBean<Shares> querySharesListForPage(Shares shares, PageBean<Shares> pageBean) {
        List<Shares> sharesList = sharesDao.querySharesListForPage(shares,pageBean.getPage(),pageBean.getLimit());
       // int count = sharesDao.querySharesCountForPage(shares,pageBean.getPageNum(),pageBean.getPageSize());
        pageBean.setTotal(2);
        pageBean.setRows(sharesList);
        return pageBean;
    }

    @Override
    public Shares getShares(Integer id) {
        return sharesDao.getShares(id);
    }

    @Override
    public Shares getShares(String code) {
        return sharesDao.getShares(code);
    }
}

package com.magic.service;

import com.magic.entity.Shares;
import com.magic.utils.PageBean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 17-9-15
 * Time: 下午4:57
 * To change this template use File | Settings | File Templates.
 */
public interface SharesService {

    /**
     * 获取所有股票数据
     * @return
     */
    public List<Shares> querySharesList();

    /**
     * 获取股票分页获取
     * @param shares
     * @return
     */
    public PageBean<Shares> querySharesListForPage(Shares shares,PageBean<Shares> pageBean);

    /**
     * 获取股票数据 根据数据编号
     * @param id
     * @return
     */
    public Shares getShares(Integer id);

    /**
     * 获取股票数据
     * @param code
     * @return
     */
    public Shares getShares(String code);
}

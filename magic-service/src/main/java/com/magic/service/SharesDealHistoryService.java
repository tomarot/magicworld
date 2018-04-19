package com.magic.service;

import com.magic.common.ResultVo;
import com.magic.entity.SharesDealHistory;
import com.magic.utils.PageBean;
import com.magic.vo.SharesDealHistoryVo;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/19
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */
public interface SharesDealHistoryService {

    /**
     * 添加交易历史记录
     * @param sharesDealHistory
     * @return
     */
    public ResultVo<SharesDealHistory> saveSharesDealHistory(SharesDealHistory sharesDealHistory);

    /**
     * 修改交易历史记录
     * @param sharesDealHistory
     * @return
     */
    public ResultVo<SharesDealHistory> updateSharesDealHistory(SharesDealHistory sharesDealHistory);

    /**
     * 删除交易历史记录
     * 逻辑删除
     * @param id
     * @return
     */
    public ResultVo<SharesDealHistory> deleteSharesDealHistoryForUpdate(String id);

    /**
     * 删除交易历史记录
     * 物理删除
     * @param id
     * @return
     */
    public ResultVo<SharesDealHistory> deleteSharesDealHistoryForDelete(String id);

    /**
     * 查询交易历史记录
     * 分页专用
     * @param sharesDealHistoryVo
     * @param pageBean
     * @return
     */
    public PageBean<SharesDealHistoryVo> querySharesDealHistory(SharesDealHistoryVo sharesDealHistoryVo, PageBean pageBean);

    /**
     * 查询交易历史记录
     * 根据id
     * @param id
     * @return
     */
    public ResultVo<SharesDealHistoryVo> querySharesDealHistory(String id);

    /**
     * 查询交易历史记录
     * 根据查询实体
     * @param sharesDealHistoryVo
     * @return
     */
    public ResultVo<SharesDealHistoryVo> querySharesDealHistory(SharesDealHistoryVo sharesDealHistoryVo);
}

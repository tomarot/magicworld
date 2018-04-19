package com.magic.service;

import com.magic.common.ResultVo;
import com.magic.entity.Dictionary;
import com.magic.entity.SharesAccountInfo;
import com.magic.utils.PageBean;
import com.magic.vo.SharesAccountInfoVo;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/18
 * Time: 19:45
 * To change this template use File | Settings | File Templates.
 */
public interface SharesAccountInfoService {

    /**
     * 添加账户
     * @param sharesAccountInfo
     * @return
     */
    public ResultVo<SharesAccountInfo> saveSharesAccountInfo(SharesAccountInfo sharesAccountInfo);

    /**
     * 修改账户
     * @param sharesAccountInfo
     * @return
     */
    public ResultVo<SharesAccountInfo> updateSharesAccountInfo(SharesAccountInfo sharesAccountInfo);

    /**
     * 删除账户
     * 逻辑删除
     * @param id
     * @return
     */
    public ResultVo<SharesAccountInfo> deleteSharesAccountInfoForUpdate(String id);

    /**
     * 删除账户
     * 物理删除
     * @param id
     * @return
     */
    public ResultVo<SharesAccountInfo> deleteSharesAccountInfoForDelete(String id);

    /**
     * 查询账户信息
     * 分页专用
     * @param sharesAccountInfoVo
     * @param pageBean
     * @return
     */
    public PageBean<SharesAccountInfoVo> querySharesAccountInfo(SharesAccountInfoVo sharesAccountInfoVo, PageBean pageBean);

    /**
     * 查询账户信息
     * 根据id
     * @param id
     * @return
     */
    public ResultVo<SharesAccountInfo> querySharesAccountInfo(String id);

    /**
     * 查询账户信息
     * 根据查询实体
     * @param sharesAccountInfoVo
     * @return
     */
    public ResultVo<SharesAccountInfoVo> querySharesAccountInfo(SharesAccountInfoVo sharesAccountInfoVo);

    /**
     * 查询账户信息
     * 根据游戏编号
     * @param gameCode
     * @return
     */
    public ResultVo<SharesAccountInfo> querySharesAccountInfoForGameCode(String gameCode);
}

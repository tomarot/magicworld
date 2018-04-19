package com.magic.service;

import com.magic.common.ResultVo;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/3/27
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public interface SharesGameRecordService {

    /**
     * 获取模拟游戏页面相关数据（数据下一天运行）
     * 包括K线数据、当日股票数据、账户信息、持仓信息、报价单信息
     * @return
     */
    public ResultVo getGameInfoData(String gameCode,String next);
}

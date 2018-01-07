package com.magic.service;

import com.magic.entity.SystemLog;
import com.magic.utils.ResultVo;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/1/7
 * Time: 13:23
 * To change this template use File | Settings | File Templates.
 */
public interface SystemLogService {

    /**
     * 添加操作日志
     * @param systemLog
     * @return
     */
    public ResultVo<SystemLog> addSystemLog(SystemLog systemLog);
}

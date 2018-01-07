package com.magic.service.com.magic.service.impl;

import com.magic.dao.SystemLogDao;
import com.magic.entity.Menu;
import com.magic.entity.SystemLog;
import com.magic.service.SystemLogService;
import com.magic.utils.ResultVo;
import com.magic.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 2018/1/7
 * Time: 13:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SystemLogServiceImpl implements SystemLogService{
    @Autowired
    private SystemLogDao systemLogDao;
    @Override
    public ResultVo<SystemLog> addSystemLog(SystemLog systemLog) {
        ResultVo<SystemLog> resultVo = new ResultVo<SystemLog>();
        systemLogDao.saveSystemLog(systemLog);
        resultVo.setState(ResultVo.SUCCESS);
        resultVo.setMessage(ResultVo.SUCCESS_MESSAGE);
        return resultVo;
    }
}

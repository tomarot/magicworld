package com.magic.service.com.magic.service.impl;

import com.magic.dao.UserDao;
import com.magic.entity.User;
import com.magic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 17-5-18
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUserByEntity(User user) {
        List<User> userList =  userDao.getUserByEntity(user);
        return userList;
    }
}

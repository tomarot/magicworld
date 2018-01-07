package com.magic.dao;

import com.magic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: T5S
 * Date: 17-5-18
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUserByEntity(User user){
        String sql = "select * from user where username = ? and password = ?";
        List<User> userList = jdbcTemplate.query(sql,new Object[]{user.getUserName(),user.getPassword()},new BeanPropertyRowMapper(User.class));
        return userList;
    }
}

package com.ljb.leeBookShop.dao;

import com.ljb.leeBookShop.domain.User;
import com.ljb.leeBookShop.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    /*
    * 添加用户
    * */
    public void addUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        queryRunner.update("insert into user(username, password,gender,email,telephone,introduce,activeCode,state,registTime) " +
                        "values(?,?,?,?,?,?,?,?,?)",
                user.getUsername(),user.getPassword(),user.getGender(),user.getEmail(),
                user.getTelephone(),user.getIntroduce(),user.getActiveCode(),
                user.getState(),user.getRegistTime());
    }

    public User findUserByActiveCode(String activeCode) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        return queryRunner.query("select * from user where activeCode = ?",new BeanHandler<User>(User.class),activeCode);
    }
    /*
    * 激活用户状态
    * */
    public void activeCode(String activeCode) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        queryRunner.update("update user set state = 1 where activeCode = ?",activeCode);
    }

    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        return queryRunner.query("select * from user where username = ? and password = ?", new BeanHandler<User>(User.class),username,password);
    }

    public User findUserById(String useid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        return queryRunner.query("select * from user where id = ?", new BeanHandler<User>(User.class),useid);
    }

    public void updateUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        queryRunner.update("update user set password=?, gender=?, telephone=? where id=? ",user.getPassword(),
                user.getGender(),user.getTelephone(),user.getId());
    }
}

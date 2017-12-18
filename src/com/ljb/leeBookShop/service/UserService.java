package com.ljb.leeBookShop.service;

import com.ljb.leeBookShop.dao.UserDao;
import com.ljb.leeBookShop.domain.User;
import com.ljb.leeBookShop.exception.UserException;
import com.ljb.leeBookShop.util.SendJMail;

import java.sql.SQLException;

public class UserService {

    UserDao ud = new UserDao();

    public void regist(User user) throws UserException {
        try {
            ud.addUser(user);
            String emailStr = "注册成功，请<a href='http://www.baidu.com/activeServlet?activeCode="+user.getActiveCode()+"'>激活</a>";
            SendJMail.sendMail(user.getEmail(),emailStr);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("注册失败");
        }
    }

    public void findUserByActiveCode(String activeCode) throws UserException {

        User user = null;
        try {
            user = ud.findUserByActiveCode(activeCode);
            if (user != null) {
                ud.activeCode(activeCode);
                return;
            }
            throw new UserException("激活异常");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("激活异常");
        }
    }


    public User login(String username, String password) throws UserException {
        User user = null;
        try {
            user = ud.findUserByUsernameAndPassword(username, password);
            if (user == null) {
                throw  new UserException("用户名或者密码错误");
            }
            if (user.getState() == 0) {
                throw new UserException("用户没激活");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new UserException("用户名或者密码错误");
        }
        return user;
    }
    /*
    * 根据id找用户
    * */
    public User findUserById(String useid) throws UserException {

        try {
            User user = ud.findUserById(useid);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("修改失败！");
        }

    }
    /*
    * 跟新用户信息
    * */
    public void updateUser(User user) throws UserException {

        try {
            ud.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("更新失败！");
        }
    }
}

package com.ljb.leeBookShop.web.servlet;

import com.ljb.leeBookShop.domain.User;
import com.ljb.leeBookShop.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "ModifyUserInfoServlet",urlPatterns = "/modifyUserInfo")
public class ModifyUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        UserService us = new UserService();
        try {
            BeanUtils.populate(user,request.getParameterMap());
            us.updateUser(user);
            request.getSession().invalidate();
            response.sendRedirect("/modifyUserInfoSuccess.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login.jsp");
        }
    }
}

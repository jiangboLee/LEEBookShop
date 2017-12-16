package com.ljb.leeBookShop.web.servlet;

import com.ljb.leeBookShop.domain.User;
import com.ljb.leeBookShop.exception.UserException;
import com.ljb.leeBookShop.service.UserService;
import com.ljb.leeBookShop.util.SendJMail;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import static java.util.UUID.*;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ckcode = request.getParameter("ckcode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");

        if (!ckcode.equals(checkcode_session)) {
            //验证码不一致
            request.setAttribute("ckcode_msg", "验证码错误!");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        //获取表单数据
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            //手动设置激活码
            user.setActiveCode(randomUUID().toString());
            //调用业务逻辑
            UserService us = new UserService();
            us.regist(user);
            //分发转向
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("/registersuccess.jsp").forward(request,response);
        } catch (UserException e) {

            request.setAttribute("user_msg",e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}

package com.ljb.leeBookShop.web.servlet;

import com.ljb.leeBookShop.domain.User;
import com.ljb.leeBookShop.exception.UserException;
import com.ljb.leeBookShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService us = new UserService();
        try {
            String path = "/product_list.jsp";
            User user = us.login(username,password);
            if ("admin".equals(user.getRole())) {
                path = "/admin/login/home.jsp";
            }
            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher(path).forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("user_msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}

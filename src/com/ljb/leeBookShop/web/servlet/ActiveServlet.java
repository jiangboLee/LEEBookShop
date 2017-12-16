package com.ljb.leeBookShop.web.servlet;

import com.ljb.leeBookShop.exception.UserException;
import com.ljb.leeBookShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActiveServlet", urlPatterns = "/activeServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        UserService us = new UserService();
        try {
            us.findUserByActiveCode(activeCode);
        } catch (UserException e) {
            e.printStackTrace();
            //向用户提示失败
            response.getWriter().print(e.getMessage());
        }
    }
}

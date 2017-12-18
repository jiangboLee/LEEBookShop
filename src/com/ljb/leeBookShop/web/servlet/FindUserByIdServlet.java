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

@WebServlet(name = "FindUserByIdServlet", urlPatterns = "/findUserById")
public class FindUserByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userid");

        UserService us = new UserService();
        try {
            User user = us.findUserById(userId);
            request.getSession().setAttribute("u",user);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request,response);
        } catch (UserException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp");
        }
    }
}

package com.ljb.leeBookShop.web.servlet;

import com.ljb.leeBookShop.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyAccountServlet", urlPatterns = "/myAccount")
public class MyAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

        if (user.getRole().equals("admin")) {
            request.getRequestDispatcher("/admin/login/home.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("/myAccount.jsp").forward(request,response);
        }
    }
}

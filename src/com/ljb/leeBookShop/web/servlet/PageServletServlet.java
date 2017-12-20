package com.ljb.leeBookShop.web.servlet;

import com.ljb.leeBookShop.domain.PageBean;
import com.ljb.leeBookShop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageServletServlet" ,urlPatterns = "/pageServlet")
public class PageServletServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String category = request.getParameter("category");
        if (category == null) {
            category = "";
        }
        System.out.println(category);
        //初始化每页显示的记录数
        int pageSize = 4;
        //当前页
        int currentPage = 1;
        //从上一页或者下一页得到数据
        String currPage = request.getParameter("currentPage");
        if (currPage != null && !"".equals(currPage)) {//第一次为null
            currentPage = Integer.parseInt(currPage);
        }

        ProductService productService = new ProductService();
        //分页查询，并返回pagebean对象
        PageBean pageBean = productService.findBooksPage(currentPage,pageSize,category);
        pageBean.toString();
        request.setAttribute("pb",pageBean);
        request.getRequestDispatcher("/product_list.jsp").forward(request,response);

    }
}

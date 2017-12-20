package com.ljb.leeBookShop.service;

import com.ljb.leeBookShop.dao.ProduceDao;
import com.ljb.leeBookShop.domain.PageBean;
import com.ljb.leeBookShop.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    ProduceDao produceDao = new ProduceDao();

    public PageBean findBooksPage(int currentPage, int pageSize, String category) {

        try {
            //得到总记录数
            int count = produceDao.count(category);
            System.out.println(count);
            //求出总页数
            int totalPage = (int)Math.ceil(count*1.0/pageSize);
            List<Product> products = produceDao.findBooks(currentPage,pageSize,category);
            //把5个变量封装到pageBean
            PageBean pageBean = new PageBean();
            pageBean.setProducts(products);
            pageBean.setCount(count);
            pageBean.setCurrentPage(currentPage);
            pageBean.setCategory(category);
            pageBean.setTotalPage(totalPage);
            return pageBean;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

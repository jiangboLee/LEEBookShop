package com.ljb.leeBookShop.dao;

import com.ljb.leeBookShop.domain.Product;
import com.ljb.leeBookShop.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduceDao {
    /*
    * 得到总记录数
    * */
    public int count(String category) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        String sql = "select count(*) from products";
        if (!"".equals(category)) {
            sql += " where category = '" + category + "'";
        }
        long l = (long) queryRunner.query(sql, new ScalarHandler(1));
        return (int)l;
    }

    public List<Product> findBooks(int currentPage, int pageSize, String category) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Util.getDs());
        String sql = "select * from products where 1=1";
        List list = new ArrayList();
        if (!"".equals(category)) {
            sql += " and category = ?";
            list.add(category);
        }
        sql += " limit ?,?";
        list.add((currentPage-1)*pageSize);
        list.add(pageSize);
        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),list.toArray());
    }
}

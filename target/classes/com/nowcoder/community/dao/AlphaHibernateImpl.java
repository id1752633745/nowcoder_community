package com.nowcoder.community.dao;


import org.springframework.stereotype.Repository;

@Repository("alphaHibernateImpl")
public class AlphaHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}

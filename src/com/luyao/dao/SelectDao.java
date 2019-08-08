package com.luyao.dao;

import com.luyao.pojo.Filetbl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SelectDao {
    private SessionFactory sessionFactory;

    public List<Filetbl> selectfile(String fileName) {
        Session session = sessionFactory.openSession();
        String hql = "";
        Query query = session.createQuery("from Filetbl  where filename like '%"+fileName+"%'");
        List<Filetbl> list = query.list();
        System.out.println(list);
        return list;
    }

    /**
     * 分类查找
     * @param fileType
     * @return
     */
    public List seleImage(String fileType) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Filetbl where sortname=:sortname");
        query.setParameter("sortname",fileType);
        List list = query.list();
        return list;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

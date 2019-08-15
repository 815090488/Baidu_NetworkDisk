package com.luyao.dao;

import com.luyao.pojo.Filetbl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SelectDao {
    private SessionFactory sessionFactory;

    /**
     * 删除文件夹
     * @param folderid
     */
    public void deleteFold(Integer folderid) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete  from Folder WHERE folderid=:folderid");
        query.setParameter("folderid",folderid);
        int update = query.executeUpdate();
        System.out.println(update);
        transaction.commit();
        session.close();
        System.out.println("删除文件夹成功");
    }
    /**
     * 删除文件
     * @param fileId
     */
    public void deletFile(Integer fileId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete  from Filetbl WHERE fileId=:fileId");
        query.setParameter("fileId",fileId);
        int update = query.executeUpdate();
        System.out.println(update);
        transaction.commit();
        session.close();
        System.out.println("删除成功");
    }

    public List<Filetbl> selectfile(String fileName, String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Filetbl  where  filename like '%"+fileName+"%' and username=:username");
        query.setParameter("username",username);
        List<Filetbl> list = query.list();
        System.out.println(list);
        return list;
    }

    /**
     * 分类查找
     * @param fileType
     * @param username
     * @return
     */
    public List seleImage(String fileType, String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Filetbl where sortname=:sortname and username=:username");
        query.setParameter("sortname",fileType);
        query.setParameter("username",username);
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

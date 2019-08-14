package com.luyao.dao;

import com.luyao.pojo.Folder;
import com.luyao.pojo.Netuser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FolderDao {
    private SessionFactory sessionFactory;

    public void save(Folder folder) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(folder);
        System.out.println("dao层" + folder);
        transaction.commit();
        session.close();
    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

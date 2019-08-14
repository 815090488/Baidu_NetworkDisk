package com.luyao.dao;

import com.luyao.pojo.Folder;
import com.luyao.pojo.Netuser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FolderDao {
    private SessionFactory sessionFactory;

    /**
     * 保存文件夹
     *
     * @param folder
     */
    public void save(Folder folder) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(folder);
        transaction.commit();
        session.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Folder> seleByIdandName(Integer parentid,String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Folder where  parentid=:parentid and username=:username");
        query.setParameter("parentid", parentid);
        query.setParameter("username", username);
        List list = query.list();
        return list;
    }

    public Folder seleById(Integer folderid) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Folder where  folderid=:folderid");
        query.setParameter("folderid", folderid);
        Folder result = (Folder) query.uniqueResult();
        return result;
    }
}

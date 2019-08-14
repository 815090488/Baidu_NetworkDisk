package com.luyao.dao;

import com.luyao.pojo.Filesort;
import com.luyao.pojo.Filetbl;
import com.luyao.pojo.Netuser;
import com.luyao.pojo.Srcfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class FileDao {
    private SessionFactory sessionFactory;

    /**
     * 根据用户查询文件夹
     * @param username
     * @return
     */
    public List seleFolder(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Folder where username=:username");
        query.setParameter("username", username);
        List folders = query.list();
        return folders;
    }
    /**
     * 查询用户
     * @param username
     * @return
     */
    public Netuser seleUser(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Netuser where username=:username");
        query.setParameter("username", username);
        Netuser result = (Netuser) query.uniqueResult();
        return result;
    }

    /**
     * 查询文件类型
     * @param remark
     * @return
     */
    public  Filesort seleFileSort(String remark) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Filesort where remark=:remark");
        query.setParameter("remark", remark);
        Filesort result = (Filesort) query.uniqueResult();
        return result;
    }

    /**
     * 修改内存
     * @param capacity
     * @param username
     */
    public void UpdateCapacity(double capacity, String username) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Netuser where username=:username");
        query.setParameter("username", username);
        Netuser user = (Netuser) query.uniqueResult();
        user.setCapacity(capacity);
        session.update(user);

        transaction.commit();
        session.close();
    }

    /**
     * 保存文件
     * @param filesort
     * @param srcfile
     * @param filetb
     */
    public void saveFile(Filesort filesort,Srcfile srcfile, Filetbl filetb) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (srcfile != null&&filesort!=null) {
            session.save(filesort);
            session.save(srcfile);
            filetb.setSortid(filesort.getSortid());
            session.save(filetb);
        }else if(srcfile!=null&&filetb!=null){
            session.save(srcfile);
            session.save(filetb);
        }else {
            session.save(filetb);
        }
        transaction.commit();
        session.close();
    }

    /**
     * 根据用户名查询用户上传文件
     * @param username
     * @return
     */
    public List seleFieName(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Filetbl where username=:username");
        query.setParameter("username", username);
        List filetbl = query.list();
        return filetbl;
    }

    /**
     * 查询文件MD5实现秒传
     * @param filecode
     * @return
     */
    public Srcfile fileSeleMd5(String filecode) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Srcfile where filecode=:filecode");
        query.setParameter("filecode", filecode);
        Srcfile result = (Srcfile) query.uniqueResult();
        return result;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

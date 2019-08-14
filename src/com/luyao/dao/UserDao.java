package com.luyao.dao;


import com.luyao.pojo.Netuser;
import com.luyao.util.MD5;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class UserDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 注册
     * @param user
     */
    public void register(Netuser user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * 登录
     * @param email
     * @return
     */
    public Netuser Login(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Netuser where email=:email");
        query.setParameter("email", email);
        Netuser user = (Netuser) query.uniqueResult();
        return user;
    }

    /**
     * 注册邮箱查重
     * @param email
     * @return
     */
    public Netuser selectEmail(String email) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Netuser where email=:email");
        query.setParameter("email", email);
        Netuser netuser = (Netuser) query.uniqueResult();
        if(netuser==null){
            return null;
        }else {
            return netuser;
        }
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int changePas(Netuser user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String email = user.getEmail();
        Query query = session.createQuery("from Netuser where email=:email");
        query.setParameter("email", email);
        Netuser netuser = (Netuser) query.uniqueResult();
        netuser.setPassword(MD5.MD5(user.getPassword()));
        session.update(netuser);
        transaction.commit();
        session.close();
        return 1;
    }

    /**
     * 购买会员
     * @param user
     */
    public Netuser buyVIP(Netuser user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println(user+"user+++++++++++++++++++UserDao++++++++++++++++");
        String email = user.getEmail();
        Query query = session.createQuery("from Netuser where email=:email");
        query.setParameter("email", email);
        Netuser netuser = (Netuser) query.uniqueResult();

        if (netuser.getUsertype()==2){
            System.out.println("请勿重复购买会员");
            return netuser;
        }else{
            double capacity = user.getCapacity();
            netuser.setUsertype(2);
            netuser.setCapacity(capacity+1024);
            session.update(netuser);
            transaction.commit();
            Netuser userVIP = session.get(Netuser.class, user.getUsername());
            System.out.println("会员修改成功");
            return userVIP;
        }
    }
}

package com.luyao.service;

import com.luyao.dao.UserDao;
import com.luyao.pojo.Netuser;
import com.luyao.util.MD5;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    private UserDao userDao;

    private static final long CAPACITY = 1024L;

    private static final int USERTYPE = 1;


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public int register(Netuser user) {
        HttpServletRequest request = ServletActionContext.getRequest();
        String AuthCode = request.getParameter("AuthCode");
        String authCode = (String) request.getSession().getAttribute("AuthCode");
        if (AuthCode != null && AuthCode.equals(authCode)) {
            user.setUsertype(USERTYPE);
            user.setCapacity(CAPACITY);
            String password = user.getPassword();
            user.setPassword(MD5.MD5(password));
            userDao.register(user);
            return 1;

        } else {
            return 0;
        }

    }

    public Netuser Login(Netuser user) {
        String password = user.getPassword();
        user.setPassword(MD5.MD5(password));
        Netuser dbUser = userDao.Login(user.getEmail());
        if (dbUser == null) {
            return null;
        }
        if (user.getPassword() != null && user.getPassword().equals(dbUser.getPassword())) {
            return dbUser;
        }
        return null;
    }

    /**
     * 查重
     *
     * @param email
     * @return
     */
    public Netuser selectEmail(String email) {
        Netuser netuser = userDao.selectEmail(email);
        return netuser;
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int changePas(Netuser user) {
        HttpServletRequest request = ServletActionContext.getRequest();
        String AuthCode = request.getParameter("AuthCode");
        String authCode = (String) request.getSession().getAttribute("AuthCode");
        if (AuthCode != null && AuthCode.equals(authCode)) {
            int index=userDao.changePas(user);
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 购买会员
     * @param user
     */
    public Netuser buyVIP(Netuser user) {
        Netuser userVIP = userDao.buyVIP(user);
        return userVIP;
    }
    /**
     * 购买超级会员
     * @param user
     */
    public Netuser buySupVip(Netuser user) {
        Netuser userVIP = userDao.buySupVip(user);
        return userVIP;
    }
}

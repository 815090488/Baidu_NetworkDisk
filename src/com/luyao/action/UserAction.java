package com.luyao.action;

import com.alibaba.fastjson.JSON;
import com.luyao.pojo.Netuser;
import com.luyao.service.UserService;
import com.luyao.test.SendMail;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserAction extends BaseAction implements ModelDriven<Netuser> {

    private Netuser user = new Netuser();

    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private HttpServletRequest requestSession = ServletActionContext.getRequest();
    private HttpServletResponse response = ServletActionContext.getResponse();


    /**
     * 购买会员
     */
    public String buyVIP(){
        Netuser user = (Netuser) session.get("user");
        Netuser userVIP = userService.buyVIP(user);

        if(user.equals(userVIP)){
            session.put("message","请勿重复购买会员");
            System.out.println("请勿重复购买会员------------------Service------------");
            return SUCCESS;
        }else{
            System.out.println("购买成功------------Service----------------");
            session.put("user",userVIP);
            return SUCCESS;
        }
    }

    /**
     * 修改密码
     */
    public String changePas() {
        System.out.println(user + "user=====================Action");
        int index = userService.changePas(user);
        if (index > 0) {
            request.put("message", "密码修改成功请使用新密码登录");
            return SUCCESS;
        } else {
            request.put("message", "密码修改失败，验证码错误");
            return INPUT;
        }
    }

    /**
     * 修改用户密码发送验证码
     */
    public void changeAuthCode() throws IOException {
        String code = (int) (Math.random() * 9000 + 1000) + "";
        String email = requestSession.getParameter("email");
        Netuser netuser = userService.selectEmail(email);
        if (netuser == null) {
            System.out.println("没有用户");
            String string = JSON.toJSONString("没有找到此用户");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(string);
        } else {
            SendMail send = new SendMail();
            send.sendAction("网盘修改密码验证码", code, email);
            session.put("AuthCode", code);
        }
    }


    /**
     * 发送验证码
     */
    public void AuthCode() throws IOException {
        String code = (int) (Math.random() * 9000 + 1000) + "";
        String email = requestSession.getParameter("email");

        Netuser netuser = userService.selectEmail(email);

        if (netuser == null) {
            System.out.println("没有用户");
            SendMail send = new SendMail();
            send.sendAction("网盘邮箱验证码", code, email);
            session.put("AuthCode", code);
        } else {
            System.out.println("有用户");
            String string = JSON.toJSONString("邮箱已被占用");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(string);
        }
    }

    /**
     * 注册
     *
     * @return
     */
    public String register() {
        int index = userService.register(user);
        System.out.println(index);
        if (index == 0) {
            System.out.println("失败");
            request.put("message", "验证码错误");
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    /**
     * 登录
     *
     * @return
     */
    public String Login() {
        Netuser dbUser = userService.Login(user);
        if (dbUser != null) {
            session.put("user", dbUser);
            return SUCCESS;
        } else {
            request.put("message", "用户名或者密码错误");
            return INPUT;
        }
    }

    @Override
    public Netuser getModel() {
        return user;
    }
}

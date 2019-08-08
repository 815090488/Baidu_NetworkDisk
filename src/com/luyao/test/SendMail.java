/*
 * 创建日期 2006-3-2
 *
 */
package com.luyao.test;

/**
 * @author zhangpeng
 */

import com.luyao.test.Log;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import javax.activation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;

public class SendMail {
    private MimeMessage mimeMsg; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    private boolean needAuth = false; // smtp是否需要认证

    private String username = ""; // smtp认证用户名和密码
    private String password = "";

    private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象

    /*
     * private String towhoaddress=""; private String thebody=""; private String
     * thetitle="";
     *
     * public void Settowhoaddress(String towhoaddress0){
     * towhoaddress=towhoaddress0; }
     *
     * public void Setthebody(String thebody0){ thebody=thebody0; }
     *
     * public void Setthetitle(String thetitle0){ thetitle=thetitle0; }
     */

    /**
     * public sendMail() {
     * setSmtpHost(getConfig.mailHost);//如果没有指定邮件服务器,就从getConfig类中获取
     * createMimeMessage(); }
     */
    public SendMail() {
    }

    public SendMail(String smtp) {
        setSmtpHost(smtp);
        createMimeMessage();
    }

    /**
     * @param hostName String
     */
    public void setSmtpHost(String hostName) {
        Log.write("设置系统属性：mail.smtp.host = " + hostName);
        if (props == null)
            props = System.getProperties(); // 获得系统属性对象

        props.put("mail.smtp.host", hostName); // 设置SMTP主机
    }

    /**
     * @return boolean
     */
    public boolean createMimeMessage() {
        try {
            Log.write("准备获取邮件会话对象！");
            session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
        } catch (Exception e) {
            Log.write("获取邮件会话对象时发生错误！" + e);
            return false;
        }

        Log.write("准备创建MIME邮件对象！");
        try {
            mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
            mp = new MimeMultipart();

            return true;
        } catch (Exception e) {
            Log.write("创建MIME邮件对象失败！" + e);
            return false;
        }
    }

    /**
     * @param need boolean
     */
    public void setNeedAuth(boolean need) {
        Log.write("设置smtp身份认证：mail.smtp.auth = " + need);
        if (props == null)
            props = System.getProperties();

        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
    }

    /**
     * @param name String
     * @param pass String
     */
    public void setNamePass(String name, String pass) {
        username = name;
        password = pass;
    }

    /**
     * @param mailSubject String
     * @return boolean
     */
    public boolean setSubject(String mailSubject) {
        Log.write("设置邮件主题！");
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            Log.write("设置邮件主题发生错误！");
            return false;
        }
    }

    /**
     * @param mailBody String
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent(
                    "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
                            + mailBody, "text/html;charset=GB2312");
            mp.addBodyPart(bp);

            return true;
        } catch (Exception e) {
            Log.write("设置邮件正文时发生错误！" + e);
            return false;
        }
    }


    public boolean addFileAffix(String filename) {

        Log.write("增加邮件附件：" + filename);

        try {
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            bp.setFileName(fileds.getName());

            mp.addBodyPart(bp);

            return true;
        } catch (Exception e) {
            Log.write("增加邮件附件：" + filename + "发生错误！" + e);
            return false;
        }
    }


    public boolean setFrom(String from) {
        Log.write("设置发信人！");
        try {
            mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean setTo(String to) {
        if (to == null)
            return false;

        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
                    .parse(to));
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public boolean setCopyTo(String copyto) {
        if (copyto == null)
            return false;
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC,
                    (Address[]) InternetAddress.parse(copyto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean sendout() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            Log.write("正在发送邮件....");

            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            // Log.write("before connect");
            // Log.write("(String)props.get('mail.smtp.host'),username,password=="+(String)props.get("mail.smtp.host")+username+password);
            transport.connect((String) props.get("mail.smtp.host"), username,
                    password);
            // Log.write("after connect");
            transport.sendMessage(mimeMsg, mimeMsg
                    .getRecipients(Message.RecipientType.TO));
            // transport.send(mimeMsg);

            Log.write("发送邮件成功！");
            transport.close();

            return true;
        } catch (Exception e) {
            Log.write("邮件发送失败！" + e);
            return false;
        }
    }

    /**
     * Just do it as this
     */
    public boolean sendAction(String thetitle, String thebody, String towhoaddress) {

        thebody =
                "<meta http-equiv=Content-Type content=text/html; charset=gb2312>" +
                        "<div align=center>尊敬的<br>" + towhoaddress + "：你好！<br>你的网盘邮箱验证码为<br>" + thebody + "<br>您正在使用注册功能，该验证码仅用于身份注册，请勿给他人使用。<br>提示：请勿泄露验证码给他人</div>";

        SendMail themail = new SendMail(SiteSmtp);
        themail.setNeedAuth(true);

        if (themail.setSubject(thetitle) == false)
            return false;
        if (themail.setBody(thebody) == false)
            return false;
        if (themail.setTo(towhoaddress) == false)
            return false;
        if (themail.setFrom(SiteEmailAddress) == false)
            return false;

        themail.setNamePass(SiteEmailUserName, SiteEmailPasswd);

        if (themail.sendout() == false)
            return false;
        return true;
    }

    public static void main(String[] args) {
            //局部内部类
            SendMail send = new SendMail();
            send.sendAction("网盘邮箱验证码", null, "1561300808@qq.com");
    }

    private static String SiteSmtp = "smtp.qq.com";
    private static String SiteEmailAddress = "815090488@qq.com";
    private static String SiteEmailUserName = "815090488@qq.com";
    private static String SiteEmailPasswd = "mknamsrcgzxdbefi";


/*	private static String SiteSmtp = "smtp.qq.com";
	private static String SiteEmailAddress = "815090488@qq.com";
	private static String SiteEmailUserName = "815090488";
	private static String SiteEmailPasswd = "qjvz";*/
}

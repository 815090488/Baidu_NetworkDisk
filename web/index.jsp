<%--
  Created by IntelliJ IDEA.
  User: 81509
  Date: 2019/8/1
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>百度云盘</title>
    <style>
        body {
            background: url("img/bg4.jpg");
            background-size: 100%;
            font-size: 14px;
        }

        .nav {
            height: 60px;
            /*line-height: 50px;*/
        }

        .title {
            float: right;
            margin: 25px 20px 0 0;
        }

        .title a:hover {
            text-decoration: underline;
        }

        .noln {
            list-style-type: none;
            color: white;
            margin: 30px 20px 0 0;
            text-decoration: none;
        }

        .entry {
            background: linear-gradient(-139deg, #f0d7a3 0, #efcb85 100%) #f0d7a3;
            color: #333;
            padding: 4px 10px;
            border-radius: 20px;
        }

        .left {
            float: left;
            margin-left: 300px;
            width: 400px;
            height: 600px;
            margin-top: 50px;
        }

        .right {
            float: right;
            height: 100%;

            width: 352px;
            margin-right: 300px;
            margin-top: 100px;
            position: relative;
        }

        .index {
            width: 352px;
            height: 330px;
            background-color: rgba(255, 255, 255, 0.75);
            border-radius: 10px;
            margin: 60px 90px;
            border: 1px solid #bcedff;
        }

        .link {
            height: 50px;
        }

        .headline {
            float: left;
            font-weight: 500;
            font-size: 18px;
            /*text-align: left;*/
            margin-top: 30px;
            margin-left: 20px;
            padding-bottom: 30px;
            margin-bottom: 0;
            text-align: center;
        }

        .headline1 {
            float: right;
            font-weight: 500;
            font-size: 18px;
            margin-top: 30px;
            margin-right: 20px;
            padding-bottom: 30px;
            margin-bottom: 0;
            text-decoration: none;
            color: #09aaff;
        }

        .aa {
            line-height: 30px;
            width: 300px;
            border-radius: 5%;
            margin-left: 20px;
        }

        .bb {
            line-height: 30px;
            width: 300px;
            border-radius: 5%;
            margin-left: 20px;
        }

        /*.bstyle{*/
        /*margin-left: 20px;*/
        /*}*/
        .cstyle {
            float: right;
            margin-top: -43px;
            text-decoration: none;
        }
    </style>
</head>
<body>
<!--导航部分开始-->
<div class="nav">
    <span class="title">
        <a class="noln" style="color: red" href="javascript">严打违规文件和盗版侵权传播</a>
        <a class="noln" href="百度云盘.html">首页</a>
        <a class="noln" href="javascript">客户端下载</a>
        <a class="noln" href="javascript">官方贴吧</a>
        <a class="noln" href="javascript">官方微博</a>
        <a class="noln" href="javascript">问题反馈</a>
        <a class="noln" href="javascript">内容商城</a>
        <a class="noln entry" href="javascript">会员中心</a>
    </span>
</div>
    <!--右边登录界面部分开始-->
    <div class="right">
        <div class="index">
            <form action="Login.action" method="post">
                <div class="link">
                    <p class="headline">账号密码登陆</p>
                    <a class="headline1" href="javascript">短信快捷登陆></a>
                </div>
                <input class="bb" name="email" placeholder="请输入您的邮箱">
                <br><br>
                <input class="bb" name="password" type="password" placeholder="请输入密码"><br>
                <div class="aa">
                    <p class="bstyle"><input type="checkbox" name="rempas"/>  记住密码</p>
                    <a class="cstyle" href="changePas.jsp">忘记密码</a>
                </div>
                <input class="bb" type="submit" value="登陆" name="login">
                </br>
                <div class="aa">
                    <p class="bstyle">没有账号？
                        <span style="margin-left: 10px ; color: red" >${requestScope.message}</span>
                    </p>
                    <a class="cstyle" href="register.jsp">立即注册</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

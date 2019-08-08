<%--
  Created by IntelliJ IDEA.
  User: 81509
  Date: 2019/8/1
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
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
            margin-top: -20px;
            position: relative;
        }

        .index {
            width: 352px;
            height: 580px;
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

        .bb {
            line-height: 30px;
            width: 300px;
            border-radius: 5%;
            margin-left: 20px;
        }

        .cc {
            text-align: left;
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
<!--主体部分开始-->
    <!--右边登录界面部分开始-->
    <div class="right">
        <div class="index">
            <form action="changePas.action" method="post">
                <div class="link">
                    <p class="headline">用户修改</p>
                    <a class="headline1" href="javascript">短信快捷注册></a>
                </div>
                <tr>
                    <td><p>邮箱账号：</p></td>
                    <td><input id="email" type="email" class="bb" name="email" placeholder="请输入您的邮箱账号"></td>
                </tr>
                <tr>
                    <td><p>修改密码：</p></td>
                    <td><input id="EnterPassword" class="bb" value="" name="password" type="password"
                               placeholder="请输入您的密码"></td>
                </tr>
                <tr>
                    <td><p>确认密码：</p></td>
                    <td><input id="ConfirmPassword" class="bb" value="" type="password" placeholder="再次输入密码"></td>
                </tr>
                <tr>
                    <td><p>请输入验证码：</p></td>
                    <td><input id="AuthCode" name="AuthCode" class="bb"></td>
                </tr>
                <br><br>
                <input id="sub" class="bb" type="submit" value="注册"/>
               <p><span id="Errorspan" style="color: red">${requestScope.message}</span></p>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        var flag;
        $("#email").blur(function () {
            var email = $("#email").val();
            if (email == "" || email == null) {
                $("#Errorspan").html("邮箱不能为空")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else {
                $("#Errorspan").html("");
                $.ajax({
                    type:"POST",
                    url:"/changeAuthCode.action",
                    data:{
                        email:email
                    },
                    success:function(string){
                        $("#Errorspan").html(string);
                        $("#Errorspan").css({"color": "red"});
                    },
                    dataType: "json"
                });
                return flag = true;
            }
        });

        $("#EnterPassword").mouseleave(function () {
            var EnterPassword = $("#EnterPassword").val();
            var ConfirmPassword = $("#ConfirmPassword").val();
            if (EnterPassword == " " || EnterPassword == null) {
                $("#Errorspan").html("密码不能为空")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else if (ConfirmPassword != EnterPassword) {
                $("#Errorspan").html("两次密码不一致")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else {
                $("#Errorspan").html("");
                return flag = true;
            }
        });
        $("#ConfirmPassword").mouseleave(function () {
            var ConfirmPassword = $("#ConfirmPassword").val();
            var EnterPassword = $("#EnterPassword").val();
            if (ConfirmPassword == " " || ConfirmPassword == null) {
                $("#Errorspan").html("密码不能为空")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else if (ConfirmPassword != EnterPassword) {
                $("#Errorspan").html("两次密码不一致")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else {
                $("#Errorspan").html("");
                return flag = true;
            }
        });

        $("#AuthCode").mouseleave(function () {
            var AuthCode = $("#AuthCode").val();
            if (AuthCode == " " || AuthCode == null) {
                $("#Errorspan").html("验证码不能为空")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else {
                $("#Errorspan").html("");
                return flag = true;
            }
        });
        $("#username").mouseleave(function () {
            var AuthCode = $("#username").val();
            if (AuthCode == " " || AuthCode == null) {
                $("#Errorspan").html("用户名不能为空")
                $("#Errorspan").css({"color": "red"});
                return flag = false;
            } else {
                $("#Errorspan").html("");
                return flag = true;
            }
        });

        $("#sub").click(function () {
            if (flag) {
                return true;
            } else {
                return false;
            }
        });

    });

</script>
</body>
</html>

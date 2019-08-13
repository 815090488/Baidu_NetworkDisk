<%--
  Created by IntelliJ IDEA.
  User: 81509
  Date: 2019/8/4
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>网盘</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <style>
        body {
            overflow-x: hidden;
            width: 100%;
            height: 100%;
        }

        .header {
            width: 100%;
            height: 62px;
            background: #fff;
            box-shadow: 0 2px 6px 0 rgba(0, 0, 0, .05);
        }

        .xjjb {
            line-height: 50px;
        }

        .xgs {
            margin: 10px 30px;
        }

        .xgs a {
            text-decoration: none;
            color: #09aaff;
        }

        .layout {
            width: 194px;
            height: 670px;
            top: 62px;
            position: absolute;
            background-color: #F7F7F7;
            margin-top: 20px;
        }

        .abxb {
            list-style: none;
            margin: 0;
            padding: 0;
        }

        .akp {
            width: 194px;
            height: 45px;
            margin-top: 20px;
            color: #666666;
            text-align: center;
        }

        .aa {
            width: 100%;
            margin-left: 200px;
        }

        .aa ul li {
            list-style: none;
            height: 40px;
            line-height: 40px;
        }

        .aa ul li .del {
            color: red;
            float: right;
            cursor: pointer;
            display: inline-block;
        }

        .aaa {
            width: 100%;
            /*height: 80px;*/
            position: absolute;
        }

        .ccc {
            list-style-type: none;
            height: 100px;
        }

        .ccc > li {
            float: left;
            margin: 10px 10px;
        }

        .akp > a {
            text-decoration: none;
        }

        .dd {
            width: 100px;
            height: 40px;
            border-radius: 10px;
            background-color: white;
            color: #09aaff;
            border: 1px solid #09aaff;
        }

        .xx {
            float: right;
            margin-right: 250px;
            margin-top: 10px;
        }

        .xxx {
            list-style-type: none;
            margin-right: 100px;
        }

        .xxx > li {
            float: left;
        }

        .oy {
            float: right;
            width: 300px;
            height: 30px;
            border-radius: 20px;
            border: 1px solid #09aaff;
            background-color: darkgray;
            /*margin-right: 100px;*/
        }

        .ao {
            width: 100px;
            height: 35px;
            border-radius: 20px;
            border: 1px solid #09aaff;
            background-color: #09aaff;
            color: white;
            /*margin-top: 5px;*/
            position: absolute;
        }

        .shadowBox {
            position: fixed;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            display: flex;
            background-color: rgba(0, 0, 0, .5);
        }

        .shadowBox .box {
            width: 600px;
            height: 400px;
            background: white;
            border-radius: 20px;
            margin: auto;
        }

        .shadowBox .box .top {
            height: 80px;
            line-height: 80px;
            width: 100%;
            background: blue;
            font-size: 50px;
            text-align: center;
            color: white;
            border-radius: 20px 20px 0 0;

        }

        .shadowBox .box .title {
            text-align: center;
            font-size: 30px;
        }

        .title {
            height: 250px;
        }

        .content {
            border-radius: 10px;
            background-color: white;
            color: #09aaff;
            border: 1px solid #09aaff;
        }

        .btn {
            width: 70px;
            height: 25px;
            border-radius: 10px;
            background-color: white;
            color: #09aaff;
            border: 1px solid #09aaff;
        }
    </style>
</head>
<body>
<!--导航部分开始-->
<div id="app">
    <div class="header">
        <dd class="xjjb">
        <span class="xgs">
            <a href="/loginOut">首页</a>
        </span>
            <span class="xgs">
            <a href="javascript">网盘</a>
        </span>
            <span class="xgs">
            <a href="javascript">分享</a>
        </span>
            <span class="xgs">
            <a href="javascript">找资源</a>
        </span>
            <span class="xgs">
            <a href="javascript">更多</a>
        </span>
            <span style="color: black;margin-right: 20px;">
            用户：${sessionScope.user.username}
        </span>
            <span style="color: black;margin-right: 20px;">
            |
        </span>
            <span>
            <c:if test="${sessionScope.user.usertype==3}">
                超级会员 &nbsp; &nbsp;&nbsp;&nbsp; 总容量：3072 M
            </c:if>
            <c:if test="${sessionScope.user.usertype==2}">
                普通会员 &nbsp; &nbsp;&nbsp;&nbsp; 总容量：2048 M
            </c:if>
            <c:if test="${sessionScope.user.usertype==1}">
                普通用户 &nbsp; &nbsp;&nbsp;&nbsp; 总容量：1024 M
            </c:if>

        </span>
            <span style="color: black;margin-left: 20px;">
                剩余容量：${sessionScope.user.capacity}   M
            </span>
            <span style="color: black;margin-left: 20px;">
                <a href="/VIP.jsp">购买会员</a>
            </span>
            <span style="color: red;margin-left: 20px;">
                ${sessionScope.message}
            </span>
            <span style="color: black;margin-left: 20px;text-decoration: none">
                <a href="/loginOut">退出登录</a>
            </span>
        </dd>
    </div>
    <div class="layout">
        <ul class="abxb">
            <li class="akp"><a href="/fileList">全部文件</a></li>
            <li class="akp"><a href="/selectAction?fileType=images">图片</a></li>
            <li class="akp"><a href="/selectAction?fileType=document">文档</a></li>
            <li class="akp"><a href="/selectAction?fileType=video">视频</a></li>
            <li class="akp"><a href="">种子</a></li>
            <li class="akp"><a href="/selectAction?fileType=music">音乐</a></li>
            <li class="akp"><a href="/selectAction?fileType=other">其他</a></li>
            <li class="akp"><a href="">我的分享</a></li>
            <li class="akp"><a href="">回收站</a></li>
        </ul>
    </div>
    <div class="aa">
        <div class="aaa">
            <ul class="ccc">
                <li>
                    <button class="dd" style="background-color: #09aaff;color: white" type="text" @click="change">上传
                    </button>
                </li>
                <li>
                    <button class="dd" type="text">新建文件夹</button>
                </li>
                <li>
                    <button class="dd" type="text">离线下载</button>
                </li>
                <li>
                    <button class="dd" type="text">我的设备</button>
                </li>
                <form method="get" action="selectFile.action">
                    <div class="xx">
                        <ul class="xxx">
                            <li><input name="selectFile" class="oy" type="text" placeholder="搜索你的文件"></li>
                            <li>
                                <button type="submit" class="ao">搜索</button>
                            </li>
                        </ul>
                    </div>
                </form>
            </ul>
            <div>
                <c:forEach var="filetbls" items="${filetbls}">
                    <p>
                        <a style="text-decoration: none" href="down.action?filename=${filetbls.filepath}">
                            <span style="margin-left: 30px;margin-right: 30px">下载</span>
                        </a>
                        <span style="font-size: 20px;line-height: 30px;color: black;">${filetbls.filename}</span>
                        <a href="deleteFile.action?fileid=${filetbls.fileId}"><span id="delete${filetbls.fileId}">删除</span></a><br>
                    </p>
                </c:forEach>
            </div>
        </div>
        <form method="post" action="upload.action" enctype="multipart/form-data">
            <div class="shadowBox" v-show="key">
                <div class="box">
                    <div class="top">文件上传框</div>
                    <div class="title">
                        <p>请选择您需要上传的文件</p>
                        <p><input id="btu" type="button" value="添加上传">
                            <button class="btn" @click="addList">上传</button>
                        </p>
                        <input class="content" name="file" value="点我上传" type="file" v-model="fileName"><br>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    $(function () {
        $("#btu").click(function () {
            var type = "${sessionScope.user.usertype}";
            if (type == 1) {
                alert("对不起，你还不是会员，请购买会员");
                return;
            }
            var li = $(".title");
            li.append("<input class='content' name='file' value='点我上传' type='file' v-model='fileName'><br>")
            li.append(li);
            $(".title").append(li);
            if ("[name=file]".fontsize > 5) {
                return;
            }
        });
    });
</script>


<script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            key: false,
            fileName: '',
            newsList: []
        },
        methods: {
            change: function () {
                this.key = true;
            }
            ,
            addList: function () {
                if (this.fileName) {
                    this.newsList.push(this.fileName);
                    this.fileName = 'file';
                    this.key = true;

                } else {
                    alert('请添加上传文件')
                }
            },
            delList: function (index) {
                this.newsList.splice(index, 1)
            }
        }
    })
</script>
</body>
</html>
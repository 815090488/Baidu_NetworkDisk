<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/4
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>出错了：<s:property value="exception.message"></s:property></h1>
    <a href="fileList.action">回到主页</a>
    <a href="/VIP.jsp">购买会员</a>
</body>
</html>

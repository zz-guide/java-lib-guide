<%--
  Created by IntelliJ IDEA.
  User: xulei
  Date: 2022/12/25
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>包含页面</title>
    <style>
        .h1{
            border:10px solid yellow;
            height:100px;
        }
    </style>

</head>
<body>
    <div>包含页面</div>

    <%
        String type=request.getParameter("type");
        String data="";
        if("1".equals(type)){
            data="热门商品";
        }
        if("2".equals(type)){
            data="折扣商品";
        }
        if("3".equals(type)){
            data="人气商品";
        }
    %>
    <h1 class="h1"><%=data %></h1>
</body>
</html>

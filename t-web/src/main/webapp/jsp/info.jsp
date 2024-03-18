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
    <title>测试jsp</title>
</head>
<body>
    <div>jsp原生标签</div>
    <jsp:include page="include.jsp">
        <jsp:param value="1" name="type"/>
    </jsp:include>
    <%
        int a = 3;
        System.out.println("java 输出="+a);
    %>
</body>
</html>

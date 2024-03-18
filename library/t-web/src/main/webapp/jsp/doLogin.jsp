<%@page import="com.xulei.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
  // 方法一
  User user=new User();
  user.setId(1);
%>
<%
  out.print(user.getId());
%>

<!-- 方法二 相当于 User u=new User(); -->
<jsp:useBean id="u" class="com.xulei.pojo.User"/>
<!-- param 相当于 request.getParameter("id") -->
<jsp:setProperty property="id" name="u" param="id"/>
<jsp:setProperty property="username" name="u" param="username"/>
<jsp:setProperty property="password" name="u" param="password"/>

<%--
<!--设置u的属性 u.setUsername("haha") -->
<jsp:setProperty property="username" name="u" value="haha"/>

<!--获得u的属性 u.getUsername() -->
<jsp:getProperty property="username" name="u"/>
--%>

<%
  out.print(u);
%>
<%--
  Created by IntelliJ IDEA.
  User: xulei
  Date: 2022/12/25
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--格式化标签库--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--函数标签库--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>测试jstl标签</title>
</head>
<body>
<%--if标签：条件判断，类似java里面的if语句
      test表示条件，返回布尔类型的值，值为true的时候，显示if标签内容，值为false就不显示if标签内容
      test里面的表达式通常配合EL表达式使用
  --%>
<c:if test="${10 > 5}">
    这是需要显示的if条件内容！
</c:if>
<%--set 标签：在作用域中声明变量并赋值
  var相当于声明变量名称
  value 变量的初始化值
  scope 声明变量所在作用域
--%>

<%--表示在session作用域声明变量num值是23--%>
<c:set var="num" scope="session" value="23"></c:set>
<%--out标签：输出变量
  value表示输出的作用域中的变量
--%>
<p>作用域中的num值是：</p>
<c:out value="${num}"></c:out>

<hr>
<%--forEach集合遍历标签:遍历集合对象的标签
   items遍历的目标集合对象
   var集合对象中的元素
   step遍历集合的步长
   begin遍历的开始位置
   end遍历的结束位置
   varStatus 元素位置状态：index 集合中元素索引
                           count 集合中元素的位置
--%>
<%--输出20以内的整数--%>
<c:forEach var="num" step="2" begin="1" end="20">
    ${num} <br>
</c:forEach>
<hr>
<%--输出一个作用域中的集合--%>
<%
    //创建整数集合
    List<Integer> list = new ArrayList<>();
    list.add(12);
    list.add(34);
    list.add(45);
    list.add(1);
    list.add(345);
    //将集合存储到作用域对象
    request.setAttribute("numList", list);
%>
<%--使用jstl标签获取作用域集合数据--%>
<c:forEach items="${numList}" var="num" varStatus="vs">
    ${num}======${vs.count}====${vs.index}  <br>
</c:forEach>

</body>
</html>

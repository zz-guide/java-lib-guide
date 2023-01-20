# 学习java-web基础

# 问题
    1.使用maven插件创建出来的项目web.xml版本过低，需要修改
    2.pom.xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
    </properties>
    <build>
        <finalName>web-02</finalName>
        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.1.0</version>
                </plugin>
                <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>3.2.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
    3.加快构建maven速度：-DarchetypeCatalog=internal
    4.本地tomcat9: /usr/local/opt/tomcat@9/libexec/webapps/ROOT/WEB-INF
    5.  <scope>provided</scope> 只在编译和测试环境有效，因为tomcat已经自带了，所以把本地环境的注释掉
    6.中文乱码解决
        Servlet默认使用ISO-8859-1编码
        (1)resp.setCharacterEncoding("UTF-8"); 设置的是Content-Type中的charset
        (2)req.setCharacterEncoding("UTF-8");
        (3)修改tomcat配置文件<Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" URIEncoding="UTF-8"/>
        增加URIEncoding="UTF-8"配置
        (4) String name = new String("许磊".getBytes(), StandardCharsets.UTF_8);对字符串进行编码解码
        

# 配置方式
    1.基于xml配置
    2.基于注解配置，需要事先Servlet接口
    3.使用注解的时候，需要web.xml的metadata-complete设置为false
    metadata-complete属性表示部署时当前的配置是否完全，值为true，表示完全，只会应用web.xml的配置，而不会去扫描类似@WebServlet,@WebFilter,@WebListener等注解和web-frame.xml配置。默认值是metadata-complete=false,即不完全，会对项目中类进行扫描，是否有相关的注解配置，同时也会加载web-frame.xml等插件配置。

# servlet执行流程
    1.servlet接口一般服务器会实现，比如tomcat
    2.tomcat通过解析url来调用servlet类的方法
    

# servlet 生命周期
    1.加载，实例化。默认情况下第一次访问才会被创建，可以通过配置修改loadOnStartUp=1
        1.负整数：第一次访问创建
        2.正整数或0：服务器启动就创建
        数字越小，优先级越高
    2.初始化。init()，只会被调用一次。一般用来加载配置文件，写一些业务逻辑使用。
    3.请求处理。service()，每次浏览器访问，都会调用处理请求。
    4.服务终止。destroy(),销毁资源，释放内存

# jsp
    1.JSP本质上就是一个servlet类，编译器会编译成servlet代码，查看字节码就知道了
    2.语法
        1.<%……%>在控制台输出
        2.<%=……%>在html页面输出
        3.<%!……%>作为成员
    3.jsp可以直接通过url访问，因为可以认为和servlet处于同一级别
    4.jsp文件放到WEB-INF目录下就不可以直接访问了，需要转发
    5.<!--  -->  HTML注释
        <%--  --%>  java注释
        jsp使用HTML注释会不生效，jsp需使用Java注释
    6.
# EL表达式
    1.四大域对象
        1.page,当前页面有效
        2.request，当前请求有效
        3.session，当前会话有效
        4.application，当前应用有效

# JSTL标签
    1.引入坐标
        javax.servlet
            jstl
            1.2

        org.apache.taglibs
	    taglibs-standard-impl
	    1.2.5

    在jsp头部加上jstl的引入标签：
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    2.常用原生jsp标签
        
       导入一个类 <%@page import="com.xulei.pojo.User"%>

        <jsp:include page="页面">	包含
        <jsp:param name="name" value="value">	传参
        <jsp:foward page="页面">	转发
        <jsp:useBean>	相当于实例化类
        <jsp:setProperty>	给useBean属性设置值
        <jsp:getProperty>	取值
        

        useBean用法
        <jsp:useBean id="" beanName=""  type=""  class="" scope="">
        id: 对象名 *
        class: 类  创建对象时,完全限定名(包名+类名)
        type: 类型 调用对象时 *  (可以用抽象父类或者接口)
        scope: 作用域 (page *  request session  application)

        setProperty用法
        <jsp:setProperty  name=""  property=""  value="" param="">
        name：useBean 的id
        property：属性名(要注意必须跟实体类中的属性名保持一致)
        value：属性值
        param：拿到用户传入的值，相当于 request.getParameter("")

    3.jstl库标签
        prefix:表示前缀名，就是在jsp页面引入标签的标识
        uri：标签库的地址
        核心标签库：<%@ taglib prefix=“c” uri=“http://java.sun.com/jsp/jstl/core” %>
        格式化标签库：<%@ taglib prefix=“fmt” uri=“http://java.sun.com/jsp/jstl/fmt” %>
        函数标签库：<%@ taglib prefix=“fn” uri=“http://java.sun.com/jsp/jstl/functions” %>
        c:if 条件判断标签 
        c:set 变量声明标签
        c:out 变量输出标签
        c:foreach 集合遍历标签

# cookie session
        当浏览器首次访问服务器的时候，服务器创建一个新的session对象，同时生成一个sessionId,然后返回响应的时候
        以Set-Cookie: JSESSIONID=A718AFD13E4961C101D74AA30B01F2A2; Path=/myweb; HttpOnly形式返回。
        之后的请求就不会再设置此cookie了，只有首次有，本次请求头也没有cookie字段。
        下次请求，浏览器会自动带入Cookie: JSESSIONID=A718AFD13E4961C101D74AA30B01F2A2设置为请求header。


# Filter 过滤器
    能够对所有资源的HTTP请求和响应进行拦截，然后做一些额外处理操作。
    它可以实现权限控制、过滤一些特殊字符、统一设置编码字符集等等，是不是很厉害，只需要写一次代码，就可以对所有的请求和响应都生效，

    1.过滤器链拦截顺序
        按照名字的顺序来决定，没有其他办法
        xml中按照配置的顺序来决定
        ======Second过滤器前======
        ======First过滤器前======
        ====进入empty servlet====
        ======First过滤器后======
        ======Second过滤器后======
    2.

# Listener 监听器
    Listener监听器也是Servlet中最重要的组件之一，监听器有什么用呢？？？通过监听器，我们可以监听某个对象，如果被监听的对象发生某些改变，那么我们的监听器就可以立即知道，从而可以做出一些处理操作。
    在整个Servlet规范中，主要定义了【8类监听器】，但是在实际开发中，使用最多的可能就只有下面这三个，分别是：
    
    用于监听ServletContext上下文的监听器。
    用于监听HttpSession会话的监听器。
    用于监听ServletRequest请求对象的监听器。
    Servlet组件中，可以利用上面三个监听器实现【对象创建和销毁】、【属性变化】、【session状态变化】的监听功能。
    
    对象的创建和销毁
    属性变化（添加属性、移除属性、替换属性）
    session状态变化（绑定session、解绑session，不常用）
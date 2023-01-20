package com.xulei.servlet.hello;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/hello2")
public class HelloServlet2 implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("=======init,初始化，只执行一次=======");
        // 获取web.xml中的配置
        //     <init-param>
        //      <param-name>username</param-name>
        //      <param-value>xulei</param-value>
        //    </init-param>
        String username = config.getInitParameter("username");
        System.out.println("username:"+username);
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("=======getServletConfig=======");
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("servlet hello world");
        // 请求行数据
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        String method = httpServletRequest.getMethod();
        System.out.println("请求方法:"+method);

        String contextPath = httpServletRequest.getContextPath();
        System.out.println("虚拟目录路径:"+contextPath);

        StringBuffer requestURL = httpServletRequest.getRequestURL();
        System.out.println("请求URL："+requestURL);

        String requestURI = httpServletRequest.getRequestURI();
        System.out.println("请求URI：" + requestURI);

        String queryString = httpServletRequest.getQueryString();
        System.out.println("请求参数字符串:"+queryString);

        // 请求头
        String header = httpServletRequest.getHeader("User-Agent");
        System.out.println("请求头:"+header);

        // 获取字符输入流
        BufferedReader reader = httpServletRequest.getReader();
        String s = reader.readLine();
        System.out.println("读取数据:"+s);
    }

    @Override
    public String getServletInfo() {
        System.out.println("=======getServletInfo=======");
        // 该方法用于返回Servlet的描述信息，这些信息可以是Servlet的作者，版本、版权信息等。
        // 默认情况下，这个方法返回空串。开发人员可以覆盖这个方法来返回有意义的信息。
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("=======destroy=======");
    }
}

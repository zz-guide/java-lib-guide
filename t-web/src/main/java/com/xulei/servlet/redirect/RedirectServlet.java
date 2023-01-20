package com.xulei.servlet.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/red")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // forward() forward是服务器请求资源，服务器直接访问目标地址的URL，把那个URL的响应内容读取过来,然后把这些内容再发给浏览器
        // 浏览器根本不知道服务器发送的内容从哪里来的，所以它的地址栏还是原来的地址
        // 可以共享request数据

        // 转发到另外一个servlet, 并且共享request数据
        req.setAttribute("name", "许磊");
        req.setAttribute("username", "xulei");
        req.getRequestDispatcher("/red1").forward(req, resp);


        // redirect() 是服务端根据逻辑，发送一个状态码，告诉浏览器重新去请求那个地址
        // 所以地址栏显示的是新的URL，所以redirect等于客户端向服务器端发出两次request，同时也接受两次response
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

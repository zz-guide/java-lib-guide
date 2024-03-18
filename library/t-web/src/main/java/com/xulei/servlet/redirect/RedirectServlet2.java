package com.xulei.servlet.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/red2")
public class RedirectServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 方式一 重定向
//        resp.setStatus(301); // 永久重定向
        // 需要加上虚拟目录,因为是url发生变化
//        resp.setHeader("location", req.getContextPath()+"/red1");

        // 方式二 重定向
        resp.sendRedirect(req.getContextPath()+"/red1");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}

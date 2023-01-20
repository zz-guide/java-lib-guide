package com.xulei.servlet.user;

import com.alibaba.fastjson2.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user", loadOnStartup = 1)
@MultipartConfig
public class UserServlet extends HttpServlet {
    // servlet内部提供了doXXX方法支持不同类型请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("======进入get请求======");
        // 获取参数
        String id = req.getParameter("id");
        String str = req.getParameter("str");
        System.out.println("str="+str);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        // 中文乱码
        String name = new String("许磊".getBytes(), StandardCharsets.UTF_8);
        map.put("name", name);
        map.put("age", 23);
        map.put("created_at", "2022-12-24 12:12");
        map.put("updated_at", "2022-12-24 12:12");
        JSONObject json = new JSONObject(map);

        // 设置response
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json"); // 返回json，此处设置charset不起作用
        resp.setStatus(200); // 设置响应行，状态码
//        resp.setHeader("", ""); // 设置响应头，键值对
//        resp.getWriter(); // 设置响应体，获取字符输入流
//        resp.getOutputStream(); // 设置响应体，获取字节输入流
        out.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("======进入post请求======");
        // post默认body类型是x-www-urlencoded,添加@MultipartConfig注解就可以解析form-data了
        String str = req.getParameter("str");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        this.getParameter(req, resp);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "成功");
        JSONObject json = new JSONObject(map);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json"); // 返回json
        out.println(json);
    }

    private void getParameter(HttpServletRequest req, HttpServletResponse resp){
        // 1.获取全部参数，getParameterMap() 得到的是一个map,因为可能是多个值，所以是String[]
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (String key: parameterMap.keySet()) {
            System.out.println("key:"+key);
            String[] strings = parameterMap.get(key);
            System.out.println(Arrays.toString(strings));
        }

        // 2.获取指定key的值，getParameterValues()
        String[] ids = req.getParameterValues("ids");
        System.out.println(Arrays.toString(ids));
        System.out.println("ssss"+ids[0]);

        // 3.获取单个参数的值，getParameter()
        String id = req.getParameter("id");
        System.out.println("id:"+id);
    }
}

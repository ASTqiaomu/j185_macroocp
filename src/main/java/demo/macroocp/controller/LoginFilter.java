package demo.macroocp.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter",urlPatterns="*.html")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("销毁过滤器");
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //先判断有无session对象存在
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpServletResponse servletResponse = (HttpServletResponse) resp;
        HttpSession session = servletRequest.getSession(false);
        String servletPath = servletRequest.getServletPath();  //获取客户端所请求的脚本文件的文件路径

        //不过滤登录页面
        if (!servletPath.equals("/login.html") )
        {
            if(session==null){
                //没有登录
//                System.out.println("未登录");
                resp.setContentType("text/html; charset=UTF-8"); //转码
                //resp.reset();
                PrintWriter out = resp.getWriter();
                out.flush();
                out.println("<script>");
                out.println("alert('未登录！');");
                out.println("window.location.href='login.html';");
                out.println("</script>");
                out.close();
                //((HttpServletResponse) resp).sendRedirect("login.html");
                //respons.sendRedirect(reques.getContextPath()+"/static/login.html");
            }
            else {
//                System.out.println("已登录");
                chain.doFilter(req, resp);
            }
        }
        else {
            chain.doFilter(req, resp);
        }
    }
    @Override
    public void init(FilterConfig config) {
        System.out.println("初始化过滤器");
    }
}

package web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 张洲徽 on 2018/9/27.
 */
public class LoginFilter implements Filter{
    //config才能一直存在，不然init之后就消失了
    private FilterConfig config;
    public void destroy() {
    }
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)arg0;
        HttpServletResponse response=(HttpServletResponse)arg1;
        //排除不需要过滤的请求
        String[] paths=new String[]{"/toLogin.do","/login.do","/createImg.do"};
        String sp= request.getServletPath();
        for(String p:paths){
            if(p.equals(sp)){
                arg2.doFilter(request, response);
                return;
            }
        }
        HttpSession session=request.getSession();
        if(session.getAttribute("admin")!=null){
            //传arg0也行
            arg2.doFilter(request, response);
        }else{
            //当前路径不确定，应用绝对路径，/netctoss/toLogin.do
            response.sendRedirect("toLogin.do");
        }
    }
    public void init(FilterConfig arg0) throws ServletException {
    }
}

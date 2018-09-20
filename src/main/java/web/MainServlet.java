package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public class MainServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        HttpSession session = request.getSession();
        if (action.equals("/login")) {

        }else if(action.equals("/logout")){
            System.out.println(123);
            session.invalidate();
            response.sendRedirect("login.jsp");
        }else{
            System.out.print("地址错误");
        }
    }
}

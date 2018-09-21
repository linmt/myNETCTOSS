package web;

import dao.cost.CostDAOImpl;
import dao.cost.ICostDAO;
import entity.Cost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public class MainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p=request.getServletPath();
        System.out.println("MainServlet的request.getServletPath()："+p+"，结束");//   /findCost.do
        //String uri = request.getRequestURI();
        //String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        if ("/findCost.do".equals(p)) {
            findCost(request,response);
        }else if("/.do".equals(p)){

        }else{
            throw new RuntimeException("地址错误");
        }
    }
    //查询资费
    protected void findCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ICostDAO dao=new CostDAOImpl();
        List<Cost> list=dao.findAll();
        request.setAttribute("costs", list);
        //当前：/netctoss/findCost.do
        //目标：/netctoss/WEB-INF/cost/find.jsp
        request.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(request, response);
    }
}

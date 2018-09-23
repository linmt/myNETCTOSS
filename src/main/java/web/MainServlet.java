package web;

import dao.cost.CostDAOImpl;
import dao.cost.CostDAO;
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
        }else if("/toAddCost.do".equals(p)){
            toAddCost(request,response);
        }else if("/addCost.do".equals(p)){
            addCost(request,response);
        }else if("/toUpdateCost.do".equals(p)){
            toUpdateCost(request,response);
        }else if("/updateCost.do".equals(p)){
            updateCost(request,response);
        }else if("/deleteCost.do".equals(p)){
            deleteCost(request,response);
        }else if("/toLogin.do".equals(p)){
            toLogin(request,response);
        }else if("/toIndex.do".equals(p)){
            toIndex(request,response);
        }else if("/login.do".equals(p)){
            login(request,response);
        }else{
            throw new RuntimeException("地址错误");
        }
    }
    //查询资费
    protected void findCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CostDAO dao=new CostDAOImpl();
        List<Cost> list=dao.findAll();
        request.setAttribute("costs", list);
        //当前：/netctoss/findCost.do
        //目标：/netctoss/WEB-INF/cost/find.jsp
        request.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(request, response);
    }

    //打开增加资费页面
    protected void toAddCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(request, response);
    }

    //增加资费数据
    protected void addCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CostDAO dao=new CostDAOImpl();
        String name=request.getParameter("name");
        String cost_type=request.getParameter("cost_type");
        String base_duration=request.getParameter("base_duration");
        String base_cost=request.getParameter("base_cost");
        String unit_cost=request.getParameter("unit_cost");
        String descr=request.getParameter("descr");
        Cost c=new Cost();
        c.setName(name);
        c.setCost_type(cost_type);
        if (base_duration!=null && !base_duration.equals("")){
            c.setBase_duration(Integer.parseInt(base_duration));
        }
        if (base_cost!=null && !base_cost.equals("")){
            c.setBase_cost(Double.parseDouble(base_cost));
        }
        if (unit_cost!=null && !unit_cost.equals("")){
            c.setUnit_cost(Double.parseDouble(unit_cost));
        }
        c.setDescr(descr);
        try {
            dao.save(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 重定向到查询功能
         * 当前：/netctoss/addCost.do
         * 目标：/netctoss/findCost.do
         */
        response.sendRedirect("findCost.do");
    }

    //打开资费修改页面
    protected void toUpdateCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cost_id=request.getParameter("cost_id");
        CostDAO dao=new CostDAOImpl();
        try {
            Cost cost=dao.findById(new Integer(cost_id));
            request.setAttribute("cost",cost);
            request.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //修改资费数据
    protected void updateCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        CostDAO dao=new CostDAOImpl();
        String cost_id=request.getParameter("cost_id");
        String name=request.getParameter("name");
        String base_duration=request.getParameter("base_duration");
        String base_cost=request.getParameter("base_cost");
        String unit_cost=request.getParameter("unit_cost");
        String descr=request.getParameter("descr");
        String cost_type=request.getParameter("cost_type");
        Cost c=new Cost();
        c.setCost_id(Integer.parseInt(cost_id));
        c.setName(name);
        if (base_duration!=null && !base_duration.equals("")){
            c.setBase_duration(Integer.parseInt(base_duration));
        }
        if (base_cost!=null && !base_cost.equals("")){
            c.setBase_cost(Double.parseDouble(base_cost));
        }
        if (unit_cost!=null && !unit_cost.equals("")){
            c.setUnit_cost(Double.parseDouble(unit_cost));
        }
        c.setDescr(descr);
        c.setCost_type(cost_type);
        try {
            dao.updateCost(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * 重定向到查询功能
         * 当前：/netctoss/addCost.do
         * 目标：/netctoss/findCost.do
         */
        response.sendRedirect("findCost.do");
    }

    //资费删除
    protected void deleteCost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cost_id=request.getParameter("cost_id");
        CostDAO dao=new CostDAOImpl();
        try {
            dao.deleteCost(Integer.parseInt(cost_id));
            response.sendRedirect("findCost.do");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打开登录页面
    protected void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);
    }

    //打开主页
    protected void toIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/main/index.jsp").forward(request, response);
    }

    //打开主页
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("WEB-INF/main/index.jsp").forward(request, response);
    }
}

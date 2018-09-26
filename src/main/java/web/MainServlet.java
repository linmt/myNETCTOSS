package web;

import dao.cost.AdminDAO;
import dao.cost.AdminDAOImpl;
import dao.cost.CostDAO;
import dao.cost.CostDAOImpl;
import entity.Admin;
import entity.Cost;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by 张洲徽 on 2018/9/20.
 */
public class MainServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String p=request.getServletPath();
        //System.out.println("MainServlet的request.getServletPath()："+p+"，结束");//   /findCost.do
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        if (action.equals("/findCost")) {
            try {
                findCost(request,response,session);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("异常",e);
            }
        }else if(action.equals("/toAddCost")){
            toAddCost(request,response);
        }else if(action.equals("/addCost")){
            addCost(request,response);
        }else if(action.equals("/toUpdateCost")){
            toUpdateCost(request,response);
        }else if(action.equals("/updateCost")){
            updateCost(request,response);
        }else if(action.equals("/deleteCost")){
            deleteCost(request,response);
        }else if(action.equals("/toLogin")){
            toLogin(request,response);
        }else if(action.equals("/toIndex")){
            toIndex(request,response);
        }else if(action.equals("/login")){
            login(request,response,session);
        }else if(action.equals("/logout")){
            session.invalidate();
            response.sendRedirect("toLogin.do");
        }else{
            throw new RuntimeException("地址错误");
        }
    }
    //查询资费
    protected void findCost(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
        //CostDAO dao=new CostDAOImpl();
        //List<Cost> list=dao.findAll();
        //request.setAttribute("costs", list);
        //当前：/netctoss/findCost.do
        //目标：/netctoss/WEB-INF/cost/find.jsp
        //request.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(request, response);
        int page;
        CostDAO dao=new CostDAOImpl();
        if(request.getParameter("page")==null){
            System.out.println("page是空的");
            page=1;
        }else {
            page=Integer.parseInt(request.getParameter("page"));
        }
        System.out.println("page:"+page);
        int pageSize=3;
        try {
            int totalPage=dao.findTotalPage(pageSize);
            List<Cost> list=dao.findByPage(page,pageSize);
            request.setAttribute("page", page);
            request.setAttribute("costs", list);
            request.setAttribute("totalPage", totalPage);
            request.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(request, response);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("有异常！",e);
        }
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

    //处理登录数据
    protected void login(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws ServletException, IOException{
        //获取网页上的验证码和自动生成的验证码
        String number1 = request.getParameter("number");
        String number2 = (String)session.getAttribute("number");

        //获取账号密码
        String admin_code=request.getParameter("admin_code");
        String password=request.getParameter("password");

        //这里是重定向，不能用request.setAttribute，如果非要传数据过去jsp，第一种方法是：
        // response.sendRedirect("a.jsp?a=cha");
        //那么在a.jsp页面上直接<%=request.getParameter("a")%>就可以了，不用写<%Object a = (Object)request.getAttribute("cha");%>
        //这种方式不可传变量？
        //request.setAttribute("admin_code",admin_code);
        //request.setAttribute("password",password);

        //session.setAttribute("admin_code", admin_code);
        //session.setAttribute("password", password);

        //校验
        AdminDAO dao=new AdminDAOImpl();
        try {
            Admin admin=dao.findByCode(admin_code);
            if(admin==null){
                request.setAttribute("error","账号错误");
                request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);
            }else if(!admin.getPassword().equals(password)){
                request.setAttribute("error","密码错误");
                request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);
            }else if(!number1.equalsIgnoreCase(number2)){
                request.setAttribute("error","验证码错误");
                request.getRequestDispatcher("WEB-INF/main/login.jsp").forward(request, response);
            }else{
                //页面显示账号
                //这里的cookie不用改路径，因为这个cookie是在/netctoss/login.do下的，对/netctoss下的所有文件都有效
                Cookie ck=new Cookie("admin_code",admin_code);
                response.addCookie(ck);
                Cookie ck2=new Cookie("password",password);
                response.addCookie(ck2);
                //这里传用户名过去是为了确保不是直接通过toIndex.do登录的
                session.setAttribute("admin", admin);
                response.sendRedirect("toIndex.do");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

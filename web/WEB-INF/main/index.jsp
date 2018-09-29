<%--
  Created by IntelliJ IDEA.
  User: 热带雨林
  Date: 2018/9/24
  Time: 1:44
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>达内－NetCTOSS</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
</head>
<body class="index">
<%--
    在过滤器中做检查
    Object obj=session.getAttribute("admin");
    if(obj==null){
        //没有登录成功，或者session超时
        response.sendRedirect("toLogin.do");
        return;
    }
--%>
<!--导航区域开始-->
<div id="index_navi">
    <%--  <%@include file="../public/header.jsp" %>   --%>
    <%--路径问题：此处要站在服务器的角度看，因为import标签是服务器调用的，
        服务器在翻译的时候使用该标签，服务器直接将header.jsp加到当前jsp中，
        所以当前和目标路径是相同的
     --%>
    <c:import url="../public/header.jsp"></c:import>
</div>
</body>
</html>

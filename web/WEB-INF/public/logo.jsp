<%--
  Created by IntelliJ IDEA.
  User: 张洲徽
  Date: 2018/9/26
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<div id="header">
    <%--路径问题三--%>
    <img src="images/logo.png" alt="logo" class="left"/>
    <%--方法一：用request.getCookies();遍历--%>
    <%--
    <span>${cookie.admin_code.value}</span>
    以下方式也行
    --%>
    <span>${sessionScope.admin.admin_code}</span>
    <a href="/toLogout.do">[退出]</a>
</div>

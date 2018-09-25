<%--
  Created by IntelliJ IDEA.
  User: 热带雨林
  Date: 2018/9/24
  Time: 1:38
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
<div class="login_box">
    <form action="login.do" method="post">
        <table>
            <tr>
                <td class="login_info">账号：</td>
                <td colspan="2"><input name="admin_code" type="text" class="width150" value="${admin_code}"/></td>
                <td class="login_error_info"><span class="required">30长度的字母、数字和下划线</span></td>
            </tr>
            <tr>
                <td class="login_info">密码：</td>
                <td colspan="2"><input name="password" type="password" class="width150" value="${password}"/></td>
                <td><span class="required">30长度的字母、数字和下划线</span></td>
            </tr>
            <tr>
                <td class="login_info">验证码：</td>
                <td class="width70"><input name="number" type="text" class="width70" /></td>
                <td>
                    <img src="checkcode" alt="验证码" title="点击更换"  id="img1"/>
                </td>
                <td><a href="javascript:;"onclick="document.getElementById('img1').src='checkcode?'+Math.random();">看不清，换一个</a></td>
            </tr>
            <tr>
                <td></td>
                <td class="login_button" colspan="2">
                    <!--
                        表单有onsubmit事件，点击submit按钮，就触发该事件
                        表单有submit方法，调用该方法也可以提交表单
                        在超链接上写JS，但必须声明，语法：javascript:js代码;
                        DOM获取元素：id，document.getElementsByTagName('form')[0];(以下是简单写法)
                    -->
                    <a href="javascript:document.forms[0].submit(); "><img src="images/login_btn.png" /></a>
                </td>
                <td><span class="required">${error}</span></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
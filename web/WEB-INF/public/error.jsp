<%--
  Created by IntelliJ IDEA.
  User: 张洲徽
  Date: 2018/9/26
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8" isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>达内－NetCTOSS</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" src="../js/jquery-1.4.3.js"></script>
    <script language="javascript" type="text/javascript">
        var timer;
        //启动跳转的定时器
        function startTimes() {
            timer = window.setInterval(showSecondes,1000);
        }
        var i = 5;
        function showSecondes() {
            if(i>0){
                i--;
                document.getElementById("secondes").innerHTML = i;
            }else{
                window.clearInterval(timer);
                //当前可能是update.do或findCost.do等转发过来的，地址不确定，所以此处要用绝对路径
                //再者此文件是公用的，用绝对路径
                location.href = "/toLogin.do";
            }
        }
        //取消跳转
        function resetTimer() {
            if(timer!=null && timer!=undefined){
                window.clearInterval(timer);
                location.href = "/toLogin.do";
            }
        }
    </script>
</head>
<body class="error_page" onload="startTimes();">
<h1 id="error">
    遇到错误，&nbsp;<span id="secondes">5</span>&nbsp;秒后将自动跳转，立即跳转请点击&nbsp;
    <a  href="javascript:resetTimer();">返回</a>
</h1>
</body>
</html>


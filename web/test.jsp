<%--
  Created by IntelliJ IDEA.
  User: 张洲徽
  Date: 2018/9/27
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Title</title>
    <script language="javascript" type="text/javascript">
        function keyDown(e) {
            var ev= window.event||e;
            if (ev.keyCode == 13) {
                alert("回车");
            }
        }
    </script>
</head>
<body>
    跳转到 <input type="text" id="skip_to_page" style="width:25px" onkeydown="keyDown(event)" /> 页
</body>
</html>

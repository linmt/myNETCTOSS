<%--
  Created by IntelliJ IDEA.
  User: 热带雨林
  Date: 2018/9/23
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>达内－NetCTOSS</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" type="text/javascript">
        //保存结果的提示
        function showResult() {
            showResultDiv(true);
            window.setTimeout("showResultDiv(false);", 3000);
        }
        function showResultDiv(flag) {
            var divResult = document.getElementById("save_result_info");
            if (flag)
                divResult.style.display = "block";
            else
                divResult.style.display = "none";
        }

        //切换资费类型
        function feeTypeChange(type) {
            var inputArray = document.getElementById("main").getElementsByTagName("input");
            if (type == 1) {
                inputArray[5].readOnly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readOnly = false;
                inputArray[6].className = "width100";
                inputArray[7].readOnly = true;
                inputArray[7].className += " readonly";
                inputArray[7].value = "";
            }
            else if (type == 2) {
                inputArray[5].readOnly = false;
                inputArray[5].className = "width100";
                inputArray[6].readOnly = false;
                inputArray[6].className = "width100";
                inputArray[7].readOnly = false;
                inputArray[7].className = "width100";
            }
            else if (type == 3) {
                inputArray[5].readOnly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readOnly = true;
                inputArray[6].value = "";
                inputArray[6].className += " readonly";
                inputArray[7].readOnly = false;
                inputArray[7].className = "width100";
            }
        }
    </script>
</head>
<body>
<!--Logo区域开始-->
<div id="header">
    <c:import url="../public/logo.jsp"></c:import>
</div>
<!--Logo区域结束-->
<!--导航区域开始-->
<div id="navi">
    <c:import url="../public/header.jsp"></c:import>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <div id="save_result_info" class="save_success">保存成功！</div>
    <form action="updateCost.do" method="post" class="main_form">
        <div class="text_info clearfix"><span>资费ID：</span></div>
        <div class="input_info">
            <%--默认值${cost.cost_id }--%>
            <input type="text" class="readonly" name="cost_id" value="${cost.cost_id }" readonly="readonly" />
        </div>
        <div class="text_info clearfix"><span>资费名称：</span></div>
        <div class="input_info">
            <input type="text" class="width300" name="name" value="${cost.name}"/>
            <span class="required">*</span>
            <div class="validate_msg_short">50长度的字母、数字、汉字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>资费类型：</span></div>
        <div class="input_info fee_type">
            <%--input包含JSTL标签--%>
            <%--EL标签方式：${cost.cost_type==1?'checked':'' }代替checked="<c:if test="${cost.cost_type==1 }">checked</c:if>"--%>
            <input type="radio" name="cost_type" value="1" id="monthly" onclick="feeTypeChange(1);"
                   checked="<c:if test="${cost.cost_type==1 }">checked</c:if>" />
            <label for="monthly">包月</label>
            <input type="radio" name="cost_type" value="2"  id="package" onclick="feeTypeChange(2);"
                   checked="<c:if test="${cost.cost_type==2 }">checked</c:if>" />
            <label for="package">套餐</label>
            <input type="radio" name="cost_type" value="3" id="timeBased" onclick="feeTypeChange(3);"
                   checked="<c:if test="${cost.cost_type==3 }">checked</c:if>" />
            <label for="timeBased">计时</label>
        </div>
        <div class="text_info clearfix"><span>基本时长：</span></div>
        <div class="input_info">
            <input type="text" name="base_duration" value="${cost.base_duration }" class="width100" />
            <span class="info">小时</span>
            <span class="required">*</span>
            <div class="validate_msg_long">1-600之间的整数</div>
        </div>
        <div class="text_info clearfix"><span>基本费用：</span></div>
        <div class="input_info">
            <input type="text" name="base_cost" value="${cost.base_cost }" class="width100" />
            <span class="info">元</span>
            <span class="required">*</span>
            <div class="validate_msg_long">0-99999.99之间的数值</div>
        </div>
        <div class="text_info clearfix"><span>单位费用：</span></div>
        <div class="input_info">
            <input type="text" name="unit_cost" value="${cost.unit_cost }" class="width100" />
            <span class="info">元/小时</span>
            <span class="required">*</span>
            <div class="validate_msg_long">0-99999.99之间的数值</div>
        </div>
        <div class="text_info clearfix"><span>资费说明：</span></div>
        <div class="input_info_high">
            <textarea class="width300 height70">${cost.descr}</textarea>
            <div class="validate_msg_short">100长度的字母、数字、汉字和下划线的组合</div>
        </div>
        <div class="button_info clearfix">
            <input type="submit" value="保存" class="btn_save"/>
            <input type="button" value="取消" class="btn_save" onclick="history.back()" />
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <c:import url="../public/footer.jsp"></c:import>
</div>
</body>
</html>


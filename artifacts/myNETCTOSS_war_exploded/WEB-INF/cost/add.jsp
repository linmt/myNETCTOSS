<%--
  Created by IntelliJ IDEA.
  User: 热带雨林
  Date: 2018/9/23
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>达内－NetCTOSS</title>
    <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
    <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
    <script language="javascript" src="../js/jquery-1.4.3.js"></script>
    <script language="javascript" type="text/javascript">
        //名称字段是否验证通过
        var nameFlag = false;
        //校验资费名称
        function checkName(){
            var costName = $("#costName").val();
            //校验资费名称是否为空
            if(costName==""){
                nameFlag = false;
                $("#costNameMsg").text("资费名称不能为空！");
                $("#costNameMsg").addClass("error_msg");
                return;
            }
            //校验资费名称是否重复
            $.post(
                "checkCostName",
                {"name":costName},
                function(data){//data是接收返回值的,实际上就是
                    var repeat = data;
                    if(repeat){
                        nameFlag = flase;
                        $("#costNameMsg").text("资费名称已经存在");
                        $("#costNameMsg").addClass("error_msg");
                    }else{
                        nameFlag=true;
                        $("#costNameMsg").text("50长度的字母、数字、汉字和下划线的组合");
                        $("#costNameMsg").removeClass("error_msg");
                    }
                }
            )
        }

        //保存结果的提示
        function showResult() {
            document.forms[0].submit();
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
                inputArray[4].readOnly = true;
                inputArray[4].value = "";
                inputArray[4].className += " readonly";
                inputArray[5].readOnly = false;
                inputArray[5].className = "width100";
                inputArray[6].readOnly = true;
                inputArray[6].className += " readonly";
                inputArray[6].value = "";
            }
            else if (type == 2) {
                inputArray[4].readOnly = false;
                inputArray[4].className = "width100";
                inputArray[5].readOnly = false;
                inputArray[5].className = "width100";
                inputArray[6].readOnly = false;
                inputArray[6].className = "width100";
            }
            else if (type == 3) {
                inputArray[4].readOnly = true;
                inputArray[4].value = "";
                inputArray[4].className += " readonly";
                inputArray[5].readOnly = true;
                inputArray[5].value = "";
                inputArray[5].className += " readonly";
                inputArray[6].readOnly = false;
                inputArray[6].className = "width100";
            }
        }
    </script>
</head>
<body>
<!--Logo区域开始-->
<div id="header">
    <img src="images/logo.png" alt="logo" class="left"/>
    <a href="#">[退出]</a>
</div>
<!--Logo区域结束-->
<!--导航区域开始-->
<div id="navi">
    <ul id="menu">
        <li><a href="../index.html" class="index_off"></a></li>
        <li><a href="../role/role_list.html" class="role_off"></a></li>
        <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
        <li><a href="../fee/fee_list.html" class="fee_on"></a></li>
        <li><a href="../account/account_list.html" class="account_off"></a></li>
        <li><a href="../service/service_list.html" class="service_off"></a></li>
        <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
        <li><a href="../report/report_list.html" class="report_off"></a></li>
        <li><a href="../user/user_info.html" class="information_off"></a></li>
        <li>cost</li>
    </ul>
</div>
<!--导航区域结束-->
<!--主要区域开始-->
<div id="main">
    <div id="save_result_info" class="save_fail">保存失败，资费名称重复！</div>
    <!--
        当前：/netctoss/toAddCost.do
        目标：/netctoss/addCost.do
    -->
    <form action="addCost.do" method="post" class="main_form">
        <div class="text_info clearfix"><span>资费名称：</span></div>
        <div class="input_info">
            <input type="text" class="width300" name="name" value="" id="costName" onblur="checkName();"/>
            <span class="required">*</span>
            <div class="validate_msg_short" id="costNameMsg">50长度的字母、数字、汉字和下划线的组合</div>
        </div>
        <div class="text_info clearfix"><span>资费类型：</span></div>
        <div class="input_info fee_type">
            <input type="radio" name="cost_type" value="1" id="monthly" onclick="feeTypeChange(1);" />
            <label for="monthly">包月</label>
            <input type="radio" name="cost_type" value="2" checked="checked" id="package" onclick="feeTypeChange(2);" />
            <label for="package">套餐</label>
            <input type="radio" name="cost_type" value="3" id="timeBased" onclick="feeTypeChange(3);" />
            <label for="timeBased">计时</label>
        </div>
        <div class="text_info clearfix"><span>基本时长：</span></div>
        <div class="input_info">
            <input type="text" name="base_duration" class="width100" />
            <span class="info">小时</span>
            <span class="required">*</span>
            <div class="validate_msg_long">1-600之间的整数</div>
        </div>
        <div class="text_info clearfix"><span>基本费用：</span></div>
        <div class="input_info">
            <input type="text" name="base_cost" class="width100" />
            <span class="info">元</span>
            <span class="required">*</span>
            <div class="validate_msg_long">0-99999.99之间的数值</div>
        </div>
        <div class="text_info clearfix"><span>单位费用：</span></div>
        <div class="input_info">
            <input type="text" name="unit_cost" class="width100" />
            <span class="info">元/小时</span>
            <span class="required">*</span>
            <div class="validate_msg_long">0-99999.99之间的数值</div>
        </div>
        <div class="text_info clearfix"><span>资费说明：</span></div>
        <div class="input_info_high">
            <textarea class="width300 height70" name="descr"></textarea>
            <div class="validate_msg_short">100长度的字母、数字、汉字和下划线的组合</div>
        </div>
        <div class="button_info clearfix">
            <input type="button" value="保存" class="btn_save"  onclick="showResult();" />
            <input type="button" value="取消" class="btn_save"  onclick="history.back();" />
        </div>
    </form>
</div>
<!--主要区域结束-->
<div id="footer">
    <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
    <br />
    <span>版权所有(C)加拿大达内IT培训集团公司 </span>
</div>
</body>
</html>

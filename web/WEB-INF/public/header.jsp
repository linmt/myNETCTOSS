<%--
  Created by IntelliJ IDEA.
  User: 张洲徽
  Date: 2018/9/25
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<script type="text/javascript" src="../js/jquery-1.4.3.js"></script>
<script type="text/javascript" >
    $("document").ready(function() {
        $("#menu .index_off").mouseover(function() {
            $(this).attr("class", "index_on");
            $("#menu .index_on").mouseout(function() {
                $(this).attr("class", "index_off");
            });
        });
        $("#menu .role_off").mouseover(function() {
            $(this).attr("class", "role_on");
            $("#menu .role_on").mouseout(function() {
                $(this).attr("class", "role_off");
            });
        });
        $("#menu .admin_off").mouseover(function() {
            $(this).attr("class", "admin_on");
            $("#menu .admin_on").mouseout(function() {
                $(this).attr("class", "admin_off");
            });
        });
        $("#menu .fee_off").mouseover(function() {
            $(this).attr("class", "fee_on");
            $("#menu .fee_on").mouseout(function() {
                $(this).attr("class", "fee_off");
            });
        });
        $("#menu .account_off").mouseover(function() {
            $(this).attr("class", "account_on");
            $("#menu .account_on").mouseout(function() {
                $(this).attr("class", "account_off");
            });
        });
        $("#menu .service_off").mouseover(function() {
            $(this).attr("class", "service_on");
            $("#menu .service_on").mouseout(function() {
                $(this).attr("class", "service_off");
            });
        });
        $("#menu .bill_off").mouseover(function() {
            $(this).attr("class", "bill_on");
            $("#menu .bill_on").mouseout(function() {
                $(this).attr("class", "bill_off");
            });
        });
        $("#menu .report_off").mouseover(function() {
            $(this).attr("class", "report_on");
            $("#menu .report_on").mouseout(function() {
                $(this).attr("class", "report_off");
            });
        });
        $("#menu .information_off").mouseover(function() {
            $(this).attr("class", "information_on");
            $("#menu .information_on").mouseout(function() {
                $(this).attr("class", "information_off");
            });
        });
        $("#menu .password_off").mouseover(function() {
            $(this).attr("class", "password_on");
            $("#menu .password_on").mouseout(function() {
                $(this).attr("class", "password_off");
            });
        });
    });
</script>
<div id="navi">
    <ul id="menu">
        <!--由于该jsp被其他jsp公用，因此无法预估目前是谁调用该jsp，即当前路径无法确定，所以此处适合写绝对路径，eclipse中/netctoss/toIndex.do-->
        <li><a href="/toIndex.do" class="index_off" ></a></li>
        <li><a href="../role/findRole.do" class="role_off" ></a></li>
        <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
        <li><a href="/findCost.do" class="fee_off"></a></li>
        <li><a href="../account/findAccount.do" class="account_off" ></a></li>
        <li><a href="../service/findService.do" class="service_off"></a></li>
        <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
        <li><a href="../report/report_list.html" class="report_off"></a></li>
        <li><a href="../user/user_info.html" class="information_off"></a></li>
        <li><a href="../user/user_modi_pwd.html" class="password_off" ></a></li>
    </ul>
</div>

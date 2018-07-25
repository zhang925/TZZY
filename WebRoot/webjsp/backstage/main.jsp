<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head id="Head1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <title>后台管理</title>
    <link href="css/default.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='js/initial.index.js'> </script>

    <script type="text/javascript">

	var _menus = {
	"menus": [{
		"menuid": "1",
		"icon": "icon-sys",
		"menuname": "资料管理",
		"menus": [{"menuname":"百度搜索","icon":"icon-nav","url":"http://www.baidu.com"},{
			"menuid": "12",
			"menuname": "用户管理",
			"icon": "icon-add",
			"url": "",
			"child": [{
				"menuid": "140",
				"menuname": "用户列表",
				"icon": "icon-role",
				"url": "../userjsp/user_list.jsp"
			},
			{
				"menuid": "150",
				"menuname": "其他",
				"icon": "icon-set",
				"url": "../userjsp/datagrid_user_list_test.jsp"
			}]
		},
		{
			"menuid": "13",
			"menuname": "工作管理",
			"icon": "icon-users",
			"url": "",
			"child": [{
				"menuid": "141",
				"menuname": "工作日志列表",
				"icon": "icon-role",
				"url": "../worklogjsp/worklog_list.jsp"
			},
			{
				"menuid": "151",
				"menuname": "办公平台",
				"icon": "icon-set",
				"url": "../worklogjsp/bamgong.jsp"
			}]
		},
		{
			"menuid": "14",
			"menuname": "省市县",
			"icon": "icon-role",
			"url": "",
			"child": [{
				"menuid": "142",
				"menuname": "省份",
				"icon": "icon-role",
				"url": "../districtjsp/province_list.jsp"
			},
			{
				"menuid": "152",
				"menuname": "市区",
				"icon": "icon-set",
				"url": "../districtjsp/city_list.jsp"
			},
			{
				"menuid": "153",
				"menuname": "地区",
				"icon": "icon-set",
				"url": "../districtjsp/area_list.jsp"
			},
			{
				"menuid": "154",
				"menuname": "地区详情",
				"icon": "icon-set",
				"url": "../districtjsp/district_list.jsp"
			}]
		},
		{
			"menuid": "15",
			"menuname": "权限设置",
			"icon": "icon-set",
			"url": "demo.html",
			"child": [{
				"menuid": "143",
				"menuname": "角色管理 3",
				"icon": "icon-role",
				"url": "demo2.html"
			},
			{
				"menuid": "153",
				"menuname": "权限设置 3",
				"icon": "icon-set",
				"url": "demo.html"
			}]
		},
		{
			"menuid": "16",
			"menuname": "文件管理",
			"icon": "icon-log",
			"url": "",
			"child": [{
				"menuid": "144",
				"menuname": "文件列表信息",
				"icon": "icon-role",
				"url": "../filejsp/file_list.jsp"
			},
			{
				"menuid": "154",
				"menuname": "权限设置 3",
				"icon": "icon-set",
				"url": "demo.html"
			}]
		}
		,
		
		{
			"menuid": "116",
			"menuname": "每日经典",
			"icon": "icon-log",
			"url": "",
			"child": [{
				"menuid": "1144",
				"menuname": "每日经典列表",
				"icon": "icon-role",
				"url": "../classicjsp/classic_list.jsp"
			},
			{
				"menuid": "1154",
				"menuname": "在线翻译",
				"icon": "icon-set",
				"url": "http://fanyi.baidu.com/"
			}]
		}
		
		,
		{
			"menuid": "117",
			"menuname": "其他",
			"icon": "icon-log",
			"url": "",
			"child": [{
				"menuid": "1171",
				"menuname": "配置管理",
				"icon": "icon-role",
				"url": "../sysconfigurejsp/sysconfigure_list.jsp"
			},
			{
				"menuid": "154",
				"menuname": "其他",
				"icon": "icon-set",
				"url": "../sysconfigurejsp/other.jsp"
			}]
		}
		
		
		
		
		]
	},
	{
		"menuid": "8",
		"icon": "icon-sys",
		"menuname": "生日管理",
		"menus": [{
			"menuid": "22",
			"menuname": "生日管理",
			"icon": "icon-nav",
			//"url": "demo1.html",
			"child": [{
				"menuid": "221",
				"menuname": "生日信息",
				"icon": "icon-nav",
				"url": "../schedulejsp/bornInfo_test.jsp"
			},
			{
				"menuid": "222",
				"menuname": "其他",
				"icon": "icon-nav",
				"url": "http://www.baidu.com"
			}]
		},{
			"menuid": "21",
			"menuname": "其他",
			"icon": "icon-nav",
			"url": "http://www.baidu.com"
		}
		]
	},
	{
		"menuid": "56",
		"icon": "icon-sys",
		"menuname": "日程管理",
		"menus": [{
			"menuid": "31",
			"menuname": "日程规划",
			"icon": "icon-nav",
			"url": "../schedulejsp/schedule_list.jsp"
		},
		{
			"menuid": "321",
			"menuname": "其他",//2级菜单
			"icon": "icon-nav",
			//"url": "demo2.html",
			"child": [{
				"menuid": "311",
				"menuname": "生日列表",//2.1级菜单
				"icon": "icon-nav",
				"url": "../schedulejsp/bornInfo_test.jsp"
			},
			{
				"menuid": "312",
				"menuname": "其他",//2.2级菜单
				"icon": "icon-nav",
				"url": "http://www.baidu.com"
			}]
		}]
	}]
};

        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('../../userController/xgmm.do?password=' + $newpass.val(), function() {
               // msgShow('系统提示', '恭喜，密码修改成功！即将返回首页，重新登录！ ', 'info');
                alert("修改密码成功！即将返回首页，重新登录！");
                $newpass.val('');
                $rePass.val('');
                closePwd();
                $.ajax({
		    		url:'../../userController/exit.do',
		    		type:'POST',
		    		success:function(){
		    			location.href="../../index.jsp";
		    		},
		    		error:function(){
		    			location.href="../../index.jsp";
		    		}
				 });
                
            })
            
        }

        $(function() {

            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        $.ajax({
				    		url:'../../userController/exit.do',
				    		async:false,
				    		type:'POST',
				    		success:function(){
				    			location.href="../../index.jsp";
				    		},
				    		error:function(){
				    			location.href="../../index.jsp";
				    		}
				    	});
                    }
                });
            })
        });
		
		

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
    <img src="images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
</div>

    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎您：${sessionScope.user.name } 
        <a href="#" id="editpass">修改密码</a> 
        <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px;"><img src="images/blocks.gif" width="20" height="20" align="middle" />ENDLESS FOREVER	
        	&nbsp;&nbsp;
        	<a href="../../index.jsp" style=" font-size: 14px; color: white; font-weight: bolder;">首&nbsp;页</a>
        </span>
    </div>
    
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By EndlessForever </div>
    </div>
    
    <div region="west" split="true"  title="导航菜单" style="width:180px;" id="west">
			<div id="nav">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="每日经典" style="padding:20px;overflow:hidden;  background-color:#FFFFDD; " >
				<h1 id="mrjd" style="font-size:20px;color:blue;" ><%-- --%>
					<%-- 每日经典部分 --%>
					
				</h1>
				<h3 id="mrjd2" style="color: red;"></h3>
				<h1 style="font-size:24px;">* 无尽的永恒</h1>
				<h1 style="font-size:24px;">* QQ：825230856</h1>
				<h1 style="font-size:24px;">* WeChat：ZZY9251314</h1>
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>


</body>
<script type="text/javascript">
	//加载经典语录
	$(function(){
		$.ajax({
			url:'../../classicController/getClassicNow.do',
			type:'POST',
			dataType:'json',
			success:function(data){
				$("#mrjd").html(data.classic.content);
				$("#mrjd2").html(data.classic.uid.name+"："+data.classic.createtime);
				
			}
		});
	});
</script>
</html>
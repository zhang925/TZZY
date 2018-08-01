<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户信息表</title>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui --%>
	<%@include file="../../common/easyuiLink.jsp" %>
	<%-- easuui和lghdialog是有冲突的 --%>
	<%--<script type="text/javascript" src="<%=basePath%>plugins_sunny/lhgDialog/lhgdialog.min.js"></script>--%>
	<script type="text/javascript">

        function test (index){
            //var rows = $('#t_user').datagrid('getChecked');//在复选框 选中的时候返回所有行
            // var rows = $('#t_user').datagrid('getSelections'); // 返回所有被选中的行，当没有记录被选中的时候将返回一个空数组。
            //var rows = $('#dg').datagrid('getSelected');//返回第一个被选中的行或如果没有选中的行则返回null。
            // resources/images/image/bishigou.jpg
             var rows = $('#t_user').datagrid('getRows');
            var row = rows[index];
            var id = row.uid;
            alert(id);

        }
        $(function(){

            $("#t_user").datagrid({
                title:'用户列表',
                idField:'uid',//标识列 必须 存在的列 不然会出现 意想不到的 bug 比如只能回去一条数据
                width:1000,
                height:350,
                url:'userController/userlisteasyui.do',
                //是否显示斑马线效果。
                striped:true,
                //如果为true，则在同一行中显示数据。设置为true可以提高加载性能
                //nowrap:true,
                rownumbers:true,//行号
                //singleSelect:true,//单选

                //真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
                //fitColumns:true,
                //同步属性列 冻结列	不能与 fitColumns同时使用
               frozenColumns:[[
                    {field:'op',
                        checkbox:true
                    }
                ]],
                columns:[[
                    {field:'uid',title:'用户ID',align:'center'},
                    {field:'photoid',title:'头像',align:'center',width:'80px',
                        formatter: function(value,row,index){
                            return "<img  onclick=\"test("+index+")\"  src=\""+row.photoid+"\" alt=\"\"  width=\"40px\" height=\"40px\" >";
                        }
                    },
                    {field:'username',title:'用户名',align:'center'},
                    {field:'name',title:'用户昵称',align:'center'},
                    {field:'password',title:'密码',align:'center'},
                    //{field:'passwordmd5',title:'MD5密码',align:'center'},
                    {field:'sex',title:'性别',align:'center' },
                    {field:'borntime',title:'出生日期',align:'center'},
                    {field:'phone',title:'手机号',align:'center'},
                    {field:'qq',title:'QQ号',align:'center'},
                    //{field:'weixin',title:'微信号',align:'center'},
                    //{field:'weibo',title:'微博',align:'center'},
                    //{field:'email',title:'邮箱',align:'center'},
                    {field:'createtime',title:'注册时间',align:'center'},
                    {field:'state',title:'状态',align:'center'},
                    {field:'opt',title:'操作',align:'center',
                        formatter: function(value,row,index){
                            return "<span onclick=\"del('"+row.uid+"');\" style=\"cursor: pointer;color:red;\">删除</span>&nbsp;"
                                +"<span onclick=\"upt('"+row.uid+"');\" style=\"cursor: pointer;color:red;\">修改</span>";
                        }
                    }
                ]],
                loadMsg:'正在加载,请稍后……',
                //pageSize:1,
                //total:3,
                //pageList:[1,2,3],
                pagination:true,
                toolbar: [{
                    iconCls: 'icon-add',
                    text:'添加用户',
                    handler: function(){
                        add();
                    }
                },'-',{
                    iconCls: '',
                    text:'查看',
                    handler: function(){
                        check();
                    }
                },'-',{
                    iconCls: '',
                    text:'修改',
                    handler: function(){
                      upt();
                    }
                },'-',{
                    iconCls: '',
                    text:'删除',
                    handler: function(){
                       del();
                    }
                },'-',{
                    iconCls: '',
                    text:'导出',
                    handler: function(){alert("导出");}
                },'-',{
                    iconCls: '',
                    text:"用户UID：<input id=\"pid\" style=\"width:50px\" />"
                    +"用户名：<input id=\"pusername\"   style=\"width:80px\" />"
                    +"用户昵称：<input id=\"pname\"   style=\"width:80px\" />"
                    +"状态：<input id=\"pstate\"   style=\"width:50px\" />",
                    handler: function(){}
                },'-',{
                    iconCls: 'icon-search',
                    text:"<span onclick=\"search();\" style=\"color:blue;\">&nbsp;搜&nbsp;索&nbsp;</span>",
                    handler: function(){}
                },'-',{
                    iconCls: 'icon-reload',
                    text:"<span onclick=\"searchreset();\" style=\"color:blue;\">&nbsp;重&nbsp;置&nbsp;</span>",
                    handler: function(){}
                },'-',{
                    iconCls: 'icon-help',
                    text:'帮助',
                    handler: function(){alert('帮助按钮')}
                }]


            });
            //按钮


            //设置分页控件
            var p = $('#t_user').datagrid('getPager');
            $(p).pagination({
                pageSize: 10,//每页显示的记录条数，默认为10
                pageList: [5,10,15,20],//可以设置每页记录条数的列表
                beforePageText: '第',//页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',  //
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                onBeforeRefresh:function(){
                    $(this).pagination('loading');
                    //alert('before refresh');
                    //$(this).pagination('loaded');
                }
            });


        });


        function add() {//添加用户
            $('#mydialog').dialog({
                title: '添加新用户',
                width: 500,
                height: 400,
                closed: false,
                cache: false,
                href: '<%=basePath%>userController/goInfoEasyUI.do',
                modal: true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        save();//调用子页面(user_add.jsp)的save方法
                    }
                },{
                    text:'关闭',
                    handler:function(){$('#mydialog').dialog('close'); }
                }]
            });
            //$("#mydialog").dialog('open');
        }

		function check() {//查看
            //允许多行选中(但只能选中一条)
            var ids = [];
            var rows = $('#t_user').datagrid('getSelections');
            for(var i=0; i<rows.length; i++){
                ids.push(rows[i].uid);
            }
            var idstr=ids.join(',');
            if(idstr==null || idstr=="" || ids.length<1){
                $.messager.alert('警告','请选择一条数据！','warning');
            }else{
                if(ids.length>1){
                    $.messager.alert('警告','只能选择一条数据！','warning');
                }else{
                    $('#mydialog').dialog({
                        title: '查看',
                        width: 500,
                        height: 400,
                        closed: false,
                        cache: false,
                        href: '<%=basePath%>userController/goInfoEasyUI.do?uid='+idstr+"&load=detail",
                        modal: true,
                        buttons:[{
                            text:'关闭',
                            handler:function(){$('#mydialog').dialog('close'); }
                        }]
                    });
                }
            }
        }


        //修改用户
		function upt() {
            //允许多行选中(但只能选中一条)
            var ids = [];
            var rows = $('#t_user').datagrid('getSelections');
            for(var i=0; i<rows.length; i++){
                ids.push(rows[i].uid);
            }
            var idstr=ids.join(',');
            if(idstr==null ||  idstr=="" || ids.length<1){
                $.messager.alert('提示','请选择一条数据！','warning');
            }else{
                if(ids.length>1){
                    $.messager.alert('提示','只能选择一条数据！','warning');
                }else{
                    uptuser(idstr);
                }
            }
        }
        function uptuser(ids){//修改
            $('#mydialog').dialog({
                title: '修改',
                width: 500,
                height: 400,
                closed: false,
                cache: false,
                href: '<%=basePath%>userController/goInfoEasyUI.do?uid='+ids ,
                modal: true,
                buttons:[{
                    text:'保存',
                    handler:function(){
                        save();//调用子页面(user_add.jsp)的save方法
                    }
                },{
                    text:'关闭',
                    handler:function(){$('#mydialog').dialog('close'); }
                }]
            });
        }

        //删除用户
        function del(uid){
            $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
                if (r){
                    var uids ; //要删除的 用户id
                    if(uid==null || uid==""){// 上方 toolbar 删除
                        //允许多行选中(但只能选中一条)
                        var ids = [];
                        var rows = $('#t_user').datagrid('getSelections');
                        if(rows==null || rows.length==null || rows.length == 0 ){
                            $.messager.alert('提示','请选择要删除的数据！','info');
                            return;
                        }
                        for(var i=0; i<rows.length; i++){
                            ids.push(rows[i].uid);
                        }
                        uids = ids.join(",");
					}else{//行后面点击的单个删除 带ID的
                        uids = uid;
					}
                    $.ajax({
                        url:'userController/delusers.do',
                        type:'POST',
                        async:false,
                        dataType:'json',
                        data:{'uids':uids},
                        success:function(data){
							$.messager.alert('提示','删除成功！','info');
							$('#t_user').datagrid('load',{});//刷新页面数据
                        },
                        error:function(data){
                           $.messager.alert('警告','异常,删除成功！','warning');
                        }
                    });
					//最后 释放  所有选中的行。不然会导致，删除后 该行还在选中状态
                    $('#t_user').datagrid('unselectAll');
                }
            });
        }

        //搜索
        function search(){
            var pid = $("#pid").val();
            var pusername = $("#pusername").val();
            var pname = $("#pname").val();
            var pstate = $("#pstate").val();
            alert("搜索："+pid+"--"+pusername+"--"+pname+"--"+pstate);
            $('#t_user').datagrid('load',{
                uid: pid,
                name: pname,
                username:pusername,
                state:pstate
            });
        }
        //重置
        function searchreset(){
            $("#pid").val("");
            $("#pusername").val("");
            $("#pname").val("");
            $("#pstate").val("");
            $('#t_user').datagrid('load',{});
        }

	</script>

</head>
<body>

<div>
	<%--<a href="<%=basePath%>index.jsp">返回首页</a>--%>
	<%-- 列表信息 --%>
	<table id="t_user"></table>
	<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
	<div id="mydialog"></div>
</div>


</body>
</html>
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
	<%@include file="../../js/easyuiLink.jsp"%>
	<%-- easuui和lghdialog是有冲突的 --%>
<%--<script type="text/javascript" src="<%=basePath%>js/lhgDialog/lhgdialog.min.js"></script>--%>
<script type="text/javascript">

	function test (src){
	 	  //var rows = $('#t_user').datagrid('getSelections');获取选中checkbox的值
	 	  //var rows = $('#dg').datagrid('getSelected');//返回第一个被选中的行 		选中行的时候
		  var rows = $('#t_user').datagrid('getRows');
		  var row = rows[8];
		  var id = row.uid;
	}
		$(function(){
			 
			$("#t_user").datagrid({
				title:'用户列表',
				idField:'id',//标识列
				width:1000,
				height:536,
				url:'userController/userlist.do',
				//是否显示斑马线效果。
				striped:true,
				//如果为true，则在同一行中显示数据。设置为true可以提高加载性能
				nowrap:true,
				rownumbers:true,//行号
				singleSelect:true,//单选
				
				//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
				//fitColumns:true, 
				//同步属性列 冻结列	不能与 fitColumns同时使用
				frozenColumns:[[
					{field:'op',
					checkbox:true,
						//当文本过多的时候可以用这个属性显示局部鼠标悬浮显示全部
						formatter: function(value,row,index){
								return row.id;
						}
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
					        		return "<span onclick=\"deluser('"+row.uid+"','single');\" style=\"cursor: pointer;color:red;\">删除</span>&nbsp;"
					        		+"<span onclick=\"uptuser('"+row.uid+"');\" style=\"cursor: pointer;color:red;\">修改</span>";
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
						$('#mydialog').dialog({    
						    title: '添加新用户',    
						    width: 500,    
						    height: 400,    
						    closed: false,    
						    cache: false,    
						    href: '<%=basePath%>webjsp/userjsp/user_add.jsp',    
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
					},'-',{
						iconCls: '',
						text:'查看',
						handler: function(){
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
									    href: '<%=basePath%>userController/gocheckuser.do?uid='+idstr,   
									    modal: true
									});
						    	 }
						    }
						}
					},'-',{
						iconCls: '',
						text:'修改',
						handler: function(){
							//允许多行选中(但只能选中一条)
						    var ids = [];
						    var rows = $('#t_user').datagrid('getSelections');
						    for(var i=0; i<rows.length; i++){
						    	ids.push(rows[i].uid);
						    }
						    var idstr=ids.join(',');
						    //alert(idstr+"--"+ids.length);
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
					},'-',{
						iconCls: '',
						text:'删除',
						handler: function(){
							/*只允许单个选中
							var row = $('#t_user').datagrid('getSelected');
							if (row){
								alert('Item ID:'+row.id+"username:"+row.username);
							}
							*/
							//允许多行选中
						    var ids = [];
						    var rows = $('#t_user').datagrid('getSelections');
						    for(var i=0; i<rows.length; i++){
						    	ids.push(rows[i].uid);
						    }
						    var idstr=ids.join(',');
						    if(idstr==null || idstr=="" || ids.length<1){
						    	$.messager.alert('警告','请选择至少一个数据！','warning');
						    }else{
						    	 deluser(idstr,'more');
						    }
						}
					},'-',{
						iconCls: '',
						text:'导出',
						handler: function(){location.href="imp.do";}
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
		
		
		
		
		//删除用户
		function deluser(uid,msg){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if (r){  
			         $.ajax({
			        	url:'userController/deluser.do',
			        	type:'POST',
			        	async:false,
			        	dataType:'json',
			        	data:{'uid':uid,'msg':msg},
			        	success:function(data){
			        		//alert(data+"data.message:"+data.message);
			        		if(data.message=="success"){
			        			$.messager.alert('提示','删除成功！','info');
			        			$('#t_user').datagrid('load',{});//刷新页面数据
			        		}else{
			        			$.messager.alert('警告',data.message,'warning');
			        		}
			        	},
			        	error:function(data){
			        		//alert(data+"data.message"+data.message);
			        		if(data.message=="success"){
			        			$.messager.alert('警告','异常,删除成功！','warning');
			        		}else{
			        			$.messager.alert('警告',data.message,'warning');
			        		}
			        	}
			        });
			    }  
			});  
		}
		
		
		//修改用户
		function uptuser(ids){
			$('#mydialog').dialog({
    	 		title: '修改',    
			    width: 500,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: '<%=basePath%>userController/goupuser.do?uid='+ids,   
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
		//搜索
		function search(){
			var pid = $("#pid").val();
			var pusername = $("#pusername").val();
			var pname = $("#pname").val();
			var pstate = $("#pstate").val();
			//alert("搜索1："+pid+"--"+pusername+"--"+pname);
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
		/*搜索2*/
		    function doSearch(){
		    	$('#t_user').datagrid('load',{
		    		pid: $('#pids').val(),
		    		pname: $('#pnames').val()
		    	});
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
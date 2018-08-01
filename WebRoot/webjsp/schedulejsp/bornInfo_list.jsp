<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>生日信息列表</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui --%>
	<%@include file="../../js/easyuiLink.jsp"%>
	<%@include file="../../js/tags.jsp"%>
	<%-- easuui和lghdialog是有冲突的 --%>
<%--<script type="text/javascript" src="<%=basePath%>plugins_sunny/lhgDialog/lhgdialog.min.js"></script>--%>


	
		


<script type="text/javascript">
		$(function(){
			 $("#t_datagrid").datagrid({
				title:'生日列表',
				idField:'id',//标识列
				//width:1150,
				height:400,
				url:'bornInfoController/birthinfolist.do',
				//是否显示斑马线效果。
				striped:true,
				//如果为true，则在同一行中显示数据。设置为true可以提高加载性能
				nowrap:true,
				rownumbers:true,//行号
				singleSelect:true,//单选
				//真正的自动展开/收缩列的大小，以适应网格的宽度，防止水平滚动。
				//fitColumns:true,//同步属性列 冻结列	不能与 fitColumns同时使用
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
							//{field:'id',title:'ID',align:'center'},
							{field:'name',title:'姓名',align:'center'},
							{field:'QQ',title:'QQ',align:'center'},
					        {field:'phone',title:'手机号',align:'center'},
					        {field:'ylborntime',title:'阳历出生日期'},
					        {field:'nlborntime',title:'农历出生日期'},
					        {field:'age',title:'年龄',align:'center'},
					        {field:'other',title:'其他说明',align:'center'
					        ,formatter: function(value,row,index){
					        		if(value.length>10){
					        			var s = value.substr(0,10)+"...";
					        			return s;
					        		}else{
					        			return value;
					        		}
								}}, 
					        {field:'type',title:'分类',align:'center'},
					        //{field:'level',title:'级别',align:'center'},
					        {field:'createtime',title:'创建时间',align:'center'},
					        {field:'lastuptime',title:'最后一次修改时间',align:'center'},
					        {field:'state',title:'状态',align:'center'},
					        {field:'opt',title:'操作',align:'center'
					        	,formatter: function(value,row,index){
					        		return "<span onclick=\"chakan('"+row.id+"');\" style=\"cursor: pointer;color:blue;\">查看</span>&nbsp;&nbsp;"
					        		+"<span onclick=\"up("+row.id+");\" style=\"cursor: pointer;color:blue;\">修改</span>&nbsp;&nbsp;" 
					        		+"<span onclick=\"del('"+row.id+"','single');\" style=\"cursor: pointer;color:red;\">删除</span>&nbsp;&nbsp;";
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
					text:'添加',
					handler: function(){
						$('#mydialog').dialog({    
						    title: '添加BornInfo',    
						    width: 530,
						    top:100,  
						    //height: 300,    
						    closed: false,    
						    cache: false,    
						    href: '<%=basePath%>webjsp/schedulejsp/bornInfo_detail.jsp?msg=add',    
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
					},'-',{
						iconCls: '',
						text:'查看',
						handler: function(){
							//允许多行选中(但只能选中一条)
						    var ids = [];
						    var rows = $('#t_datagrid').datagrid('getSelections');
						    for(var i=0; i<rows.length; i++){
						    	ids.push(rows[i].id);
						    }
						    var idstr=ids.join(',');
						    if(idstr==null || idstr=="" || ids.length<1){
						    	$.messager.alert('警告','请选择一条数据！','warning');
						    }else{
						    	 if(ids.length>1){
						    	 	$.messager.alert('警告','只能选择一条数据！','warning');
						    	 }else{
						    	 	chakan(ids);
						    	 }
						    }
						}
					},'-',{
						iconCls: '',
						text:'修改',
						handler: function(){
							//允许多行选中(但只能选中一条)
						    var ids = [];
						    var rows = $('#t_datagrid').datagrid('getSelections');
						    for(var i=0; i<rows.length; i++){
						    	ids.push(rows[i].id);
						    }
						    var idstr=ids.join(',');
						    //alert(idstr+"--"+ids.length);
						    if(idstr==null ||  idstr=="" || ids.length<1){
						    	$.messager.alert('提示','请选择一条数据！','warning');
						    }else{
						    	 if(ids.length>1){
						    	 	$.messager.alert('提示','只能选择一条数据！','warning');
						    	 }else{
						    	 	up(idstr);
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
						    var rows = $('#t_datagrid').datagrid('getSelections');
						    for(var i=0; i<rows.length; i++){
						    	ids.push(rows[i].id);
						    }
						    var idstr=ids.join(',');
						    if(idstr==null || idstr=="" || ids.length<1){
						    	$.messager.alert('警告','请选择至少一个数据！','warning');
						    }else{
						    	 del(idstr,'more');
						    }
						}
					},'-',{
						iconCls: '',
						text:'导出',
						handler: function(){alert(1);}
					},'-',{
						iconCls: '',
						text:"姓名：<input id=\"pname\" style=\"width:50px\" />"
							+"分类：<input id=\"ptype\"   style=\"width:80px\" />"
							+"级别：<input id=\"plevel\"   style=\"width:80px\" />",
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
		    var p = $('#t_datagrid').datagrid('getPager'); 
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
		    
		  
			
		});//加载事件结束
		
		
		
		
		//删除
		function del(id,msg){
			$.messager.confirm('确认','您确认想要删除记录吗？',function(r){
				if (r){  
			         $.ajax({
			        	url:'bornInfoController/delBornInfo.do',
			        	type:'POST',
			        	async:false,
			        	dataType:'json',
			        	data:{'id':id,'msg':msg},
			        	success:function(data){
			        		//alert(data+"data.message:"+data.message);
			        		if(data.message=="success"){
			        			$.messager.alert('提示','删除成功！','info');
			        			$('#t_datagrid').datagrid('load',{});//刷新页面数据
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
		
		function chakan(id){
			$('#mydialog').dialog({
				    title: '查看',    
				    width: 530,
				    top:100,
				    //height: 300,    
				    closed: false,    
				    cache: false,    
				    href: '<%=basePath%>bornInfoController/checkBornInfo.do?msg=check&id='+id,   
				    modal: true,
				   buttons:[]
				});
		}
		
		
		//修改用户
		function up(ids){
			$('#mydialog').dialog({
    	 		title: '修改',    
			    width: 530,
			    top:100,    
			    //height: 400,    
			    closed: false,    
			    cache: false,    
			    href: '<%=basePath%>bornInfoController/checkBornInfo.do?msg=upt&id='+ids,   
			    modal: true,
			     buttons:[{
				text:'保存',
					handler:function(){
						save();//调用子页面的save方法
					}
				},{
					text:'关闭',
					handler:function(){$('#mydialog').dialog('close'); }
				}]
			});
		}
		//搜索
		function search(){
			var pname = $("#pname").val();
			var plevel = $("#plevel").val();
			var ptype = $("#ptype").val();
			//alert("搜索1："+pid+"--"+pusername+"--"+pname);
			$('#t_datagrid').datagrid('load',{
		    		name: pname,
		    		type: ptype,
		    		level:plevel
		    });
		}
		//重置
		function searchreset(){
			$("#plevel").val("");
			$("#pname").val("");
			$("#ptype").val("");
			$('#t_datagrid').datagrid('load',{});
		}
	</script>	


<script type="text/javascript">
	$(function(){
		//查看过生的
			$("#gobirthday").click(function(){
				$('#mydialog').dialog({
				    title: '${sessionScope.nowdate}',    
				    width: 615,
				    top:100,
				    //height: 300,  
				    cache: false,    
				    href: "<%=basePath%>webjsp/schedulejsp/bornInfo_birthday.jsp",   
				    modal: true,
				   buttons:[]
				});
			});
	
	});
</script>


</head>
<body>
	
	
	<div>
<!-- 	<a href="<%=basePath%>index.jsp" target="_top">返回首页</a><br/> -->
	<span style="color: red;">${sessionScope.nowdate }</span><br/>
	<a style="cursor: pointer;color: blue;" id="gobirthday" >查看今天过生日的人员</a><br/>
	<%-- 列表信息 --%>
	<table id="t_datagrid"></table>
	
	<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
	 <div id="mydialog" style="background-color:#FFFFDD;"></div>
   </div>
   	
</body>
</html>
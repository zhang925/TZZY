<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>通讯录分组</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui --%>
	<%@include file="../../js/easyuiLink.jsp"%>
	<%-- easuui和lghdialog是有冲突的 --%>
<%--<script type="text/javascript" src="<%=basePath%>js/lhgDialog/lhgdialog.min.js"></script>--%>
<script type="text/javascript">

	function test (src){
	 	  //var rows = $("#t_txlGroup").datagrid("getSelections");获取选中checkbox的值
	 	  //var rows = $("#dg").datagrid("getSelected");//返回第一个被选中的行 		选中行的时候
		  var rows = $("#t_txlGroup").datagrid("getRows");
		  var row = rows[2];
		  var id = row.id;
	}
		$(function(){
			$("#t_txlGroup").datagrid({
				title:"通讯录分组列表",
				idField:"id",//标识列
				width:800,
				height:500,
				url:"txlGroupController/list.do",
				striped:true,
				nowrap:true,
				rownumbers:true,//行号
				singleSelect:true,//单选
				frozenColumns:[[
					{field:"op",
					checkbox:true,
						formatter: function(value,row,index){
								return row.id;
						}
					}
				]],
				columns:[[    
					        {field:"id",title:"ID",align:"center"},
					        {field:"name",title:"分组名称",align:"center"},
					        {field:"other",title:"其他信息",align:"center"},
					        //{field:"createuserid",title:"创建人",align:"center"},
					        //{field:"uptuserid",title:"修改人",align:"center"},
					        //{field:"cteatetime",title:"创建时间",align:"center"},
					        //{field:"state",title:"状态",align:"center"}
					        /* {field:"opt",title:"操作",align:"center",
					        	formatter: function(value,row,index){
					        		return "<span onclick=\"deltxlGroupConfirm(""+row.id+"","single");\" style=\"cursor: pointer;color:red;\">删除</span>&nbsp;"
					        		+"<span onclick=\"upttxlGroup(""+row.id+"");\" style=\"cursor: pointer;color:red;\">修改</span>";
								}
					        } */
					     ]],
				loadMsg:"正在加载,请稍后……",
				pagination:true,
				toolbar: [{
						iconCls: "icon-add",
						text:"添加",
						handler: function(){
							addtxlGroup();
						}
					},"-",{
						iconCls: "",
						text:"查看",
						handler: function(){
							seetxlGroup();  
						}
					},"-",{
						iconCls: "",
						text:"修改",
						handler: function(){
							upttxlGroup();
						}
					},"-",{
						iconCls: "",
						text:"删除",
						handler: function(){
							deltxlGroup();
						}
					},"-",{
						iconCls: "",
						text:"导出",
						handler: function(){location.href="imp.do";}
					},"-",{
						iconCls: "",
						text:"创建人：<input id=\"pcode\" style=\"width:50px\" />"
							+"通讯人：<input id=\"pname\"   style=\"width:80px\" />"
							+"其他：<input id=\"pcitycode\"   style=\"width:50px\" />",
						handler: function(){}
					},"-",{
						iconCls: "icon-search",
						text:"<span onclick=\"search();\" style=\"color:blue;\">&nbsp;搜&nbsp;索&nbsp;</span>",
						handler: function(){}
					},"-",{
						iconCls: "icon-reload",
						text:"<span onclick=\"searchreset();\" style=\"color:blue;\">&nbsp;重&nbsp;置&nbsp;</span>",
						handler: function(){}
					},"-",{
						iconCls: "icon-help",
						text:"帮助",
						handler: function(){alert("帮助按钮")}
					}]
			});
			
			 //设置分页控件  
		    var p = $("#t_txlGroup").datagrid("getPager"); 
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15,20],//可以设置每页记录条数的列表  
		        beforePageText: "第",//页数文本框前显示的汉字  
		        afterPageText: "页    共 {pages} 页",  //
		        displayMsg: "当前显示 {from} - {to} 条记录   共 {total} 条记录",  
		        onBeforeRefresh:function(){ 
		           $(this).pagination("loading");
		            //$(this).pagination("loaded"); 
		        }  
		    }); 
		    
			
		});
		
		//添加
		function addtxlGroup(){
			$("#mydialog").dialog({    
			    title: "添加",    
				top:100,			    
			    width: 400,    
			    height: 260,
			    closed: false,    
			    cache: false,    
			    href: "<%=basePath%>txlGroupController/goAddOrUptPage.do?load=add",    
			    modal: true,
			    buttons:[{
				text:"保存",
					handler:function(){
						save();//调用子页面的save方法
					}
				},{
					text:"关闭",
					handler:function(){$("#mydialog").dialog("close"); }
				}]
			}); 
							
		}
		
		//查看
		function seetxlGroup(){
			//允许多行选中(但只能选中一条)
		    var ids = [];
		    var rows = $("#t_txlGroup").datagrid("getSelections");
		    for(var i=0; i<rows.length; i++){
		    	ids.push(rows[i].id);
		    }
		    var idstr=ids.join(",");
		    if(idstr==null || idstr=="" || ids.length<1){
		    	$.messager.alert("警告","请选择一条数据！","warning");
		    }else{
		    	 if(ids.length>1){
		    	 	$.messager.alert("警告","只能选择一条数据！","warning");
		    	 }else{
		    	 	$("#mydialog").dialog({
					    href: "<%=basePath%>txlGroupController/goAddOrUptPage.do?load=see&id="+idstr,
					    title: "查看",    
					    top:100,			    
			    		width: 400,    
			    		height: 260,    
					    closed: false,    
					    cache: false, 
					    modal: true,
					    buttons:[{
								text:"关闭",
								handler:function(){$("#mydialog").dialog("close"); }
						}]
					});
		    	 }
			}
		}
		
		//修改
		function upttxlGroup(){
			//允许多行选中(但只能选中一条)
		    var ids = [];
		    var rows = $("#t_txlGroup").datagrid("getSelections");
		    for(var i=0; i<rows.length; i++){
		    	ids.push(rows[i].id);
		    }
		    var idstr=ids.join(",");
		    if(idstr==null ||  idstr=="" || ids.length<1){
		    	$.messager.alert("提示","请选择一条数据！","warning");
		    }else{
		    	 if(ids.length>1){
		    	 	$.messager.alert("提示","只能选择一条数据！","warning");
		    	 }else{
		    	 	$("#mydialog").dialog({
		    	 		title: "修改",    
					    top:100,			    
			    		width: 400,    
			    		height: 260,    
					    closed: false,    
					    cache: false,    
					    href: "<%=basePath%>txlGroupController/goAddOrUptPage.do?load=upt&id="+ids,   
					    modal: true,
					     buttons:[{
						text:"保存",
							handler:function(){
								save();//调用子页面(txlGroup_add.jsp)的save方法
							}
						},{
							text:"关闭",
							handler:function(){$("#mydialog").dialog("close"); }
						}]
					});
		    	 }
		    }
		}
		
		//删除
		function deltxlGroup(){
			/*只允许单个选中
			var row = $("#t_txlGroup").datagrid("getSelected");
			if (row){
				alert("Item ID:"+row.id+"txlGroupname:"+row.txlGroupname);
			}
			*/
			//允许多行选中
		    var ids = [];
		    var rows = $("#t_txlGroup").datagrid("getSelections");
		    for(var i=0; i<rows.length; i++){
		    	ids.push(rows[i].id);
		    }
		    var idstr=ids.join(",");
		    if(idstr==null || idstr=="" || ids.length<1){
		    	$.messager.alert("警告","请选择至少一条数据！","warning");
		    }else{
		    	 deltxlGroupConfirm(idstr,"more");
		    } 
		}
		//确认删除[msg==more多行删除][msg==single单行删除]
		function deltxlGroupConfirm(idstr,msg){
			$.messager.confirm("确认","您确认想要删除记录吗？",function(r){
				if (r){  
			         $.ajax({
			        	url:"txlGroupController/del.do",
			        	type:"POST",
			        	async:false,
			        	dataType:"json",
			        	data:{"ids":idstr,"msg":msg},
			        	success:function(data){
			        		if(data.message=="success"){
			        			$.messager.alert("提示","删除成功！","info");
			        			$("#t_txlGroup").datagrid("load",{});//刷新页面数据
			        		}else{
			        			$.messager.alert("警告","删除失败！","warning");
			        		}
			        	},
			        	error:function(data){
			        		if(data.message=="success"){
			        			$.messager.alert("警告","异常,删除成功！","warning");
			        		}else{
			        			$.messager.alert("警告","删除失败！","warning");
			        		}
			        	}
			        });
			    }  
			}); 
		}
		
		//搜索
		function search(){
			var pcode = $("#pcode").val();
			var pname = $("#pname").val();
			var pcitycode = $("#pcitycode").val();
			$("#t_txlGroup").datagrid("load",{
		    		code: pcode,
		    		name: pname,
		    		citycode:pcitycode
		    });
		}
		
		//重置
		function searchreset(){
			$("#pcode").val("");
			$("#pname").val("");
			$("#pcitycode").val("");
			//取消选中的所有行
			$("#t_txlGroup").datagrid("unselectAll");
			//重新加载
			$("#t_txlGroup").datagrid("load",{});
			
		}
		
	</script>	

</head>
<body>

	<div>
	<%-- 列表信息 --%>
	<table id="t_txlGroup"></table>
	<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
	 <div id="mydialog"></div>
   </div>
	
	 	
</body>
</html>
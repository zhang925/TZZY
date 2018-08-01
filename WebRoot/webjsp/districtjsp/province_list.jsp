<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>省份信息表</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui --%>
	<%@include file="../../js/easyuiLink.jsp"%>
	<%-- easuui和lghdialog是有冲突的 --%>
<%--<script type="text/javascript" src="<%=basePath%>plugins_sunny/lhgDialog/lhgdialog.min.js"></script>--%>
<script type="text/javascript">

	function test (src){
	 	  //var rows = $('#t_province').datagrid('getSelections');获取选中checkbox的值
	 	  //var rows = $('#dg').datagrid('getSelected');//返回第一个被选中的行 		选中行的时候
		  var rows = $('#t_province').datagrid('getRows');
		  var row = rows[8];
		  var id = row.uid;
	}
		$(function(){
			 
			$("#t_province").datagrid({
				//title:'省份列表',
				idField:'id',//标识列
				width:295,
				height:393,
				url:'provinceController/provincelist.do',
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
					        {field:'id',title:'ID',align:'center'},
					        {field:'code',title:'省份编码',align:'center'},
					        {field:'name',title:'省份',align:'center'},
					        {field:'opt',title:'操作',align:'center',
					        	formatter: function(value,row,index){
					        		return "<span onclick=\"seecity('"+row.code+"');\" style=\"cursor: pointer;color:red;\">下属市区</span>";
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
					text:'添加省份',
					handler: function(){
						$('#mydialog').dialog({    
						    title: '添加新省份',    
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
						    var rows = $('#t_province').datagrid('getSelections');
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
						    var rows = $('#t_province').datagrid('getSelections');
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
							var row = $('#t_province').datagrid('getSelected');
							if (row){
								alert('Item ID:'+row.id+"username:"+row.username);
							}
							*/
							//允许多行选中
						    var ids = [];
						    var rows = $('#t_province').datagrid('getSelections');
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
					}
					
					
					
					/* 
					,'-',{
						iconCls: '',
						text:'导出',
						handler: function(){location.href="imp.do";}
					},'-',{
						iconCls: '',
						text:"省份UID：<input id=\"pid\" style=\"width:50px\" />"
							+"省份名：<input id=\"pusername\"   style=\"width:80px\" />"
							+"省份昵称：<input id=\"pname\"   style=\"width:80px\" />"
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
					} */
					
					]
				
				
			});
			//按钮
			
			
			  //设置分页控件  
		    var p = $('#t_province').datagrid('getPager');
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
		
		
		
		
		//删除省份
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
			        			$('#t_province').datagrid('load',{});//刷新页面数据
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
		
		
		//修改省份
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
			$('#t_province').datagrid('load',{
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
			$('#t_province').datagrid('load',{});
		}
		/*搜索2*/
		    function doSearch(){
		    	$('#t_province').datagrid('load',{
		    		pid: $('#pids').val(),
		    		pname: $('#pnames').val()
		    	});
    		}
	</script>	


<script type="text/javascript">
	
	function seecity(code){
	
		
		$("#t_city").datagrid({
				idField:'id',//标识列
				width:280,
				height:380,
				url:'cityController/list.do?provincecode='+code,
				singleSelect:true,//单选
				columns:[[    
					        //{field:'id',title:'ID',align:'center'},
					        {field:'code',title:'市编码',align:'center'},
					        {field:'name',title:'市区',align:'center'},
					        {field:'opt',title:'操作',align:'center',
					        	formatter: function(value,row,index){
					        		return "<span onclick=\"seearea('"+row.code+"');\" style=\"cursor: pointer;color:red;\">下属地区</span>";
								}
					        }
					     ]],
				loadMsg:'正在加载,请稍后……',
				pagination:true,
				onLoadSuccess : function(data){
					//默认选第一个区
					var rows = $("#t_city").datagrid("getRows");
					if(rows!=null){
						seearea(rows[0].code);
					}
				}
			});
			
			
			
	}


	function seearea(code){
		$("#t_area").datagrid({
				idField:'id',//标识列
				width:280,
				height:380,
				url:'areaController/list.do?citycode='+code,
				singleSelect:true,//单选
				columns:[[    
					        //{field:'id',title:'ID',align:'center'},
					        {field:'code',title:'地区编码',align:'center'},
					        {field:'name',title:'地区',align:'center'}
					     ]],
				loadMsg:'正在加载,请稍后……',
				pagination:true
			});
	}
	
</script>

<%-- 
1.首先你需要设置datagrid的onLoadSuccess
$('#dg').datagrid({onLoadSuccess : function(data){
    $('#dg').datagrid('selectRow',3);
}});
 
2.onLoadSuccess如果是通过 data-options的方式设置的没问题，如果通过js脚本设置的话，
需要保证在loadData之前绑定这个事件函数，类似下面这样
$('#dg').datagrid({"onLoadSuccess":function(data){
    $(this).datagrid('selectRow',3);
}}).datagrid("loadData",XXXXX);
否则在加载数据之后，可能不会去执行onLoadSuccess里面的代码；
 
3.reload方法重新加载当前页面数据
$('#dg').datagrid("reload"); 

--%>

</head>
<body>

	<div>
	<%--<a href="<%=basePath%>index.jsp">返回首页</a>--%>
	<%-- 列表信息 --%>
<div id="cc" class="easyui-layout" style="width:880px;height:422px;">
<!-- 上 -->
<!--     <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>    -->
<!-- 下 -->     
<!--     <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>    -->
<!-- 中  右  title:'中  右', -->      
    <div data-options="region:'east',title:'地区',split:false,collapsible:false" style="width:285px;">
    	<!-- 地区 的datagrid -->
    	<table id="t_area"></table>
    </div>   
<!-- 中  左 ,title:'中  左'  -->    
    <div data-options="region:'west',title:'省份',split:false,collapsible:false" style="width:300px;">
    	<!-- 省份的datagrid -->
    	<table id="t_province"></table>
    </div>   
<!-- 中   -->     
    <div data-options="region:'center',title:'市区'" style="padding:1px;">
    	<!-- 市区 的datagrid -->
    	<table id="t_city"></table>
    </div>   
</div>  
	
	<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
	 <div id="mydialog"></div>
   </div>
	
	 	
</body>
</html>
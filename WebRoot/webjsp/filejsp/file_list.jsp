<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件列表页面</title>
<%@include file="../../js/easyuiLink.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>

<link rel="stylesheet" href="<%=basePath%>publicity/zyupload/skins/zyupload-1.0.0.min.css" type="text/css">
<script type="text/javascript" src="<%=basePath%>publicity/zyupload/zyupload-1.0.0.min.js"></script>

<script type="text/javascript">
		$(function(){
			$("#t_file").datagrid({
				title:'文件列表',
				idField:'id',//标识列
				height:450,
				url:'fileController/fileslist.do',
				nowrap:true,
				singleSelect:true,
				rownumbers:true,//行号
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
					        {field:'fid',title:'文件FID',align:'center'},
					        {field:'uid',title:'上传人',align:'center',
					        		formatter: function(value,row,index){
					        		return value.name;
								}},
							{field:'filesrc',title:'文件地址',align:'center'},
					        {field:'filename',title:'文件名',align:'center'},    
					        {field:'filesize',title:'文件大小',align:'center'},
					        {field:'filetype',title:'文件类型',align:'center'},
					        {field:'createtime',title:'创建时间',align:'center'},
					        {field:'state',title:'状态',align:'center'},
					        {field:'opt',title:'操作',align:'center',
					        	formatter: function(value,row,index){
					        		return "<span onclick=\"xiazai('"+row.fid+"');\" style=\"cursor: pointer;color:red;\">下载</span>";
					        	}
					        }
					     ]],
				loadMsg:'正在加载,请稍后……',
				pagination:true,
				toolbar: [{
					text:'上传文件',
					iconCls: 'icon-add',
					handler: function(){
						$('#mydialog').dialog({
									    title: '上传文件',    
									    width: 800,    
									    //height: 400,
									    top:60,
									    //left:80,  
									    closed: false,    
									    cache: false,
									    //href:'webjsp/filejsp/file_upload.jsp',
									    href:'<%=basePath%>publicity/upload.jsp',   
									    modal: true,
									    buttons:[{
										text:'完成',
											handler:function(){
												$('#t_file').datagrid('load',{});
												$('#mydialog').dialog('close'); 
											}
										},{
										text:'关闭',
											handler:function(){
											$('#mydialog').dialog('close');
												$.messager.confirm('确认','您确认关闭吗？',function(r){    
												    if (r){    
												    	$('#t_file').datagrid('load',{});
												        $('#mydialog').dialog('close');   
												    }    
												}); 
											}
										}]
									});
								}
				},'-',{
					iconCls: 'icon-help',
					handler: function(){alert('帮助按钮')}
				}]
				
				
			});
			//设置分页控件  
		    var p = $('#t_file').datagrid('getPager'); 
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15,20],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  //
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		        onBeforeRefresh:function(){ 
		           $(this).pagination('loading');
		        }  
		    }); 
		    
			
		});
		
		function xiazai(fid){
		window.location.href="fileController/downfiles.do?fid="+fid;
			
		}
	</script>	

</head>
<body>

	<div>
	<%-- 列表信息 --%>
	<table id="t_file"></table>
	<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
	 <div id="mydialog"  style="background-color:#FFFFDD;"></div>
   </div>
	
</body>

</html>
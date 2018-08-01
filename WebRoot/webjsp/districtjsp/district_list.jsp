<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>地区详情 信息表</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%-- 引入easuui --%>
	<%@include file="../../js/easyuiLink.jsp"%>
	<%-- easuui和lghdialog是有冲突的 --%>
<%--<script type="text/javascript" src="<%=basePath%>plugins_sunny/lhgDialog/lhgdialog.min.js"></script>--%>
<script type="text/javascript">

	function test (src){
	 	  //var rows = $('#t_user').datagrid('getSelections');获取选中checkbox的值
	 	  //var rows = $('#dg').datagrid('getSelected');//返回第一个被选中的行 		选中行的时候
		  var rows = $('#t_user').datagrid('getRows');
		  var row = rows[8];
		  var id = row.id;
	}
		$(function(){
			 
			$("#t_district").datagrid({
				//title:'地区详情列表',
				idField:'id',//标识列
				width:1000,
				height:536,
				url:'districtController/districtlist.do',
				singleSelect:true,//单选
				frozenColumns:[[
					{field:'op',
					checkbox:true,
						formatter: function(value,row,index){
								return row.id;
						}
					}
				]],
				columns:[[    
					        {field:'id',title:'ID',align:'center'},
					      /*    {field:'photoid',title:'头像',align:'center',width:'80px',
					        	formatter: function(value,row,index){
											return "<img  onclick=\"test("+index+")\"  src=\""+row.photoid+"\" alt=\"\"  width=\"40px\" height=\"40px\" >";
									}
								}, */
					        {field:'xzid',title:'行政编码',align:'center'},
					        {field:'name',title:'地区名',align:'center'},    
					        {field:'parentid',title:'所属上一级地区行政编码',align:'center'},
					        {field:'shotname',title:'地区名简称',align:'center'},
					        {field:'leveltype',title:'级别',align:'center' },
					        {field:'zipcode',title:'地区区号',align:'center'},
					        {field:'citycode',title:'地区邮政编码',align:'center'},
					        {field:'mergername',title:'地区全称',align:'center'}, 
					        {field:'lng',title:'经度[东经]',align:'center'},
					        {field:'lat',title:'纬度[北纬]',align:'center'},
					        {field:'pinyin',title:'地区拼音',align:'center'}
					        /* {field:'opt',title:'操作',align:'center',
					        	formatter: function(value,row,index){
					        		return "<span onclick=\"deluser('"+row.uid+"','single');\" style=\"cursor: pointer;color:red;\">删除</span>&nbsp;"
					        		+"<span onclick=\"uptdistrict('"+row.uid+"');\" style=\"cursor: pointer;color:red;\">修改</span>";
								}
					        } */
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
						    title: '添加',    
						    width: 500,    
						    height: 400,    
						    closed: false,    
						    cache: false,    
						    href: '<%=basePath%>webjsp/districtjsp/district_addorupt.jsp',    
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
						//$("#mydialog").dialog('open');
						
					}
					},'-',{
						iconCls: '',
						text:'查看',
						handler: function(){
							//允许多行选中(但只能选中一条)
						    var ids = [];
						    var rows = $('#t_district').datagrid('getSelections');
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
						    	 	$('#mydialog').dialog({
									    title: '查看',    
									    width: 500,    
									    height: 400,    
									    closed: false,    
									    cache: false,    
									    href: '<%=basePath%>districtController/goAddOrUptDistrict.do?type=see&id='+idstr,   
									    modal: true,
									    buttons:[
									    	{
											text:'关闭',
											handler:function(){$('#mydialog').dialog('close'); }
											}]
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
						    var rows = $('#t_district').datagrid('getSelections');
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
						    	 	uptdistrict(idstr);
						    	 }
						    }
						
						}
					},'-',{
						iconCls: '',
						text:'删除',
						handler: function(){
							/*只允许单个选中
							var row = $('#t_district').datagrid('getSelected');
							if (row){
								alert('Item ID:'+row.id+"username:"+row.username);
							}
							*/
							//允许多行选中
						    var ids = [];
						    var rows = $('#t_district').datagrid('getSelections');
						    for(var i=0; i<rows.length; i++){
						    	ids.push(rows[i].id);
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
						text:'导入',
						handler: function(){daoru();}
					},'-',{
						iconCls: '',
						text:"行政编码：<input id=\"dxzid\" style=\"width:50px\" />"
							+"地区名：<input id=\"dname\"   style=\"width:80px\" />"
							+"邮编：<input id=\"dcitycode\"   style=\"width:80px\" />",
						handler: function(){}
					},'-',{
						iconCls: 'icon-search',
						text:"<span onclick=\"search();\" style=\"color:blue;\">&nbsp;搜&nbsp;索&nbsp;</span>",
						handler: function(){}
					},'-',{
						iconCls: 'icon-reload',
						text:"<span onclick=\"searchreset();\" style=\"color:blue;\">&nbsp;重&nbsp;置&nbsp;</span>",
						handler: function(){}
					} ]
				
				
			});
			//按钮
			
			
			  //设置分页控件  
		    var p = $('#t_district').datagrid('getPager'); 
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
		
		
		
		
		//删除用户
		/* function deluser(uid,msg){
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
		} */
		
		
		//修改用户
		 function uptdistrict(ids){
			$('#mydialog').dialog({
    	 		title: '修改',    
			    width: 500,    
			    height: 400,    
			    closed: false,    
			    cache: false,    
			    href: '<%=basePath%>districtController/goAddOrUptDistrict.do?type=upt&id='+ids,   
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
			var dxzid = $("#dxzid").val();
			var dname = $("#dname").val();
			var dcitycode = $("#dcitycode").val();
			$('#t_district').datagrid('load',{
		    		xzid: dxzid,
		    		name: dname,
		    		citycode:dcitycode
		    });
		} 
		//重置
		 function searchreset(){
			$("#dxzid").val("");
			$("#dname").val("");
			$("#dcitycode").val("");
			$('#t_district').datagrid('load',{});
		} 
		/*搜索2*/
		   /*  function doSearch(){
		    	$('#t_district').datagrid('load',{
		    		pid: $('#pids').val(),
		    		pname: $('#pnames').val()
		    	});
    		} */
    		
    		function daoru(){
    			alert("这里导入只能用一次！如果数据清空在页面解开注释即可！");
    			 /* $.ajax({
    				url:"districtController/importDistrict.do",
    				type:"POST",
    				dataType:"json",
    				data:{},
    				async:false,
    				success:function(){
    					alert("导入成功!");
    					$('#t_district').datagrid('load',{});
    				},
    				error:function(){
    					//alert("异常!");
    					$('#t_district').datagrid('load',{});
    				}
    			});  */
    			
    		}
	</script>	

</head>
<body>

	<div>
	<%-- 列表信息 --%>
	<table id="t_district"></table>
	<%-- 添加窗口 子页面和父页面可认为同一个页面方法随意调取 但子页面的script要写在body内--%>
	 <div id="mydialog"></div>
   </div>
	
	 	
</body>
</html>
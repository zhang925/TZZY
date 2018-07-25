<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	


<link href="phone/css/app_iscroll.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="phone/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="phone/js/app_iscroll.js"></script>

<style type="text/css" media="all"></style>

<script type="text/javascript" charset="utf-8">
    var curPage = 0;//当前页
    var pageCount = 0;//总页数
    var flag = false;//是否为重新查询
    var myScroll, pullUpEl;
    
    var _url = 'testController/phoneuserlist2.do';
    $(document).ready(function() {
        pullUpEl = document.getElementById('pullUp');
        myScroll = new iScroll('wrapper_scro', {
            useTransition: true,
            zoom: false,
            onRefresh: function() {
                if (pullUpEl.className.match('loading')) {
                    pullUpEl.className = '';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '上滑显示更多';
                }
            },
            onBeforeScrollStart:function(e){
            	var nodeType = e.explicitOriginalTarget ? e.explicitOriginalTarget.nodeName.toLowerCase():(e.target ? e.target.nodeName.toLowerCase():'');
            	    if(nodeType !='select'&& nodeType !='option'&& nodeType !='input'&& nodeType!='textarea') 
            	               e.preventDefault();
            	}, 
            onScrollMove: function() {
                if (this.y < (this.maxScrollY - 10) && !pullUpEl.className.match('flip')) {
                    pullUpEl.className = 'flip';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '松开刷新';
                    this.maxScrollY = this.maxScrollY;
                } else if (this.y > (this.maxScrollY + 10) && pullUpEl.className.match('flip')) {
                    pullUpEl.className = '';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '上滑显示更多';
                    this.maxScrollY = pullUpOffset;
                }
            },
            onScrollEnd: function() {
            
                if (pullUpEl.className.match('flip')) {
					
                    pullUpEl.className = 'loading';
                    pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中';
                    /*****/
					
                    var html = "";
                    //第一次进来 需要点击 查询按钮
                    if (pageCount != 0 && parseInt(curPage) == parseInt(pageCount) || curPage == 0 ) {
                        $('.pullUpLabel').html('没有更多内容了！');
                        pullUpEl.className = '';
                        return;
                    }
                  
                 
                    $.ajax({
                        type: 'POST',
                        url: _url,
                        async : false,
            			cache : false,
            			dataType:"json",
                        data:{
                        	curPage : curPage
                        },
                        success: function(data) {
                        	//调用添加
	    	  				var newhtml="";
	    	  				newhtml = appendhtml(data);
	    	  				
                        	$("#scro_content").append(newhtml);
        	                curPage++;
            	  		
                        },
                        complete: function() {
                           myScroll.refresh();
                        }
                    });
                }
            }
        });


		
    	$("#search").on("click",function(){
    		//每次查询清空
    		$("#scro_content").html('');
    		//语言
	    		$.ajax({
	    	  		url: _url ,
	    	  		async : false,
	    			cache : false,
	    	  		type:"POST",
	    	  		dataType:"json",
	    	  		data:{  },
	    	  		success:function(data) {
	    	  				pageCount = data.pageCount;
							curPage = 2;//点击更多的时候从第二页开始
	    	  				//调用添加
	    	  				var newhtml="";
	    	  				newhtml = appendhtml(data);
	    	  			if(newhtml.length<=0){
	   	  					newhtml ="没有查询到相关信息";
	   	  					pullUpEl.querySelector('.pullUpLabel').innerHTML = '';
	   	  					curPage = 0;// 初始化
	   	  				}
	   	  				myScroll.refresh();
	                    $("#scro_content").append(newhtml);
	                    
	    	  		}
	    	  	});
    	  	
    	});
    });
  	
  	function appendhtml(data){
  			var arr = data.list;
  			var newhtml="";
  			for(var i=0; i<arr.length;i++){
  				var uid = arr[i].uid;
  				var name = arr[i].name;
  				var qq = arr[i].qq;
  				newhtml +=""+
  					"<tr class=\"mxlan\" style=\"height:30px;background-color: blue;color: white;\">"
    				+"<td style=\"width: 30%\">"+uid+"</td>"
    				+"<td style=\"width: 40%\">"+name+"</td>"
    				+"<td style=\"width: 30%\">"+qq+"</td>"
    				+"</tr>";
  			}
 			return newhtml;
  		}
  	
  
    </script>
	
		
	</head>
	
	
	
	
	
	
	
	
	<body>
	
			<h1>测试刷新</h1>
	<!-- content--start -->
		<section  id="wrapper_scro" style="overflow: hidden;">
		  <article>
            <p class="mt20"><button id="search" type="button" class="btn btn-zhs btn-lg btn-block">查 询</button></p>
            <div class="activity">
				<strong class="mr8 redtit">查询结果</strong>
			</div>
			
			
			
			 <input id="curPage" value="0" style="display:none;">
				<!--滚动start  -->
				<div>
					<div style="transition-property: transform; transform-origin: 0px 0px 0px; transition-timing-function: cubic-bezier(0.33, 0.66, 0.66, 1); transform: translate(0px, 0px) translateZ(0px);" class="scroller">
						
						<table class="table ypgltable" id="scro_content" ></table>
						
						<div id="pullUp">
							<div class="pullUpIcon"></div>
							<div class="pullUpLabel">上滑显示更多</div>
						</div>
					</div>
				</div>
				<!--滚动end  -->
            
          </article>
          
		</section>
	
	
</body>
	
	
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日程管理(添加/查看/修改)</title>
<style type="text/css">
	body{
		background-color: #FFFFDD;
	}
	/*去除表格间隙*/
 	table{
 		  border-collapse:collapse;
 	}
 	/*表格边框*/
 	table,tr,th,td{
 		border: 2px solid #959BAA;
 	}
 	h5{
 		color: red;
 	}
</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<td colspan="4" align="center"><span style="color: red;font-weight: bolder;">已进行</span></td>
			</tr>
			<tr>
				<td>1</td>
				<td><a href="schedule_rmbjh.jsp">第五套人民币风景</a></td>
				<td>(2010年--20??年)</td>
				<td> 【<span style="color: blue;">4</span>/<span style="color: red;">6</span>】</td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="schedule_qingdao.jsp">山东青岛</a></td>
				<td>(计划2016年6月)(预计2016年6月17日)</td>
				<td>【2016年6月17日晚】</td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="schedule_zhejiang.jsp">浙江[杭州、宁波]</a></td>
				<td>(计划2016年7月)(预计2016年8月6日早上)</td>
				<td>【2016年8月6日早】</td>
			</tr>
			<tr>
				<td>4</td>
				<td><a href="schedule_guilin.jsp">桂林[桂林、南宁]</a></td>
				<td>(计划2018年4月)(预计2018年5月4日)</td>
				<td>【2018年5月5日早】</td>
			</tr>
			<tr>
				<td>5</td>
				<td><a href="schedule_blg.jsp">新乡八里沟</a></td>
				<td>(计划2018年)</td>
				<td>【2018年6月30日早】</td>
			</tr>
			<tr>
				<td>6</td>
				<td><a href="qmshy.jsp">开封清明上河园</a></td>
				<td>(计划2018年8月)</td>
				<td>【2018年8月11日早】</td>
			</tr>
			<tr>
				<td>7</td>
				<td><a href="songshan.jsp">郑州嵩山</a></td>
				<td>(计划2018年8月)</td>
				<td>【2018年8月26日早】</td>
			</tr>
			<tr>
				<td>8</td>
				<td><a href="schedule_taishan.jsp">泰山</a></td>
				<td>(计划2018年10月)</td>
				<td>【2018年8月10日02早上】</td>
			</tr>


			<tr>
				<td colspan="4" align="center"><span style="color: red;font-weight: bolder;">计划中</span><br/></td>
			</tr>

			<tr>
				<td>1</td>
				<td><a href="javascript:void(0);">宁夏沙湖</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="javascript:void(0);">四川九寨沟</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="javascript:void(0);">山西壶口瀑布</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>4</td>
				<td><a href="javascript:void(0);">湖南张家界</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>5</td>
				<td><a href="javascript:void(0);">湖北神农架</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>6</td>
				<td><a href="javascript:void(0);">安徽黄山</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>7</td>
				<td><a href="javascript:void(0);">苏州昆山市周庄</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>8</td>
				<td><a href="javascript:void(0);">海南南山海上观音</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>9</td>
				<td><a href="javascript:void(0);">云南省大理</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>10</td>
				<td><a href="javascript:void(0);">西双版纳 </a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>11</td>
				<td><a href="javascript:void(0);">甘肃敦煌</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>12</td>
				<td><a href="javascript:void(0);">南阳诸葛庐(武侯祠)</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>13</td>
				<td><a href="javascript:void(0);">焦作云台山</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="color: purple">以下为2018新增</td>
			</tr>
			<tr>
				<td>1</td>
				<td><a href="javascript:void(0);">崖山</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="javascript:void(0);">青海湖</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="javascript:void(0);">三峡水电站</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>4</td>
				<td><a href="javascript:void(0);">成都</a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>

			<tr>
				<td colspan="4" align="center" style="color: purple">其他</td>
			</tr>
			<tr>
				<td>1</td>
				<td><a href="javascript:void(0);"></a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>2</td>
				<td><a href="javascript:void(0);"></a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>3</td>
				<td><a href="javascript:void(0);"></a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>4</td>
				<td><a href="javascript:void(0);"></a></td>
				<td>(20??年?月)</td>
				<td></td>
			</tr>
			<tr>
				<td>5</td>
				<td><a href="javascript:void(0);"></a></td>
				<td>(20??年?月)</td>
				<td>5</td>
			</tr>
		</table>
		
	</div>
		
	
	
</body>
</html>
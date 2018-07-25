<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.zzy.mp3.ConnDB"%>
<%
ConnDB conn=new ConnDB();
ResultSet rs=conn.executeQuery("select * from tb_mp3list order by hit desc");
%>
<script language="javascript" >
//判断用户是否选择了要删除的记录，如果是，则提示“是否删除”；否则提示“请选择要删除的记录”
function check(id,formname){

	  	var rusult=0;
         var id=document.getElementsByName("playid");
         /*
           for(var i=0;i<check_array.length;i++){
               if(check_array[i].checked==true){         
                  rusult=parseInt(rusult)+parseInt(check_array[i].value);
               }
           }
           */
	
	
	
	//获取ID
	var flag = false;
	for(i=0;i<id.length;i++){
		if(id[i].checked){
			flag = true;
			break;
		}
	}
	if(!flag){
		alert("请选择要播放的歌曲！");
		return false;
	}
}
</script>
<html>
<head>
<title>在线播放MP3歌曲列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="../../css/style.css" rel="stylesheet">
</head>

<body>
<table width="602" height="192"  border="0" align="center" cellpadding="0" cellspacing="0" class="tableBorder">
<tr>
  <td>
<table width="600" height="288"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="600" align="center" valign="top"><table width="100%" height="82"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td height="82" valign="top" background="../../images/top_bg.gif">&nbsp;</td>
        </tr>
        </table>
      <table width="96%" height="192"  border="0" cellpadding="0" cellspacing="0">
	          <tr>
	<form action="index_deal.jsp" method="post" name="form1" onSubmit="return check(playid,form1)">
          <td valign="top">
  <table width="100%" height="14"  border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td height="13" align="center">&nbsp;</td>
              </tr>
    </table>
              <table width="100%" height="48"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#CCCCCC" bordercolorlight="#FFFFFF">
			                  <tr>
                  <td width="54%" height="27" align="center">歌曲名称</td>
                  <td width="23%" align="center">演唱者</td>
                  <td width="12%" align="center">推荐指数</td>
                  <td width="11%" align="center">点播</td>
                </tr>
  <%
	  int ID=0;
	  String name="";
	  String reader="";
	  int hit=0;
	  String url="";
	try{
	  while(rs.next()){
            ID=rs.getInt("id");
            name=rs.getString("name");
            reader=rs.getString("reader");
            hit=rs.getInt("hit");
            url=rs.getString("url");
	  %>

                <tr style="padding:5px;">
                  <td height="24" align="center"><%=name%></td>
                  <td align="center"><%=reader%></td>
                  <td align="center"><%=hit%>和<%=ID%></td>
                  
                  	<td align="center">
                    	<input name="playid" type="checkbox" class="noborder" value="<%=ID%>">
                    </td>
                </tr>
  <%
	}
		}catch(Exception e){
		System.out.println(e.getMessage());
		}
%>
                </table>
              <table width="100%" height="33" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td align="center">
                  	<input name="Submit" type="submit" class="btn_grey" value="开始播放">
                  </td>
                </tr>
              </table></td>
			</form>
          </tr>
      </table>
      <table width="100%" height="46"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td background="../../images/manage_06.gif">&nbsp;</td>
          </tr>
      </table></td>
    </tr>
</table>
</td>
</tr>
</table>
</body>
</html>

﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>文本编辑器PiscesTextEditor</title>
<link rel="stylesheet" type="text/css" href="PiscesTextEditor/style.css">
</head>
<body>
<div id="EditDiv" style="width:666px;height:300px;">
      <table id="EditTable" width="100%" border="0" class="EditTable" align="center">
        <tr>
          <td>
          <table id="TextEdit_Toolbar" style="display: inline;" cellSpacing="0" cellPadding="0" border="0">
            <tr>
              <td>
              <div class="TextEdit_Toolbar" id="TextEdit_Toolbar_Toolbar0">
                <table cellspacing="0" cellpadding="0" border="0">
                  <tr>
                    <td><img src="PiscesTextEditor/images/toolbar.start.gif" width="8" height="24" border="0" /></td>
                    <td>
                   <table cellpadding="0" cellspacing="0">
                      <tr>
                        <td id="Save" class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="Save();" noWrap>
                        <img src="PiscesTextEditor/images/save.gif" alt="保存" width="20" height="20" align="middle" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('Print',null);" noWrap>
                        <img src="PiscesTextEditor/images/print.gif" alt="打印" width="20" height="20" align="middle" />						</td>
						<td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="Replace();" noWrap>
                        <img src="PiscesTextEditor/images/replace.gif" alt="查找替换" width="20" height="20" align="middle" /></td>
						<td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="FullScreen();" noWrap>
                        <img src="PiscesTextEditor/images/fullscreen.gif" alt="全屏模式" name="ScreenMode" id="ScreenMode" width="20" height="20" align="middle" /></td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('undo',null);" noWrap>
						<img src="PiscesTextEditor/images/undo.gif" alt="撤销" width="20" height="20" align="middle" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('redo',null);" noWrap>
                        <img src="PiscesTextEditor/images/redo.gif" alt="恢复" width="20" height="20" align="middle" /></td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('cut',null);" noWrap>
                        <img src="PiscesTextEditor/images/cut.gif" alt="剪切" width="20" height="20" align="middle" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('copy',null);" noWrap>
                        <img src="PiscesTextEditor/images/copy.gif" alt="复制" width="20" height="20" align="middle" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('paste',null);" noWrap>
                        <img src="PiscesTextEditor/images/paste.gif" alt="粘贴" width="20" height="20" align="middle" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('delete',null);" noWrap>
                        <img src="PiscesTextEditor/images/delete.gif" alt="删除" width="20" height="20" align="middle" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="IeSpellCheck();" noWrap>
                        <img alt="检查拼写" border="0" src="PiscesTextEditor/images/spellcheck.gif" style="display:none;" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('selectall',null);" noWrap>
                        <img src="PiscesTextEditor/images/SelectAll.gif" alt="全选" width="20" height="20" border="0"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('removeFormat',null);" noWrap>
                        <img src="PiscesTextEditor/images/removeformat.gif" alt="清除字体类型设置" border="0" width="20" height="20" align="middle" /></td>
                        <td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertSymbol();" noWrap>
                        <img src="PiscesTextEditor/images/specialchar.gif" alt="插入符号" width="20" height="20" align="middle" border="0" />						</td>
						<td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertEmot();" noWrap>
                        <img src="PiscesTextEditor/images/emot.gif" alt="插入表情" width="20" height="20" align="middle" border="0" />						</td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="CreateLink()" noWrap>
                        <img src="PiscesTextEditor/images/createlink.gif" alt="插入超链接" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="UnLink();" noWrap>
                        <img src="PiscesTextEditor/images/unlink.gif" alt="删除超链接" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseDown="ButtonDown(this);" onMouseOver="ButtonOver(this);" onClick="InsertPicture()" onMouseOut="ButtonOut(this);" noWrap>
                        <img src="PiscesTextEditor/images/insertimage.gif" alt="插入图片" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('inserthorizontalrule',null);" noWrap>
                        <img src="PiscesTextEditor/images/insertrule.gif" alt="插入水平线" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertToday();" noWrap>
                        <img src="PiscesTextEditor/images/today.gif" alt="插入今天日期" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertTime();" noWrap>
                        <img src="PiscesTextEditor/images/time.gif" alt="插入现在时间" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertTable();" noWrap>
						<img src="PiscesTextEditor/images/table.gif" alt="插入表格" width="20" height="20" border="0"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertLayer();" noWrap>
                        <img src="PiscesTextEditor/images/layer.gif" alt="插入层" width="20" height="20" align="middle" border="0" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertFieldset();" noWrap>
                        <img src="PiscesTextEditor/images/fieldset.gif" alt="插入栏目框" width="20" height="20"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertIframe();" noWrap>
                        <img src="PiscesTextEditor/images/htm.gif" alt="插入网页" width="20" height="20"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertFlash();" noWrap>
                        <img src="PiscesTextEditor/images/flash.gif" alt="插入flash多媒体文件" width="20" height="20"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertMediaPlayer();" noWrap>
                        <img src="PiscesTextEditor/images/media.gif" alt="插入视频文件，支持格式为：avi、wmv、asf" width="20" height="20"></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertRealPlayer();" noWrap>
                        <img src="PiscesTextEditor/images/real.gif" alt="插入RealPlay文件，支持格式为：rm、ra、ram" width="20" height="20"></td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="InsertCode();" noWrap>
                        <img src="PiscesTextEditor/images/code.gif" alt="插入代码" width="20" height="20" border="0"border="0" /></td>
                     </tr>
                    </table>
                    </td>
                    <td><img src="PiscesTextEditor/images/toolbar.end.gif"></td>
                  </tr>
                </table>
              </div>
			<div class="TextEdit_Toolbar" id="TextEdit_Toolbar_Toolbar1">
                <table cellSpacing="0" cellPadding="0" border="0">
                  <tr>
                    <td>
                    <img src="PiscesTextEditor/images/toolbar.start.gif" border="0" width="8" height="24"></td>
                    <td>
                    <table cellSpacing="0" cellPadding="0" border="0">
                      <tr>
                        <td class="None" style="PADDING-LEFT: 4px">
						<table cellspacing="0" cellpadding="0" border="0">
						  <tr>
							<td class="TextEdit_Select" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" noWrap><span id="currentParagraph" style="width:41px;vertical-align:top;background-color:#FFFFFF;height:16px;padding-top:2px;padding-left:2px;font-size:9pt;font-family:'宋体';overflow: hidden;">段落</span><span onClick="paragraph.style.display='inline';" style="vertical-align:top;width:10px;height:16px;"><img alt="字体" src="PiscesTextEditor/images/arrow.gif" align="baseline" width="9" height="13"></span><div style="position:relative; z-index: 10;"><table cellpadding="0" cellspacing="0" border="0"><tr><td id="paragraph" style="display:none;position: absolute;left:-1px;top:-19px;" onMouseOver="this.style.display='block'" onMouseOut="this.style.display='none'"><table width="51" border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td style="border-width:0px; height: 20px;"></td>
  </tr>
</table><div style="overflow:auto; height:200px; border: 1px solid #BBBBBB">
<table width="120" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF" style="font-size: 9pt;">
  <tr>
    <td style="border: 1px solid #FFFFFF;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','Normal',this.innerText);">普通</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font:24pt 宋体;font-weight: bold;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;h1&gt;',this.innerText);">标题 1</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font:18pt 宋体;font-weight: bold;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;h2&gt;',this.innerText);">标题 2</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font:15pt 宋体;font-weight: bold;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;h3&gt;',this.innerText);">标题 3</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font:12pt 宋体;font-weight: bold;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;h4&gt;',this.innerText);">标题 4</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font:9pt 宋体;font-weight: bold;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;h5&gt;',this.innerText);">标题 5</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font:7pt 宋体;font-weight: bold;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;h6&gt;',this.innerText);">标题 6</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;pre&gt;',this.innerText);">带格式的</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;menu&gt;',this.innerText);">菜单列表</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetParagraph('paragraph','&lt;address&gt;',this.innerText);"><address>地址</address></td>
  </tr>
</table></div>
</td></tr></table></div></td>
						  </tr>
						</table></td>
                        <td class="None" style="PADDING-LEFT: 4px;">
						<table cellspacing="0" cellpadding="0" border="0">
						  <tr>
							<td class="TextEdit_Select" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" noWrap><span id="currentfontname" style="width:70px;vertical-align:top;background-color:#FFFFFF;height:16px;padding-top:2px;padding-left:2px;font-size:9pt;font-family:'宋体';overflow: hidden;">宋体</span><span onClick="fontname.style.display='inline';" style="vertical-align:top;width:10px;height:16px;"><img alt="字体" src="PiscesTextEditor/images/arrow.gif" align="baseline" width="9" height="13" /></span><div style="position:relative; z-index: 10;"><table cellpadding="0" cellspacing="0" border="0"><tr><td id="fontname" style="display:none;position: absolute;left:-1px;top:-19px;overflow:inherit;height:100px;" onMouseOver="this.style.display='block'" onMouseOut="this.style.display='none'"><table width="80" border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td style="border-width:0px; height: 20px;"></td>
  </tr>
</table><div style="overflow:auto; height:200px; border: 1px solid #BBBBBB">
<table width="176" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF" style="font-size: 12pt;">
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:宋体;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> 宋体</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:黑体;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> 黑体</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:楷体_GB2312;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily );"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> 楷体_GB2312</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:仿宋_GB2312;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> 仿宋_GB2312</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:隶书;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> 隶书</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:幼圆;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> 幼圆</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:Arial;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Arial</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:Verdana;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Verdana</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:Comic Sans MS;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Comic Sans MS</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:Georgia;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Georgia</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF;font-family:Tahoma;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Tahoma</td>
  </tr>
    <td style="border: 1px solid #FFFFFF;font-family:Times New Roman;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Times New Roman</td>
  </tr>
  </tr>
    <td style="border: 1px solid #FFFFFF;font-family:Lucida Console;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Lucida Console</td>
  </tr>
  </tr>
    <td style="border: 1px solid #FFFFFF;font-family:Courier;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Courier</td>
  </tr>
  </tr>
    <td style="border: 1px solid #FFFFFF;font-family:Impact;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontName('fontname',this.style.fontFamily);"><img src="PiscesTextEditor/images/fontname.gif" width="16" height="16" border="0" align="absmiddle" /> Impact</td>
  </tr>
</table></div>
</td></tr></table></div></td>
						  </tr>
						</table></td>
                        <td class="None" style="PADDING-LEFT: 4px;padding-right:4px;">
						<table cellspacing="0" cellpadding="0" border="0">
						  <tr>
							<td class="TextEdit_Select" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" noWrap><span id="currentfontsize" style="width:27x;vertical-align:top;background-color:#FFFFFF;height:16px;padding-top:2px;padding-left:2px;font-size:9pt;font-family:'宋体';overflow: hidden;">字号</span><span onClick="fontsize.style.display='inline';" style="vertical-align:top;width:10px;height:16px;"><img alt="字号" src="PiscesTextEditor/images/arrow.gif" align="baseline" width="9" height="13" /></span><div style="position:relative; z-index: 10;"><table cellpadding="0" cellspacing="0" border="0"><tr><td id="fontsize" style="display:none;position: absolute;left:-1px;top:-19px;" onMouseOver="this.style.display='block'" onMouseOut="this.style.display='none'"><table width="37" border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td style="border-width:0px; height: 20px;"></td>
  </tr>
</table><div style="overflow:auto; height:200px; border: 1px solid #BBBBBB">
<table width="80" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',1);"><span style="font-size:8pt;font-family:Tahoma;">1</span> (8pt)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',2);"><span style="font-size:10pt;font-family:Tahoma;">2</span> (10pt)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',3);"><span style="font-size:12pt;font-family:Tahoma;">3</span> (12pt)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',4);"><span style="font-size:14pt;font-family:Tahoma;">4</span> (14pt)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',5);"><span style="font-size:18pt;font-family:Tahoma;">5</span> (18pt)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',6);"><span style="font-size:24pt;font-family:Tahoma;">6</span> (24pt)</td>
  </tr>
  <tr>
    <td style="border: 1px solid #FFFFFF; font-size:9pt;" onMouseOver="this.style.backgroundColor='#c2cfe5';" onMouseOut="this.style.backgroundColor='#FFFFFF';" onMouseDown="this.style.backgroundColor='#e2e5ee';" onClick="SetFontSize('fontsize',7);"><span style="font-size:36pt;font-family:Tahoma;">7</span> (36pt)</td>
  </tr>
</table></div>
</td></tr></table></div></td>
						  </tr>
						</table></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" noWrap><span id="ForeColor" onClick="ForeColor(this.style.backgroundColor)" style="background-color:#FF0000"><img alt="文字颜色" src="PiscesTextEditor/images/fontcolor.gif" align="middle" width="20" height="20" border="0" /></span><span onClick="SetForeColor(document.getElementById('ForeColor').style.backgroundColor);"><img src="PiscesTextEditor/images/arrow.gif" align="middle" border="0" width="9" height="13" /></span></td>
						<td width="0"><div style="position:relative; z-index: 10;"><table cellpadding="0" cellspacing="0" border="0"><tr><td id="forecolorpicket" style="display:none;position: absolute; width:152px; height: 162px;left:-31px;top:-11px;" onMouseOver="this.style.display='block'" onMouseOut="this.style.display='none'"></td></tr></table></div></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" noWrap><span id="BackColor" onClick="BackColor(this.style.backgroundColor)" style="background-color:#FFFF00">
                        <img alt="高亮颜色" src="PiscesTextEditor/images/colorpen.gif" align="middle" width="20" height="20" border="0"></span><span onClick="SetBackColor(document.getElementById('BackColor').style.backgroundColor)"><img src="PiscesTextEditor/images/arrow.gif" align="middle" border="0" width="9" height="13"></span></td>
						<td width="0"><div style="position:relative; z-index: 10;"><table cellpadding="0" cellspacing="0" border="0"><tr><td id="backcolorpicket" style="display:none;position: absolute; width:98px; height: 92px;left:-31px;top:-11px;" onMouseOver="this.style.display='block'" onMouseOut="this.style.display='none'"></td></tr></table></div></td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ButtonSelect(this);ExecCmd('bold',null);" noWrap>
                        <img alt="粗体" src="PiscesTextEditor/images/bold.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ButtonSelect(this);ExecCmd('italic',null);" noWrap>
                        <img alt="斜体" src="PiscesTextEditor/images/italic.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ButtonSelect(this);ExecCmd('underline',null);" noWrap>
                        <img alt="下划线" src="PiscesTextEditor/images/underline.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ButtonSelect(this);ExecCmd('strikethrough',null);" noWrap>
                        <img alt="删除线" src="PiscesTextEditor/images/strikethrough.gif" align="middle" border="0" width="20" height="20" /></td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="SetAlign(this,'justifycenter',null);ButtonSelect(this);" noWrap>
                        <img alt="居中对齐" src="PiscesTextEditor/images/justifycenter.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="SetAlign(this,'justifyright',null);ButtonSelect(this);" noWrap>
                        <img alt="居右对齐" src="PiscesTextEditor/images/justifyright.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="SetAlign(this,'justifyfull',null);ButtonSelect(this);" noWrap>
                        <img alt="两端对齐" src="PiscesTextEditor/images/justifyfull.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('insertorderedlist',null);" noWrap>
                        <img alt="数字编号" src="PiscesTextEditor/images/numberedlist.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('insertunorderedlist',null);" noWrap>
                        <img alt="项目符号" src="PiscesTextEditor/images/bullets.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('outdent',null);" noWrap>
                        <img alt="减少缩进量" src="PiscesTextEditor/images/outdent.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('indent',null);" noWrap>
                        <img alt="增加缩进量" src="PiscesTextEditor/images/indent.gif" align="middle" border="0" width="20" height="20" /></td>
						<td><img src="PiscesTextEditor/images/separator.gif" border="0" width="4" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('superscript',null);" noWrap>
                        <img alt="上角文字" src="PiscesTextEditor/images/superscript.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="ExecCmd('subscript',null);" noWrap>
                        <img alt="下角文字" src="PiscesTextEditor/images/subscript.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="UpperCase();" noWrap>
                        <img alt="转换为大写字母" src="PiscesTextEditor/images/UCase.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="LowerCase();" noWrap>
                        <img alt="转换为小写字母" src="PiscesTextEditor/images/Lcase.gif" align="middle" border="0" width="20" height="20" /></td>
                        <td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="SetAbsolutePosition();" noWrap>
                        <img alt="设置绝对位置" src="PiscesTextEditor/images/abspos.gif" align="middle" border="0" width="20" height="20" /></td>
						<td class="TextEdit_ButtonNormal" onMouseOver="ButtonOver(this);" onMouseOut="ButtonOut(this);" onMouseDown="ButtonDown(this);" onClick="Help();" noWrap>
                        <img alt="帮助" src="PiscesTextEditor/images/help.gif" border="0" border="0" width="20" height="20"></td>
                      </tr>
                    </table>
                    </td>
                    <td><img src="PiscesTextEditor/images/toolbar.end.gif" border="0" width="12" height="24"></td>
                  </tr>
                </table>
              </div>
              </td>
            </tr>
          </table>
          </td>
        </tr>
        <tr>
          <td style="height: 100%;">
          <iframe class="TextEdit_iframe" name="TextEdit_Editor" id="TextEdit_Editor" onblur="CopyToHtmlBox();"></iframe>
		  <textarea name="TextEdit" id="TextEdit" class="TextEdit"></textarea>
		  <iframe class="TextEdit_iframe" style="display:none;" name="TextEdit_Preview" id="TextEdit_Preview"></iframe>
		  </td>
        </tr>
        <tr>
          <td>
          <table border="0" cellPadding="0" class="MenuTable">
            <tr id="mainrow">
              <td width="259" class="TextEdit_TabOn" id="TextEdit_DesignModeTab" onClick="TextEdit_ChangeMode('design');" onMouseOver="TabOver();" onMouseOut="TabOut();">
              <img src="PiscesTextEditor/images/mode.design.gif" align="absmiddle" unselectable="on"> Design</td>
			  <td class="TextEdit_BlankTab"></td>
              <td width="54" class="TextEdit_TabOff" id="TextEdit_HtmlModeTab" onClick="TextEdit_ChangeMode('html');" onMouseOver="TabOver();" onMouseOut="TabOut();" unselectable="on">
              <img src="PiscesTextEditor/images/mode.html.gif" align="absmiddle" unselectable="on"> HTML</td>
              <td class="TextEdit_BlankTab"></td>
			  <td class="TextEdit_TabOff" id="TextEdit_PreviewModeTab" onMouseOver="TabOver();" onClick="TextEdit_ChangeMode('preview');" onMouseOut="TabOut();" align="right" width="54">
              <img border="0" src="PiscesTextEditor/images/mode.view.gif" align="absmiddle" unselectable="on"> Preview</td>
              <td width="200" align="center" class="TextEdit_EndTab">总字数：<span id="totalchars">0</span></td>
              <td class="TextEdit_EndTab" align="right"><img src="PiscesTextEditor/images/plus.gif" alt="扩展编辑器" style="cursor:hand;"  onClick="EditDiv.style.height=parseInt(EditDiv.style.height)+100;"><img src="PiscesTextEditor/images/minus.gif" alt="收缩编辑器" style="cursor:hand;" onClick="if(parseInt(EditDiv.style.height)>200) EditDiv.style.height=parseInt(EditDiv.style.height)-100;"></td>
            </tr>
          </table>
        </td>
        </tr>
     </table>
	<script language="JavaScript" type="text/javascript" src="PiscesTextEditor/PiscesTextEditor.js"></script>
</div>
</body>

</html>
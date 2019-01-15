<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>图书馆管理系统</title>
	<link href="CSS/style.css" rel="stylesheet">
	<script src="JS/function.js"></script>
	<script type="text/javascript">
	function check(myform){
	if(myform.flag[0].checked==false && myform.flag[1].checked==false){
		alert("请选择查询方式!");return false;
	}
	if (myform.flag[1].checked){
		if(myform.sdate.value==""){
			alert("请输入开始日期");myform.sdate.focus();return false;
		}		
		if(CheckDate(myform.sdate.value)){
			alert("您输入的开始日期不正确（如：2006-07-05）\n 请注意闰年!");myform.sDate.focus();return false;
		}
		if(myform.edate.value==""){
			alert("请输入结束日期");myform.edate.focus();return false;
		}		
		if(CheckDate(myform.edate.value)){
			alert("您输入的结束日期不正确（如：2006-07-05）\n 请注意闰年!");myform.edate.focus();return false;
		}
	}}
	function quit(){
				if(confirm("真的要退出系统吗?")){
					window.location.href="login.html";
				}
	}
	function myclose(){
			if(confirm("真的要关闭当前窗口吗?")){
				window.close();
			}
		}
	</script>
</head>
	<body onLoad="clockon(bgclock)">
		<table width="778" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr>
		    <td height="118" valign="top" background="Images/top_bg.gif" bgcolor="#EEEEEE"><table width="100%" height="33"  border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td width="81%" height="10"></td>
		        <td colspan="2"></td>
		      </tr>
		      <tr>
		        <td height="20">&nbsp;</td>
		        <td width="10%"><a href="#" onClick="window.location.reload();" class="word_dark">刷新页面</a></td>
		        <td width="9%"><a href="#" onClick="myclose()" class="word_dark">关闭系统</a></td>
		        </tr>
		    </table>
		      <table width="93%" height="79"  border="0" cellpadding="0" cellspacing="0">
		        <tr>
		          <td height="69" align="right" valign="bottom">当前登录用户：${manager.name }</td>
		        </tr>
		    </table></td>
		  </tr>
		</table>
		
		<script src="JS/onclock.JS" charset="gbk"></script>
		<script src="JS/menu.JS" charset="gbk"></script>
		<div class=menuskin id=popmenu
		      onmouseover="clearhidemenu();highlightmenu(event,'on')"
		      onmouseout="highlightmenu(event,'off');dynamichide(event)" style="Z-index:100;position:absolute;"></div>
		<table width="778"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
		      <tr bgcolor="#DFA40C">
		        <td width="3%" height="27">&nbsp;</td>
		        <td width="29%"><div id="bgclock" class="word_white"></div></td>
		        <td width="66%" align="right" bgcolor="#B0690B" class="word_white"><a href="BookServlet" class="word_white">首页</a> |
		        <a  onmouseover=showmenu(event,sysmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand" >系统设置</a> | <a  onmouseover=showmenu(event,readermenu) onmouseout=delayhidemenu() style="CURSOR:hand"  class="word_white">读者管理</a> | <a  onmouseover=showmenu(event,bookmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand" >图书管理</a> | <a  onmouseover=showmenu(event,borrowmenu) onmouseout=delayhidemenu() class="word_white" style="CURSOR:hand">图书借还</a> | <a  onmouseover=showmenu(event,querymenu) onmouseout=delayhidemenu()  class="word_white" style="CURSOR:hand" >系统查询</a> | <a  href="pwd_Modify.jsp" class="word_white">更改口令</a> | <a href="AdminServlet2?operation=logout" onClick="quit()" class="word_white">退出系统</a></td>
		        <td width="2%" bgcolor="#B0690B">&nbsp;</td>
		  </tr>
		      <tr bgcolor="#DFA40C">
		        <td height="9" colspan="4" background="Images/navigation_bg_bottom.gif"></td>
		      </tr>
		</table>
		
		<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
		  <tr>
		    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
		  <tr>
		    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
		      <tr>
		        <td height="22" valign="top" class="word_orange">当前位置：系统查询 &gt; 图书借阅查询 &gt;&gt;&gt;</td>
		      </tr>
		      <tr>
		        <td align="center" valign="top">
				<form name="myform" method="post" action="borrow.do?action=borrowQuery">
		          <table width="98%" height="67"  border="0" cellpadding="0" cellspacing="0" bgcolor="#E3F4F7" class="tableBorder_gray">
		            <tr>
		              <td rowspan="2" align="center" bgcolor="#F9D16B">&nbsp;<img src="Images/search.gif" width="45" height="28"></td>
		              <td height="29" bgcolor="#F9D16B"><input name="flag" type="checkbox" class="noborder" value="a" checked>
		                请选择查询依据：
		                <select name="f" class="wenbenkuang" id="f">
		                  <option value="barcode">图书条形码</option>
		                  <option value="bookname">图书名称</option>
		                  <option value="readerbarcode">读者条形码</option>
		                  <option value="readername">读者名称</option>
		                  </select>
		                  <input name="key" type="text" id="key" size="50">
		                  <input name="Submit" type="submit" class="btn_grey" value="查询" onClick="return check(myform)"></td>
		            </tr>
		            <tr>
		              <td height="26" bgcolor="#F9D16B">
		                <input name="flag" type="checkbox" class="noborder" value="b">
		                借阅时间：                从
		                <input name="sdate" type="text" id="sdate">
		                到
		                <input name="edate" type="text" id="edate">
		                (日期格式为：2006-07-05)</td>
		            </tr>
		          </table>		
				</form>
				
		          <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
		  <tr align="center" bgcolor="#e3F4F7">
		    <td width="11%" bgcolor="#F9D16B">图书条形码</td>
		    <td width="29%" bgcolor="#F9D16B">图书名称</td>
		    <td width="15%" bgcolor="#F9D16B">读者条形码</td>
		    <td width="13%" bgcolor="#F9D16B">读者名称</td>
		    <td width="12%" bgcolor="#F9D16B">借阅时间</td>
		    <td width="12%" bgcolor="#F9D16B">应还时间</td>
		    <td width="8%" bgcolor="#F9D16B">是否归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;9787302047230</td>
		    <td style="padding:5px;">Java学习指南</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2007-11-22</td>
		    <td style="padding:5px;">&nbsp;2007-12-22</td>
		    <td align="center" style="padding:5px;">&nbsp;已归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;9787115157690</td>
		    <td style="padding:5px;">JSP啊</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2007-11-26</td>
		    <td style="padding:5px;">&nbsp;2007-12-26</td>
		    <td align="center" style="padding:5px;">&nbsp;未归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;9787302047230</td>
		    <td style="padding:5px;">Java学习指南</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2007-11-26</td>
		    <td style="padding:5px;">&nbsp;2007-12-26</td>
		    <td align="center" style="padding:5px;">&nbsp;未归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;001</td>
		    <td style="padding:5px;">建筑测试</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2013-05-03</td>
		    <td style="padding:5px;">&nbsp;2013-05-23</td>
		    <td align="center" style="padding:5px;">&nbsp;已归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;001</td>
		    <td style="padding:5px;">建筑测试</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2013-05-03</td>
		    <td style="padding:5px;">&nbsp;2013-05-23</td>
		    <td align="center" style="padding:5px;">&nbsp;已归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;001</td>
		    <td style="padding:5px;">建筑测试</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2013-05-03</td>
		    <td style="padding:5px;">&nbsp;2013-05-23</td>
		    <td align="center" style="padding:5px;">&nbsp;已归还</td>
		  </tr>
		
		  <tr>
		    <td style="padding:5px;">&nbsp;9787302047230</td>
		    <td style="padding:5px;">Java学习指南</td>
		    <td style="padding:5px;">&nbsp;2008010100001</td>
		    <td style="padding:5px;">&nbsp;wgh</td>
		    <td style="padding:5px;">&nbsp;2013-05-03</td>
		    <td style="padding:5px;">&nbsp;2013-06-02</td>
		    <td align="center" style="padding:5px;">&nbsp;未归还</td>
		  </tr>
		
		</table>
		          </td>
		      </tr>
		    </table>
		</td>
		  </tr>
		</table>
		<table width="778" height="66"  border="0" align="center" cellpadding="-2" cellspacing="-2" bgcolor="#FFFFFF">
		      <tr>
		        <td height="11" colspan="4" background="Images/copyright_t.gif"></td>
		  </tr>
		      <tr>
		        <td width="124" height="23">&nbsp;</td>
		        <td valign="bottom" align="center"> CopyRight &copy; 2008 www.**********.com 长春市*****有限公司</td>
		        <td width="141">&nbsp;</td>
		      </tr>
		      <tr>
		        <td height="23">&nbsp;</td>
		        <td align="center">本站请使用IE6.0或以上版本 1024*768为最佳显示效果</td>
		        <td>&nbsp;</td>
		      </tr>
		      <tr>
		        <td height="8"></td>
		        <td height="8"></td>
		        <td height="8"></td>
		      </tr>
		</table>
		</td>
		  </tr>
		</table>
	</body>
</html>

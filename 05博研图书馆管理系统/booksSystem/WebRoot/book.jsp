<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<script src="JS/onclock.JS" charset="gbk"></script>
        <script src="JS/menu.JS" charset="gbk"></script>
        <script type="text/javascript" src="JS/jquery-1.9.1.js"></script>
		<script type="text/javascript">
			function quit(){
				if(confirm("真的要退出系统吗?")){
					window.location.href="login.jsp";
				}
			}
			
			function myclose(){
					if(confirm("真的要关闭当前窗口吗?")){
						window.close();
					}
				}
				
		/*	$(function(){
			
			$.post("AdminServlet1",{operation:"pagesList"},function(result){
			
				//alert(result);
				//把json格式的字符串转换成数组
				var varjson=JSON.parse(result);
				//遍历
				for(var i=0;i<varjson.length;i++){
				
				//获取文本
					var pagesid=$("#pagesid");
					pagesid.text();
				
				}
				
			
			
			})
								
			
			})*/
			
			
				
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
	    <td height="510" valign="top" style="padding:5px;"><table width="98%"  border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="22" valign="top" class="word_orange">当前位置：图书管理 &gt; 图书档案管理 &gt;&gt;&gt;</td>
	      </tr>
	      <tr>
	        <td align="center" valign="top">
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td width="87%">&nbsp;      </td>
	<td width="13%">
	      <a href="login.jsp">添加图书信息</a></td>	  
	  </tr>
	</table>  
	  <table width="98%"  border="1" cellpadding="0" cellspacing="0">
	  <tr align="center" bgcolor="#e3F4F7">
	    <td width="13%" bgcolor="#F9D16B">条形码</td>  
	    <td width="26%" bgcolor="#F9D16B">图书名称</td>
	    <td width="15%" bgcolor="#F9D16B">图书类型</td>
	    <td width="14%" bgcolor="#F9D16B">出版社</td>
	    <td width="12%" bgcolor="#F9D16B">书架</td>
	    <td width="9%" bgcolor="#F9D16B">修改</td>
	    <td width="5%" bgcolor="#F9D16B">删除</td>
	  </tr>
	 <c:forEach items="${bookquaryList }" var="each">
	  <tr>
	    <td style="padding:5px;">&nbsp;${each[4] }</td>  
	    <td style="padding:5px;"><a href="book.do?action=bookDetail&ID=1">${each[5] }</a></td>
	    <td style="padding:5px;">&nbsp;${each[8]}</td>  
	    <td style="padding:5px;">&nbsp;${each[6] }</td>  
	    <td style="padding:5px;" id="pagesid">&nbsp;${each[7]}</td>  
		
	    <td align="center"><a href="book.do?action=bookModifyQuery&ID=1">修改</a></td>
	    <td align="center"><a href="book.do?action=bookDel&ID=1">删除</a></td>
	  </tr>
	 </c:forEach>
	
	  
	</table></td>
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
    
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.2.0.min.js"></script>
<script src="js/pintuer.js"></script>
<style type="text/css">
	
	td {
		font-family: "微软雅黑";
		font-size: 14px;
		color: #555555;
		text-decoration: none;
		text-align: center;
		line-height: 20px;
		padding-top: 5px;
	}
	
</style>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	
    $(function() {
        
        $.ajax({
        	url: "servlet/AdminServlet?method=findAllWork",
        	type: "post",
        	dataType: "text",
        	success: function(data) {
				eval("var arr="+data);
				var str = "";
				for (var i = 0; i < arr.length; i++) {
					str += "<tr>";
					str += "<td height='20'>" + arr[i].hname + "</td>";
					str += "<td height='20'>" + arr[i].content + "</td>";
					str += "<td height='20'>" + arr[i].releaseTime + "</td>";
					str += "<td height='20'><a class='dwork' href='servlet/AdminServlet?method=deleteWorkByHid&hid="+ arr[i].hid +"'>删除</a></td>";
					str += "</tr>";
				}
				
				$("#cont").html(str);
				 // 隔行变色
			   	var aTr = document.getElementsByTagName('tr');
			       for(var i=0; i<aTr.length; i++) {
			           if(i % 2 == 0) {
			               aTr[i].style.background = '#DBDBDB';
			           } else {
			                aTr[i].style.background = '#F0F0EE';
			           }
			     }
			    /*  
			     $(".dwork").onclick=function() {
			    	var flag = window.confirm("您确定要删除该条记录吗？");
					if(flag) {
						window.location="servlet/CourseServlet?method=removeCourse&cno=" + cno + "&tno=" + tno;
					}
			     }; */
			       
			},
			
        	error: function(xhr) {
				alert(xhr.status);
			}
        });
        
	    
	});   
       
     
</script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 已发布作业列表</strong>
		</div>
		<div class="body-content">
			<br />
			<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">

				<tr>
					<td colspan="6" align="center" bgcolor="#EEEEEE" class="tablestyle_title" style="text-align: center;">详情如下 </td>
				</tr>
				
				<tr>
					<td width="9%" height="20" align="center" bgcolor="#EEEEEE">作业标题</td>
					<td width="8%" align="center" bgcolor="#EEEEEE">详细内容</td>
					<td width="8%" align="center" bgcolor="#EEEEEE">发布日期</td>
					<td width="9%" align="center" bgcolor="#EEEEEE">操作</td>
				</tr>
				<tbody id="cont">
					
				</tbody>
				
			</table>
		</div>
	</div>
</body>
</html>

				
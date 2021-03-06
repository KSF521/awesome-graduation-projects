<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">

<!-- <meta charset="utf-8"> -->
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!-- [if lt IE 9]> -->
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<!-- <![endif] -->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!-- [if IE 6]> -->
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript" src="/eis/js/jquery-1.8.2.js"></script>
<script>DD_belatedPNG.fix('*');</script>
<!-- <![endif] -->
<title>管理员列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
	         
		<input type="text" class="input-text" style="width:250px" placeholder="输入登录名 &nbsp/&nbsp;操作员姓名" id="uName" name="uName">
		<input type="button" class="btn btn-success Hui-iconfont" id=""  name="" value="搜索操作员" onclick="admin_queryByName()">
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="deleteMore(this)" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
	 <a href="javascript:;" onclick="admin_add('添加操作员','admin/adminAdd.jsp','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加操作员</a></span> <span class="r" >共有数据：<strong style="color: red">${size}</strong> 条</span> </div>
	
	<form action="servlet/UserServlet?method=deleteMore" method="post">
	<table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">员工列表</th>
			</tr>
			<tr class="text-c">
				<th width="35"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="150">登录名称</th>
				<th width="140">操作员名称</th>
				<!-- <th width="70">性别</th> -->
				<th width="150">手机号</th>
				<th>权限</th>
				<th width="100">是否已启用</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${list }" var="user">
			<tr class="text-c">
				<td><input type="checkbox" value="${user.id }" name="checkbox" id="checkbox"></td>
				<td id="userId" name="userId">${user.id }</td>
				<td>${user.name }</td>
				<td>${user.userName }</td>
				<td>${user.phone }</td>
				<td>
				<c:if test="${user.quan==0 }">系统管理员</c:if>
				<c:if test="${user.quan==1 }">普通管理员</c:if>
				</td >
				<c:if test="${user.status==0 }">
				   <td class="td-status"><span class="label label-success radius">已启用</span></td>
				   <td class="td-manage"><a style="text-decoration:none" onClick="admin_stop(this,'10001',${user.id})" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> 
				<a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','/eis/servlet/UserServlet?method=toUpdate&id=${user.id }','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
			   <a title="删除" href="javascript:;" onclick="admin_del(this,'${user.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</c:if>
				<c:if test="${user.status==1 }">
				  <td class="td-status"><span class="label radius">已停用</span></td>
				  <td class="td-manage"><a style="text-decoration:none" onClick="admin_start(this,'10001',${user.id})" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe615;</i></a>
				<a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','/eis/servlet/UserServlet?method=toUpdate&id=${user.id }','2','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
				<a title="删除" href="javascript:;" onclick="admin_del(this,'${user.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</c:if>
				
			</tr>
		</c:forEach>			
		</tbody>
	</table>
	</form>	
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
 function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: "POST",
			url: "servlet/UserServlet?method=delete",
			dataType: "text",
			data:{"id":id},
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}  

/**
 * 删除操作
 */
 
 
function admin_delete(id){
	var flag = window.confirm("你确定要删除该管理员吗？");
	if (flag) {
		location.href = "servlet/UserServlet?method=delete&id="+id;
	}
}
 function deleteMore(obj){
	    layer.confirm('确认要批量删除吗？',function(index){
				//$(obj).parents("tr").remove();
				document.forms[0].submit();
				layer.msg('已删除!',{icon:1,time:2000});
					
		});
	}

/*管理员-编辑*/
 function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
} 

/*管理员-停用*/
function admin_stop(obj,id,userId){
	var id=userId;
	var status=0;	
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		updateStatus(id,status);
		layer.msg('已停用!',{icon: 5,time:1000});
		
	});
	
	/* updateStatus(id,status); */
}
/*管理员-启用*/
function admin_start(obj,id,userId){
	var id=userId;
	var status=1;
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		updateStatus(id,status);
		layer.msg('已启用!', {icon: 6,time:1000});
	});
	
}

function updateStatus(id,status){
	window.location.href="servlet/UserServlet?method=UpdateStatus&id="+id+"&status="+status;
}

function admin_queryByName(){
	 var uName=document.getElementById("uName").value;
	 if(uName==null){
		window.location.href="servlet/UserServlet?method=userShow";
	}else{
	    window.location.href="servlet/UserServlet?method=queryByName&uName="+uName;
	 } 
}
</script>
</body>
</html>
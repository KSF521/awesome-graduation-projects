<%@ page contentType="text/html;charset=gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/aqu" prefix="aqu" %>
<c:set value="${aqu:getUrl()}/admin/login.jsp" var="url"/>
<c:if test="${sessionScope.admin==null||sessionScope.admin!='yes'}">
	<c:redirect url="${url}"/>
</c:if>
<html>
<body>
<c:if test="${param.newPassword==null||param.formerPassword==null}">
	这位大哥，不要黑我啊!!!!!
</c:if>
<c:if test="${param.newPassword!=null&&param.formerPassword!=null}">
	<c:set value="${aqu:modifyPassword(param.formerPassword,param.newPassword)}" var="check"/>
	<c:if test="${check=='ok'}">
		修改密码成功，请勿重复提交
	</c:if>
	<c:if test="${check!='ok'}">
		原密码输入错误，请重新输入
	</c:if>
</c:if>
</body>
</html>

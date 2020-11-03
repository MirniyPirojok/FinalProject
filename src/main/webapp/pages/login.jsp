<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru" scope="request"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>

<!DOCTYPE html>
<head>
    <title><fmt:message key="label.login"/></title>
</head>
<body>
<%@ include file="/pages/include/head.jsp" %>

<form name="LoginForm" method="post" action="do">
    <input type="hidden" name="command" value="login"/>

    <fmt:message key="label.login"/><br/>
    <label>
        <input type="text" name="login" value=""/>
    </label>
    <br/><fmt:message key="label.password"/><br/>
    <label>
        <input type="password" name="password" value=""/>
    </label>
    <br/>
    <input type="submit" value=<fmt:message key="button.login"/> />
    <a href="${pageContext.request.contextPath}/pages/signup.jsp">
        <fmt:message key="label.signup"/></a>
</form>

<%@ include file="/pages/include/footer.jsp" %>
<hr/>
</body>
</html>

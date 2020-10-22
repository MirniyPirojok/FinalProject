<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru" scope="request"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<!DOCTYPE html>
<head>
    <title><fmt:message key="label.title"/></title>
</head>
<body>
<form name="LoginForm" method="post" action="controller">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="label.login"/><br/>
    <input type="text" name="login" value=""/>
    <br/><fmt:message key="label.password"/><br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value=<fmt:message key="button.login"/> />

</form>

<form name="WrongActionForm" method="post" action="controller" style="margin-top: 10px">
    <input type="hidden" name="command" value="wrongAction">
    <input type="submit" value=<fmt:message key="button.wrongAction"/>/>
</form>

<hr/>
</body>
</html>

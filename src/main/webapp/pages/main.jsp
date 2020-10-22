<%--@elvariable id="user" type="java"--%>
<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru" scope="request"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<!DOCTYPE html>
<head>
    <title>Welcome</title>
</head>
<body>
<form name="mainForm" method="post" action="controller">

    <input type="hidden" name="command" value="logout"/>
    <h3><fmt:message key="message.welcome"/></h3>
    <hr/>
    ${user}, <fmt:message key="message.hello"/><!doctype html>
    <hr/>
    <input type="submit" name="logout" value=<fmt:message key="button.logout"/> />

</form>
</body>
</html>

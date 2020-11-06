<%--@elvariable id="locale" type="java"--%>
<%--@elvariable id="userName" type="java"--%>
<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru" scope="request"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<!DOCTYPE html>
<html lang="${locale}">
<%@ include file="/pages/include/head.jsp" %>

<head>
    <title>Welcome</title>
</head>
<body>
<form name="mainForm" method="post" action="do?command=logout">
    <h3><fmt:message key="message.welcome"/></h3>
    <hr/>
    ${userName}, <fmt:message key="message.hello"/><!doctype html>
    <hr/>
    <input type="submit" name="logout" value=<fmt:message key="button.logout"/>/>

</form>
</body>
<%@ include file="/pages/include/footer.jsp" %>
</html>

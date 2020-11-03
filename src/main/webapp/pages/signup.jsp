<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 01.11.2020
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru" scope="request"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="label.login"/></title>
</head>
<body>
<form name="SignUpForm" method="post" action="do">
    <input type="hidden" name="command" value="sign_up"/>

    <fmt:message key="label.login"/>*
    <label>
        <input type="text" name="login" value="" required/>
    </label>

    <br/><br/><fmt:message key="label.email"/>*
    <label>
        <input type="email" name="email" value="" />
    </label>

    <br/><br/><fmt:message key="label.password"/>*
    <label>
        <input type="password" name="password" value="" />
    </label>

    <br/><br/><fmt:message key="label.phone"/>*
    <label>
        <input type="tel" name="phone" value="" />
    </label>

    <br/><br/><fmt:message key="label.name"/>
    <label>
        <input type="text" name="name" value=""/>
    </label>

    <br/><br/><fmt:message key="label.surname"/>
    <label>
        <input type="text" name="surname" value=""/>
    </label>

    <br/><br/><input type="submit" value=
        <fmt:message key="button.signup"/>/>
</form>

</body>
</html>
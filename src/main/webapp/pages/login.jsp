<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="LoginForm" method="post" action="controller">
    <input type="hidden" name="command" value="login"/>
    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>
    ${errorLoginMessage}
    <br/>
    ${wrongAction}
    <br/>
    ${nullPage}
    <br/>
    <input type="submit" value="Log in"/>

</form>

<form name="WrongActionForm" method="post" action="controller">
    <input type="hidden" name="command" value="wrongAction">
    <input type="submit" value="Wrong action"/>
</form>

<hr/>
</body>
</html>

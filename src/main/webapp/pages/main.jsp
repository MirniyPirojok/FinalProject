<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome</title>
</head>
<body>
<form name="mainForm" method="post" action="controller">

    <input type="hidden" name="command" value="logout"/>
    <h3>Welcome</h3>
    <hr/>
    ${user}, hello<!doctype html>
    <hr/>
    <input type="submit" name="logout" value="Logout">

</form>
</body>
</html>

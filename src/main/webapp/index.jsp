<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<style>
    body {
        background-size: cover;
        background-color: gray;
    }

    b {
        color: black;
    }
</style>

<%@ include file="/pages/include/head.jsp" %>
<%@ include file="/pages/include/menu.jsp" %>
<body>
<table align="right">
    <tr>
        <td>
            <form class="form-horizontal" action="do?command=change_locale" method="post">
                <button id="en" name="en" class=" btn-outline-primary">en</button>
            </form>
        </td>
        <td>
            <form class="form-horizontal" action="do?command=change_locale" method="post">
                <button id="ru" name="ru" class=" btn-outline-primary">ru</button>
            </form>
        </td>
    </tr>
</table>


<b><h2 align="center"><fmt:message key="message.welcome"/></h2></b>

<%@ include file="/pages/include/footer.jsp" %>
</body>
</html>

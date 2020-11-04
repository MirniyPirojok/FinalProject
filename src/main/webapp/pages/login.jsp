<%--@elvariable id="errormailOrLogin" type="java"--%>
<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 07.09.2020
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<%@ include file="/pages/include/head.jsp" %>

<body>

<form class="mx-auto my-5" style="width: 300px;" method="post" action="do?command=login">
    <div class="form-group">
        <label for="emailInput"><fmt:message key="label.email"/><br/></label>
        <input name="email" type="email" class="form-control" id="emailInput" aria-describedby="emailHelp" required>
    </div>

    <div class="form-group">
        <label for="passwordInput"><fmt:message key="label.password"/></label>
        <input name="password" type="password" class="form-control" id="passwordInput" required>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="button.login"/>
        </button>
        <a id="registrationLink" href="do?command=sign_up"><fmt:message key="label.signup"/></a>
    </div>

    <div class="form-group">
    </div>

    <div class="form-group" style="color: #ff0000">
        ${errormailOrLogin}
    </div>

</form>

<%@ include file="/pages/include/footer.jsp" %>
</body>
</html>

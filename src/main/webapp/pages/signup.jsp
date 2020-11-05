<%--@elvariable id="locale" type="java"--%>
<%--@elvariable id="mes_error" type="java"--%>
<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 01.11.2020
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="${locale}">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/include/head.jsp" %>

<body>

<form class="mx-auto my-5" style="width: 300px;" method="post" action="do?command=sign_up">
    <div class="form-group">
        <label for="emailInput"><fmt:message key="label.email"/>*</label>
        <input type="email" name="email" class="form-control" id="emailInput" aria-describedby="emailHelp" required/>
        <small id="emailHelp" class="form-text text-muted"><fmt:message key="label.emailSmall"/></small>
    </div>

    <div class="form-group">
        <label for="passwordInput"><fmt:message key="label.password"/>*</label>
        <input id="passwordInput" type="password" name="password" class="form-control" required/>
    </div>

    <div class="form-group">
        <label for="phoneInput"><fmt:message key="label.phone"/>*</label>
        <input id="phoneInput" type="tel" name="phone" class="form-control" required/>
    </div>

    <div class="form-group">
        <label for="nameInput"><fmt:message key="label.nameUser"/>*</label>
        <input id="nameInput" type="text" name="name" class="form-control" required/>
    </div>

    <div class="form-group">
        <label for="surnameInput"><fmt:message key="label.surname"/>*</label>
        <input id="surnameInput" type="text" name="surname" class="form-control" required/>
    </div>

    <div class="form-group">
        <label for="registerButton">
            <button type="submit" id="registerButton" class="btn btn-primary">
                <fmt:message key="button.signup"/>
            </button>
        </label>
    </div>

    <div class="form-group" style="color: red">
        ${mes_error}
    </div>

</form>

<%@ include file="/pages/include/footer.jsp" %>
</body>
</html>
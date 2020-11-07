<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>
<!DOCTYPE html>
<html lang="${locale}">

<%@ include file="/pages/include/head.jsp" %>

<body>

<form class="mx-auto my-5" style="width: 300px;" method="post" action="do?command=sign_up">
    <div class="form-group">
        <label for="emailInput"><fmt:message key="label.email"/>*</label>
        <input type="email" name="email" class="form-control" id="emailInput" aria-describedby="emailHelp" required/>
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
        ${msg_error}
    </div>

</form>

<%@ include file="/pages/include/footer.jsp" %>
</body>
</html>
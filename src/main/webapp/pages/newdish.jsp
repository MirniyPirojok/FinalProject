<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/head.jsp" %>
<html>
<body>
<h3 align="center" ><fmt:message key="message.addDish"/></h3>
<form class="mx-auto my-5" style="width: 300px;" method="post" action="do?command=new_dish">
    <div class="form-group">
        <label for="nameInput"><fmt:message key="label.dishName"/>*</label>
        <input type="text" name="name" class="form-control" id="nameInput" required/>
    </div>

    <div class="form-group">
        <label for="costInput"><fmt:message key="label.dishCost"/>*</label>
        <input type="text" name="cost" class="form-control" id="costInput" required/>
    </div>

    <div class="form-group">
        <label for="submitButton">
            <button type="submit" id="submitButton" class="btn btn-primary">
                <fmt:message key="button.submit"/>
            </button>
        </label>
    </div>

    <div class="form-group" style="color: limegreen">
        ${msg_success}
    </div>

    <div class="form-group" style="color: red">
        ${msg_error}
    </div>
</form>

</body>
</html>

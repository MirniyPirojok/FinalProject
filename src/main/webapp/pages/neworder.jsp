<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="include/head.jsp" %>
<html>
<body>
<h3 align="center">
    <fmt:message key="message.makeOrder"/>
</h3>

<table class="table">
    <tbody>
    <c:forEach items="${dishes}" var="dishes">

        <tr>
            <td>${dishes.name}</td>
            <td>${dishes.cost}</td>

            <td>
                <form action="do?command=order_dish" method="post">
                    <input name="id_dish" type="hidden" value="${dishes.id}">
                    <button type="submit" class="btn btn-toolbar">
                        +
                    </button>
                </form>
            </td>

            <td>
                <form action="do?command=disorder_dish" method="post">
                    <button type="submit" class="btn btn-toolbar">
                        -
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    <div class="ui-state-error">
        ${msg_error}
    </div>

</table>

</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>

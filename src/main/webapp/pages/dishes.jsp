<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
    <h3 align="center"><fmt:message key="message.menu"/></h3>

    <table class="table">
        <tbody>
        <c:forEach items="${dishes}" var="dishes">
            <tr>
                <td>${dishes.name}</td>
                <td>${dishes.cost}</td>
        </c:forEach>
        </tbody>
    </table>

</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>

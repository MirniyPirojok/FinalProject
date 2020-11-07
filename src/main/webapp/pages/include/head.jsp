<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent.pagecontent"/>

<head>
    <title>Borisov's restaurant</title>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
            crossorigin="anonymous"></script>

    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <%--                    <li class="nav-item dropdown">--%>
                    <%--                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink1" role="button"--%>
                    <%--                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                    <%--                            <fmt:message key="navBar.dishes"/>--%>
                    <%--                        </a>--%>
                    <%--                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">--%>
                    <%--                            <a class="dropdown-item" href="do?command=new_dish"><fmt:message--%>
                    <%--                                    key="navBar.newDish"/></a>--%>
                    <%--                            <a class="dropdown-item" href="do?command=view_dishes"><fmt:message--%>
                    <%--                                    key="navBar.menu"/></a>--%>
                    <%--                        </div>--%>
                    <%--                    </li>--%>

                    <%--                    <li class="nav-item dropdown">--%>
                    <%--                        <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink2" role="button"--%>
                    <%--                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                    <%--                            <fmt:message key="navBar.orders"/>--%>
                    <%--                        </a>--%>
                    <%--                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">--%>
                    <%--                            <a class="dropdown-item" href="do?command=new_order"><fmt:message--%>
                    <%--                                    key="navBar.newOrder"/></a>--%>
                    <%--                            <a class="dropdown-item" href="do?command=view_orders"><fmt:message--%>
                    <%--                                    key="navBar.ordersList"/></a>--%>
                    <%--                        </div>--%>
                    <%--                    </li>--%>


                    <%--                        TODO available only for user--%>
                    <li class="nav-item">
                        <a class="nav-link" href="do?command=new_order"><fmt:message
                                key="navBar.newOrder"/></a>
                    </li>

                    <%--                        TODO available only for administrator--%>
                    <li class="nav-item">
                        <a class="nav-link" href="do?command=view_orders"><fmt:message
                                key="navBar.ordersList"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="do?command=new_dish"><fmt:message
                                key="navBar.newDish"/></a>
                    </li>


                    <%--                        TODO available for everyone--%>
                    <li class="nav-item">
                        <a class="nav-link" href="do?command=view_dishes"><fmt:message
                                key="navBar.menu"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="do?command=login"><fmt:message key="navBar.login"/> </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="do?command=sign_up"><fmt:message key="navBar.signup"/> </a>
                    </li>

                </ul>
            </div>
        </nav>
    </div>
</head>


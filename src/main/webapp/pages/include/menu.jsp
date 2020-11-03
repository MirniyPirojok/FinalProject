<%--
  Created by IntelliJ IDEA.
  User: MirniyPirojok
  Date: 02.11.2020
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="do?command=home">
                        <fmt:message key="navBar.home"/>
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="do?command=view_dishes"><fmt:message key="navBar.dishes"/> </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="navBar.orders"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="do?command=create_new_order"><fmt:message key="navBar.newOrder"/></a>
                        <a class="dropdown-item" href="do?command=view_orders_list"><fmt:message key="navBar.ordersList"/></a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" media="all"
          href="http://demo.appfuse.org/appfuse-struts/styles/simplicity/theme.css"/>
    <style type="text/css">
        h1 {
            margin-left: -300px;
            margin-top: 50px
        }
    </style>
</head>
<body>
<h1>Login</h1>

<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>">
    <fieldset style="padding-bottom: 0">
        <ul>
            <c:if test="${param.error != null}">
                <li class="error">
                    ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
                </li>
            </c:if>
            <li>
                <label for="j_username" class="required desc">
                    Username <span class="req">*</span>
                </label>
                <input type="text" class="text medium" name="j_username"
                       id="j_username" tabindex="1"/>
            </li>

            <li>
                <label for="j_password" class="required desc">
                    Password <span class="req">*</span>
                </label>
                <input type="password" class="text medium" name="j_password"
                       id="j_password" tabindex="2"/>
            </li>
            <li>
                <input type="submit" class="button" name="login" value="Login"
                       tabindex="3"/>
            </li>
        </ul>
    </fieldset>
</form>
</body>
</html>

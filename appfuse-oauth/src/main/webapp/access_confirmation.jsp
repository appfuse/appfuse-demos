<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>
<%@ page import="org.springframework.security.oauth2.provider.verification.BasicUserApprovalFilter" %>
<%@ page import="org.springframework.security.oauth2.provider.verification.VerificationCodeFilter" %>
<%@ page import="org.springframework.security.web.WebAttributes" %>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Confirm Access</title>
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

<h1>Confirm Access</h1>

<div id="content">

    <% if (session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) != null && 
                 !(session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) instanceof UnapprovedClientAuthenticationException)) { %>
    <div class="error">
        <h2>Woops!</h2>

        <p>Access could not be granted.
            (<%= ((AuthenticationException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)).getMessage() %>)</p>
    </div>
    <% } %>
    <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>

    <authz:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
        <h2>Please Confirm</h2>

        <p>You hereby authorize "<c:out value="${client.clientId}" escapeXml="true"/>" to access your protected resources.</p>

        <form id="confirmationForm" name="confirmationForm"
              action="<%=request.getContextPath() + VerificationCodeFilter.DEFAULT_PROCESSING_URL%>" method="POST">
            <input name="<%=BasicUserApprovalFilter.DEFAULT_APPROVAL_REQUEST_PARAMETER%>"
                   value="<%=BasicUserApprovalFilter.DEFAULT_APPROVAL_PARAMETER_VALUE%>" type="hidden"/>
            <label><input name="authorize" value="Authorize" type="submit"></label>
        </form>
        <form id="denialForm" name="denialForm"
              action="<%=request.getContextPath() + VerificationCodeFilter.DEFAULT_PROCESSING_URL%>" method="POST">
            <input name="<%=BasicUserApprovalFilter.DEFAULT_APPROVAL_REQUEST_PARAMETER%>"
                   value="not_<%=BasicUserApprovalFilter.DEFAULT_APPROVAL_PARAMETER_VALUE%>" type="hidden"/>
            <label><input name="deny" value="Deny" type="submit"></label>
        </form>
    </authz:authorize>
</div>
</body>
</html>

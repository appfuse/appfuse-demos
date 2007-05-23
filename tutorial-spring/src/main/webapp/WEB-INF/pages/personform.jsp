<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='personDetail.heading'/>"/>
</head>

<form:form commandName="person" method="post" action="personform.html" id="personForm" onsubmit="return validatePerson(this)">
<form:errors path="*" cssClass="error" element="div"/>
<form:hidden path="id"/>
<ul>
    <li>
        <appfuse:label styleClass="desc" key="person.firstName"/>
        <form:errors path="firstName" cssClass="fieldError"/>
        <form:input path="firstName" id="firstName" cssClass="text medium"/>
    </li>

    <li>
        <appfuse:label styleClass="desc" key="person.lastName"/>
        <form:errors path="lastName" cssClass="fieldError"/>
        <form:input path="lastName" id="lastName" cssClass="text medium"/>
    </li>

    <li class="buttonBar bottom">
        <input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/>
        <c:if test="${not empty person.id}">
        <input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('person')" 
            value="<fmt:message key="button.delete"/>" />
        </c:if>
        <input type="submit" class="button" name="cancel" value="<fmt:message key="button.cancel"/>" onclick="bCancel=true"/>
    </li>
</ul>
</form:form>

<v:javascript formName="person" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>

<script type="text/javascript">
    Form.focusFirstElement($('personForm'));
</script>

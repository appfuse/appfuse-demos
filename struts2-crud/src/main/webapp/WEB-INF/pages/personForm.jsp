<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='personDetail.heading'/>"/>
</head>

<s:form id="personForm" action="savePerson" method="post" validate="true">
    <s:hidden key="person.id" cssClass="text medium"/>
    <s:textfield key="person.firstName" required="false" cssClass="text medium"/>
    <s:textfield key="person.lastName" required="false" cssClass="text medium"/>

    <li class="buttonBar bottom">
        <s:submit cssClass="button" method="save" key="button.save" theme="simple"/>
        <c:if test="${not empty person.id}">
            <s:submit cssClass="button" method="delete" key="button.delete"
                onclick="return confirmDelete('Person')" theme="simple"/>
        </c:if>
        <s:submit cssClass="button" method="cancel" key="button.cancel" theme="simple"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement($("personForm"));
</script>

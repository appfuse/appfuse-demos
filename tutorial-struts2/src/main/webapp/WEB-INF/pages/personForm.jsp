<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <content tag="heading"><fmt:message key="personDetail.heading"/></content>
</head>

<s:form id="personForm" action="savePerson" method="post" validate="true">
<s:hidden name="person.id" value="%{person.id}"/>

    <s:textfield key="person.firstName" required="true" cssClass="text medium"/>
    <s:textfield key="person.lastName" required="true" cssClass="text medium"/>

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

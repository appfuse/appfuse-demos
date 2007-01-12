<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <content tag="heading"><fmt:message key="personDetail.heading"/></content>
</head>

<s:form name="personForm" action="savePerson" method="post" validate="true">
<s:hidden name="person.id" value="%{person.id}"/>

    <s:textfield label="%{getText('person.firstName')}" name="person.firstName"
        value="%{person.firstName}" required="true"/>

    <s:textfield label="%{getText('person.lastName')}" name="person.lastName"
        value="%{person.lastName}" required="true"/>

    <tr>
        <td></td>
        <td class="buttonBar">
            <s:submit cssClass="button" method="save" value="%{getText('button.save')}" theme="simple"/>
            <s:submit cssClass="button" method="delete" value="%{getText('button.delete')}"
                onclick="return confirmDelete('Person')" theme="simple"/>
            <s:submit cssClass="button" name="cancel" value="%{getText('button.cancel')}" theme="simple"/>
        </td>
    </tr>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement(document.forms["personForm"]);
</script>

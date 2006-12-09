<%@ include file="/common/taglibs.jsp" %>

<title>Person Example</title>

<p>Please use the form below to modify a person's information.</p>

<s:form name="personForm" action="person" method="post" validate="true">
    <s:hidden name="person.id" value="%{person.id}"/>

    <s:textfield label="First Name" name="person.firstName"
                 value="%{person.firstName}" required="true" cssClass="text medium"/>

    <s:textfield label="Last Name" name="person.lastName"
                 value="%{person.lastName}" required="true" cssClass="text medium"/>

    <li class="buttonBar left">
        <s:submit method="save" value="Save" cssClass="button"/>
        <s:submit method="delete" value="Delete" cssClass="button"/>
    </li>
</s:form>

<script type="text/javascript">
    Form.focusFirstElement('person');
</script>

<div style="margin-top: 50px">
<s:action name="person!list" id="action" namespace="default"/>

<strong>Current folks:</strong>
<ul class="glassList">
    <s:iterator value="#action.persons">
        <li><s:a href="person.html?id=%{id}"><s:property value="firstName"/> <s:property value="lastName"/></s:a></li>
    </s:iterator>
</ul>
</div>
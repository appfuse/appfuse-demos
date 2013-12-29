<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <meta name="menu" content="PersonMenu"/>
</head>

<div class="col-sm-3">
    <h2><fmt:message key="personDetail.heading"/></h2>
</div>
<div class="col-sm-7">
    <s:form id="personForm" action="savePerson" method="post" cssClass="well" validate="true">
        <s:hidden key="person.id"/>

        <s:textfield cssClass="form-control" key="person.firstName" required="false" maxlength="50" autofocus="true"/>
        <s:textfield cssClass="form-control" key="person.lastName" required="false" maxlength="50"/>

        <div id="actions" class="form-group form-actions">
            <s:submit type="button" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i>
                <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty person.id}">
                <s:submit type="button" cssClass="btn btn-danger" method="delete" key="button.delete"
                          onclick="return confirmDelete('Person')" theme="simple">
                    <i class="icon-trash"></i>
                    <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <s:submit type="button" cssClass="btn btn-default" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i>
                <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
    </s:form>
</div
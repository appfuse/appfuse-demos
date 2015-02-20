<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <meta name="menu" content="PersonMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="personList.person"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="personDetail.heading"/></h2>
    <fmt:message key="personDetail.message"/>
</div>

<div class="col-sm-6">
    <s:form id="personForm" action="savePerson" method="post" validate="true" cssClass="well">
        <s:hidden key="person.id"/>
        <s:textfield cssClass="form-control" key="person.firstName" required="false" maxlength="50" />
        <s:textfield cssClass="form-control" key="person.lastName" required="false" maxlength="50" />

        <div class="form-group">
            <s:submit type="button" id="save" cssClass="btn btn-primary" method="save" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </s:submit>
            <c:if test="${not empty person.id}">
                <s:submit type="button" id="delete" cssClass="btn btn-danger" method="delete" key="button.delete"
                          onclick="return confirmMessage(msgDelConfirm)" theme="simple">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </s:submit>
            </c:if>
            <a href="${ctx}/persons" class="btn btn-default">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/></a>
        </div>
    </s:form>
</div>

<%@ include file="/common/taglibs.jsp"%>

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
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="person" method="post" action="personform" cssClass="well"
               id="personForm" onsubmit="return validatePerson(this)">
        <form:hidden path="id"/>
        <spring:bind path="person.firstName">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="person.firstName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="firstName" id="firstName"  maxlength="50"/>
        <form:errors path="firstName" cssClass="help-block"/>
        </div>
        <spring:bind path="person.lastName">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="person.lastName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="lastName" id="lastName"  maxlength="50"/>
        <form:errors path="lastName" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            <c:if test="${not empty person.id}">
                <button type="submit" class="btn btn-danger" id="delete" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>

            <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<v:javascript formName="person" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['personForm']).focus();
    });
</script>

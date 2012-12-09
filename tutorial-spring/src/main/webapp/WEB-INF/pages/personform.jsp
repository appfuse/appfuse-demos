<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personDetail.title"/></title>
    <meta name="menu" content="PersonMenu"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userList.user"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key='personDetail.heading'/></h2>
</div>
<div class="span7">
    <form:errors path="*" cssClass="alert alert-error fade in" element="div"/>

    <form:form commandName="person" method="post" action="personform" id="personForm"
               cssClass="well form-horizontal" onsubmit="return validatePerson(this)">
    <form:hidden path="id"/>

    <spring:bind path="person.firstName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="person.firstName"/>
        <div class="controls">
            <form:input path="firstName" id="firstName" maxlength="50"/>
            <form:errors path="firstName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="person.lastName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label styleClass="control-label" key="person.lastName"/>
        <div class="controls">
            <form:input path="lastName" id="lastName" maxlength="50"/>
            <form:errors path="lastName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>

        <c:if test="${not empty person.id}">
          <button type="submit" class="btn" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
              <i class="icon-trash"></i> <fmt:message key="button.delete"/>
          </button>
        </c:if>

        <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
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

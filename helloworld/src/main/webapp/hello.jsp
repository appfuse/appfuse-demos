<%@ include file="/common/taglibs.jsp"%>

<s:action name="hello" id="action" namespace="default"/>

<strong>Message from Struts:</strong>

<span style="color: blue"><s:property value="#action.message"/></span>
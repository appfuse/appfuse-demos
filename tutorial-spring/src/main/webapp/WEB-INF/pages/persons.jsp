<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personList.title"/></title>
    <meta name="heading" content="<fmt:message key='personList.heading'/>"/>
    <meta name="menu" content="PersonMenu"/>
</head>

<input type="button" style="margin-right: 5px" onclick="location.href='<c:url value="/personform"/>'" value="<fmt:message key="button.add"/>"/>
<input type="button" onclick="location.href='<c:url value="/mainMenu"/>'" value="<fmt:message key="button.done"/>"/>

<display:table name="personList" class="table" requestURI="" id="personList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="personform" media="html"
        paramId="id" paramProperty="id" titleKey="person.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="person.id"/>
    <display:column property="firstName" sortable="true" titleKey="person.firstName"/>
    <display:column property="lastName" sortable="true" titleKey="person.lastName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="personList.person"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="personList.persons"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="personList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="personList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="personList.title"/>.pdf</display:setProperty>
</display:table>

<input type="button" style="margin-right: 5px" onclick="location.href='<c:url value="/personform"/>'" value="<fmt:message key="button.add"/>"/>
<input type="button" onclick="location.href='<c:url value="/mainMenu"/>'" value="<fmt:message key="button.done"/>"/>

<script type="text/javascript">
    highlightTableRows("personList");
</script> 

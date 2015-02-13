<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personList.title"/></title>
    <meta name="menu" content="PersonMenu"/>
</head>

<h2><fmt:message key="personList.heading"/></h2>

<div id="actions" class="btn-group">
    <a href='<c:url value="/personform"/>' class="btn btn-primary">
        <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
    <a href='<c:url value="/home"/>' class="btn btn-default"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
</div>

<display:table name="personList" class="table table-condensed table-striped table-hover" requestURI=""
               id="personList" export="true" pagesize="25">
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
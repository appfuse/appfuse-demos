<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personList.title"/></title>
    <meta name="heading" content="<fmt:message key='personList.heading'/>"/>
    <meta name="menu" content="PersonMenu"/>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px" class="button"
        onclick="location.href='<c:url value="/editPerson.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" class="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false" />

<display:table name="persons" class="table" requestURI="" id="personList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="editPerson.html" media="html"
        paramId="id" paramProperty="id" titleKey="person.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="person.id"/>
    <display:column property="firstName" sortable="true" titleKey="person.firstName"/>
    <display:column property="lastName" sortable="true" titleKey="person.lastName"/>

    <display:setProperty name="paging.banner.item_name" value="person"/>
    <display:setProperty name="paging.banner.items_name" value="persons"/>

    <display:setProperty name="export.excel.filename" value="Person List.xls"/>
    <display:setProperty name="export.csv.filename" value="Person List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Person List.pdf"/>
</display:table>

<c:out value="${buttons}" escapeXml="false" />

<script type="text/javascript">
    highlightTableRows("personList");
</script>

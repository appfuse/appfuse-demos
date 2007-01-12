<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="personList.title"/></title>
    <content tag="heading"><fmt:message key="personList.heading"/></content>
</head>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/editPerson.html"/>'"
        value="<fmt:message key="button.add"/>"/>
    
    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false" />

<s:set name="persons" value="persons" scope="request"/>
<display:table name="persons" class="table" requestURI="" id="personList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="editPerson.html" 
        paramId="id" paramProperty="id" titleKey="person.id"/>
    <display:column property="firstName" sortable="true" 
        titleKey="person.firstName"/>
    <display:column property="lastName" sortable="true" 
        titleKey="person.lastName"/>

    <display:setProperty name="paging.banner.item_name" value="person"/>
    <display:setProperty name="paging.banner.items_name" value="people"/>

    <display:setProperty name="export.excel.filename" value="Person List.xls"/>
    <display:setProperty name="export.csv.filename" value="Person List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Person List.pdf"/>
</display:table>

<c:out value="${buttons}" escapeXml="false" />

<script type="text/javascript">
    highlightTableRows("personList");
</script>

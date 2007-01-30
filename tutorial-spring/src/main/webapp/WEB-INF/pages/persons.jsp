<%@ include file="/common/taglibs.jsp"%>

<title><fmt:message key="personList.title"/></title>
<content tag="heading"><fmt:message key="personList.heading"/></content>
<meta name="menu" content="PersonMenu"/>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/personform.html"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<c:url value="/mainMenu.html"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<display:table name="personList" cellspacing="0" cellpadding="0" requestURI=""
    id="personList" pagesize="25" class="table personList" export="true">

    <display:column property="id" escapeXml="true" sortable="true"
        url="/personform.html" paramId="id" paramProperty="id" titleKey="person.id"/>
    <display:column property="firstName" escapeXml="true" sortable="true" titleKey="person.firstName"/>
    <display:column property="lastName" escapeXml="true" sortable="true" titleKey="person.lastName"/>

    <display:setProperty name="paging.banner.item_name" value="person"/>
    <display:setProperty name="paging.banner.items_name" value="people"/>

    <display:setProperty name="export.excel.filename" value="Person List.xls"/>
    <display:setProperty name="export.csv.filename" value="Person List.csv"/>
    <display:setProperty name="export.pdf.filename" value="Person List.pdf"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("personList");
</script> 
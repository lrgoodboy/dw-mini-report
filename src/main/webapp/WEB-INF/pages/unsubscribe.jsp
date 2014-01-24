<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="naviTab" value="home" scope="request" />
<t:base>

<jsp:attribute name="styles">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
</jsp:attribute>

<jsp:body>

<div class="row">
  <div class="span12">
  Report ID: ${reportId}
  </div>
</div>

</jsp:body>

</t:base>

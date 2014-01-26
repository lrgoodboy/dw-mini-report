<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>

<jsp:body>

<div class="row">
  <div class="span12" style="font-size: 14px;">
    <div class="alert alert-error">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      <strong>操作失败！</strong>${error}
    </div>
  </div>
</div>

</jsp:body>

</t:base>

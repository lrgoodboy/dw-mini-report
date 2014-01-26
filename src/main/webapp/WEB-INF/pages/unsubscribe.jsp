<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:base>

<jsp:body>

<div class="row">
  <div class="span12" style="font-size: 14px;">
    <p>${currentUser.name}：</p>
    <p>您正在退订《${report.name}》，报表创建人：${report.ownerName}。</p>
    <p>
      <a href="<c:url value="/unsubscribe/confirm?reportId=${report.id}" />" class="btn btn-primary">确认退订</a>
      <a href="http://max.corp.anjuke.com" class="btn">取消</a>
    </p>
  </div>
</div>

</jsp:body>

</t:base>

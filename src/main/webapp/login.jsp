<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:body>
      <jsp:include page="./_login_form.jsp" />
    </jsp:body>
</t:layout>
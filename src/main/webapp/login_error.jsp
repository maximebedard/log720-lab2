<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:body>
      <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Erreur:</span>
        Veuillez essayer à nouveau
      </div>
      <t:login_form />
    </jsp:body>
</t:layout>
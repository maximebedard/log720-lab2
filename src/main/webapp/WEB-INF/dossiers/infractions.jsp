<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Modification du dossier</h1>
    </jsp:attribute>
    <jsp:body>
      <form action="${pageContext.request.contextPath}/dossier-infractions?id=${dossier.id}" method="post">

        <c:if test="${infractions.size() > 0}">
          <c:forEach items="${infractions}" var="infraction">
            <div class="checkbox">
              <label>
                <input type="checkbox" id="infraction_${infraction.id}" name="infractions" value="${infraction.id}" ${selectedInfractions.contains(infraction)? 'checked="checked"' : ''}> ${infraction.description}<br />
              </label>
            </div>
          </c:forEach>
        </c:if>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Sauvegarder</button>
            <button type="submit" class="btn btn-danger">Supprimer</button>
          </div>
        </div>
      </form>
    </jsp:body>
</t:layout>

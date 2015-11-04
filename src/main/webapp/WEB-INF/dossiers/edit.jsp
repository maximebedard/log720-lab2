<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Modification du dossier</h1>
    </jsp:attribute>
    <jsp:body>
      <form action="${pageContext.request.contextPath}/dossiers?id=${dossier.id}" method="post" class="form-horizontal">
        <div class="form-group">
          <label for="nom" class="col-sm-2 control-label">Nom</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom" value="${dossier.nom}">
          </div>
        </div>


        <div class="form-group">
          <label for="prenom" class="col-sm-2 control-label">Prenom</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prenom" value="${dossier.prenom}">
          </div>
        </div>

        <div class="form-group">
          <label for="noPlaque" class="col-sm-2 control-label"># Plaque</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="noPlaque" name="noPlaque" placeholder="# Plaque" value="${dossier.noPlaque}">
          </div>
        </div>

        <div class="form-group">
          <label for="noPermis" class="col-sm-2 control-label"># Permis</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="noPermis" name="noPermis" placeholder="# Permis" value="${dossier.noPermis}">
          </div>
        </div>

        <div class="form-group">
          <label for="infractions" class="col-sm-2 control-label">Infractions</label>
          <div class="col-sm-10">
            <c:if test="${selectedInfractions.size() > 0}">
              <h3>Infractions attributés</h3>
              <ul class="list-group">
                <c:forEach items="${selectedInfractions}" var="infraction">
                  <li class="list-group-item">
                    <span class="badge">${infraction.gravite}</span>
                    ${infraction.description}
                  </li>
                </c:forEach>
              </ul>
            </c:if>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary" name="btnSave">Sauvegarder</button>
            <button type="submit" class="btn btn-danger" name="btnDelete">Supprimer</button>
            <button type="submit" class="btn btn-warning" name="btnCancel">Annuler</button>
          </div>
        </div>
      </form>
    </jsp:body>
</t:layout>

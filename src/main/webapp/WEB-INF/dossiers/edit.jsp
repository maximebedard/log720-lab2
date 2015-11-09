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
            <c:choose>
              <c:when test="${selectedInfractions.size() > 0}">
                <ul class="list-group">
                  <table class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Description</th>
                        <c:if test="${role.equals('utilisateur')}">
                          <th>Actions</th>
                        </c:if>
                      </tr>
                    </thead>
                  <c:forEach items="${selectedInfractions}" var="infraction">
                    <tr>
                      <td>
                        ${infraction.description}
                        <span class="badge pull-right">${infraction.gravite}</span>
                      </td>
                      <c:if test="${role.equals('utilisateur')}">
                        <td>
                          <button type="submit" class="btn btn-danger" name="btnDelInfraction" value="${infraction.id_dossierInfraction}">
                          <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Supprimer
                          </button>
                        </td>
                      </c:if>
                    </tr>
                  </c:forEach>
                  </table>
                </ul>
              </c:when>
              <c:otherwise>
                Aucune infractions attribués à ce dossier.
              </c:otherwise>
            </c:choose>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary" name="btnSave" ${role.equals('utilisateur') ? "disabled": ""}>Sauvegarder</button>
            <button type="submit" class="btn btn-danger" name="btnDelete" ${role.equals('utilisateur') ? "disabled": ""}>Supprimer</button>
            <button type="submit" class="btn btn-warning" name="btnCancel"${role.equals('utilisateur') ? "disabled": ""}>Annuler</button>
          </div>
        </div>

      </form>
      <c:if test="${role.equals('utilisateur')}">
        <h1>Ajout des infractions</h1>
        <form action="${pageContext.request.contextPath}/dossiers?id=${dossier.id}" method="post" class="form-horizontal">
          <c:choose>
            <c:when test="${infractions.size() > 0}">
              <div class="well">
                <c:forEach items="${infractions}" var="infraction">
                  <div class="checkbox">
                    <label>
                      <input type="checkbox" id="infraction_${infraction.id}" name="infractions" value="${infraction.id}"> ${infraction.description} (${infraction.gravite})<br />
                    </label>
                  </div>
                </c:forEach>
              </div>
              <button type="submit" class="btn btn-primary" name="btnAddInfractions">Ajouter les infractions selectionnés</button>
            </c:when>
            <c:otherwise>
              Vous devez ajouter des infractions à la banque d'infractions avant de procéder à l'attribution d'infractions à ce dossier.
            </c:otherwise>
          </c:choose>
        </form>
      </c:if>
    </jsp:body>
</t:layout>

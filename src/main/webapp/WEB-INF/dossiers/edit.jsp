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
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary" name="btnSave">Sauvegarder</button>
            <button type="submit" class="btn btn-danger" name="btnDelete">Supprimer</button>
            <button type="submit" class="btn btn-warning" name="btnCancel">Annuler</button>
          </div>
        </div>

        <div class="form-group">
          <label for="infractions" class="col-sm-2 control-label">Infractions</label>
          <div class="col-sm-10">
            <c:if test="${selectedInfractions.size() > 0}">
              <h3>Infractions attributés</h3>
              <ul class="list-group">
                <table class="table table-bordered table-hover">
                  <thead>
                    <tr>
                      <th>Description</th>
                      <th>Sévérité</th>
                      <th></th>
                    </tr>
                  </thead>
                <c:forEach items="${selectedInfractions}" var="infraction">
                  <tr>
                    <td>${infraction.description}</td>
                    <td><span class="badge">${infraction.gravite}</span></td>
                    <td>
                      <c:if test="${role.equals('utilisateur')}">
                        <button type="submit" class="btn btn-danger" name="btnDelInfraction" value="${infraction.id_dossierInfraction}">Supprimer cette infraction du dossier</button>
                      </c:if>
                    </td>
                  </tr>
                </c:forEach>
                </table>
              </ul>
            </c:if>
          </div>
        </div>

        <c:if test="${role.equals('utilisateur')}">
          <div class="form-group" id="accordion">
              <div class="panel panel-default" id="panel1">
                  <div class="panel-heading">
                    <h4 class="panel-title">
                      <a data-toggle="collapse" data-target="#collapseOne" href="#collapseOne">
                        Ajouter des infractions
                      </a>
                    </h4>
                  </div>
                  <div id="collapseOne" class="panel-collapse collapse collapse">
                      <div class="panel-body">
                        <t:layout>
                          <jsp:attribute name="header">
                            <h1>Liste de toutes les infractions</h1>
                          </jsp:attribute>
                          <jsp:attribute name="scripts">
                            <script type="text/javascript">
                            </script>
                          </jsp:attribute>
                          <jsp:body>
                            <table class="table table-bordered table-hover">
                              <thead>
                                <tr>
                                  <th>ID</th>
                                  <th>Sévérité</th>
                                  <th>Description</th>
                                  <th></th>
                                </tr>
                              </thead>
                              <tbody>
                              <c:forEach items="${infractions}" var="infraction">
                                <tr>
                                  <td>${infraction.id}</td>
                                  <td>${infraction.gravite}</td>
                                  <td>${infraction.description}</td>
                                  <td><button type="submit" class="btn btn-primary" name="btnAddInfraction" value="${infraction.id}">Ajouter cette infraction au dossier</button></td>
                                </tr>
                              </c:forEach>
                              </tbody>
                            </table>
                          </jsp:body>
                        </t:layout>
                      </div>
                  </div>
              </div>
          </div>
        </c:if>




      </form>
    </jsp:body>
</t:layout>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
  <jsp:attribute name="header">
    <h1>Liste de tous les dossiers</h1>
  </jsp:attribute>
  <jsp:attribute name="scripts">
    <script type="text/javascript">
      $(document).ready(function(){
        $('tbody tr').click(function(){
          id = $(this).find('td:first').html();
          window.location = "${pageContext.request.contextPath}/dossiers?id=" + id;
        })
      });
    </script>
  </jsp:attribute>
  <jsp:body>
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nom</th>
          <th>Prenom</th>
          <th>NoPermis</th>
          <th>NoPlaque</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${dossiers}" var="dossier">
        <tr>
          <td>${dossier.id}</td>
          <td>${dossier.nom}</td>
          <td>${dossier.prenom}</td>
          <td>${dossier.noPermis}</td>
          <td>${dossier.noPlaque}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <c:if test="${role.equals('administrateur')}">
      <form action="${pageContext.request.contextPath}/dossiers" method="post" class="form-inline">
        <div class="form-group">
          <label class="sr-only" for="nom">Nom</label>
          <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom">
        </div>
        <div class="form-group">
          <label class="sr-only" for="prenom">Prenom</label>
          <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Prenom">
        </div>
        <div class="form-group">
          <label class="sr-only" for="noPlaque"># Plaque</label>
          <input type="text" class="form-control" id="noPlaque" name="noPlaque" placeholder="# Plaque">
        </div>
        <div class="form-group">
          <label class="sr-only" for="noPermis"># Permis</label>
          <input type="text" class="form-control" id="noPermis" name="noPermis" placeholder="# Permis">
        </div>
        <button type="submit" class="btn btn-default btn-primary" name="btnSave">Ajouter</button>
    </c:if>
    </form>
  </jsp:body>
</t:layout>

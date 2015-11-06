<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
  <jsp:attribute name="header">
    <h1>Liste de tous les utilisateurs</h1>
  </jsp:attribute>
  <jsp:attribute name="scripts">
  </jsp:attribute>
  <jsp:body>
  <form action="${pageContext.request.contextPath}/utilisateurs" method="post" class="form-horizontal">
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nom</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${utils}" var="utils">
          <tr>
            <td>${utils.id}</td>
            <td>${utils.nom}</td>
            <td><button type="submit" class="btn btn-danger" name="btnDelUser" value="${utils.id}">Supprimer cet utilisateur</button></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </form>


    <form action="${pageContext.request.contextPath}/utilisateurs" method="post" class="form-inline">
      <div class="form-group">
        <label class="sr-only" for="nom">Nom</label>
        <input type="text" class="form-control" id="nom" name="nom" placeholder="Nom">
      </div>
      <div class="form-group">
        <label class="sr-only" for="prenom">Prenom</label>
        <input type="text" class="form-control" id="pwd" name="pwd" placeholder="Password">
      </div>
      <button type="submit" class="btn btn-default btn-primary" name="btnSave">Ajouter</button>
  </form>
  </jsp:body>
</t:layout>

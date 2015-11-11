<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:attribute name="header">
      <h1>Modification du dossier</h1>
    </jsp:attribute>
    <jsp:body>
      <form action="${pageContext.request.contextPath}/infractions?id=${infraction.id}" method="post" class="form-horizontal">
        <div class="form-group">
          <label for="gravite" class="col-sm-2 control-label">Sévérité</label>
          <div class="col-sm-10">
            <input type="text" class="form-control" id="gravite" name="gravite" placeholder="Sévérité" value="${infraction.gravite}">
          </div>
        </div>


        <div class="form-group">
          <label for="description" class="col-sm-2 control-label">Description</label>
          <div class="col-sm-10">
            <input type="textarea" class="form-control" id="description" name="description" placeholder="Description" value="${infraction.description}">
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary" name="btnSave" ${role.equals("utilisateur") ? "disabled" : ""}>Sauvegarder</button>
            <button type="submit" class="btn btn-danger" name="btnDelete" ${role.equals("utilisateur") ? "disabled" : ""}>Supprimer</button>
            <button type="submit" class="btn btn-warning" name="btnCancel" ${role.equals("utilisateur") ? "disabled" : ""}>Annuler</button>
          </div>
        </div>
      </form>
    </jsp:body>
</t:layout>

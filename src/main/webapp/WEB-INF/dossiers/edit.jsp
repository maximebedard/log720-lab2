<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
    <jsp:attribute name="header">
        <h1>Modification du dossier</h1>
    </jsp:attribute>
    <jsp:body>
      <form class="form-horizontal">
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
            <button type="submit" class="btn btn-default">Sauvegarder</button>
          </div>
        </div>
      </form>
    </jsp:body>
</t:layout>

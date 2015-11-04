<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>
  <jsp:attribute name="header">
    <h1>Liste de toutes les infractions</h1>
  </jsp:attribute>
  <jsp:attribute name="scripts">
    <script type="text/javascript">
      $(document).ready(function(){
        $('tbody tr').click(function(){
          id = $(this).find('td:first').html();
          window.location = "${pageContext.request.contextPath}/infractions?id=" + id;
        })
      });
    </script>
  </jsp:attribute>
  <jsp:body>
    <table class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>ID</th>
          <th>Sévérité</th>
          <th>Description</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${infractions}" var="infraction">
        <tr>
          <td>${infraction.id}</td>
          <td>${infraction.gravite}</td>
          <td>${infraction.description}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <c:if test="${role.equals('administrateur')}">
      <form action="${pageContext.request.contextPath}/infractions" method="post" class="form-inline">
        <div class="form-group">
          <label class="sr-only" for="gravite">Sévérité</label>
          <input type="text" class="form-control" id="gravite" name="gravite" placeholder="Sévérité">
        </div>
        <div class="form-group">
          <label class="sr-only" for="description">Description</label>
          <input type="text" class="form-control" id="description" name="description" placeholder="Description">
        </div>
        <button type="submit" class="btn btn-default btn-primary" name="btnSave">Ajouter</button>
      </form>
    </c:if>
  </jsp:body>
</t:layout>

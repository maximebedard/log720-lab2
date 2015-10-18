<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:body>
      <form class="form-signin" method="post" action="j_security_check">
        <h2 class="form-signin-heading">Veuillez vous authentifier</h2>
        <label for="j_username" class="sr-only">Email address</label>
        <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Email address" required="" autofocus="">
        <label for="j_password" class="sr-only">Password</label>
        <input type="password" id="j_password" name="j_password" class="form-control" placeholder="Password" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>
      </form>
    </jsp:body>
</t:layout>
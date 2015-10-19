<%@tag description="Global layout" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>LOG720-LAB2</title>

    <!-- Bootstrap core CSS -->
    <link href="/lab2/support/css/bootstrap.min.css" rel="stylesheet">
    <link href="/lab2/support/css/main.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Laboratoire 2</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Dossiers</a></li>
            <li><a href="#about">Infractions</a></li>
            <li>
                <c:choose>
                    <c:when test="${pageContext.request.remoteUser != null}">
                        <a href="/lab2/logout.jsp">${pageContext.request.remoteUser}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/lab2/login.jsp">M'authentifier</a>
                    </c:otherwise>
                </c:choose>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container">
      <jsp:invoke fragment="header"/>
      <jsp:doBody/>
    </div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="/lab2/support/js/bootstrap.min.js"></script>
  </body>
</html>

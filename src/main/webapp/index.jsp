<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%@ page import="ca.etsmtl.log720.lab2.*" %>
<%!
    private DataConnector con = new DataConnector();
%>





<t:layout>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:body>
      <p>Hi I'm the heart of the message</p>

      <sql:query var="rs" dataSource="jdbc/pg">
          select id, foo, bar from testdata
      </sql:query>

      <c:forEach var="row" items="${rs.rows}">
        Foo ${row.foo}<br />
        Bar ${row.bar}<br />
      </c:forEach>
    </jsp:body>
</t:layout>
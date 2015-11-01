<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="ca.etsmtl.log720.lab2.daos.Lab2DAOFactory" %>
<t:layout>
    <jsp:attribute name="header">
      <h1>Ajout infraction</h1>
    </jsp:attribute>

    <jsp:body>
        <FORM ACTION="ajoutInfraction.jsp" METHOD="POST">
            <table>
                <tr>
                    <td>Description de l'infraction :</td>
                    <td><TEXTAREA NAME="desc" ROWS="5"></TEXTAREA></td>
                </tr>

                <tr>
                    <td>Niveau de severité</td>
                    <td><TEXTAREA NAME="sev" ROWS="1"></TEXTAREA></td>
                </tr>
                <tr>
                    <td></td>
                    <td><INPUT TYPE="SUBMIT" VALUE="ajouter" /></td>
                <tr>
            </table>
        </FORM>
    </jsp:body>
</t:layout>

<%
            boolean isPostback = true;
            StringBuffer desc = null;
            StringBuffer sev = null;
            try{
                desc = new StringBuffer(request.getParameter("desc"));
                sev = new StringBuffer(request.getParameter("sev"));
            }catch(Exception ex){
                isPostback = false;
                System.out.println("notAPostback");
            }

            if(isPostback){
                try{
                    int severity = Integer.parseInt(sev.toString());
                    Lab2DAOFactory.getInfractionDAO().create(desc.toString(),severity);
                    //todo : echo success
                }catch(Exception ex){
                    // todo : err mssg
                }
            }

%>
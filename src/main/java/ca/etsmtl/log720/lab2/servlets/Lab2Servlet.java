package ca.etsmtl.log720.lab2.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class Lab2Servlet extends HttpServlet {

    protected Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    protected boolean isAdmin(HttpServletRequest req) {
        return req.isUserInRole("administrateur");
    }
}

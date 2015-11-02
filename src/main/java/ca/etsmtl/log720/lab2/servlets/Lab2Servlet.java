package ca.etsmtl.log720.lab2.servlets;

import javax.servlet.http.HttpServlet;

public class Lab2Servlet extends HttpServlet {

    protected Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}

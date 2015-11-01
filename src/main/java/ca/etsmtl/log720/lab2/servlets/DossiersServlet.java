package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.daos.DossierDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DossiersServlet extends HttpServlet {

    private final DossierDAO dao;

    public DossiersServlet() {
        dao = new DossierDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = tryParse(req.getParameter("id"));

        if (id == null) {
            req.setAttribute("dossiers", dao.readAll());
            req.getRequestDispatcher("/WEB-INF/dossiers/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("dossier", dao.read(id));
            req.getRequestDispatcher("/WEB-INF/dossiers/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom, prenom, noPlaque, noPermis;
        Integer id = tryParse(req.getParameter("id"));
        nom = req.getParameter("nom");
        prenom = req.getParameter("prenom");
        noPlaque = req.getParameter("noPlaque");
        noPermis = req.getParameter("noPermis");

        if (id == null) {
            dao.create(nom, prenom, noPlaque, noPermis);
        } else {
            dao.update(id, nom, prenom, noPlaque, noPermis);
        }

        resp.sendRedirect(req.getContextPath() + "/dossiers");
    }

    private Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}

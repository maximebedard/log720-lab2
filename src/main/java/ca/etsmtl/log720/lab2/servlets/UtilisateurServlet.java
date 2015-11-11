package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.daos.UtilisateurDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UtilisateurServlet extends Lab2Servlet {

    UtilisateurDAO udao;

    public UtilisateurServlet() {
        udao = new UtilisateurDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.isUserInRole("administrateur")) {
            req.setAttribute("role", "administrateur");
        } else {
            req.setAttribute("role", "utilisateur");
        }

        req.setAttribute("utils", udao.readAll());
        req.getRequestDispatcher("/WEB-INF/utilisateurs/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("btnSave") != null) {
            save(req, resp);
        } else if (req.getParameter("btnDelUser") != null) {
            delete(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = tryParse(req.getParameter("btnDelUser"));
        boolean worked = udao.delete(udao.read(id));
        if (!worked) {
            resp.sendError(404, "Une erreur s'est produite.");
        } else {
            resp.sendRedirect(req.getContextPath() + "/utilisateurs");
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nom = req.getParameter("nom");
        String password = req.getParameter("pwd");
        boolean worked = udao.create(nom, password, 2);
        if (!worked) {
            resp.sendError(404, "Une erreur s'est produite, le nom d'utilisateur doit être unique.");
        } else {
            resp.sendRedirect(req.getContextPath() + "/utilisateurs");
        }
    }
}

package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.Infraction;
import ca.etsmtl.log720.lab2.daos.InfractionDAO;
import ca.etsmtl.log720.lab2.daos.Lab2DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InfractionServlet extends Lab2Servlet {

    private final InfractionDAO idao;

    public InfractionServlet() {
        idao = Lab2DAO.getInfractionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (isAdmin(req)) {
            req.setAttribute("role", "administrateur");
        } else {
            req.setAttribute("role", "utilisateur");
        }

        if (id == null) {
            req.setAttribute("infractions", idao.readAll());
            req.getRequestDispatcher("/WEB-INF/infractions/index.jsp").forward(req, resp);
        } else {
            Infraction infraction = idao.read(tryParse(id));
            if (infraction == null) {
                resp.sendError(404, "L'infraction n'existe pas.");
            } else {
                req.setAttribute("infraction", infraction);
                req.getRequestDispatcher("/WEB-INF/infractions/edit.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("btnSave") != null) {
            save(req, resp);
        } else if (req.getParameter("btnDelete") != null) {
            delete(req, resp);
        } else if (req.getParameter("btnCancel") != null) {
            resp.sendRedirect(req.getContextPath() + "/infractions");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isAdmin(req)) {
            resp.sendError(401, "Accès refusé.");
            return;
        }

        Integer id = tryParse(req.getParameter("id"));
        if (id != null) {
            idao.delete(idao.read(id));
            resp.sendRedirect(req.getContextPath() + "/infractions");
        } else {
            resp.sendError(404, "Une erreur s'est produite... L'infraction n'existe pas!");
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isAdmin(req)) {
            resp.sendError(401, "Accès refusé.");
            return;
        }

        Integer id = tryParse(req.getParameter("id"));
        Integer gravite = null;

        try {
            gravite = Integer.parseInt(req.getParameter("gravite"));
        } catch (NumberFormatException ex) {
            resp.sendError(500, "Une erreur s'est produite. La sévérité doit être numérique.");
            return;
        }

        String description = req.getParameter("description");
        boolean worked;
        if (id == null) {
            worked = idao.create(description, gravite);
        } else {
            worked = idao.update(id, gravite, description);
        }

        if (!worked){
            resp.sendError(500, "Une erreur s'est produit lors de la sauvegarde. Veuillez essayer à nouveau.");
        }

        resp.sendRedirect(req.getContextPath() + "/infractions");
    }
}

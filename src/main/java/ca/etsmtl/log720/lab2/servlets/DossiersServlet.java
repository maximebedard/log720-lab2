package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.Dossier;
import ca.etsmtl.log720.lab2.daos.DossierDAO;
import ca.etsmtl.log720.lab2.daos.InfractionDAO;
import ca.etsmtl.log720.lab2.daos.Lab2DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DossiersServlet extends Lab2Servlet {

    private final DossierDAO dao;
    private final InfractionDAO idao;

    public DossiersServlet() {
        dao = Lab2DAO.getDossierDAO();
        idao = Lab2DAO.getInfractionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (isAdmin(req)) {
            req.setAttribute("role", "administrateur");
        } else {
            req.setAttribute("role", "utilisateur");
        }

        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("dossiers", dao.readAll());
            req.getRequestDispatcher("/WEB-INF/dossiers/index.jsp").forward(req, resp);
        } else {
            Dossier dossier = dao.read(tryParse(id));
            if (dossier == null) {
                resp.sendError(404, "Le dossier n'existe pas.");
            } else {
                req.setAttribute("dossier", dossier);
                req.setAttribute("infractions", idao.readAll());
                req.setAttribute("selectedInfractions", idao.allForDossier(dossier));
                req.getRequestDispatcher("/WEB-INF/dossiers/edit.jsp").forward(req, resp);
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
            resp.sendRedirect(req.getContextPath() + "/dossiers");
        } else if (req.getParameter("btnAddInfractions") != null) {
            addInfraction(req, resp);
        } else if (req.getParameter("btnDelInfraction") != null) {
            deleteInfraction(req, resp);
        }
    }

    private void deleteInfraction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer infractionId = tryParse(req.getParameter("btnDelInfraction"));
        Integer dossierId = tryParse(req.getParameter("id"));
        if (infractionId != null && dossierId != null) {
            dao.deleteInfraction(infractionId);
            resp.sendRedirect(req.getContextPath() + "/dossiers?id=" + dossierId);
        } else {
            resp.sendError(404, "Une erreur s'est produite lors de la suppression de l'infraction");
        }
    }

    private void addInfraction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer dossierId = tryParse(req.getParameter("id"));
        String[] infractions = req.getParameterValues("infractions");
        for(String infraction:infractions) {
            Integer infractionId = tryParse(infraction);
            dao.createInfractionForDossier(dossierId, infractionId);
        }
        resp.sendRedirect(req.getContextPath() + "/dossiers?id=" + dossierId);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isAdmin(req)) {
            resp.sendError(401, "Accès refusé.");
            return;
        }

        Integer dossierId = tryParse(req.getParameter("id"));
        if (dossierId != null) {
            dao.delete(dao.read(dossierId));
            resp.sendRedirect(req.getContextPath() + "/dossiers");
        } else {
            resp.sendError(404, "Le dossier n'existe pas.");
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!isAdmin(req)) {
            resp.sendError(401, "Accès refusé.");
            return;
        }

        Integer dossierId = tryParse(req.getParameter("id"));
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String noPlaque = req.getParameter("noPlaque");
        String noPermis = req.getParameter("noPermis");

        boolean worked;
        if (dossierId == null) {
            worked = dao.create(nom, prenom, noPlaque, noPermis);
        } else {
            worked = dao.update(dossierId, nom, prenom, noPlaque, noPermis);
        }

        if (!worked) {
            resp.sendError(500, "Le numeros du permis n'est pas unique.");
        }

        resp.sendRedirect(req.getContextPath() + "/dossiers");
    }
}

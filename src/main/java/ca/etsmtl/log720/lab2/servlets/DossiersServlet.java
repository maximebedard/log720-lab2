package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.Dossier;
import ca.etsmtl.log720.lab2.daos.DossierDAO;
import ca.etsmtl.log720.lab2.daos.InfractionDAO;
import ca.etsmtl.log720.lab2.daos.Lab2DAO;

import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

public class DossiersServlet extends Lab2Servlet {

    private final DossierDAO dao;
    private final InfractionDAO idao;

    public DossiersServlet() {
        dao = Lab2DAO.getDossierDAO();
        idao = Lab2DAO.getInfractionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.isUserInRole("administrateur")){
            req.setAttribute("role","administrateur");
        }else{
            req.setAttribute("role","utilisateur");
        }

        String id = req.getParameter("id");
        if (id == null) {
            req.setAttribute("dossiers", dao.readAll());
            req.getRequestDispatcher("/WEB-INF/dossiers/index.jsp").forward(req, resp);
        } else {
            Dossier dossier = dao.read(tryParse(id));
            if (dossier == null) {
                resp.sendError(404, "Le dossier n'existe pas.");
            }
            else {
                req.setAttribute("dossier", dossier);
                req.setAttribute("infractions", idao.readAll());
                req.setAttribute("selectedInfractions", idao.allForDossier(dossier));
                req.getRequestDispatcher("/WEB-INF/dossiers/edit.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("btnSave") != null) {
            // user pressed save
            Integer id = tryParse(req.getParameter("id"));
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String noPlaque = req.getParameter("noPlaque");
            String noPermis = req.getParameter("noPermis");

            if (id == null) {
                boolean worked = dao.create(nom, prenom, noPlaque, noPermis);
                if(!worked){
                    resp.sendError(404, "Le numeros du permis n'est pas unique.");
                }
            } else {
                boolean worked = dao.update(id, nom, prenom, noPlaque, noPermis);
                if(!worked){
                    resp.sendError(404, "Le numeros du permis n'est pas unique.");
                }
            }

            resp.sendRedirect(req.getContextPath() + "/dossiers");

        }else if(req.getParameter("btnDelete") != null){
            // user pressed delete
            Integer id = tryParse(req.getParameter("id"));
            if(id != null){
                dao.delete(dao.read(id));
                resp.sendRedirect(req.getContextPath() + "/dossiers");
            }else{
                resp.sendError(404, "Le dossier n'existe pas.");
            }

        }else if(req.getParameter("btnCancel") != null){
            // user pressed cancel
            resp.sendRedirect(req.getContextPath() + "/dossiers");
        }else if(req.getParameter("btnAddInfraction") != null){
            Integer idInfraction = tryParse(req.getParameter("btnAddInfraction"));
            Integer idDossier = tryParse(req.getParameter("id"));
            if(idInfraction != null && idDossier != null){
                dao.createInfractionForDossier(idDossier, idInfraction);
                resp.sendRedirect(req.getContextPath() + "/dossiers");
            }else{
                resp.sendError(404, "Une erreur s'est produite lors de l'ajout de l'infraction");
            }
        }else if(req.getParameter("btnDelInfraction") != null){
            Integer idInfraction = tryParse(req.getParameter("btnDelInfraction"));
            Integer idDossier = tryParse(req.getParameter("id"));
            if(idInfraction != null && idDossier != null){
                dao.deleteInfraction(idInfraction);
                resp.sendRedirect(req.getContextPath() + "/dossiers");
            }else{
                resp.sendError(404, "Une erreur s'est produite lors de la suppression de l'infraction");
            }
        }
    }
}

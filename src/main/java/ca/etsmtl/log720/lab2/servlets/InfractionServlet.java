package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.Dossier;
import ca.etsmtl.log720.lab2.Infraction;
import ca.etsmtl.log720.lab2.daos.DossierDAO;
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

        if(req.isUserInRole("administrateur")){
            req.setAttribute("role","administrateur");
        }else{
            req.setAttribute("role","utilisateur");
        }

        if (id == null) {
            req.setAttribute("infractions", idao.readAll());
            req.getRequestDispatcher("/WEB-INF/infractions/index.jsp").forward(req, resp);
        } else {
            Infraction infraction = idao.read(tryParse(id));
            if (infraction == null) {
                resp.sendError(404, "L'infraction n'existe pas.");
            }
            else {
                req.setAttribute("infraction", infraction);
                req.getRequestDispatcher("/WEB-INF/infractions/edit.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("btnSave") != null) {
            // user pressed save
            Integer id = tryParse(req.getParameter("id"));
            Integer gravite = null;

            try {
                gravite = Integer.parseInt(req.getParameter("gravite"));
            }catch (Exception ex){
                resp.sendError(404, "Une erreur s'est produite... la sévérité doit être numérique");
            }

            String description = req.getParameter("description");
            if(gravite != null){

                if(gravite > 100 || gravite < 0){
                    resp.sendError(404, "La sévérité doit être entre 0 et 100 (inclusivement) ...");
                }else {
                    if (id == null) {
                        boolean worked = idao.create(description, gravite);
                        if (!worked) {
                            resp.sendError(404, "Une erreur s'est produite...");
                        }
                    } else {
                        boolean worked = idao.update(id, gravite, description);
                        if (!worked) {
                            resp.sendError(404, "Une erreur s'est produite...");
                        }
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/infractions");
            }


        }else if(req.getParameter("btnDelete") != null){
            // user pressed delete
            Integer id = tryParse(req.getParameter("id"));
            if(id != null){
                idao.delete(idao.read(id));
                resp.sendRedirect(req.getContextPath() + "/infractions");
            }else{
                resp.sendError(404, "Une erreur s'est produite... L'infraction n'existe pas!");
            }

        }else if(req.getParameter("btnCancel") != null){
            // user pressed cancel
            resp.sendRedirect(req.getContextPath() + "/infractions");

        }
    }
}

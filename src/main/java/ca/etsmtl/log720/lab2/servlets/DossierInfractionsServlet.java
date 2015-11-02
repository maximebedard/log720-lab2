package ca.etsmtl.log720.lab2.servlets;

import ca.etsmtl.log720.lab2.Dossier;
import ca.etsmtl.log720.lab2.daos.DossierDAO;
import ca.etsmtl.log720.lab2.daos.InfractionDAO;
import ca.etsmtl.log720.lab2.daos.Lab2DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DossierInfractionsServlet extends Lab2Servlet {

    private final DossierDAO dao;
    private final InfractionDAO idao;

    public DossierInfractionsServlet(){
        dao = Lab2DAO.getDossierDAO();
        idao = Lab2DAO.getInfractionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        Dossier dossier = dao.read(tryParse(id));
        if (dossier == null) {
            resp.sendError(404, "Le dossier n'existe pas.");
        }
        else {
            req.setAttribute("dossier", dossier);
            req.getRequestDispatcher("/WEB-INF/dossiers/infractions.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = tryParse(req.getParameter("id"));
        String[] infractionIds = req.getParameterValues("infractions");
        dao.createInfractionsForDossier(id, infractionIds);

        resp.sendRedirect(req.getContextPath() + "/dossiers?id=" + id);
    }
}

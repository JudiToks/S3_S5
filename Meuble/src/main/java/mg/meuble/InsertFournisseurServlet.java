package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Fournisseur;
import mg.models.Matiere_premiere;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertFournisseurServlet", value = "/insert-fournisseur-servlet")
public class InsertFournisseurServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException
    {
        String nom = request.getParameter("nom");
        Fournisseur f = new Fournisseur();
        f.setNom(nom);
        f.insert(null);
        response.sendRedirect("fournisseur-servlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

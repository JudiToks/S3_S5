package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Produit;
import mg.models.Profil;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "insertProfilServlet", value = "/insert-profil-servlet")
public class InsertProfilServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String nom = request.getParameter("nom");
            double annee = Double.parseDouble(request.getParameter("annee"));
            double coeff = Double.parseDouble(request.getParameter("coeff"));
            Profil temp = new Profil();
            temp.setNom(nom);
            temp.setAnnee(annee);
            temp.setCoeff(coeff);
            temp.insert(null);

            response.sendRedirect("profil-servlet");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}

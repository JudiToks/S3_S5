package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Prix_vente_produit;

import java.io.IOException;

@WebServlet(name = "insertPrixProduitServlet", value = "/insert-prix-produit-servlet")
public class InsertPrixProduitServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id_produit = Integer.parseInt(request.getParameter("produit"));
            int id_taille = Integer.parseInt(request.getParameter("taille"));
            double prix = Double.parseDouble(request.getParameter("prix"));
            Prix_vente_produit prixProduit = new Prix_vente_produit();
            prixProduit.setId_produit(id_produit);
            prixProduit.setId_taille(id_taille);
            prixProduit.setPrix(prix);
            prixProduit.insert(null);
            response.sendRedirect("prix-produit-servlet");
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

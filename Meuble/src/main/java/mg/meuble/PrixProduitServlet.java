package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Dept;
import mg.models.Emp;
import mg.models.Produit;
import mg.models.Taille;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "prixProduitServlet", value = "/prix-produit-servlet")
public class PrixProduitServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            List<Produit> listProduit = Produit.getAllProduit(null);
            request.setAttribute("listProduit", listProduit);
            List<Taille> listTaille = Taille.getAllTaille(null);
            request.setAttribute("listTaille", listTaille);

            RequestDispatcher dispatcher = request.getRequestDispatcher("prixProduit.jsp");
            dispatcher.forward(request, response);
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

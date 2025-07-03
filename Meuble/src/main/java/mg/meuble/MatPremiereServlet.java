package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Matiere_premiere;
import mg.models.Produit;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "matPremiereServlet", value = "/matPremiere-servlet")
public class MatPremiereServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Produit> listProduit = Produit.getAllProduit(null);
        request.setAttribute("listProduit", listProduit);
        List<Matiere_premiere> listMatPrem = Matiere_premiere.getAllMatierePremiere(null);
        request.setAttribute("listMatPrem", listMatPrem);
        List<Client> listClient = Client.getAllClient(null);
        request.setAttribute("listClient", listClient);
        RequestDispatcher dispatcher = request.getRequestDispatcher("matprem.jsp");
        dispatcher.forward(request, response);
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

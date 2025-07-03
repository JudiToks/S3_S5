package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "achatServlet", value = "/achat-servlet")
public class AchatServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();

            List<Produit> listProduit = Produit.getAllProduit(connection);
            request.setAttribute("listProduit", listProduit);
            List<Client> listClient = Client.getAllClient(connection);
            request.setAttribute("listClient", listClient);

            List<Fournisseur> listFournisseur = Fournisseur.getAllFournisseur(connection);
            List<Matiere_premiere> listMatPrem = Matiere_premiere.getAllMatierePremiere(connection);
            request.setAttribute("listFournisseur", listFournisseur);
            request.setAttribute("listMatPrem", listMatPrem);

            List<Achat> listStock = Achat.getStockActuelle(connection);
            request.setAttribute("stock", listStock);

            connection.close();

            RequestDispatcher dispatcher = request.getRequestDispatcher("achat.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

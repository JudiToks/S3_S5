package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Produit;
import mg.models.ResultRechercheEntreDeuxPrix;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "beneficeProduitTailleServlet", value = "/benefice-produit-taille-servlet")
public class BeneficeProduitTailleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        try
        {
            List<Produit> listProduit = Produit.getAllProduit(null);
            request.setAttribute("listProduit", listProduit);
            List<Client> listClient = Client.getAllClient(null);
            request.setAttribute("listClient", listClient);

            RequestDispatcher dispatcher = request.getRequestDispatcher("beneficeProduitTaille.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

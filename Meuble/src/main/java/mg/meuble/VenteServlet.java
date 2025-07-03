package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mg.models.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "venteServlet", value = "/vente-servlet")
public class VenteServlet extends HttpServlet
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
            HttpSession session = request.getSession();

            int id_client = 0;
            if (request.getParameter("client") != null)
            {
                id_client = Integer.parseInt(request.getParameter("client"));
                session.setAttribute("id_client", id_client);
            }
            else
            {
                id_client = (int) session.getAttribute("id_client");
            }

            int id_panier = 0;
            Panier panierClient = Panier.getPanierByIdClient(connection, id_client);
            List<Details_panier> listDp = new ArrayList<>();
            if (panierClient.getId_panier() != 0)
            {
                id_panier = panierClient.getId_panier();
                listDp = Details_panier.getDetailsPanierClient(connection, id_panier);
            }
            request.setAttribute("listDp", listDp);

            connection.close();

            RequestDispatcher dispatcher = request.getRequestDispatcher("vente.jsp");
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

package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Connect;
import mg.models.Produit;
import mg.models.StatistiqueVente;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "statistiqueVenteServlet", value = "/statistique-vente-servlet")
public class StatistiqueVenteServlet extends HttpServlet
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


            List<StatistiqueVente> temp = StatistiqueVente.getAllStatistique(connection);
            List<StatistiqueVente> listStat = StatistiqueVente.getAllStatistiqueVente(connection, temp);
            request.setAttribute("listStat", listStat);
            List<StatistiqueVente> listStatPie = StatistiqueVente.getAllStatistiquePieForm(connection);
            int somme = 0;
            for (int i = 0; i < listStatPie.size(); i++)
            {
                somme = somme + listStatPie.get(i).getNombre();
            }
            for (int i = 0; i < listStatPie.size(); i++)
            {
                listStatPie.get(i).setStatistique((listStatPie.get(i).getNombre() * 100 ) / somme);
            }
            request.setAttribute("listStatPie", listStatPie);

            connection.close();

            RequestDispatcher dispatcher = request.getRequestDispatcher("statistiqueVente.jsp");
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

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "validationVenteServlet", value = "/validation-vente-servlet")
public class ValidationVenteServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();

            int id_client = Integer.parseInt(request.getParameter("client"));
            int id_panier = 0;
            Panier panierClient = Panier.getPanierByIdClient(connection, id_client);
            List<Details_panier> listDp = new ArrayList<>();
            if (panierClient.getId_panier() != 0)
            {
                id_panier = panierClient.getId_panier();
                listDp = Details_panier.getDetailsPanierClient(connection, id_panier);
            }
            for (int i = 0; i < listDp.size(); i++)
            {
                List<Matiere_premiere> list = Matiere_premiere.getMatierePremiereByIdProduit(connection, listDp.get(i).getId_produit());
                for (int j = 0; j < listDp.size(); j++)
                {
                    Sortie sortie = new Sortie();
                    sortie.setId_mat_prem(list.get(j).getId_matiere_premiere());
                    sortie.setQte(list.get(j).getQte() * listDp.get(i).getQuantite());
                    sortie.insert(connection);
                }
            }
            Panier.updateEtat(connection, id_panier);

            connection.close();

            response.sendRedirect("vente-servlet");

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

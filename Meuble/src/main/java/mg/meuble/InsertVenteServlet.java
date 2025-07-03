package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.*;

import java.io.IOException;
import java.security.cert.PolicyNode;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "insertVenteServlet", value = "/insert-vente-servlet")
public class InsertVenteServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();

            int id_produit = Integer.parseInt(request.getParameter("produit"));
            int id_client = Integer.parseInt(request.getParameter("client"));
            int nombre = Integer.parseInt(request.getParameter("nombre"));

            List<Fabrication> listFabrication = Fabrication.getPresFabrication(connection, id_produit, nombre);
            for (Fabrication fabrication : listFabrication)
            {
                if (fabrication.getDisponibilite().contains("Tsy ampy"))
                {
                    request.setAttribute("listFabrication", listFabrication);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("resultatPresFabrication.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                else
                {
                    int id_panier = 0;
                    Panier panierClient = Panier.getPanierByIdClient(connection, id_client);
                    if (panierClient.getId_panier() != 0)
                    {
                        id_panier = panierClient.getId_panier();
                    }
                    else
                    {
                        Panier tempPan = new Panier();
                        tempPan.setId_client(id_client);
                        tempPan.insert(connection);
                        Panier panierClient1 = Panier.getPanierByIdClient(connection, id_client);
                        id_panier = panierClient1.getId_panier();
                    }
                    Details_panier dp = new Details_panier();
                    dp.setId_panier(id_panier);
                    dp.setId_produit(id_produit);
                    dp.setQuantite(nombre);
                    dp.insert(connection);

                    response.sendRedirect("vente-servlet");
                    break;
                }
            }

            connection.close();

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

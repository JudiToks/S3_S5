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
            System.out.println("taille : "+listFabrication.size());
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
                    Vente temp = new Vente();
                    temp.setId_produit(id_produit);
                    temp.setId_client(id_client);
                    temp.setNombre(nombre);
                    temp.insert(connection);

                    List<Matiere_premiere> list = Matiere_premiere.getMatierePremiereByIdProduit(connection, id_produit);
                    for (int i = 0; i < list.size(); i++)
                    {
                        Sortie sortie = new Sortie();
                        sortie.setId_mat_prem(list.get(i).getId_matiere_premiere());
                        sortie.setQte(list.get(i).getQte() * nombre);
                        sortie.insert(connection);
                    }

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

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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "detailsProduitServlet", value = "/details-produit-servlet")
public class DetailsProduitServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Produit> listProduit = Produit.getAllProduit(connection);
            request.setAttribute("listProduit", listProduit);
            List<Client> listClient = Client.getAllClient(connection);
            request.setAttribute("listClient", listClient);

            int id_produit = Integer.parseInt(request.getParameter("produit"));
            Produit produit = Produit.getProduitById(connection, id_produit);
            int id_style = produit.getId_style();
            List<Taille> listTaille = Taille.getAllTaille(connection);
            List<Matiere_premiere> listMatPrem = CompositionStyle.getCompositionStyle(connection, id_style).getListMatPrem();
            connection.close();

            request.setAttribute("produit", produit);
            request.setAttribute("listTaille", listTaille);
            request.setAttribute("listMatPrem", listMatPrem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("detailsProduit.jsp");
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

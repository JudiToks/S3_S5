package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Produit;
import mg.models.ResultRechercheMatPrem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchServlet", value = "/search-servlet")
public class SearchServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        try
        {
            List<Produit> listProduit = Produit.getAllProduit(null);
            request.setAttribute("listProduit", listProduit);
            List<Client> listClient = Client.getAllClient(null);
            request.setAttribute("listClient", listClient);

            String search = request.getParameter("search");
            List<ResultRechercheMatPrem> searchList = ResultRechercheMatPrem.getRechercheMatPrem(null, search);
            request.setAttribute("listSearch", searchList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("resultatSearch.jsp");
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

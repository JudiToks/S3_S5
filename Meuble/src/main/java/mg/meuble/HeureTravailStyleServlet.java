package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Produit;
import mg.models.Style;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "heureTravailStyleServlet", value = "/heure-travail-style-servlet")
public class HeureTravailStyleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            List<Produit> listProduit = Produit.getAllProduit(null);
            request.setAttribute("listProduit", listProduit);
            List<Style> listStyle = Style.getAllStyle(null);
            request.setAttribute("listStyle", listStyle);
            List<Client> listClient = Client.getAllClient(null);
            request.setAttribute("listClient", listClient);

            RequestDispatcher dispatcher = request.getRequestDispatcher("heureTravailStyle.jsp");
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

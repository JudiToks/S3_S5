package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Fabrication;
import mg.models.Produit;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "fabriquerServet", value = "/fabriquer-servlet")
public class FabriquerServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Produit> listProduit = Produit.getAllProduit(null);
        request.setAttribute("listProduit", listProduit);
        List<Client> listClient = Client.getAllClient(null);
        request.setAttribute("listClient", listClient);

        int id_produit = Integer.parseInt(request.getParameter("produit"));
        double qte = Double.parseDouble(request.getParameter("qte"));

        List<Fabrication> listFabrication = Fabrication.getPresFabrication(null, id_produit, qte);
        request.setAttribute("listFabrication", listFabrication);

        RequestDispatcher dispatcher = request.getRequestDispatcher("resultatPresFabrication.jsp");
        dispatcher.forward(request, response);
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

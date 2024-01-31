package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.BeneficeProduitTaille;
import mg.models.Produit;
import mg.models.ResultRechercheEntreDeuxPrix;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "searchBeneficeServlet", value = "/search-benefice-servlet")
public class SearchBeneficeServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        try
        {
            List<Produit> listProduit = Produit.getAllProduit(null);
            request.setAttribute("listProduit", listProduit);

            double prix1 = Double.parseDouble(request.getParameter("prixun"));
            double prix2 = Double.parseDouble(request.getParameter("prixdeux"));

            String search = request.getParameter("search");
            List<BeneficeProduitTaille> searchList = BeneficeProduitTaille.getBeneficeProduitTaille(null, prix1, prix2);
            request.setAttribute("listSearch", searchList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("resultSearchBenefice.jsp");
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

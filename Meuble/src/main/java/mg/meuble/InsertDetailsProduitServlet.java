package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "insertDetailsProduitServlet", value = "/insert-details-produit-servlet")
public class InsertDetailsProduitServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            int id_produit = Integer.parseInt(request.getParameter("id_produit"));
            int id_taille = Integer.parseInt(request.getParameter("taille"));
            String[] listMatPremString = request.getParameterValues("matPrem");
            String[] qteString = request.getParameterValues("qte");
            for (int i = 0; i < listMatPremString.length; i++)
            {
                Details_produit dp = new Details_produit();
                dp.setId_produit(id_produit);
                dp.setId_taille(id_taille);
                dp.setId_mat_prem(Integer.parseInt(listMatPremString[i]));
                dp.setQte(Double.parseDouble(qteString[i]));
                dp.insert(connection);
            }
            connection.close();
            response.sendRedirect("produit-servlet");
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

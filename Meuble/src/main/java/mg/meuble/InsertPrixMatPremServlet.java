package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Prix_mat_prem;
import mg.models.Produit;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertPrixMatPremServlet", value = "/insert-prix-matPrem-servlet")
public class InsertPrixMatPremServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        int id_matPrem = Integer.parseInt(request.getParameter("matPrem"));
        double prix = Double.parseDouble(request.getParameter("prix"));
        Prix_mat_prem temp = new Prix_mat_prem();
        temp.setId_mat_prem(id_matPrem);
        temp.setPrix(prix);
        temp.insert(null);
        response.sendRedirect("prix-matPrem-servlet");
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

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

@WebServlet(name = "insertFormuleServlet", value = "/insert-formule-servlet")
public class InsertFormuleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            int id_meuble = Integer.parseInt(request.getParameter("meuble"));
            int id_style = Integer.parseInt(request.getParameter("style"));
            int id_taille = Integer.parseInt(request.getParameter("taille"));
            String[] listMatPremString = request.getParameterValues("matPrem");
            String[] qteString = request.getParameterValues("qte");
            for (int i = 0; i < listMatPremString.length; i++)
            {
                Formule_fabrication fb = new Formule_fabrication();
                fb.setId_meuble(id_meuble);
                fb.setId_style_matiere_premiere(Style_mat_prem.getIdStyleMatPremByIdStyleIdMatPrem(connection, id_style, Integer.parseInt(listMatPremString[i])));
                fb.setId_taille(id_taille);
                fb.setQte(Double.parseDouble(qteString[i]));
                fb.insert(connection);
            }
            connection.close();
            response.sendRedirect("formule-servlet");
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

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

@WebServlet(name = "detailsFormuleServlet", value = "/details-formule-servlet")
public class DetailsFormuleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            int id_style = Integer.parseInt(request.getParameter("style"));
            int id_meuble = Integer.parseInt(request.getParameter("meuble"));
            Style style = Style.getStyleById(connection, id_style);
            Meuble meuble = Meuble.getMeubleById(connection, id_meuble);
            List<Taille> listTaille = Taille.getAllTaille(connection);
            List<Matiere_premiere> listMatPrem = CompositionStyle.getCompositionStyle(connection, id_style).getListMatPrem();
            connection.close();

            request.setAttribute("style", style);
            request.setAttribute("meuble", meuble);
            request.setAttribute("listTaille", listTaille);
            request.setAttribute("listMatPrem", listMatPrem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("detailsFormule.jsp");
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

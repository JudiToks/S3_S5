package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Connect;
import mg.models.Meuble;
import mg.models.Style;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "formuleServlet", value = "/formule-servlet")
public class FormuleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Style> listStyle = Style.getAllStyle(connection);
            List<Meuble> listMeuble = Meuble.getAllMeuble(connection);
            connection.close();

            request.setAttribute("listStyle", listStyle);
            request.setAttribute("listMeuble", listMeuble);
            RequestDispatcher dispatcher = request.getRequestDispatcher("formule.jsp");
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

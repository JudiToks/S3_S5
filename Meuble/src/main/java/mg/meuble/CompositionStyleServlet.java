package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Connect;
import mg.models.Produit;
import mg.models.Style;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "compositionStyleServlet", value = "/composition-style-servlet")
public class CompositionStyleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        Connection connection = Connect.connectToPostgre();
        List<Style> listStyle = Style.getAllStyle(connection);
        request.setAttribute("listStyle", listStyle);
        List<Produit> listProduit = Produit.getAllProduit(connection);
        request.setAttribute("listProduit", listProduit);
        List<Client> listClient = Client.getAllClient(connection);
        request.setAttribute("listClient", listClient);
        connection.close();
        RequestDispatcher dispatcher = request.getRequestDispatcher("getCompositionStyle.jsp");
        dispatcher.forward(request, response);
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

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
import java.util.List;

@WebServlet(name = "tailleStyleDeptServlet", value = "/taille-style-dept-servlet")
public class TailleStyleDeptServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Produit> listProduit = Produit.getAllProduit(connection);
            request.setAttribute("listProduit", listProduit);
            List<Taille> listTaille = Taille.getAllTaille(connection);
            request.setAttribute("listTaille", listTaille);
            List<Style> listStyle = Style.getAllStyle(connection);
            request.setAttribute("listStyle", listStyle);
            List<Dept> listDept = Dept.getAllDept(connection);
            request.setAttribute("listDept", listDept);

            connection.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("tailleStyleDeptServlet.jsp");
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

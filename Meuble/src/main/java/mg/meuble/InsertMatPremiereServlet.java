package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Matiere_premiere;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertMatPremServlet", value = "/insert-matprem-servlet")
public class InsertMatPremiereServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException
    {
        String nom = request.getParameter("matiere");
        Matiere_premiere mp = new Matiere_premiere();
        mp.setNom(nom);
        mp.insert(null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("matprem.jsp");
        dispatcher.forward(request, response);
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

package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Meuble;
import mg.models.Produit;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertProduitServlet", value = "/insert-produit-servlet")
public class InsertProduitServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String nom = request.getParameter("nom");
        int id_meuble = Integer.parseInt(request.getParameter("meuble"));
        int id_style = Integer.parseInt(request.getParameter("style"));
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setId_meuble(id_meuble);
        produit.setId_style(id_style);
        produit.insert(null);
        response.sendRedirect("produit-servlet");
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

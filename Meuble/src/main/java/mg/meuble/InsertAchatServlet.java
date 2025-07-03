package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Achat;
import mg.models.Fournisseur;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertAchatServlet", value = "/insert-achat-servlet")
public class InsertAchatServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int id_fournisseur = Integer.parseInt(request.getParameter("fournisseur"));
            int id_matPrem = Integer.parseInt(request.getParameter("matprem"));
            double qte = Double.parseDouble(request.getParameter("qte"));
            Achat achat = new Achat();
            achat.setId_fournisseur(id_fournisseur);
            achat.setId_mat_prem(id_matPrem);
            achat.setQte(qte);
            achat.insert(null);
            response.sendRedirect("achat-servlet");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}





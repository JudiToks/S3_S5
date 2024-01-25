package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Heure_travail_style;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "insertProduitEmpHoraireServlet", value = "/insert-produit-emp-horaire-servlet")
public class InsertHeureTravailStyleServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id_style = Integer.parseInt(request.getParameter("style"));
            String heure_travail = request.getParameter("heure");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(heure_travail, formatter);
            int seconds = localTime.toSecondOfDay();
            double doubleValue = (double) seconds;

            Heure_travail_style temp = new Heure_travail_style();
            temp.setId_style(id_style);
            temp.setHeure_travail(doubleValue);
            temp.insert(null);

            response.sendRedirect("heure-travail-style-servlet");
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

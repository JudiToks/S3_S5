package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.ResultRechercheMatPrem;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "jojojo", value = "/alea")
public class RechercheMatPremServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String search = request.getParameter("search");
            List<ResultRechercheMatPrem> valiny = ResultRechercheMatPrem.getRechercheMatPrem(null, search);
            for (int i = 0; i < valiny.size(); i++) {
                System.out.println(valiny.get(i).getTaille()+" "+valiny.get(i).getMeuble());
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

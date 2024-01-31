package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Connect;
import mg.models.Dept_salaire;
import mg.models.Taille;
import mg.models.Taille_style_dept;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "insertTailleStyleDeptServlet", value = "/insert-taille-style-dept-servlet")
public class InsertTailleStyleDeptServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
//            open connection
            Connection connection = Connect.connectToPostgre();

//            activites
            int id_taille = Integer.parseInt(request.getParameter("taille"));
            int id_style = Integer.parseInt(request.getParameter("style"));
            int id_dept = Integer.parseInt(request.getParameter("dept"));
            double nombre = Double.parseDouble(request.getParameter("nombre"));
            List<Taille> listTaille = Taille.getAllTaille(connection);
            int coeff = 1;
            for (Taille taille : listTaille)
            {
                Taille_style_dept temp = new Taille_style_dept();
                temp.setId_taille(taille.getId_taille());
                temp.setId_style(id_style);
                temp.setId_dept(id_dept);
                temp.setNombre(nombre * coeff);
                temp.insert(connection);
                coeff = coeff * 2;
            }

//            close connection
            connection.close();

            response.sendRedirect("taille-style-dept-servlet");
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

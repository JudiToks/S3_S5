package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Dept_salaire;
import mg.models.Taille_style_dept;

import java.io.IOException;

@WebServlet(name = "insertTailleStyleDeptServlet", value = "/insert-taille-style-dept-servlet")
public class InsertTailleStyleDeptServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id_taille = Integer.parseInt(request.getParameter("taille"));
            int id_style = Integer.parseInt(request.getParameter("style"));
            int id_dept = Integer.parseInt(request.getParameter("dept"));
            double nombre = Double.parseDouble(request.getParameter("nombre"));
            Taille_style_dept temp = new Taille_style_dept();
            temp.setId_taille(id_taille);
            temp.setId_style(id_style);
            temp.setId_dept(id_dept);
            temp.setNombre(nombre);
            temp.insert(null);

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

package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Emp_dept_embauche;
import mg.models.Produit;
import mg.models.Profil;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "insertEmpDeptEmbaucheServlet", value = "/insert-emp-dept-embauche-servlet")
public class InsertEmpDeptEmbaucheServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id_emp_dept = Integer.parseInt(request.getParameter("emp_dept"));
            int id_profil = Integer.parseInt(request.getParameter("profil"));
            String dateString = request.getParameter("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateString);
            Date sqlDate = new Date(utilDate.getTime());

            Emp_dept_embauche temp = new Emp_dept_embauche();
            temp.setId_emp_dept(id_emp_dept);
            temp.setId_profil(id_profil);
            temp.setDate_embauche(sqlDate);
            temp.insert(null);

            response.sendRedirect("emp-dept-embauche-servlet");
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

package mg.meuble;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Emp_dept;

import java.io.IOException;

@WebServlet(name = "insertEmpDeptHoraireSerlvet", value = "/insert-emp-dept-servlet")
public class InsertEmpDeptServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            int id_emp = Integer.parseInt(request.getParameter("emp"));
            int id_dept = Integer.parseInt(request.getParameter("dept"));
            Emp_dept temp = new Emp_dept();
            temp.setId_emp(id_emp);
            temp.setId_dept(id_dept);
            temp.insert(null);
            response.sendRedirect("emp-dept-horaire-servlet");
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

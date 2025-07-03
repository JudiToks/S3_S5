package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Client;
import mg.models.Dept;
import mg.models.Emp;
import mg.models.Produit;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "empDeptHoraireServlet", value = "/emp-dept-horaire-servlet")
public class EmpDeptServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            List<Produit> listProduit = Produit.getAllProduit(null);
            request.setAttribute("listProduit", listProduit);
            List<Emp> listEmp = Emp.getAllEmp(null);
            request.setAttribute("listEmp", listEmp);
            List<Dept> listDept = Dept.getAllDept(null);
            request.setAttribute("listDept", listDept);
            List<Client> listClient = Client.getAllClient(null);
            request.setAttribute("listClient", listClient);

            RequestDispatcher dispatcher = request.getRequestDispatcher("employeeDepartement.jsp");
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

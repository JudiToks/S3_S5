package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Connect;
import mg.models.Emp_dept;
import mg.models.Produit;
import mg.models.Profil;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "empDeptEmbaucheServlet", value = "/emp-dept-embauche-servlet")
public class EmpDeptEmbaucheServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Produit> listProduit = Produit.getAllProduit(connection);
            request.setAttribute("listProduit", listProduit);
            List<Emp_dept> listEmpDept = Emp_dept.getAllEmpDept(connection);
            request.setAttribute("listEmpDept", listEmpDept);
            List<Profil> listProfil = Profil.getAllProfil(connection);
            request.setAttribute("listProfil", listProfil);

            connection.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("empDeptEmbauche.jsp");
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

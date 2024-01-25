package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "empDeptProfilStatusServlet", value = "/emp-dept-profil-status-servlet")
public class EmpDeptProfilStatusServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
//            connection
            Connection connection = Connect.connectToPostgre();
//            parametre
            String recherche_text = "";
            String recherche_date = "";
            List<TempStatusEmp> listTempStatusEmp = new ArrayList<>();
            if (request.getParameter("recherche_text") == null && request.getParameter("recherche_date") == null)
            {
                long currentTimeMillis = System.currentTimeMillis();
                Date currentDate = new Date(currentTimeMillis);
                listTempStatusEmp = TempStatusEmp.getAllTempStatus(connection, currentDate, recherche_text);
                listTempStatusEmp = TempStatusEmp.getAllStatus(connection, listTempStatusEmp);
                request.setAttribute("listStatus", listTempStatusEmp);
            }
            else
            {
                if ((!request.getParameter("recherche_text").isEmpty()) && request.getParameter("recherche_date").isEmpty())
                {
                    recherche_text = request.getParameter("recherche_text");
                    System.out.println("if voalohany : "+recherche_date);
                    System.out.println("if voalohany : "+recherche_text);
                    long currentTimeMillis = System.currentTimeMillis();
                    Date currentDate = new Date(currentTimeMillis);
                    listTempStatusEmp = TempStatusEmp.getAllTempStatus(connection, currentDate, recherche_text);
                    listTempStatusEmp = TempStatusEmp.getAllStatus(connection, listTempStatusEmp);
                    request.setAttribute("listStatus", listTempStatusEmp);

                }
                if (!request.getParameter("recherche_date").isEmpty() && request.getParameter("recherche_text").isEmpty())
                {
                    recherche_date = request.getParameter("recherche_date");
                    System.out.println("if faharoa : "+recherche_date);
                    System.out.println("if faharoa : "+recherche_text);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = dateFormat.parse(recherche_date);
                    Date sqlDate = new Date(utilDate.getTime());
                    listTempStatusEmp = TempStatusEmp.getAllTempStatus(connection, sqlDate, recherche_text);
                    listTempStatusEmp = TempStatusEmp.getAllStatus(connection, listTempStatusEmp);
                    request.setAttribute("listStatus", listTempStatusEmp);
                }
                if ((!request.getParameter("recherche_date").isEmpty()) && (!request.getParameter("recherche_text").isEmpty()))
                {
                    recherche_text = request.getParameter("recherche_text");
                    recherche_date = request.getParameter("recherche_date");
                    System.out.println("if fahatelo : "+recherche_date);
                    System.out.println("if fahatelo : "+recherche_text);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = dateFormat.parse(recherche_date);
                    Date sqlDate = new Date(utilDate.getTime());
                    listTempStatusEmp = TempStatusEmp.getAllTempStatus(connection, sqlDate, recherche_text);
                    listTempStatusEmp = TempStatusEmp.getAllStatus(connection, listTempStatusEmp);
                    request.setAttribute("listStatus", listTempStatusEmp);
                }
            }

            List<Produit> listProduit = Produit.getAllProduit(connection);
            request.setAttribute("listProduit", listProduit);

            connection.close();
            RequestDispatcher dispatcher = request.getRequestDispatcher("empDeptProfilStatus.jsp");
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
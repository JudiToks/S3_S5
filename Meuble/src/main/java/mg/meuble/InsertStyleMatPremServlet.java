package mg.meuble;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.models.Style_mat_prem;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "insertStyleMatPremServlet", value = "/insert-stylematprem-servlet")
public class InsertStyleMatPremServlet extends HttpServlet
{
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        String id_style = request.getParameter("style");
        String id_matprem = request.getParameter("matprem");
        Style_mat_prem smp = new Style_mat_prem();
        smp.setId_style(Integer.parseInt(id_style));
        smp.setId_mat_premiere(Integer.parseInt(id_matprem));
        smp.insert(null);
        response.sendRedirect("styleMatPremiere-servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

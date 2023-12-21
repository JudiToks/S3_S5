package mg.models;

import jakarta.servlet.annotation.WebServlet;

import javax.xml.transform.stax.StAXResult;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultRechercheMatPrem
{
    String taille;
    String meuble;

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getMeuble() {
        return meuble;
    }

    public void setMeuble(String meuble) {
        this.meuble = meuble;
    }

    public static List<ResultRechercheMatPrem> getRechercheMatPrem(Connection connection, String search)
    {
        boolean isOuvert = false;
        List<ResultRechercheMatPrem> valiny = new ArrayList<>();
        String query = "select taille.nom, meuble.nom from formule_fabrication\n" +
                "    join taille on taille.id_taille = formule_fabrication.id_taille\n" +
                "    join meuble on meuble.id_meuble = formule_fabrication.id_meuble\n" +
                "    join style_mat_prem on style_mat_prem.id_style_mat_prem = formule_fabrication.id_style_matprem\n" +
                "                  where formule_fabrication.id_style_matprem = (select id_style_mat_prem from style_mat_prem where id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where nom like '%"+search+"%'));";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                ResultRechercheMatPrem temp = new ResultRechercheMatPrem();
                temp.setTaille(resultSet.getString(1));
                temp.setMeuble(resultSet.getString(2));
                valiny.add(temp);
            }
            resultSet.close();
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("alea alea issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

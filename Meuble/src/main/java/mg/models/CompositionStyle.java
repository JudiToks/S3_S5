package mg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompositionStyle
{
    String style;
    List<Matiere_premiere> listMatPrem;

//    getters & setters
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public List<Matiere_premiere> getListMatPrem() {
        return listMatPrem;
    }
    public void setListMatPrem(List<Matiere_premiere> listMatPrem) {
        this.listMatPrem = listMatPrem;
    }

//    function
    public static CompositionStyle getCompositionStyle(Connection connection, int id_style)
    {
        boolean isOuvert = false;
        CompositionStyle valiny = new CompositionStyle();
        String query = "select * from style_mat_prem where id_style = "+id_style+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Matiere_premiere> listMatPrem = new ArrayList<>();
            while (resultSet.next())
            {
                CompositionStyle temp = new CompositionStyle();
                temp.setStyle(Style.getStyleById(connection, resultSet.getInt("id_style")).getNom());
                Matiere_premiere tempMatPrem = Matiere_premiere.getMatierePremiereById(connection, resultSet.getInt("id_matiere_premiere"));
                listMatPrem.add(tempMatPrem);
                temp.setListMatPrem(listMatPrem);
                valiny = temp;
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
            System.out.println("CompositionStyle getCompositionStyle issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

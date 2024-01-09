package mg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultRechercheEntreDeuxPrix
{
    String nom;
    double prix;

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }

    public static List<ResultRechercheEntreDeuxPrix> getRechercheEntreDeuxPrix(Connection connection, double prix1, double prix2)
    {
        boolean isOuvert = false;
        List<ResultRechercheEntreDeuxPrix> valiny = new ArrayList<>();
        String query = "select * from v_produit_prix where prix >= "+prix1+" and prix <= "+prix2+";";
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
                ResultRechercheEntreDeuxPrix temp = new ResultRechercheEntreDeuxPrix();
                temp.setNom(resultSet.getString(1));
                temp.setPrix(resultSet.getDouble(2));
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
            System.out.println("ResultRechercheEntreDeuxPrix getRechercheEntreDeuxPrix issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

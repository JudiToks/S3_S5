package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Matiere_premiere
{
    int id_matiere_premiere;
    String nom;

//    getters & setters
    public int getId_matiere_premiere() {
        return id_matiere_premiere;
    }
    public void setId_matiere_premiere(int id_matiere_premiere) {
        this.id_matiere_premiere = id_matiere_premiere;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void insert(Connection connection) throws SQLException {
        boolean isOuvert = false;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES (default, '"+this.getNom()+"')";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        }
        catch (SQLException e)
        {
            System.out.println("Insertion "+this.getClass().getSimpleName()+" issues");
            e.printStackTrace();
        }
        finally
        {
            if (isOuvert)
            {
                connection.close();
            }
        }
    }

    public static List<Matiere_premiere> getAllMatierePremiere(Connection connection)
    {
        boolean isOuvert = false;
        List<Matiere_premiere> valiny = new ArrayList<>();
        String query = "select * from matiere_premiere;";
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
                Matiere_premiere temp = new Matiere_premiere();
                temp.setId_matiere_premiere(resultSet.getInt("id_matiere_premiere"));
                temp.setNom(resultSet.getString("nom"));
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
            System.out.println("Matiere premiere getAllMatierePremiere issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static Matiere_premiere getMatierePremiereById(Connection connection, int id_matiere_premiere)
    {
        boolean isOuvert = false;
        Matiere_premiere valiny = new Matiere_premiere();
        String query = "select * from matiere_premiere where id_matiere_premiere = "+id_matiere_premiere+";";
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
                Matiere_premiere temp = new Matiere_premiere();
                temp.setId_matiere_premiere(resultSet.getInt("id_matiere_premiere"));
                temp.setNom(resultSet.getString("nom"));
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
            System.out.println("Matiere premiere getMatierePremiereById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

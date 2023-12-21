package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Meuble
{
    int id_meuble;
    String nom;

//    getters & setters
    public int getId_meuble() {
        return id_meuble;
    }
    public void setId_meuble(int id_meuble) {
        this.id_meuble = id_meuble;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, '"+this.getNom()+"');";
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

    public static List<Meuble> getAllMeuble(Connection connection)
    {
        boolean isOuvert = false;
        List<Meuble> valiny = new ArrayList<>();
        String query = "select * from meuble;";
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
                Meuble temp = new Meuble();
                temp.setId_meuble(resultSet.getInt("id_meuble"));
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
            System.out.println("Style getAllStyle issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static Meuble getMeubleById(Connection connection, int id_meuble)
    {
        boolean isOuvert = false;
        Meuble valiny = new Meuble();
        String query = "select * from meuble where id_meuble = "+id_meuble+";";
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
                Meuble temp = new Meuble();
                temp.setId_meuble(resultSet.getInt("id_meuble"));
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
            System.out.println("Meuble getMeubleById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

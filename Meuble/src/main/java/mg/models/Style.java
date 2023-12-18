package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Style
{
    int id_style;
    String nom;

//    getters & setters
    public int getId_style() {
        return id_style;
    }
    public void setId_style(int id_style) {
        this.id_style = id_style;
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

    public static List<Style> getAllStyle(Connection connection)
    {
        boolean isOuvert = false;
        List<Style> valiny = new ArrayList<>();
        String query = "select * from style;";
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
                Style temp = new Style();
                temp.setId_style(resultSet.getInt("id_style"));
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

    public static Style getStyleById(Connection connection, int id_style)
    {
        boolean isOuvert = false;
        Style valiny = new Style();
        String query = "select * from style where id_style = "+id_style+";";
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
                Style temp = new Style();
                temp.setId_style(resultSet.getInt("id_style"));
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
            System.out.println("Style getStyleById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

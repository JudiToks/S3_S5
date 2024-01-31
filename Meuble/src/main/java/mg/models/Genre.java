package mg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Genre
{
    int id_genre;
    String nom;

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static List<Genre> getAllGenre(Connection connection)
    {
        boolean isOuvert = false;
        List<Genre> valiny = new ArrayList<>();
        String query = "select * from genre;";
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
                Genre temp = new Genre();
                temp.setId_genre(resultSet.getInt(1));
                temp.setNom(resultSet.getString(2));
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
            System.out.println("Genre getAllGenre issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static Genre getGenreById(Connection connection, int id_genre)
    {
        boolean isOuvert = false;
       Genre valiny = new Genre();
        String query = "select * from genre where id_genre = "+id_genre+";";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next())
            {
                Genre temp = new Genre();
                temp.setId_genre(resultSet.getInt(1));
                temp.setNom(resultSet.getString(2));
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
            System.out.println("Genre getGenreById issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

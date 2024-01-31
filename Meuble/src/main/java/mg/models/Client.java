package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Client
{
    int id_client;
    String nom;
    String prenom;
    Date dtn;
    int id_genre;
    String genre;

    public int getId_client() {
        return id_client;
    }

    public int getId_genre() {
        return id_genre;
    }

    public void setId_genre(int id_genre) {
        this.id_genre = id_genre;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDtn() {
        return dtn;
    }

    public void setDtn(Date dtn) {
        this.dtn = dtn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, '"+this.getNom()+"', '"+this.getPrenom()+"', '"+this.getDtn()+"', "+this.getId_genre()+");";
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

    public static List<Client> getAllClient(Connection connection)
    {
        boolean isOuvert = false;
        List<Client> valiny = new ArrayList<>();
        String query = "select * from client;";
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
                Client temp = new Client();
                temp.setId_client(resultSet.getInt(1));
                temp.setNom(resultSet.getString(2));
                temp.setPrenom(resultSet.getString(3));
                temp.setDtn(resultSet.getDate(4));
                temp.setId_genre(resultSet.getInt(5));
                temp.setGenre(Genre.getGenreById(connection, temp.getId_genre()).getNom());
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
            System.out.println("Dept getAllDept issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

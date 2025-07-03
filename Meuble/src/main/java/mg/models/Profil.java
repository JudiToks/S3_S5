package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Profil
{
    int id_profil;
    String nom;
    double annee;
    double coeff;

    public int getId_profil() {
        return id_profil;
    }

    public void setId_profil(int id_profil) {
        this.id_profil = id_profil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public double getCoeff() {
        return coeff;
    }

    public void setCoeff(double coeff) {
        this.coeff = coeff;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, '"+this.getNom()+"', "+this.getAnnee()+", "+this.getCoeff()+");";
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

    public static List<Profil> getAllProfil(Connection connection)
    {
        boolean isOuvert = false;
        List<Profil> valiny = new ArrayList<>();
        String query = "select * from profil;";
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
                Profil temp = new Profil();
                temp.setId_profil(resultSet.getInt(1));
                temp.setNom(resultSet.getString(2));
                temp.setAnnee(resultSet.getDouble(3));
                temp.setCoeff(resultSet.getDouble(4));
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
            System.out.println("Profil getAllProfil issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

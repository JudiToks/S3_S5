package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Produit
{
    int id_produit;
    String nom;
    int id_meuble;
    int id_style;

//    getters & setters
    public int getId_produit() {
        return id_produit;
    }
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId_meuble() {
        return id_meuble;
    }
    public void setId_meuble(int id_meuble) {
        this.id_meuble = id_meuble;
    }
    public int getId_style() {
        return id_style;
    }
    public void setId_style(int id_style) {
        this.id_style = id_style;
    }

//    contructor
    public Produit() {}
    public Produit(int id_produit, String nom, int id_meuble, int id_style) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.id_meuble = id_meuble;
        this.id_style = id_style;
    }

//    function
    public void insert(Connection connection) throws SQLException
    {
        boolean isOuvert = false;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES (default, '"+this.getNom()+"', "+this.getId_meuble()+", "+this.getId_style()+");";
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

    public static List<Produit> getAllProduit(Connection connection)
    {
        boolean isOuvert = false;
        List<Produit> valiny = new ArrayList<>();
        String query = "select * from produit;";
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
                Produit temp = new Produit();
                temp.setId_produit(resultSet.getInt("id_produit"));
                temp.setNom(resultSet.getString("nom"));
                temp.setId_meuble(resultSet.getInt("id_meuble"));
                temp.setId_style(resultSet.getInt("id_style"));
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
            System.out.println("Produit getAllProduit issues");
            e.printStackTrace();
        }
        return valiny;
    }

    public static Produit getProduitById(Connection connection, int id_produit)
    {
        boolean isOuvert = false;
        Produit valiny = new Produit();
        String query = "select * from produit where id_produit = "+id_produit+";";
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
                Produit temp = new Produit();
                temp.setId_produit(resultSet.getInt("id_produit"));
                temp.setNom(resultSet.getString("nom"));
                temp.setId_meuble(resultSet.getInt("id_meuble"));
                temp.setId_style(resultSet.getInt("id_style"));
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

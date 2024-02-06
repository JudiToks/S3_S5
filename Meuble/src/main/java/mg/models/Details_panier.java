package mg.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Details_panier
{
    int id_details_panier;
    int id_panier;
    int id_produit;
    String produit;
    int quantite;

    public int getId_details_panier() {
        return id_details_panier;
    }

    public void setId_details_panier(int id_details_panier) {
        this.id_details_panier = id_details_panier;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

//    function
    public void insert(Connection connection) throws SQLException {
        boolean isOuvert = false;
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_panier()+", "+this.getId_produit()+", "+this.getQuantite()+");";
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

    public static List<Details_panier> getDetailsPanierClient(Connection connection, int id_panier)
    {
        boolean isOuvert = false;
        List<Details_panier> valiny = new ArrayList<>();
        String query = "select\n" +
                "    p.id_produit," +
                "    nom,\n" +
                "    qte\n" +
                "from details_panier\n" +
                "    join produit p on details_panier.id_produit = p.id_produit\n" +
                "where id_panier = "+id_panier+";";
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
                Details_panier temp = new Details_panier();
                temp.setId_produit(resultSet.getInt(1));
                temp.setProduit(resultSet.getString(2));
                temp.setQuantite(resultSet.getInt(3));
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
            System.out.println("Details_panier getDetailsPanierClient issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

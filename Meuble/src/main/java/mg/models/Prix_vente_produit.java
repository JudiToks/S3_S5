package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prix_vente_produit
{
    int id_prix_produit;
    int id_produit;
    int id_taille;
    double prix;

    public int getId_taille() {
        return id_taille;
    }

    public void setId_taille(int id_taille) {
        this.id_taille = id_taille;
    }

    public int getId_prix_produit() {
        return id_prix_produit;
    }

    public void setId_prix_produit(int id_prix_produit) {
        this.id_prix_produit = id_prix_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_produit()+", "+this.getId_taille()+", "+this.getPrix()+");";
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
}

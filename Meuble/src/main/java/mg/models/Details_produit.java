package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Details_produit
{
    int id_details_produit;
    int id_produit;
    int id_taille;
    int id_mat_prem;
    double qte;

//    getters & setters
    public int getId_details_produit() {
        return id_details_produit;
    }
    public void setId_details_produit(int id_details_produit) {
        this.id_details_produit = id_details_produit;
    }
    public int getId_produit() {
        return id_produit;
    }
    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }
    public int getId_taille() {
        return id_taille;
    }
    public void setId_taille(int id_taille) {
        this.id_taille = id_taille;
    }
    public int getId_mat_prem() {
        return id_mat_prem;
    }
    public void setId_mat_prem(int id_mat_prem) {
        this.id_mat_prem = id_mat_prem;
    }
    public double getQte() {
        return qte;
    }
    public void setQte(double qte) {
        this.qte = qte;
    }

//    constructor
    public Details_produit() {}
    public Details_produit(int id_details_produit, int id_produit, int id_taille, int id_mat_prem, double qte) {
        this.id_details_produit = id_details_produit;
        this.id_produit = id_produit;
        this.id_taille = id_taille;
        this.id_mat_prem = id_mat_prem;
        this.qte = qte;
    }

//    funciton
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES (default, "+this.getId_produit()+", "+this.getId_taille()+", "+this.getId_mat_prem()+", "+this.getQte()+");";
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

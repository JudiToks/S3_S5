package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Achat
{
    int id_achat;
    int id_fournisseur;
    int id_mat_prem;
    double qte;
    Timestamp date_achat;

    public int getId_achat() {
        return id_achat;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
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

    public void setQte(double qte) throws Exception{
        if (qte < 0)
        {
            throw new Exception("Quantite negatif");
        }
        else
        {
            this.qte = qte;
        }
    }

    public Timestamp getDate_achat() {
        return date_achat;
    }

    public void setDate_achat(Timestamp date_achat) {
        this.date_achat = date_achat;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_mat_prem()+", "+this.getId_fournisseur()+", "+this.getQte()+", current_timestamp);";
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

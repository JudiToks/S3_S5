package mg.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sortie
{
    int sortie;
    int id_mat_prem;
    double qte;
    Date date_sortie;

    public int getSortie() {
        return sortie;
    }

    public void setSortie(int sortie) {
        this.sortie = sortie;
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

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_mat_prem()+", "+this.getQte()+", current_timestamp);";
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

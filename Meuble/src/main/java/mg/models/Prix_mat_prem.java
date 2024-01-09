package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Prix_mat_prem
{
    int id_prix_mat_prem;
    int id_mat_prem;
    double prix;

//    getters & setters
    public int getId_prix_mat_prem() {
        return id_prix_mat_prem;
    }
    public void setId_prix_mat_prem(int id_prix_mat_prem) {
        this.id_prix_mat_prem = id_prix_mat_prem;
    }
    public int getId_mat_prem() {
        return id_mat_prem;
    }
    public void setId_mat_prem(int id_mat_prem) {
        this.id_mat_prem = id_mat_prem;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES (default, "+this.getId_mat_prem()+", "+this.getPrix()+")";
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

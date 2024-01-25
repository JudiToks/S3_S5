package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Heure_travail_style
{
    int id_heure_travail_style;
    int id_style;
    double heure_travail;

    public int getId_heure_travail_style() {
        return id_heure_travail_style;
    }

    public void setId_heure_travail_style(int id_heure_travail_style) {
        this.id_heure_travail_style = id_heure_travail_style;
    }

    public int getId_style() {
        return id_style;
    }

    public void setId_style(int id_style) {
        this.id_style = id_style;
    }

    public double getHeure_travail() {
        return heure_travail;
    }

    public void setHeure_travail(double heure_travail) {
        this.heure_travail = heure_travail;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_style()+", "+this.getHeure_travail()+");";
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

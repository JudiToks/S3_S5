package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dept_salaire
{
    int id_dept_salaire;
    int id_dept;
    double salaire;

    public int getId_dept_salaire() {
        return id_dept_salaire;
    }

    public void setId_dept_salaire(int id_dept_salaire) {
        this.id_dept_salaire = id_dept_salaire;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_dept()+", "+this.getSalaire()+");";
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

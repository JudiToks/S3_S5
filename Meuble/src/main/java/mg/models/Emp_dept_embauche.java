package mg.models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emp_dept_embauche
{
    int id_emp_dept_embauche;
    int id_emp_dept;
    int id_profil;
    Date date_embauche;

    public int getId_emp_dept_embauche() {
        return id_emp_dept_embauche;
    }

    public int getId_profil() {
        return id_profil;
    }

    public void setId_profil(int id_profil) {
        this.id_profil = id_profil;
    }

    public void setId_emp_dept_embauche(int id_emp_dept_embauche) {
        this.id_emp_dept_embauche = id_emp_dept_embauche;
    }

    public int getId_emp_dept() {
        return id_emp_dept;
    }

    public void setId_emp_dept(int id_emp_dept) {
        this.id_emp_dept = id_emp_dept;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_emp_dept()+", "+this.getId_profil()+", '"+this.getDate_embauche()+"');";
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

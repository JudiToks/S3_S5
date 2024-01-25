package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Taille_style_dept
{
    int id_taille_style_dept;
    int id_taille;
    int id_style;
    int id_dept;
    double nombre;

    public int getId_taille_style_dept() {
        return id_taille_style_dept;
    }

    public void setId_taille_style_dept(int id_taille_style_dept) {
        this.id_taille_style_dept = id_taille_style_dept;
    }

    public int getId_taille() {
        return id_taille;
    }

    public void setId_taille(int id_taille) {
        this.id_taille = id_taille;
    }

    public int getId_style() {
        return id_style;
    }

    public void setId_style(int id_style) {
        this.id_style = id_style;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public double getNombre() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre = nombre;
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES(default, "+this.getId_taille()+", "+this.getId_style()+", "+this.getId_dept()+", "+this.getNombre()+");";
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

package mg.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Formule_fabrication
{
    int id_formule;
    int id_style_matiere_premiere;
    int id_meuble;
    int id_taille;
    double qte;

//    getters & setters
    public int getId_formule() {
        return id_formule;
    }
    public void setId_formule(int id_formule) {
        this.id_formule = id_formule;
    }
    public int getId_style_matiere_premiere() {
        return id_style_matiere_premiere;
    }
    public void setId_style_matiere_premiere(int id_style_matiere_premiere) {
        this.id_style_matiere_premiere = id_style_matiere_premiere;
    }
    public int getId_meuble() {
        return id_meuble;
    }
    public void setId_meuble(int id_meuble) {
        this.id_meuble = id_meuble;
    }
    public int getId_taille() {
        return id_taille;
    }
    public void setId_taille(int id_taille) {
        this.id_taille = id_taille;
    }
    public double getQte() {
        return qte;
    }
    public void setQte(double qte) {
        this.qte = qte;
    }

//    function
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
            String sql = "INSERT INTO "+this.getClass().getSimpleName().toLowerCase()+" VALUES (default, "+this.getId_style_matiere_premiere()+", "+this.getId_meuble()+", "+this.getId_taille()+", "+this.getQte()+");";
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

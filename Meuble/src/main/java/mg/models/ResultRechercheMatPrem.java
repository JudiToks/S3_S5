package mg.models;

import jakarta.servlet.annotation.WebServlet;

import javax.xml.transform.stax.StAXResult;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResultRechercheMatPrem
{
    String nom_produit;
    String taille;
    String mat_prem;
    double qte;

//    getters & setters
    public String getNom_produit() {
        return nom_produit;
    }
    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }
    public String getTaille() {
        return taille;
    }
    public void setTaille(String taille) {
        this.taille = taille;
    }
    public String getMat_prem() {
        return mat_prem;
    }
    public void setMat_prem(String mat_prem) {
        this.mat_prem = mat_prem;
    }
    public double getQte() {
        return qte;
    }
    public void setQte(double qte) {
        this.qte = qte;
    }

//    function
    public static List<ResultRechercheMatPrem> getRechercheMatPrem(Connection connection, String search)
    {
        boolean isOuvert = false;
        List<ResultRechercheMatPrem> valiny = new ArrayList<>();
        String query = "select produit.nom, taille.nom, matiere_premiere.nom, details_produit.qte from details_produit\n" +
                "    join produit on produit.id_produit = details_produit.id_produit\n" +
                "    join taille on taille.id_taille = details_produit.id_taille\n" +
                "    join matiere_premiere on  matiere_premiere.id_matiere_premiere = details_produit.id_matiere_premiere\n" +
                "        where details_produit.id_matiere_premiere = (select id_matiere_premiere from matiere_premiere where LOWER(nom) like LOWER('%"+search+"%'));";
        try
        {
            if (connection == null)
            {
                connection = Connect.connectToPostgre();
                isOuvert = true;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                ResultRechercheMatPrem temp = new ResultRechercheMatPrem();
                temp.setNom_produit(resultSet.getString(1));
                temp.setTaille(resultSet.getString(2));
                temp.setMat_prem(resultSet.getString(3));
                temp.setQte(resultSet.getDouble(4));
                valiny.add(temp);
            }
            resultSet.close();
            statement.close();
            if (isOuvert)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            System.out.println("RechercheMatPrem getRecherche issues");
            e.printStackTrace();
        }
        return valiny;
    }
}

package mg.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BeneficeProduitTaille
{
    String produit;
    String taille;
    double benefice;

//    getters & setters
    public String getProduit() {
        return produit;
    }
    public void setProduit(String produit) {
        this.produit = produit;
    }
    public String getTaille() {
        return taille;
    }
    public void setTaille(String taille) {
        this.taille = taille;
    }
    public double getBenefice() {
        return benefice;
    }
    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }

//    function
    public static List<BeneficeProduitTaille> getBeneficeProduitTaille(Connection connection, double prix1, double prix2)
    {
        boolean isOuvert = false;
        List<BeneficeProduitTaille> valiny = new ArrayList<>();
            String query = "select * from v_benefice_produit  where benefice >= "+prix1+" and benefice <= "+prix2+";";
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
                BeneficeProduitTaille temp = new BeneficeProduitTaille();
                temp.setProduit(resultSet.getString(1));
                temp.setTaille(resultSet.getString(2));
                temp.setBenefice(resultSet.getDouble(3));
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
            System.out.println("BeneficeProduitTaille BeneficeProduitTaille issues");
            e.printStackTrace();
        }
        return valiny;
    }
}
